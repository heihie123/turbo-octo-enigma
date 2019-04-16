package com.example.latte_core.ui.refresh;

/**
 * 分页bean
 */
public class IndexPageBean {

    private int mPageIndex = 0;     // 当前页数
    private int mCurrentCount = 0;  // 当前条数
    private int mPageSize = 0;      // 总页数
    private int mTotal = 0;         // 总条数
    private int mDelayed = 0;

    public int getPageIndex() {
        return mPageIndex;
    }

    public IndexPageBean setPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public int getTotal() {
        return mTotal;
    }

    public IndexPageBean setTotal(int mTotal) {
        this.mTotal = mTotal;
        return this;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public IndexPageBean setPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;
    }

    public int getCurrentCount() {
        return mCurrentCount;
    }

    public IndexPageBean setCurrentCount(int mCurrentCount) {
        this.mCurrentCount = mCurrentCount;
        return this;
    }

    public int getDelayed() {
        return mDelayed;
    }

    public IndexPageBean setDelayed(int mDelayed) {
        this.mDelayed = mDelayed;
        return this;
    }

    public IndexPageBean addIndex(){
        mPageIndex++;
        return this;
    }
}
