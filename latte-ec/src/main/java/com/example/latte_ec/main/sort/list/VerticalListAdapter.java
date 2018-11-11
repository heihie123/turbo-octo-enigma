package com.example.latte_ec.main.sort.list;

import android.view.View;

import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.ui.recycler.ItemType;
import com.example.latte_core.ui.recycler.MultipleFields;
import com.example.latte_core.ui.recycler.MultipleItemEntity;
import com.example.latte_core.ui.recycler.MultipleRecyclerAdapter;
import com.example.latte_core.ui.recycler.MultipleViewHolder;
import com.example.latte_ec.R;
import com.example.latte_ec.main.sort.SortDelegate;
import com.example.latte_ec.main.sort.content.ContentDelegate;

import java.util.List;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import me.yokeyword.fragmentation.SupportHelper;

/**
 * 首页sort menu适配器
 */
public class VerticalListAdapter extends MultipleRecyclerAdapter {

    private final SortDelegate DELEGATE;
    private int mPrePosition = 0;


    public VerticalListAdapter(List<MultipleItemEntity> data, SortDelegate sortDelegate) {
        super(data);
        this.DELEGATE = sortDelegate;
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, MultipleItemEntity item) {
        super.convert(holder, item);
        switch (holder.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST:
                final String text = item.getField(MultipleFields.TEXT);
                final boolean isClicked = item.getField(MultipleFields.TAG);
                final AppCompatTextView name = holder.getView(R.id.txt_vertiacl_item_name);
                final View line = holder.getView(R.id.v_line);
                final View itemView = holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentPosition = holder.getAdapterPosition();
                        if (mPrePosition != currentPosition) {
                            getData().get(mPrePosition).setField(MultipleFields.TAG, false);
                            notifyItemChanged(mPrePosition);
                            getData().get(currentPosition).setField(MultipleFields.TAG, true);
                            notifyItemChanged(currentPosition);
                            mPrePosition = currentPosition;
                            final int currentId = getData().get(currentPosition).getField(MultipleFields.ID);
                            showContent(currentId);
                        }
                    }
                });

                if (!isClicked) {
                    line.setVisibility(View.VISIBLE);
                    // 使用ContextCompat兼容老机型
                    name.setBackgroundColor(ContextCompat.getColor(mContext, R.color.orange_dark));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color._FFF));

                } else {
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color._323232));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color._F1F1F1));
                }
                holder.setText(R.id.txt_vertiacl_item_name, text);
                break;
        }
    }

    private void showContent(int contentId) {
        final ContentDelegate contentDelegate = ContentDelegate.newInstance(contentId);
        swtichContent(contentDelegate);
    }

    private void swtichContent(ContentDelegate contentDelegate) {
        final LatteDelegate latteDelegate = SupportHelper.findFragment(DELEGATE.getChildFragmentManager(), ContentDelegate.class);
        if (latteDelegate != null) {
            latteDelegate.getSupportDelegate().replaceFragment(contentDelegate, false);
        }
    }
}
