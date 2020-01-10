package com.example.demo.model;


import com.google.gson.annotations.Expose;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MqttMessageModel {
    @Expose
    MapsPublishMessage message;
    @Expose
    int qos = 2;
    @Expose
    String topic;
    @Expose
    boolean retained = true;
}
