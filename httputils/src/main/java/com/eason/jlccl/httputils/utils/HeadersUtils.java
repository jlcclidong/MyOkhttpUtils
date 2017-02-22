package com.eason.jlccl.httputils.utils;

import android.os.Build;
import android.text.TextUtils;

import com.eason.jlccl.httputils.Ok;

import java.lang.reflect.Field;
import java.util.Locale;

/**
 * 获得公共头配置
 * Created by lidong on 2017/2/22.
 */

public class HeadersUtils {
    /**
     * Accept-Language: zh-CN,zh;q=0.8
     */
    public static String getAcceptLanguage() {
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        String country = locale.getCountry();
        StringBuilder acceptLanguageBuilder = new StringBuilder(language);
        if (!TextUtils.isEmpty(country))
            acceptLanguageBuilder.append('-').append(country).append(',').append(language).append(";q=0.8");
        String acceptLanguage = acceptLanguageBuilder.toString();
        return acceptLanguage;
    }


    /**
     * User-Agent: Mozilla/5.0 (Linux; U; Android 5.0.2; zh-cn; Redmi Note 3 Build/LRX22G) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Mobile Safari/537.36
     */
    public static String getUserAgent() {
        String webUserAgent = null;
        try {
            Class<?> sysResCls = Class.forName("com.android.internal.R$string");
            Field webUserAgentField = sysResCls.getDeclaredField("web_user_agent");
            Integer resId = (Integer) webUserAgentField.get(null);
            webUserAgent = Ok.getContext().getString(resId);
        } catch (Exception e) {
            // We have nothing to do
        }
        if (TextUtils.isEmpty(webUserAgent)) {
            webUserAgent = "Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko) Version/5.0 %sSafari/533.1";
        }

        Locale locale = Locale.getDefault();
        StringBuffer buffer = new StringBuffer();
        // Add version
        final String version = Build.VERSION.RELEASE;
        if (version.length() > 0) {
            buffer.append(version);
        } else {
            // default to "1.0"
            buffer.append("1.0");
        }
        buffer.append("; ");
        final String language = locale.getLanguage();
        if (language != null) {
            buffer.append(language.toLowerCase(locale));
            final String country = locale.getCountry();
            if (!TextUtils.isEmpty(country)) {
                buffer.append("-");
                buffer.append(country.toLowerCase(locale));
            }
        } else {
            // default to "en"
            buffer.append("en");
        }
        // add the model for the release build
        if ("REL".equals(Build.VERSION.CODENAME)) {
            final String model = Build.MODEL;
            if (model.length() > 0) {
                buffer.append("; ");
                buffer.append(model);
            }
        }
        final String id = Build.ID;
        if (id.length() > 0) {
            buffer.append(" Build/");
            buffer.append(id);
        }
        String userAgent = String.format(webUserAgent, buffer, "Mobile ");
        return userAgent;

    }


}
