package com.example.chen10.myapplication.generators;

import com.example.latte_annotations.PayEntryGenerator;
import com.example.latte_core.wechat.templates.WXPayEntryTemplate;

/**
 * 可以再任何你想要的类上加上注解，使其实现微信支付回调
 */
@PayEntryGenerator(
        packageName = "com.example.chen10.myapplication",
        entryTemple = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {

}
