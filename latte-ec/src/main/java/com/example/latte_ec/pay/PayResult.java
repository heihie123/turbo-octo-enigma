package com.example.latte_ec.pay;

import android.text.TextUtils;

/**
 * 支付宝支付回调实体类
 */
public class PayResult {

    private String resultStatus;
    private String result;
    private String memo;

    public PayResult(String rawResult) {
        if (TextUtils.isEmpty(rawResult)) {
            return;
        }
        String[] resultParams = rawResult.split(";");
        for (String resultParam : resultParams) {
            if (resultParam.startsWith("resultStatus")) {
                resultStatus = getValue(resultParam, "resultStatus");
            }
            if (resultParam.startsWith("result")) {
                resultStatus = getValue(resultParam, "result");
            }
            if (resultParam.startsWith("memo")) {
                resultStatus = getValue(resultParam, "memo");
            }
        }
    }

    // 截取括号之间的参数
    private String getValue(String content, String key) {
        String prefix = key + "={";
        return content.substring(content.indexOf(prefix) + prefix.length(),
                content.lastIndexOf("}"));
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public String getResult() {
        return result;
    }

    public String getMemo() {
        return memo;
    }
}
