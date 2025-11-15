package com.project.restAPI.JobModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/myApplications")
public class ApplicationController {
    @Autowired
    private AppRepository jobApplicationRepository;

    // --- SUBMIT JOB APPLICATION (POST) ---

    @PostMapping
    public ResponseEntity<JobApplication> createApplication(@RequestBody JobApplication newApplication){

        if (newApplication.getStatus() == null || newApplication.getStatus().isEmpty()) {
            newApplication.setStatus("Submitted");
        }

        JobApplication savedApplication = jobApplicationRepository.save(newApplication);

        return new ResponseEntity<>(newApplication, HttpStatus.CREATED);
    }

    // --- SEE ALL JOB APPLICATIONS (GET) ---

    @GetMapping
    public List<JobApplication> getApplications(){
        return jobApplicationRepository.findAll();
    }

    // --- SEE SPECIFIC JOB APPLICATION (GET) ---
    @GetMapping ("/{id}")
    public ResponseEntity<JobApplication> getApplicationById(@PathVariable long id) {
        Optional<JobApplication> application = jobApplicationRepository.findById(id);

        return application.map(app -> new ResponseEntity<>(app, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

        // --- UPDATE THE JOB APPLICATION (PUT) ---

    @PutMapping ("/{id}")
    public ResponseEntity<JobApplication> updateApplication(@PathVariable Long id, @RequestBody JobApplication updatedApplication){

        if (!jobApplicationRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updatedApplication.setId(id);

        JobApplication result = jobApplicationRepository.save(updatedApplication);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // --- DELETE JOB APPLICATION (DELETE) ---

    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable Long id){

        if (!jobApplicationRepository.existsById(id)) {
            return new ResponseEntity<>("Application ID: " + id + " not found", HttpStatus.NOT_FOUND);
        }

        jobApplicationRepository.deleteById(id);

        return new ResponseEntity<>("Deletion successful for ID: " + id, HttpStatus.OK);
    }



}
