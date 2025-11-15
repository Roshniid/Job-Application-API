package com.project.restAPI.JobModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * This interface is the Data Access Layer. By extending JpaRepository,
 * Spring automatically provides implementation for all basic CRUD methods:
 * - save() -> (for POST and PUT)
 * - findAll() -> (for GET All)
 * - findById(id) -> (for GET Single)
 * - deleteById(id) -> (for DELETE)
 * * JobApplication is the Entity type, and Long is the type of its ID (primary key).
 */
@Repository
public interface AppRepository extends JpaRepository<JobApplication, Long> {

    // We don't need to add any methods here for basic CRUD!

}