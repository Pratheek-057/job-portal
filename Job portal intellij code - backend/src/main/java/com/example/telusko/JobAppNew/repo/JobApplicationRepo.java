package com.example.telusko.JobAppNew.repo;

import com.example.telusko.JobAppNew.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobApplicationRepo extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByPostId(int postId);
}
