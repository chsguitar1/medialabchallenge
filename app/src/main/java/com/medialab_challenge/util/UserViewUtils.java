package com.medialab_challenge.util;

import android.text.TextUtils;
import android.util.Patterns;

public abstract class UserViewUtils {
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
