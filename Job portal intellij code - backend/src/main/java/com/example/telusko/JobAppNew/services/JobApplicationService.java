package com.example.telusko.JobAppNew.services;

import com.example.telusko.JobAppNew.model.JobApplication;
import com.example.telusko.JobAppNew.repo.JobApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepo repo;

    // Use absolute path - change this to where you want files stored
    private final String uploadDir = System.getProperty("user.dir") + File.separator + "uploads";

    public JobApplication apply(JobApplication application, MultipartFile resume) throws IOException {
        if (resume != null && !resume.isEmpty()) {
            // Create folder if it doesn't exist
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            // Create unique filename
            String fileName = System.currentTimeMillis() + "_" + resume.getOriginalFilename();
            String filePath = Paths.get(uploadDir, fileName).toString();

            // Save file to disk
            resume.transferTo(new File(filePath));

            // Save file path to DB
            application.setResumePath(filePath);
        }
        return repo.save(application);
    }

    public List<JobApplication> getApplicationsForJob(int postId) {

        return repo.findByPostId(postId);
    }
    public JobApplication getApplicationById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<JobApplication> getApplicationsForAdmin(String username, int postId) {
        return repo.findByPostId(postId).stream()
                .filter(app -> app.getJobPost() != null &&
                        username.equals(app.getJobPost().getCreatedBy()))
                .toList();
    }



}
