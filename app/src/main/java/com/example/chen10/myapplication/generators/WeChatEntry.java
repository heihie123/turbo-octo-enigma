package com.example.chen10.myapplication.generators;

import com.example.latte_annotations.EntryGenerator;
import com.example.latte_core.wechat.templates.WXEntryTemplate;

/**
 * 可以再任何你想要的类上加上注解，使其实现微信回调
 */
@EntryGenerator(
        packageName = "com.example.chen10.myapplication",
        entryTemple = WXEntryTemplate.class
)
public interface WeChatEntry {

}
