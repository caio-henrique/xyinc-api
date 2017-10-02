package com.xyinc.xyinc.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xyinc.xyinc.api.core.entity.Poi;

public interface PoiRepository extends JpaRepository<Poi, Long> {

}
