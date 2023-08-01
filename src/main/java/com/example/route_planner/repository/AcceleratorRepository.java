package com.example.route_planner.repository;

import com.example.route_planner.models.Accelerator;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableScan
public interface AcceleratorRepository extends CrudRepository<Accelerator, String> {
}