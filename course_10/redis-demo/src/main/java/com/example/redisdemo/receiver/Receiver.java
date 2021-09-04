package com.example.redisdemo.receiver;

import lombok.extern.slf4j.Slf4j;

/**
 * 消息接收者
 */
@Slf4j
public class Receiver {
    public void receiveMessage(String message) {
        log.info(message);
    }
}