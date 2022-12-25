package com.tedu.java.client.fallback;

import com.tedu.java.client.CoreUserInfoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author： zyy
 * @date： 2022/12/20 10:46
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Service
@Slf4j
public class CoreUserInfoClientFallback implements CoreUserInfoClient {
    @Override
    public boolean checkMobile(String mobile) {
        log.error("远程调用失败，服务熔断");
        return false;
    }
}
