package com.luke_j_smith.play_sports_group.model;

import javax.persistence.*;

/**
 * Channel Domain Object.
 */
@Entity
@Table(name = "channels")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "channel_name", length = 45)
    private String channelName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", channelName='" + channelName + '\'' +
                '}';
    }
}
