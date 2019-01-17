package com.example.latte_ec.main.sort.content;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.latte_core.util.image.GlideUtils;
import com.example.latte_ec.R;

import java.util.List;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * 首页sort content适配器
 */
public class ContentAdapter extends BaseSectionQuickAdapter<ContentBean, BaseViewHolder> {

    public ContentAdapter(int layoutResId, int sectionHeadResId, List<ContentBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, ContentBean item) {
        helper.setText(R.id.txt_content_header, item.header);
        helper.setVisible(R.id.text_more, item.isIsMore());
        helper.addOnClickListener(R.id.text_more);
    }

    @Override
    protected void convert(BaseViewHolder helper, ContentBean item) {
        final int goodsId = item.t.getGoodsId();
        final String goodsThumb = item.t.getGoodsThumb();
        final String goodsName = item.t.getGoodsName();
        final ContentItemEntity itemEntity = item.t;
        helper.setText(R.id.text_content_name, goodsName);
        GlideUtils.loadNormalImg(mContext, goodsThumb, (AppCompatImageView) helper.getView(R.id.img_content));
    }
}
