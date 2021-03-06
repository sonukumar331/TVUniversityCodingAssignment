package com.testvagrant.tvu.amazonshopping.interceptors;

import com.google.inject.Inject;
import com.testvagrant.tvu.amazonshopping.Toggles;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.concurrent.atomic.AtomicReference;

public class ScreenshotInterceptor extends SiteInterceptor implements MethodInterceptor {

    @Inject
    ScreenshotTaker screenshotTaker;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        AtomicReference<Object> proceed = retryObjInvoke(invocation);
        if (Toggles.TIMELINE.isActive()) screenshotTaker.saveScreenshot();
        return proceed.get();
    }

}
