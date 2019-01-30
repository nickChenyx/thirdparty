package com.nickchen.thirdparty.sample.springretry;

import com.nickchen.thirdparty.sample.springretry.model.User;
import com.nickchen.thirdparty.sample.springretry.service.TService;
import com.nickchen.thirdparty.sample.springretry.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:chenyuxiong@vpgame.cn">nickChen</a>
 * @date 2019年01月24日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Resource
    private UserService userService;

    @Resource
    private TService tService;

    @Resource
    private RetryTemplate retryTemplate;


    /**
     * 重试2次, 而后会执行 recover 方法.
     */
    @Test
    public void testRetryAndRecover() {
        User nico = userService.findUser("nico");
        Assert.assertEquals(nico.getName(), "nico");
        /*
        期望输出
        find user error
        find user error
        recover: nico e: error
         */
    }

    @Test
    public void testTemplate() {
        retryTemplate.execute(new RetryCallback<Void, RuntimeException>() {
            @Override
            public Void doWithRetry(RetryContext retryContext) {
                tService.templateRetryService();
                return null;
            }
        }, new RecoveryCallback<Void>() {
            @Override
            public Void recover(RetryContext retryContext) throws Exception {
                System.out.println("recover");
                return null;
            }
        });

        /*retryTemplate.execute(arg0 -> {
            tService.templateRetryService();
            return null;
        }, arg1 -> {
            System.out.println("recover");
            return null;
        });*/
    }


}
