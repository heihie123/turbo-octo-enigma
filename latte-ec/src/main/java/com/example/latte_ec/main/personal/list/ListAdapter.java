package com.example.latte_ec.main.personal.list;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.latte_core.util.image.GlideUtils;
import com.example.latte_ec.R;

import java.util.List;

import androidx.appcompat.widget.SwitchCompat;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 *
 */
public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ListItemType.ITEM_NORMAL, R.layout.item_normal);
        addItemType(ListItemType.ITEM_AVATAR, R.layout.item_avatar);
        addItemType(ListItemType.ITEM_SWITCH, R.layout.item_switch);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        switch (helper.getItemViewType()) {
            case ListItemType.ITEM_NORMAL:
                helper.setText(R.id.txt_arrow_text, item.getText());
                helper.setText(R.id.txt_arrow_value, item.getValue());
                break;
            case ListItemType.ITEM_AVATAR:
                final CircleImageView circleImageView = helper.getView(R.id.img_arrow_avatar);
                GlideUtils.loadNormalImg(mContext, item.getImageUrl(), circleImageView);
                break;
            case ListItemType.ITEM_SWITCH:
                helper.setText(R.id.txt_arrow_switch_text, item.getText());
                final SwitchCompat switchCompat = helper.getView(R.id.list_item_switch);
                switchCompat.setChecked(true);
                switchCompat.setOnCheckedChangeListener(item.getOnCheckedChangeListener());
                break;
            default:
                break;
        }
    }
}
