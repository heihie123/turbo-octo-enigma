package com.example.chen10.myapplication.generators;

import com.example.latte_annotations.AppRegisterEntryGenerator;
import com.example.latte_core.wechat.templates.AppRegisterTemplate;

/**
 * 可以再任何你想要的类上加上注解，使其实现微信回调
 */
@AppRegisterEntryGenerator(
        packageName = "com.example.chen10.myapplication",
        entryTemple = AppRegisterTemplate.class
)
public interface AppRegister {

}
