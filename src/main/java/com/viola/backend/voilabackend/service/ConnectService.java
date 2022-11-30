package com.viola.backend.voilabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.viola.backend.voilabackend.model.domain.Connect;
import com.viola.backend.voilabackend.model.domain.User;
import com.viola.backend.voilabackend.repository.ConnectRepository;
import com.viola.backend.voilabackend.repository.UserRepository;

@Service("connectService")
public class ConnectService {
    @Autowired
	UserRepository userRepository;

    @Autowired
    ConnectRepository connectRepository;

    public List<Connect> getUserConnections(User user) {
        List<Connect> connections = connectRepository.findByUser(user);
        return connections;
	}
    
    public void deleteConnect(Connect connect) {
        connectRepository.delete(connect);
    }

    public void save(Connect connect) {
        connectRepository.save(connect);
    }

    public Connect getConnect(User user, Long id) {
        List<Connect> connections = connectRepository.findByUserAndId(user, id);
        if(connections != null && connections.size() > 0) {
            return connections.get(0);
        }
        return null;
    }
}
