package com.example.latte_ec.detail;

import android.os.Bundle;
import android.view.View;

import com.example.latte_core.detegates.LatteDelegate;
import com.example.latte_core.ui.recycler.ItemType;
import com.example.latte_core.ui.recycler.MultipleFields;
import com.example.latte_core.ui.recycler.MultipleItemEntity;
import com.example.latte_ec.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 商品详情大图的Delegate
 */
public class ImageDelegate extends LatteDelegate {

    private RecyclerView mImageRList = null;
    private List<String> PICTURES;

    private static final String ARG_PICTURES = "ARG_PICTURES";

    public static Fragment create(ArrayList<String> pictures) {
        final Bundle args = new Bundle();
        args.putStringArrayList(ARG_PICTURES, pictures);
        final ImageDelegate imageDelegate = new ImageDelegate();
        imageDelegate.setArguments(args);
        return imageDelegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            PICTURES = args.getStringArrayList(ARG_PICTURES);
        } else {
            PICTURES = new ArrayList<>();
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_image;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        initView();
        initData();
    }

    private void initView() {
        mImageRList = $(R.id.rlist_image_container);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mImageRList.setLayoutManager(linearLayoutManager);
    }

    private void initData() {
        final ArrayList<MultipleItemEntity> entities = new ArrayList<>();
        final int size = PICTURES.size();
        for (int i = 0; i < size; i++) {
            final MultipleItemEntity entity = MultipleItemEntity
                    .builder()
                    .setItemType(ItemType.SINGLE_BIG_IMAGE)
                    .setField(MultipleFields.IMAGE_URL, PICTURES.get(i))
                    .build();
            entities.add(entity);
        }
        final RecycleImageAdapter recycleImageAdapter = new RecycleImageAdapter(entities);
        mImageRList.setAdapter(recycleImageAdapter);
    }
}
