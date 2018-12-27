package com.example.latte_ec.main.personal.list;

import android.widget.CompoundButton;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.latte_core.detegates.LatteDelegate;

public class ListBean implements MultiItemEntity {

    private int mItemType = 0;
    private String mImageUrl = null;
    private String mText = null;
    private String mValue = null;
    private int mId = 0;
    private LatteDelegate mDelegate = null;
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = null;

    public ListBean(int itemType, String imageUrl, String text, String value, int id, LatteDelegate delegate,
                    CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.mItemType = itemType;
        this.mImageUrl = imageUrl;
        this.mText = text;
        this.mValue = value;
        this.mId = id;
        this.mDelegate = delegate;
        this.mOnCheckedChangeListener = onCheckedChangeListener;
    }

    public int getItemType() {
        return mItemType;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getText() {
        return mText;
    }

    public String getValue() {
        return mValue;
    }

    public int getId() {
        return mId;
    }

    public LatteDelegate getDelegate() {
        return mDelegate;
    }

    public CompoundButton.OnCheckedChangeListener getOnCheckedChangeListener() {
        return mOnCheckedChangeListener;
    }

    public static final class Builder {

        private int itemType = 0;
        private String imageUrl = null;
        private String text = null;
        private String value = null;
        private int id = 0;
        private LatteDelegate delegate = null;
        private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = null;

        public Builder setItemType(int itemType) {
            this.itemType = itemType;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setDelegate(LatteDelegate delegate) {
            this.delegate = delegate;
            return this;
        }

        public Builder setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
            this.onCheckedChangeListener = onCheckedChangeListener;
            return this;
        }

        public ListBean build() {
            return new ListBean(itemType, imageUrl, text, value, id, delegate, onCheckedChangeListener);
        }
    }
}
