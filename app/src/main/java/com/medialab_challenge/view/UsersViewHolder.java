package com.medialab_challenge.view;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.medialab_challenge.databinding.ItemUserListBinding;

public class UsersViewHolder extends RecyclerView.ViewHolder {
    ItemUserListBinding binding;

    public UsersViewHolder(ItemUserListBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        this.binding.getRoot().setLayoutParams(new LinearLayout.LayoutParams(width,height));
    }
}
