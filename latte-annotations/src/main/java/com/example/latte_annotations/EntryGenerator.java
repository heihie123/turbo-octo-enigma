package com.example.latte_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 类注解
@Target(ElementType.TYPE)
// 编译器处理注解在源码阶段处理(对性能没有影响)
@Retention(RetentionPolicy.SOURCE)
public @interface EntryGenerator {
    String packageName();
    Class<?> entryTemple();
}
