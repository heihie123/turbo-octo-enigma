package com.example.latte_ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.latte_core.app.AccountManager;
import com.example.latte_ec.database.DatabaseManager;
import com.example.latte_ec.database.UserProfile;

import java.util.List;

/**
 * 登录注册逻辑处理
 */
class SignHandler {

    private static final int TYPE_SIGN_UP = 0;
    private static final int TYPE_SIGN_IN = 1;

    static void onSignUp(String response, ISignListener iSignListener) {
        try {
            final UserProfile userProfile = formData(response);
            List<UserProfile> queryRaw = DatabaseManager.getInstance().getDao()
                    .queryRaw("where name=?", userProfile.getName());
            if (queryRaw.size() == 0) {
                DatabaseManager.getInstance().getDao().insert(userProfile);
                AccountManager.setSignState(true);
                setCallback(iSignListener, TYPE_SIGN_UP, true);
            } else {
                throw new Exception("register error");
            }
        } catch (Exception ignored) {
            AccountManager.setSignState(false);
            setCallback(iSignListener, TYPE_SIGN_UP, false);
        }
    }

    static void onSignIn(String response, ISignListener iSignListener) {
        try {
            final UserProfile userProfile = formData(response);
            List<UserProfile> queryRaw = DatabaseManager.getInstance().getDao()
                    .queryRaw("where name=? and avatar=?", userProfile.getName(), userProfile.getAvatar());
            if (queryRaw.size() > 0) {
                AccountManager.setSignState(true);
                setCallback(iSignListener, TYPE_SIGN_IN, true);
            } else {
                throw new Exception("login error");
            }
        } catch (Exception ignored) {
            AccountManager.setSignState(false);
            setCallback(iSignListener, TYPE_SIGN_IN, false);
        }
    }

    private static UserProfile formData(String response) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        UserProfile userProfile = new UserProfile();
        userProfile.setName(name);
        userProfile.setAvatar(avatar);
        return userProfile;
    }

    private static void setCallback(ISignListener iSignListener, int type, boolean isSuccess) {
        if (iSignListener != null) {
            switch (type) {
                case TYPE_SIGN_UP:
                    iSignListener.onSignUp(isSuccess);
                    break;
                case TYPE_SIGN_IN:
                    iSignListener.onSignIn(isSuccess);
                    break;
            }
        }
    }
}
