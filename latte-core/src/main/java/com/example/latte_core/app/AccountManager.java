package com.example.latte_core.app;

import com.example.latte_core.util.storage.LattePreference;

/**
 * 用户登录管理类
 */
public class AccountManager {
    private enum SignTag {
        SIGN_TAG
    }

    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }

    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }
}
