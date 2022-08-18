package com.viola.backend.voilabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.viola.backend.voilabackend.model.domain.Connection;
import com.viola.backend.voilabackend.model.domain.User;
import com.viola.backend.voilabackend.repository.ConnectionRepository;
import com.viola.backend.voilabackend.repository.UserRepository;

@Service("connectionService")
public class ConnectionService {
    @Autowired
	UserRepository userRepository;

    @Autowired
    ConnectionRepository connectionRepository;

    public List<Connection> getUserConnections(User user) {
        List<Connection> connections1 = connectionRepository.findByUser1(user);
        List<Connection> connections2 = connectionRepository.findByUser2(user);
        connections1.addAll(connections2);
        return connections1;
	}

    public Connection getConnection(User user1, User user2) {
        Connection connection = connectionRepository.findByUser1AndUser2(user1, user2);
        return connection;
    }

    public void deleteConnection(Connection connection) {
        connectionRepository.delete(connection);
    }

    public void save(Connection connection) {
        connectionRepository.save(connection);
    }

    public List<User> getConnections(User user) {
        List<User> users = new ArrayList<User>();
        List<Connection> connections = getUserConnections(user);
        if (connections != null && connections.size()>0) {
            for (Connection connection : connections) {
                if (!connection.getUser1().getId().equals( user.getId())) {
                    User u = userRepository.findById(connection.getUser1().getId()).orElse(null);
                    users.add(u);
                } else {
                    User u = userRepository.findById(connection.getUser2().getId()).orElse(null);
                    users.add(u);
                }
            }
        }
        return users;
    }

    public void createConnection(User user1, User user2) {
        user1 = userRepository.findById(user1.getId()).orElse(null);
        user2 = userRepository.findById(user2.getId()).orElse(null);
        Connection connection = new Connection(user1, user2);
        save(connection);
    }
}
