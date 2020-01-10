package com.example.demo.controller;

import com.example.demo.config.Mqtt;
import com.example.demo.config.WebMvcConfig;
import com.example.demo.model.MqttMessageModel;
import lombok.val;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/mqtt")
public class MqttController {

    @PostMapping("/publish")
    public String publish(
            @RequestBody MqttMessageModel model
    ) throws MqttException {

        final val message = new MqttMessage();
        message.setPayload(
                WebMvcConfig.DEFAULT_GSON
                        .toJson(model.getMessage())
                        .getBytes()
        );
        message.setQos(model.getQos());
        message.setRetained(model.isRetained());

        Mqtt.getInstance().publish(model.getTopic(), message);
        return "MQTT you send this: " + message + " to topic#" + model.getTopic();
    }
}
