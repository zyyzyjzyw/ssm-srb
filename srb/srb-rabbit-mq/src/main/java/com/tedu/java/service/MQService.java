package com.tedu.java.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author： zyy
 * @date： 2022/12/25 19:17
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Service
@Slf4j
public class MQService {
    @Resource
    private AmqpTemplate amqpTemplate;

    /**
     *  发送消息
     * @param exchange 交换机
     * @param routingKey 路由
     * @param message 消息
     */
    public boolean sendMessage(String exchange, String routingKey, Object message) {
        log.info("发送消息...........");
        amqpTemplate.convertAndSend(exchange, routingKey, message);
        return true;
    }
}
