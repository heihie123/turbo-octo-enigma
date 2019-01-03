package com.example.latte_ec.main.personal.address;

import android.view.View;

import com.example.latte_core.ui.recycler.MultipleFields;
import com.example.latte_core.ui.recycler.MultipleItemEntity;
import com.example.latte_core.ui.recycler.MultipleRecyclerAdapter;
import com.example.latte_core.ui.recycler.MultipleViewHolder;
import com.example.latte_ec.R;

import java.util.List;

import androidx.appcompat.widget.AppCompatTextView;

public class AddressAdapter extends MultipleRecyclerAdapter {
    protected AddressAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(AddressItemType.ITEM_ADDRESS, R.layout.item_address);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, MultipleItemEntity item) {
        super.convert(holder, item);
        switch (holder.getItemViewType()) {
            case AddressItemType.ITEM_ADDRESS:
                final String name = item.getField(MultipleFields.NAME);
                final String phone = item.getField(AddressItemFields.PHONE);
                final String address = item.getField(AddressItemFields.ADDRESS);
                final boolean isDefault = item.getField(MultipleFields.TAG);
                final int id = item.getField(MultipleFields.ID);

                final AppCompatTextView nameText = holder.getView(R.id.txt_address_name);
                final AppCompatTextView phoneText = holder.getView(R.id.txt_address_phone);
                final AppCompatTextView deleteText = holder.getView(R.id.txt_address_delete);
                final AppCompatTextView addressText = holder.getView(R.id.txt_address_address);

                nameText.setText(name);
                phoneText.setText(phone);
                addressText.setText(address);
                deleteText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        remove(holder.getLayoutPosition());
                    }
                });
                break;
            default:
                break;
        }
    }
}
