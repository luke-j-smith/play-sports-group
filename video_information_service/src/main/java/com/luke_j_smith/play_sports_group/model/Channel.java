package com.luke_j_smith.play_sports_group.model;

/**
 * Channel Domain Object.
 */
public class Channel {
    private int id;

    private String channel_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", channel_name='" + channel_name + '\'' +
                '}';
    }
}
