package com.nickchen.thirdparty.sample.springretry.service;

import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:chenyuxiong@vpgame.cn">nickChen</a>
 * @date 2019年01月28日
 */
@Service
public class TService {

    public void templateRetryService() {
        System.out.println("retry service");
        throw new RuntimeException("custom error");
    }


}
