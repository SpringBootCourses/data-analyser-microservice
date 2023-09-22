package com.example.dataanalysermicroservice.repository;

import com.example.dataanalysermicroservice.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data, Long> {
}
