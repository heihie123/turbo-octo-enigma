package com.example.latte_compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/**
 * 微信广播访问器
 */
public final class AppRegisterEntryVisitor extends SimpleAnnotationValueVisitor7<Void, Void> {

    private Filer mFiler = null;
    private String mPackageName = null;

    AppRegisterEntryVisitor(Filer filer) {
        this.mFiler = filer;
    }

    @Override
    public Void visitString(String s, Void aVoid) {
        this.mPackageName = s;
        return aVoid;
    }

    @Override
    public Void visitType(TypeMirror typeMirror, Void aVoid) {
        generateJavaCode(typeMirror);
        return aVoid;
    }

    /**
     * 把创建的java文件写入filer中
     */
    private void generateJavaCode(TypeMirror typeMirror) {
        // 创建一个类
        final TypeSpec targetActivity = TypeSpec.classBuilder("AppRegister")
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.FINAL)
                .superclass(TypeName.get(typeMirror))
                .build();
        // 创捷类的包名
        final JavaFile javaFile = JavaFile.builder(mPackageName + ".wxapi", targetActivity)
                .addFileComment("微信广播接收器")
                .build();
        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
