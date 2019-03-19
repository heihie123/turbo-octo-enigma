package com.example.latte_ec.detail;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * 商品详情viewpager的adapter
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> TAB_TITLES;
    private ArrayList<ArrayList<String>> PICTURES;

    public TabPagerAdapter(FragmentManager fm, ArrayList<String> titleList, ArrayList<ArrayList<String>> pictureList) {
        super(fm);
        this.TAB_TITLES = titleList;
        this.PICTURES = pictureList;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageDelegate.create(PICTURES.get(position));
    }

    @Override
    public int getCount() {
        return TAB_TITLES.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES.get(position);
    }
}
