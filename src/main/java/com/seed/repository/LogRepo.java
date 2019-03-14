package com.seed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seed.entity.Log;

public interface LogRepo extends JpaRepository<Log, Long> {

}
