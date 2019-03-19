package com.example.latte_ec.detail;

import com.example.latte_core.ui.recycler.ItemType;
import com.example.latte_core.ui.recycler.MultipleFields;
import com.example.latte_core.ui.recycler.MultipleItemEntity;
import com.example.latte_core.ui.recycler.MultipleRecyclerAdapter;
import com.example.latte_core.ui.recycler.MultipleViewHolder;
import com.example.latte_core.util.image.GlideUtils;
import com.example.latte_ec.R;

import java.util.List;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * 大图的adapter
 */
public class RecycleImageAdapter extends MultipleRecyclerAdapter {

    protected RecycleImageAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.SINGLE_BIG_IMAGE, R.layout.item_goods_image);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity item) {
        super.convert(holder, item);
        final int type = holder.getItemViewType();
        switch (type) {
            case ItemType.SINGLE_BIG_IMAGE:
                final AppCompatImageView itemImg = holder.getView(R.id.img_item);
                final String url = item.getField(MultipleFields.IMAGE_URL);
                GlideUtils.loadNoCacheImg(mContext, url, itemImg);
        }
    }
}
