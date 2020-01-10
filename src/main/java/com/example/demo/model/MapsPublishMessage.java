package com.example.demo.model;

import com.google.gson.annotations.Expose;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
class MapsPublishMessage {
    @Expose
    String deviceId;
    @Expose
    Integer step;
    @Expose
    Double longitude;
    @Expose
    Double latitude;
}
