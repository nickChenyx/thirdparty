package com.nickchen.thirdparty.sample.springretry.config;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;

/**
 * @author <a href="mailto:chenyuxiong@vpgame.cn">nickChen</a>
 * @date 2019年01月30日
 */
public class DefaultListenerSupport implements RetryListener {
    @Override
    public <T, E extends Throwable> boolean open(RetryContext retryContext, RetryCallback<T, E> retryCallback) {
        System.out.println("listener open");
        return true;
    }

    @Override
    public <T, E extends Throwable> void close(RetryContext retryContext, RetryCallback<T, E> retryCallback, Throwable throwable) {
        System.out.println("listener close");
    }

    @Override
    public <T, E extends Throwable> void onError(RetryContext retryContext, RetryCallback<T, E> retryCallback, Throwable throwable) {
        System.out.println("listener on error");
    }
}
