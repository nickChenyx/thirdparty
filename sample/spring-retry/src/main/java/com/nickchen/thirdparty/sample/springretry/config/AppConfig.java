package com.nickchen.thirdparty.sample.springretry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryListener;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @author <a href="mailto:chenyuxiong@vpgame.cn">nickChen</a>
 * @date 2019年01月24日
 */
@Configuration
@EnableRetry
public class AppConfig {

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        // 决定了每次 retry 的延时, 默认使用 Thread.sleep() 来实现
        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(2000L);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

        // 设置 retry 策略, 限制次数
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);
        retryTemplate.setRetryPolicy(retryPolicy);

        // 设置 retry 监听器
        retryTemplate.setListeners(new RetryListener[]{new DefaultListenerSupport()});

        return retryTemplate;
    }

}
