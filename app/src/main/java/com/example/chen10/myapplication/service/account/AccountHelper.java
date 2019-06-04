package com.example.chen10.myapplication.service.account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;

/**
 * 账户帮助类
 */
public class AccountHelper {

    //与authenticator.xml中accountType一致
    private static final String ACCOUNT_TYPE = "com.example.chen10.myapplication.account";
    private static final String CONTENT_AUTHORITY = "com.example.chen10.myapplication.provider";

    public static void addAccount(Context context) {
        AccountManager accountManager = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);
        Account[] accounts = accountManager.getAccountsByType(ACCOUNT_TYPE);
        if (accounts.length > 0) {
            // 账户已存在
            return;
        }
        // 添加账户
        Account account = new Account("dongnao", ACCOUNT_TYPE);
        accountManager.addAccountExplicitly(account, "dn123", new Bundle());
    }

    public static void autoSync() {
        Account account = new Account("dongnao", ACCOUNT_TYPE);
        // 设置同步
        ContentResolver.setIsSyncable(account, CONTENT_AUTHORITY, 1);
        // 设置自动同步
        ContentResolver.setSyncAutomatically(account, CONTENT_AUTHORITY, true);
        // 设置同步周期(秒)
        ContentResolver.addPeriodicSync(account, CONTENT_AUTHORITY, new Bundle(), 1);
    }
}
