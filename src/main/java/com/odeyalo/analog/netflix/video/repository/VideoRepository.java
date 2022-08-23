package com.odeyalo.analog.netflix.video.repository;

import com.odeyalo.analog.netflix.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {

    List<Video> findAllByUserId(String userId);
}
