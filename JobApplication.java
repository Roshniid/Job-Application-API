package com.project.restAPI.JobModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class JobApplication {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String applicantName;
        private String position;
        private String status;

        public JobApplication(long id, String applicantName, String position, String status) {
            this.id = id;
            this.applicantName = applicantName;
            this.position = position;
            this.status = status;
        }
        public JobApplication() {}

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getApplicantName() {
            return applicantName;
        }

        public void setApplicantName(String applicantName) {
            this.applicantName = applicantName;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }


        //debugging puporses...
        @Override
        public String toString() {
            return "JobApplication [id=" + id + ", applicantName=" + applicantName + ", position=" + position + ", status="
                    + status + "]";
        }
    }

