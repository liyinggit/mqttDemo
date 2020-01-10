package com.example.demo.config;


import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

@Slf4j
public class Mqtt {

    // just for test
    private static final String MQTT_PUBLISHER_ID = "spring-server";
    // fixme mqtt 服务器地址
    private static final String MQTT_SERVER_ADDRES = "tcp://127.0.0.1:1883";

    private static IMqttClient instance;

    public static IMqttClient getInstance() {
        try {
            if (instance == null) {
                instance = new MqttClient(MQTT_SERVER_ADDRES, MQTT_PUBLISHER_ID);
                log.info("CREATE MQTT CLIENT : SERVER_ADDRESS{} , PUBLISHER_ID{}", MQTT_SERVER_ADDRES, MQTT_PUBLISHER_ID);
            }

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);

            if (!instance.isConnected()) {
                instance.connect(options);
                log.info("CONNECTED TO MQTT SERVER , CONNECT OPTIONS : {}", options);
            }

        } catch (MqttException e) {
            e.printStackTrace();
        }

        return instance;
    }

    private Mqtt() {

    }
}
