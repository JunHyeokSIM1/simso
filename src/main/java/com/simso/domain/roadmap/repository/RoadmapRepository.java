package com.simso.domain.roadmap.repository;

import com.simso.domain.roadmap.entity.Roadmap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoadmapRepository extends JpaRepository<Roadmap, Long> {

    @Query("SELECT r FROM Roadmap r ORDER BY r.id DESC")
    List<Roadmap> findALlDesc();


}
