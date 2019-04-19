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
import android.widget.ImageView;

import com.example.latte_core.R;
import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.util.image.GlideUtils;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;

/**
 * 自定义选择图片View
 */
public class AutoPhotoLayout extends LinearLayoutCompat {

    private final int mMaxNum;              // 图片最大数
    private final int mMaxLineNum;          // 一行最大数量
    private final float mIconSize;          // 弹窗item字体样式
    private final int mImageMargin;         // 图片间距

    private int mCurrentNum = 0;            // addicon位置
    private int mCurrentId = 0;             // 图片id
    private int mDeleteId = 0;              // 删除图片id
    private IconTextView mAddIcon = null;   // addicon
    private LayoutParams mParams = null;    // 子类的layoutparam
    private AppCompatImageView mTargetImg = null;   // 创建的imageview
    private LatteDelegate mDelegate = null;
    private AlertDialog mTargetDialog = null;
    private static final String ICON_TEXT = "{fa-plus}";

    private final ArrayList<ArrayList<View>> ALL_VIEWS = new ArrayList<>();
    private final ArrayList<Integer> LINE_HEIGHTS = new ArrayList<>();  // 行高集合

    private boolean mIsOnceInitOnMeasure = false; // 防止多次测量和布局

    public AutoPhotoLayout(Context context) {
        this(context, null);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoPhotoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.camera_flow_layout);
        mMaxNum = typedArray.getInt(R.styleable.camera_flow_layout_max_count, 3);
        mMaxLineNum = typedArray.getInt(R.styleable.camera_flow_layout_line_count, 3);
        mImageMargin = typedArray.getInt(R.styleable.camera_flow_layout_item_margin, 0);
        mIconSize = typedArray.getDimension(R.styleable.camera_flow_layout_icon_size, 20);
        typedArray.recycle();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 初始化
        final int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        final int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        final int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        final int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int width = 0;          // 总宽度
        int height = 0;         // 总高度
        int lineWidth = 0;      // 行宽
        int lineHeight = 0;     // 行高

        // 循环每个子view，获取宽高
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            // 测量子view
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            // 获取测量后的子view宽高
            final MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            final int childWidth = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            final int childHeight = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            // 判断是否超过了行宽
            if (lineWidth + childWidth > (sizeWidth - getPaddingLeft() - getPaddingRight())) {
                width = Math.max(width, lineWidth);
                height += lineHeight + mImageMargin;
                lineWidth = childWidth;
                lineHeight = childHeight;
            } else {
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight + layoutParams.topMargin + layoutParams.bottomMargin);
            }
            // 最后一项的时候，获取测量后的整体宽高
            if (i == childCount - 1) {
                width = Math.max(lineWidth, width);
                height += lineHeight;
            }
        }
        // 测量
        setMeasuredDimension(
                modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width + getPaddingLeft() + getPaddingRight(),
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height + getPaddingTop() + getPaddingBottom()
        );
        // 设置图片的param
        final int imageSideLen = (sizeWidth - getPaddingLeft() - getPaddingRight()) / mMaxLineNum;
        if (!mIsOnceInitOnMeasure) {
            mParams = new LayoutParams(imageSideLen, imageSideLen);
            mIsOnceInitOnMeasure = true;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        getChildData();
        layoutChild();
        mAddIcon.setLayoutParams(mParams);
    }

    private void getChildData() {
        // 初始化
        ALL_VIEWS.clear();
        LINE_HEIGHTS.clear();
        final int width = getWidth();
        final ArrayList<View> lineViews = new ArrayList<>();
        int lineWidth = 0;
        int lineHeight = 0;

        // 循环每个子view，添加进集合
        final int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            final View child = getChildAt(i);
            final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            final int childWidth = child.getMeasuredWidth();
            final int childHeight = child.getMeasuredHeight();
            // 判断是否需要换行
            if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin > (width - getPaddingLeft() - getPaddingRight())) {
                // 保存该行数据
                ALL_VIEWS.add(lineViews);
                LINE_HEIGHTS.add(lineHeight);
                // 重置
                lineWidth = 0;
                lineHeight = 0;
                lineViews.clear();
            }
            lineWidth += (childWidth + lp.leftMargin + lp.rightMargin);
            lineHeight = Math.max(lineHeight, childHeight + lp.topMargin + lp.bottomMargin);
            lineViews.add(child);
        }
        ALL_VIEWS.add(lineViews);
        LINE_HEIGHTS.add(lineHeight);
    }

    private void layoutChild() {
        int left = getPaddingLeft();
        int top = getPaddingTop();

        // 取出每一行数据进行循环
        final int lineNum = ALL_VIEWS.size();
        for (int i = 0; i < lineNum; i++) {
            final ArrayList<View> itemView = ALL_VIEWS.get(i);
            final int itemHeight = LINE_HEIGHTS.get(i);

            // 取出每一行的每个view
            final int size = itemView.size();
            for (int j = 0; j < size; j++) {
                final View child = itemView.get(j);
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
            // 初始化
            left = getPaddingLeft();
            top += (itemHeight + mImageMargin);
        }
    }

    public void setDelegate(LatteDelegate delegate) {
        this.mDelegate = delegate;
    }

    // 加载图片功能
    public void onCropTarget(Uri uri) {
        createNewImageView();
        GlideUtils.loadNoCacheImg(mDelegate.getContext(), uri, mTargetImg);
    }

    private void createNewImageView() {
        mTargetImg = new AppCompatImageView(getContext());
        mTargetImg.setId(mCurrentId++);
        mTargetImg.setLayoutParams(mParams);
        mTargetImg.setScaleType(ImageView.ScaleType.FIT_XY);
        mTargetImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mDeleteId = view.getId();
                mTargetDialog.show();
                createDialog();
            }
        });
        this.addView(mTargetImg, mCurrentNum++);
        // 是否隐藏addicon
        if (mCurrentNum >= mMaxNum) {
            mAddIcon.setVisibility(GONE);
        }
    }

    private void createDialog() {
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
