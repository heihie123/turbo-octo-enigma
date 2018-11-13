package com.example.latte_ec.main.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

public class ContentBean extends SectionEntity<ContentItemEntity> {

    private boolean mIsMore = false;
    private int mId = -1;

    public ContentBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public ContentBean(ContentItemEntity contentItemEntity) {
        super(contentItemEntity);
    }

    public boolean isIsMore() {
        return mIsMore;
    }

    public void setIsMore(boolean isMore) {
        this.mIsMore = isMore;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }
}
