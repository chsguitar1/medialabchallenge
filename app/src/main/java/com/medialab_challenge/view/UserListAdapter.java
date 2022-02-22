package com.medialab_challenge.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.medialab_challenge.R;
import com.medialab_challenge.databinding.ItemUserListBinding;
import com.medialab_challenge.viewmodel.MainActivityViewModel;
import com.medialab_challenge.vo.User;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UsersViewHolder> {
    List<User> usersList;
    Context context;
    MainActivityViewModel viewModel;

    public UserListAdapter(List<User> usersList, Context context, MainActivityViewModel viewModel) {
        this.usersList = usersList;
        this.context = context;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UsersViewHolder(ItemUserListBinding.inflate(LayoutInflater.from(this.context)));
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        User user = usersList.get(position);
        holder.binding.tvName.setText(user.name);
        holder.binding.tvEmail.setText(user.email);
        holder.binding.btDeleteUser.setOnClickListener(view -> deleteUser(user));
        holder.binding.cvItemUser.setOnClickListener(view -> {
            goToUserDetails(user.id);
        });

        if (user.image == null) {
            setImagedefault(holder);
        } else {
            holder.binding.picture.setImageBitmap(user.image);
        }

    }

    private void goToUserDetails(Integer userId) {
        Intent intent = new Intent(context, UserDetailsActivity.class);
        intent.putExtra(UserDetailsActivity.USER_ID, userId);
        context.startActivity(intent);
    }

    private void deleteUser(User user) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context)
                .setTitle(context.getString(R.string.tile_dialog))
                .setMessage(context.getString(R.string.dialog_message))
                .setPositiveButton(context.getString(R.string.yes),
                        (dialogInterface, i) -> {
                            viewModel.deleteUser(user);
                            viewModel.getUserListLiveData();
                            dialogInterface.dismiss();
                        }).setNegativeButton(context.getString(R.string.no),
                        (dialogInterface, i) -> dialogInterface.dismiss());
        builder.show();


    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    private void setImagedefault(UsersViewHolder holder) {
        String uri = "@drawable/ic_round_account_circle";
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        Drawable res = context.getResources().getDrawable(imageResource);
        holder.binding.picture.setImageDrawable(res);
    }
}
