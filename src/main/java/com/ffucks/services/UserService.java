package com.ffucks.services;

import com.ffucks.dtos.UserResponse;
import com.ffucks.entities.Device;
import com.ffucks.entities.User;
import com.ffucks.repositories.DeviceRepository;
import com.ffucks.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;

    public UserService(UserRepository userRepository, DeviceRepository deviceRepository) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
    }

    @Transactional
    public UserResponse createUserWithDevice(String name, String serial) {
        User user = new User();
        user.setName(name);

        Device device = new Device();
        device.setSerial(serial);
        device.setOwner(user);

        deviceRepository.save(device);
        UserResponse response = new UserResponse();
        response.setUser(user);
        response.setDevice(device);

        return response;
    }

    @Transactional
    public void removeDevice(Long deviceId) {
        deviceRepository.deleteById(deviceId);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
