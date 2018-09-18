package com.test.controller;

import com.test.data.Item;
import com.test.data.ListOfItems;
import com.test.data.Monitoring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import java.util.ArrayList;

@Controller
public class SocketController {
    Monitoring monitoring = new Monitoring();

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/user/{path}")
    public void showData(@DestinationVariable String path) {
        System.out.println("Getting " + path);
        ArrayList<Item> data = new ArrayList<>();

        if (path != null) {
            data.addAll((new ListOfItems(path)).data());
        }
        simpMessagingTemplate.convertAndSend("/topic/files", data);

        monitoring.update(path, simpMessagingTemplate);
    }
}