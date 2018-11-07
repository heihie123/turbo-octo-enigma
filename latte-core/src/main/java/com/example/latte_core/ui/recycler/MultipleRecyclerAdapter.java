package com.example.latte_core.ui.recycler;

import androidx.recyclerview.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.latte_core.R;
import com.example.latte_core.ui.banner.BannerCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页list适配器
 */
public class MultipleRecyclerAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder>
        implements BaseQuickAdapter.SpanSizeLookup, OnItemClickListener {

    private boolean mIsInitBanner = false;

    private static final RequestOptions GLIDE_OPTIONS = new RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    private MultipleRecyclerAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    private void init() {
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_image_text);
        addItemType(ItemType.IMAGE, R.layout.item_multiple_image);
        addItemType(ItemType.BANNER, R.layout.item_multiple_banner);
        // 设置宽度监听
        setSpanSizeLookup(this);
        // 开启加载动画
        openLoadAnimation();
        // 多次执行动画
        isFirstOnly(false);
    }

    public static MultipleRecyclerAdapter create(List<MultipleItemEntity> data) {
        return new MultipleRecyclerAdapter(data);
    }

    public static MultipleRecyclerAdapter create(BaseDataConverter converter) {
        return new MultipleRecyclerAdapter(converter.convert());
    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view) {
        return MultipleViewHolder.create(view);
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity item) {
        final String text;
        final String imageUrl;
        final ArrayList<String> bannerImages;
        switch (holder.getItemViewType()) {
            case ItemType.TEXT:
                text = item.getField(MultipleFields.TEXT);
                holder.setText(R.id.text_single, text);
                break;
            case ItemType.IMAGE:
                imageUrl = item.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(GLIDE_OPTIONS)
                        .into((ImageView) holder.getView(R.id.img_single));
                break;
            case ItemType.TEXT_IMAGE:
                text = item.getField(MultipleFields.TEXT);
                holder.setText(R.id.text_multiple, text);
                imageUrl = item.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(imageUrl)
                        .apply(GLIDE_OPTIONS)
                        .into((ImageView) holder.getView(R.id.img_multiple));
                break;
            case ItemType.BANNER:
                if (!mIsInitBanner) {
                    bannerImages = item.getField(MultipleFields.BANNERS);
                    final ConvenientBanner<String> convenientBanner = holder.getView(R.id.banner_single);
                    BannerCreator.setDefault(mContext, convenientBanner, bannerImages, this);
                    mIsInitBanner = true;
                }
                break;
        }
    }

    @Override
    public void onItemClick(int position) {

    }

    public void refresh(List<MultipleItemEntity> data){
        getData().clear();
        setNewData(data);
        notifyDataSetChanged();
    }
}
