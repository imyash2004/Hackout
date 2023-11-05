package com.example.hack1.Repository;

import com.example.hack1.entities.User;
import com.example.hack1.entities.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoRepo extends JpaRepository<Video,Integer> {
    @Query(value = "select  *from Video ", nativeQuery = true)
//    private  Page<Video> getAllVideo(@Param(""), Pageable pageable) ;

    List<Video> findAll(int i, int i1);
    Video getVideoByVideoId(Integer videoId);
}
