package com.example.latte_ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.latte_core.app.Latte;
import com.example.latte_core.detegates.bottom.BottomItemDelegate;
import com.example.latte_core.ui.recycler.MultipleItemEntity;
import com.example.latte_core.ui.refresh.RefreshHandler;
import com.example.latte_ec.R;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

/**
 * 首页底部按钮delegate
 */
public class IndexDelagate extends BottomItemDelegate {

    private SwipeRefreshLayout mSwipeRefreshLayout = null;
    private RecyclerView mIndexList = null;
    private Toolbar mToolbar = null;
    private IconTextView mScanIcon = null;
    private AppCompatEditText mSearchEdit = null;
    private IconTextView mMsgIcon = null;

    private RefreshHandler mRefreshHandler = null;
    private int index = 0;

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @Nullable View rootView) {
        initView();
        mRefreshHandler = new RefreshHandler(mSwipeRefreshLayout);
        // 模拟网络请求
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                    {"data":[
//                        {
//                            {
//                                "banners":[{
//                                "imageUrl":"http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%AE%BD%E5%9B%BE%E8%83%8C%E6%99%AF&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=1208188544,1513879059&os=392717502,1138275663&simid=9497353,890253649&pn=17&rn=1&di=3701205750&ln=1676&fr=&fmq=1541236406972_R&fm=rs1&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&oriquery=%E5%AE%BD%E5%9B%BE&objurl=http%3A%2F%2Fbpic.ooopic.com%2F15%2F64%2F42%2F15644277-b06a3fbd7745a392fab1ac9a21dcbe50.jpg&rpstart=0&rpnum=0&adpicid=0"
//                            },{
//                                "imageUrl":"http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E5%AE%BD%E5%9B%BE%E8%83%8C%E6%99%AF&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=3428309959,1480357108&os=2110224293,1400436748&simid=0,0&pn=2&rn=1&di=85716488880&ln=1676&fr=&fmq=1541236406972_R&fm=rs1&ic=undefined&s=undefined&se=&sme=&tab=0&width=1024&height=480&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=78&oriquery=%E5%AE%BD%E5%9B%BE&objurl=http%3A%2F%2Fbpic.ooopic.com%2F16%2F54%2F92%2F16549228-3396654f265c6b942170b216f7c2a753.jpg&rpstart=0&rpnum=0&adpicid=0"
//                            },{
//                                "imageUrl":"http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E5%AE%BD%E5%9B%BE%E8%83%8C%E6%99%AF&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=3636747541,1994743070&os=2896142659,34293409&simid=3290763023,276872586&pn=8&rn=1&di=107910405680&ln=1676&fr=&fmq=1541236406972_R&fm=rs1&ic=undefined&s=undefined&se=&sme=&tab=0&width=1024&height=480&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=78&oriquery=%E5%AE%BD%E5%9B%BE&objurl=http%3A%2F%2Fbpic.ooopic.com%2F16%2F37%2F91%2F16379119-59336ecc6904333c3d1d880ed6e461dd-4.jpg&rpstart=0&rpnum=0&adpicid=0"
//                            }]},
//                            {
//                                "imageUrl":"http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E5%AE%BD%E5%9B%BE%E8%83%8C%E6%99%AF&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=313001586,2990268225&os=2047997987,4006238210&simid=0,0&pn=18&rn=1&di=106846319690&ln=1676&fr=&fmq=1541236406972_R&fm=rs1&ic=undefined&s=undefined&se=&sme=&tab=0&width=1024&height=480&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=78&oriquery=%E5%AE%BD%E5%9B%BE&objurl=http%3A%2F%2Fbpic.ooopic.com%2F16%2F81%2F22%2F16812205-afe75bedf7e5c3eee4fa0e2c6eaf11f7-1.jpg&rpstart=0&rpnum=0&adpicid=0"
//                            },
//                            {
//                                "text":"特购特购"
//                            },
//                            {
//                                "spanSize":""
//                            },
//                            {
//                                "goodsId":""
//                            }
//                        },
//                        ]
//                    }
//                }
                IndexDataConverter converter = new IndexDataConverter();
                String dataJson = "";
                converter.setJsonData(dataJson);
                final ArrayList<MultipleItemEntity> itemEntities = converter.convert();
            }
        }, 1000);
    }

    private void initView() {
        mSwipeRefreshLayout = $(R.id.srl_index);
        mIndexList = $(R.id.rv_index);
        mToolbar = $(R.id.tb_index);
        mScanIcon = $(R.id.icon_index_scan);
        mSearchEdit = $(R.id.et_search_view);
        mMsgIcon = $(R.id.icon_index_message);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        // 懒加载
        super.onLazyInitView(savedInstanceState);
        initRefresh();
    }

    private void initRefresh() {
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        // scale是否由小变大、回弹由小变大
        mSwipeRefreshLayout.setProgressViewOffset(true, 120, 300);
    }
}
