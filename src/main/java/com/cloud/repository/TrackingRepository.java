package com.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.entity.TrackingInfo;

public interface TrackingRepository extends JpaRepository<TrackingInfo,Long>{

}
