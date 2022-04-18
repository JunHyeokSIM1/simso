package com.simso.repository;

import com.simso.domain.Posts;
import com.simso.domain.Roadmap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoadmapRepository extends JpaRepository<Roadmap, Long> {

    @Query("SELECT r FROM Roadmap r ORDER BY r.id DESC")
    List<Roadmap> findALlDesc();


}
