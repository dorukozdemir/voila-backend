package com.viola.backend.voilabackend.model.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "v_connections_t")
public class Connection {
    
    private Long id;
    private User user1;
    private User user2;
    private Date dateConnected = new Date();
    

    public Connection() {

    }

    public Connection(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user1_id")
    public User getUser1() {
        return user1;
    }
    public void setUser1(User user1) {
        this.user1 = user1;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user2_id")
    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public Date getDateConnected() {
        return dateConnected;
    }

    public void setDateConnected(Date dateConnected) {
        this.dateConnected = dateConnected;
    }
    
    
}
