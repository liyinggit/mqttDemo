package com.example.demo.mqtt;

import com.example.demo.config.Mqtt;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MqttService {

    @Autowired
    public MqttService() throws MqttException {
        // 数组结构用于接受多个主题消息
        // todo  mqtt 这里写业务逻辑
        Mqtt.getInstance()
                .subscribeWithResponse(
                        new String[]{"hello", "test"},
                        new IMqttMessageListener[]{
                                (s, mqttMessage) -> {
                                    log.info("MQTT you got message from topic: {} , content: {}", s, mqttMessage.toString());
                                },
                                (s, mqttMessage) -> {
                                    log.info(" MQTT you got message from topic: {} , content: {}", s, mqttMessage.toString());
                                }
                        });
    }
}
