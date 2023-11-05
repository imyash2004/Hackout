package com.example.hack1.entities;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name="Video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer videoId;
    private String description;
    private String title;
    @ElementCollection
    private Set<String> tags;

    private Integer userId;
    private Integer likes;
    private Integer dislikes;
    private String VideoUrl;

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }


    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public String getVideoUrl() {
        return VideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        VideoUrl = videoUrl;
    }

    public Video(Integer videoId, String description, Set<String> tags, ArrayList<String> comments, Integer likes, Integer dislikes, String videoUrl) {
        this.videoId = videoId;
        this.description = description;
        this.tags = tags;
        this.likes = likes;
        this.dislikes = dislikes;
        VideoUrl = videoUrl;
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoId=" + videoId +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", VideoUrl='" + VideoUrl + '\'' +
                '}';
    }

    public Video() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Video(Integer videoId, String description, String title, Set<String> tags, ArrayList<String> comments, Integer userId, Integer likes, Integer dislikes, String videoUrl) {
        this.videoId = videoId;
        this.description = description;
        this.title = title;
        this.tags = tags;
        this.userId = userId;
        this.likes = likes;
        this.dislikes = dislikes;
        VideoUrl = videoUrl;
    }
}
