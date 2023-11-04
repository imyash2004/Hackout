package com.example.hack1.Repository;

import com.example.hack1.entities.User;
import com.example.hack1.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepo extends JpaRepository<Video,Integer> {

}
