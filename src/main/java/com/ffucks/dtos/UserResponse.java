package com.ffucks.dtos;

import com.ffucks.entities.Device;
import com.ffucks.entities.User;

public class UserResponse {
    private User user;
    private Device device;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
