package com.example.demo.controller;

import com.example.demo.config.Mqtt;
import com.example.demo.config.WebMvcConfig;
import com.example.demo.model.MapsPublishMessage;
import com.example.demo.model.MqttMessageModel;
import lombok.val;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * mqtt测试
 */
@RestController
@RequestMapping("/test_api")
public class TestController {


    @PostMapping("/publish")
    public String publish(
            final HttpServletRequest httpServletRequest
    ) throws MqttException {

        String checkWarningRangeResult = checkInputtedMqtt(httpServletRequest);
        if (!checkWarningRangeResult.isEmpty()) {
            return checkWarningRangeResult;
        }

        final String iotDeviceId = httpServletRequest.getParameter("iotDeviceId");
        final double longitude = Double.parseDouble(httpServletRequest.getParameter("longitude"));
        final double latitude = Double.parseDouble(httpServletRequest.getParameter("latitude"));
        final double power = Double.parseDouble(httpServletRequest.getParameter("power"));
        final int step = Integer.parseInt(httpServletRequest.getParameter("step"));
        final String type = httpServletRequest.getParameter("type");
        final boolean sleep = Boolean.parseBoolean(httpServletRequest.getParameter("sleep"));

        MqttMessageModel model = new MqttMessageModel();
        final val message = new MqttMessage();

        MapsPublishMessage mapsPublishMessage = new MapsPublishMessage();
        mapsPublishMessage.setIotDeviceId(iotDeviceId);
        mapsPublishMessage.setLatitude(latitude);
        mapsPublishMessage.setLongitude(longitude);
        mapsPublishMessage.setPower(power);
        mapsPublishMessage.setStep(step);
        mapsPublishMessage.setSleep(sleep);

        model.setMessage(mapsPublishMessage);
        model.setTopic(type);

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

    private String checkInputtedMqtt(
            final HttpServletRequest httpServletRequest
    ) {
        String iotDeviceId = httpServletRequest.getParameter("iotDeviceId");
        String power = httpServletRequest.getParameter("power");
        String step = httpServletRequest.getParameter("step");
        String longitude = httpServletRequest.getParameter("longitude");
        String latitude = httpServletRequest.getParameter("latitude");
        String type = httpServletRequest.getParameter("type");
        String sleep = httpServletRequest.getParameter("sleep");

        String iotDeviceIdCheckResult;
        String powerCheckResult;
        String stepCheckResult;
        String longitudeCheckResult;
        String latitudeCheckResult;
        String typeCheckResult;
        String sleepCheckResult;


        iotDeviceIdCheckResult = checkStrNullOrEmpty("iotDeviceId", iotDeviceId);
        if (!iotDeviceIdCheckResult.isEmpty()) {
            return iotDeviceIdCheckResult;
        }

        powerCheckResult = checkStrNullOrEmpty("power", power);
        if (!powerCheckResult.isEmpty()) {
            return powerCheckResult;
        }

        stepCheckResult = checkStrNullOrEmpty("step", step);
        if (!stepCheckResult.isEmpty()) {
            return stepCheckResult;
        }

        longitudeCheckResult = checkStrNullOrEmpty("longitude", longitude);
        if (!longitudeCheckResult.isEmpty()) {
            return longitudeCheckResult;
        }

        latitudeCheckResult = checkStrNullOrEmpty("latitude", latitude);
        if (!latitudeCheckResult.isEmpty()) {
            return latitudeCheckResult;
        }

        typeCheckResult = checkStrNullOrEmpty("type", type);
        if (!typeCheckResult.isEmpty()) {
            return typeCheckResult;
        }

        sleepCheckResult = checkStrNullOrEmpty("sleep", sleep);
        if (!sleepCheckResult.isEmpty()) {
            return sleepCheckResult;
        }

        return "";
    }

    /**
     * 检测字符串是否为Null或empty
     *
     * @param strName    name
     * @param checkedStr value
     * @return message
     */
    private String checkStrNullOrEmpty(
            final String strName,
            final String checkedStr
    ) {
        String result = "";
        if (checkedStr == null) {
            result = strName + " is null";
        } else if (checkedStr.trim().isEmpty()) {
            result = strName + " is empty";
        }
        return result;
    }
}
