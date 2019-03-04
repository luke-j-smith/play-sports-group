package com.luke_j_smith.play_sports_group.dto;

import java.util.Objects;

/**
 * Basic Video DTO - used when we are only interested in the video ID and title.
 */
public class BasicVideoDTO {
    private int id;

    private String title;

    public BasicVideoDTO(final int id, final String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicVideoDTO that = (BasicVideoDTO) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "BasicVideoDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
