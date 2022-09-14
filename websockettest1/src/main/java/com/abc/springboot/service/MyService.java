package com.abc.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MyService {

    @Autowired
    private WebSocket socket;

    public void sendMessageToSocket() {
        for (int i = 0; i < 5; i++) {
            socket.sendMessage("a new id " + new Random().nextLong() + " come!");
            try {
                int second = new Random().nextInt(5);
                Thread.sleep(second * 1000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
