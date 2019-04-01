package com.example.latte_core.ui.widget;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;

import com.example.latte_core.R;
import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.util.image.GlideUtils;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;

public class AutoPhotoLayout extends LinearLayoutCompat {

    private final int mMaxNum;
    private final int mMaxLineNum;
    private final float mIconSize;
    private final int mImageMargin;

    private int mCurrentNum = 0;
    private int mDeleteId = 0;
    private IconTextView mAddIcon = null;
    private LayoutParams mParams = null;
    private AppCompatImageView mTargetImg = null;
    private LatteDelegate mDelegate = null;
    private AlertDialog mTargetDialog = null;
    private static final String ICON_TEXT = "{fa-plus}";

    private ArrayList<View> mLineViews = null;
    private final ArrayList<ArrayList<View>> ALL_VIEWS = new ArrayList<>();
    private final ArrayList<Integer> LINE_HEIGHTS = new ArrayList<>();
    // 防止多次测量和布局
    private boolean mIsOnceInitOnMeasure = false;
    private boolean mHasInitOnLayout = false;

    public AutoPhotoLayout(Context context) {
        this(context, null);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.camera_flow_layout);
        mMaxNum = typedArray.getInt(R.styleable.camera_flow_layout_max_count, 1);
        mMaxLineNum = typedArray.getInt(R.styleable.camera_flow_layout_line_count, 3);
        mImageMargin = typedArray.getInt(R.styleable.camera_flow_layout_item_margin, 0);
        mIconSize = typedArray.getDimension(R.styleable.camera_flow_layout_icon_size, 20);
        typedArray.recycle();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        final int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        final int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        final int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int width = 0;
        int height = 0;
        int lineWidth = 0;
        int lineHeight = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            final MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            final int childWidth = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            final int childHeight = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            if (lineWidth + childWidth > sizeWidth - getPaddingLeft() - getPaddingRight()) {
                width = Math.max(width, lineWidth);
                lineWidth = childWidth;
                height += lineHeight;
                lineHeight = childHeight;
            } else {
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }
            if (i == childCount - 1) {
                width = Math.max(lineWidth, width);
                height += lineHeight;
            }
        }
        setMeasuredDimension(
                modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width + getPaddingLeft() + getPaddingRight(),
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height + getPaddingTop() + getPaddingBottom()
        );
        final int imageSideLen = sizeWidth / mMaxLineNum;
        if (!mIsOnceInitOnMeasure) {
            mParams = new LayoutParams(imageSideLen, imageSideLen);
            mIsOnceInitOnMeasure = true;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        ALL_VIEWS.clear();
        LINE_HEIGHTS.clear();
        // 当前viewgroup的宽度
        final int width = getWidth();
        int lineWidth = 0;
        int lineHeight = 0;
        // 只初始化一次
        if (!mHasInitOnLayout) {
            mLineViews = new ArrayList<>();
            mHasInitOnLayout = true;
        }
        final int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            final View child = getChildAt(i);
            final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            final int childWidth = child.getMeasuredWidth();
            final int childHeight = child.getMeasuredHeight();
            // 判断是否需要换行
            if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width - getPaddingStart() - getPaddingEnd()) {
                // 记录lineHeight和当前一行的views
                LINE_HEIGHTS.add(lineHeight);
                ALL_VIEWS.add(mLineViews);
                // 重置
                lineWidth = 0;
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
                mLineViews.clear();
            }
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, lineHeight + lp.topMargin + lp.bottomMargin);
            mLineViews.add(child);
        }
        // 处理最后一行
        LINE_HEIGHTS.add(lineHeight);
        ALL_VIEWS.add(mLineViews);
        int left = getPaddingLeft();
        int top = getPaddingTop();
        final int lineNum = ALL_VIEWS.size();
        for (int j = 0; j < lineNum; j++) {
            mLineViews = ALL_VIEWS.get(j);
            lineHeight = LINE_HEIGHTS.get(j);
            final int size = mLineViews.size();
            for (int k = 0; k < size; k++) {
                final View child = mLineViews.get(k);
                // 过滤gone的组件
                if (child.getVisibility() == GONE) {
                    continue;
                }
                // 设置子view的边距
                final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                final int lc = left + lp.leftMargin;
                final int tc = top + lp.topMargin;
                final int rc = lc + child.getMeasuredWidth() - mImageMargin;
                final int bc = tc + child.getMeasuredHeight();
                // 子view布局
                child.layout(lc, tc, rc, bc);
                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            left += getPaddingStart();
            top += lineHeight;
        }
        mAddIcon.setLayoutParams(mParams);
        mHasInitOnLayout = false;
    }

    public void setDelegate(LatteDelegate delegate) {
        this.mDelegate = delegate;
    }

    public void onCropTarget(Uri uri) {
        createNewImageView();
        GlideUtils.loadNoCacheImg(mDelegate.getContext(), uri, mTargetImg);
    }

    private void createNewImageView() {
        mTargetImg = new AppCompatImageView(getContext());
        mTargetImg.setId(mCurrentNum);
        mTargetImg.setLayoutParams(mParams);
        mTargetImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mDeleteId = view.getId();
                mTargetDialog.show();
                final Window window = mTargetDialog.getWindow();
                if (window != null) {
                    window.setContentView(R.layout.dialog_image_click_panel);
                    window.setGravity(Gravity.BOTTOM);
                    window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    final WindowManager.LayoutParams params = window.getAttributes();
                    params.width = WindowManager.LayoutParams.MATCH_PARENT;
                    params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                    params.dimAmount = 0.5f;
                    window.setAttributes(params);
                    window.findViewById(R.id.btn_dialog_delete).setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final AppCompatImageView deleteImg = findViewById(mDeleteId);
                            final AlphaAnimation animation = new AlphaAnimation(1, 0);
                            animation.setDuration(500);
                            animation.setRepeatCount(0);
                            animation.setFillAfter(true);
                            animation.setStartOffset(0);
                            deleteImg.setAnimation(animation);
                            animation.start();
                            AutoPhotoLayout.this.removeView(deleteImg);
                            mCurrentNum -= 1;
                            if (mCurrentNum < mMaxNum) {
                                mAddIcon.setVisibility(VISIBLE);
                            }
                            mTargetDialog.cancel();
                        }
                    });
                    window.findViewById(R.id.btn_dialog_undetermined).setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mTargetDialog.cancel();
                        }
                    });
                    window.findViewById(R.id.btn_dialog_cancel).setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mTargetDialog.cancel();
                        }
                    });
                }
            }
        });
        this.addView(mTargetImg, mCurrentNum);
        mCurrentNum++;
        if (mCurrentNum >= mMaxNum) {
            mAddIcon.setVisibility(GONE);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initAddIcon();
        mTargetDialog = new AlertDialog.Builder(getContext()).create();
    }

    private void initAddIcon() {
        mAddIcon = new IconTextView(getContext());
        mAddIcon.setText(ICON_TEXT);
        mAddIcon.setGravity(Gravity.CENTER);
        mAddIcon.setTextSize(mIconSize);
        mAddIcon.setBackgroundResource(R.drawable.border_text);
        mAddIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mDelegate.startCameraWithCheck();
            }
        });
        this.addView(mAddIcon);
    }
}
