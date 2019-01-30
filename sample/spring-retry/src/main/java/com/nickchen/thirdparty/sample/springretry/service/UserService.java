package com.nickchen.thirdparty.sample.springretry.service;

import com.nickchen.thirdparty.sample.springretry.model.User;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

/**
 * @author <a href="mailto:chenyuxiong@vpgame.cn">nickChen</a>
 * @date 2019年01月24日
 */
public interface UserService {

    @Retryable(
            value = { RuntimeException.class },
            maxAttempts = 2,
            backoff = @Backoff(delay = 1000))
    User findUser(String name);

    @Recover
    User recover(RuntimeException e, String name);
}
