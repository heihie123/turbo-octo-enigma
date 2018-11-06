package com.example.latte_core.detegates;

/**
 * 对外提供的Fragment基类
 */
public abstract class LatteDelegate extends PermissionCheckDelegate {

    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
