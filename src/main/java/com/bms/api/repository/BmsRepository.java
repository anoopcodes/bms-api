package com.bms.api.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bms.api.model.BmsData;

public interface BmsRepository extends JpaRepository<BmsData, LocalDate> {
	
	Page<BmsData> findAll(Pageable pageable);

}
