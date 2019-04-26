package com.example.latte_ec.detail;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.ui.animation.BezierAnimation;
import com.example.latte_core.ui.animation.BezierUtil;
import com.example.latte_core.ui.banner.HolderCreator;
import com.example.latte_core.ui.widget.CircleTextView;
import com.example.latte_core.util.image.GlideUtils;
import com.example.latte_core.util.log.LatteLogger;
import com.example.latte_ec.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 商品详情Delegate
 */
public class GoodsDetailDelegate extends LatteDelegate implements BezierUtil.AnimationListener,
        AppBarLayout.OnOffsetChangedListener, View.OnClickListener {

    private ConvenientBanner<String> mDetailBanner = null;

    private TabLayout mGoodsTabLaout = null;
    private ViewPager mGoodsViewPager = null;

    private AppCompatTextView mAddCarText;
    private IconTextView mShopCarIcon = null;
    private CircleTextView mCircleAmountText = null;

    private static final String ARG_GOODS_ID = "ARG_GOODS_ID";
    private int mGoodsId = -1;
    private String mGoodsThumbUrl = null;
    private int mShopCount = 0;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate()
            .override(100, 100);

    public static GoodsDetailDelegate create(int goodsId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_GOODS_ID, goodsId);
        final GoodsDetailDelegate goodsDetailDelegate = new GoodsDetailDelegate();
        goodsDetailDelegate.setArguments(args);
        return goodsDetailDelegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mGoodsId = args.getInt(ARG_GOODS_ID);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        initView();
        initData();
    }

    private void initView() {
        mDetailBanner = $(R.id.banner_detail);
        mGoodsTabLaout = $(R.id.tablayout_goods);
        mGoodsViewPager = $(R.id.vpage_goods);
        mShopCarIcon = $(R.id.icon_shop_car);
        mCircleAmountText = $(R.id.txt_circle_amount);
        mAddCarText = $(R.id.txt_add_shop_car);
        mCircleAmountText.setCircleBackgroud(Color.RED);

        // 设置折叠滚动监听
        ((AppBarLayout) $(R.id.ablayout_bar_detail)).addOnOffsetChangedListener(this);
        // tab标签设置
        mGoodsTabLaout.setTabMode(TabLayout.MODE_FIXED);
        if (mContext != null) {
            mGoodsTabLaout.setSelectedTabIndicatorColor(ContextCompat.getColor(mContext, R.color.APPBG_DARK));
        }
        mGoodsTabLaout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));
        mGoodsTabLaout.setBackgroundColor(Color.WHITE);
        mGoodsTabLaout.setupWithViewPager(mGoodsViewPager);

        // listener
        $(R.id.icon_goods_back).setOnClickListener(this);
        $(R.id.icon_favor).setOnClickListener(this);
        mShopCarIcon.setOnClickListener(this);
        mAddCarText.setOnClickListener(this);
    }

    private void initData() {
        mGoodsThumbUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554719480217&di=58bb0f13fd60693c2a30dcb7e4f95ded&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F018c195912da8bb5b3086ed48c1a64.jpg";
        // banner
        List<String> images = new ArrayList<>();
        images.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1933002625,436410812&fm=26&gp=0.jpg");
        images.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=429051493,3249465606&fm=26&gp=0.jpg");
        images.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2644298337,3979711271&fm=26&gp=0.jpg");
        images.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1757962551,3296233266&fm=26&gp=0.jpg");
        mDetailBanner.setPages(new HolderCreator(mContext), images)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .startTurning(3000)
                .setCanLoop(true);

        // 商品详情
        getSupportDelegate().loadRootFragment(R.id.flayout_info, GoodsInfoDelegate.create("1"));

        // 商品详情viewpager
        final PagerAdapter pagerAdapter = new TabPagerAdapter(getFragmentManager(), createTitles(), createImages());
        mGoodsViewPager.setAdapter(pagerAdapter);

        // 购物车数量
        mShopCount = 0;
        setShopCountUi();
    }

    private ArrayList<String> createTitles() {
        ArrayList<String> titleList = new ArrayList<>();
        titleList.add("商品详情");
        titleList.add("规格参数");
        return titleList;
    }

    private ArrayList<ArrayList<String>> createImages() {
        ArrayList<String> images1 = new ArrayList<>();
        images1.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3984049879,942869024&fm=26&gp=0.jpg");
        images1.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=169668311,2050124505&fm=26&gp=0.jpg");
        ArrayList<String> images2 = new ArrayList<>();
        images2.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=398807204,2914994402&fm=26&gp=0.jpg");
        images2.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3483489778,158660965&fm=26&gp=0.jpg");
        ArrayList<ArrayList<String>> imageList = new ArrayList<>();
        imageList.add(images1);
        imageList.add(images2);
        return imageList;
    }

    private CollapsingToolbarLayoutState state;

    // 折叠滚动监听
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        // 可以在这里设置滚动到某个位置时候需要的操作
        if (verticalOffset == 0) {
            if (state != CollapsingToolbarLayoutState.EXPANDED) {
                LatteLogger.e("EXPANDED");
                //修改状态标记为展开
                state = CollapsingToolbarLayoutState.EXPANDED;
            }
        } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
            if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                //隐藏播放按钮

                //修改状态标记为折叠
                state = CollapsingToolbarLayoutState.COLLAPSED;
            }
        } else {
            if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                    //由折叠变为中间状态时隐藏播放按钮
                }
                LatteLogger.e("INTERNEDIATE");
                //修改状态标记为中间
                state = CollapsingToolbarLayoutState.INTERNEDIATE;
            }
        }
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        if (id == R.id.icon_goods_back) {
            getSupportDelegate().pop();
        }
        if (id == R.id.icon_favor) {
            onClickFavor();
        }
        if (id == R.id.icon_shop_car) {
            onClickGoShopCar();
        }
        if (id == R.id.txt_add_shop_car) {
            onClickAddShopCar();
        }
    }

    // 喜欢点击
    private void onClickFavor() {

    }

    // 跳转购物车
    private void onClickGoShopCar() {

    }

    // 执行飞入购物车动画
    private void onClickAddShopCar() {
        final CircleImageView animationImg = new CircleImageView(mContext);
        GlideUtils.loadNormalImgWithOption(mContext, mGoodsThumbUrl, animationImg, OPTIONS);
        BezierAnimation.addCart(this, mAddCarText, mShopCarIcon, animationImg, this);
    }

    @Override
    public void onAnimationEnd() {
//        YoYo.with(new ScaleUpAnimator())
        YoYo.with(Techniques.Tada)
                .duration(500)
                .playOn(mShopCarIcon);
                /*  Attension
            Flash, Pulse, RubberBand, Shake, Swing, Wobble, Bounce, Tada, StandUp, Wave
            Special
            Hinge, RollIn, RollOut,Landing,TakingOff,DropOut
            Bounce
            BounceIn, BounceInDown, BounceInLeft, BounceInRight, BounceInUp
            Fade
            FadeIn, FadeInUp, FadeInDown, FadeInLeft, FadeInRight
            FadeOut, FadeOutDown, FadeOutLeft, FadeOutRight, FadeOutUp
            Flip
            FlipInX, FlipOutX, FlipOutY
            Rotate
            RotateIn, RotateInDownLeft, RotateInDownRight, RotateInUpLeft, RotateInUpRight
            RotateOut, RotateOutDownLeft, RotateOutDownRight, RotateOutUpLeft, RotateOutUpRight
            Slide
            SlideInLeft, SlideInRight, SlideInUp, SlideInDown
            SlideOutLeft, SlideOutRight, SlideOutUp, SlideOutDown
            Zoom
            ZoomIn, ZoomInDown, ZoomInLeft, ZoomInRight, ZoomInUp
            ZoomOut, ZoomOutDown, ZoomOutLeft, ZoomOutRight, ZoomOutUp */
        mShopCount++;
        setShopCountUi();
    }

    private void setShopCountUi() {
        if (mShopCount == 0) {
            mCircleAmountText.setVisibility(View.GONE);
        } else {
            mCircleAmountText.setVisibility(View.VISIBLE);
            mCircleAmountText.setText(String.valueOf(mShopCount));
        }
    }

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }
}
