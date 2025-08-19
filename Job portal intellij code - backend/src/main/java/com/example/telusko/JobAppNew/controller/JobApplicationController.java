package com.example.telusko.JobAppNew.controller;

import com.example.telusko.JobAppNew.model.JobApplication;
import com.example.telusko.JobAppNew.services.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JobApplicationController {

    @Autowired
    private JobApplicationService service;

    @PostMapping("/apply")
    public JobApplication applyForJob(
            @RequestParam int postId,
            @RequestParam String applicantName,
            @RequestParam String applicantEmail,
            @RequestParam(required = false) String coverLetter,
            @RequestParam("resume") MultipartFile resume
    ) throws IOException {
        JobApplication application = new JobApplication();
        application.setPostId(postId);
        application.setApplicantName(applicantName);
        application.setApplicantEmail(applicantEmail);
        application.setCoverLetter(coverLetter);
        return service.apply(application, resume);
    }

    @GetMapping("/applications/{postId}")
    public List<JobApplication> getApplications(@PathVariable int postId) {
        return service.getApplicationsForJob(postId);
    }

    @GetMapping("/admin/applications/{username}/{postId}")


    public List<JobApplication> getApplicationsForAdmin(
            @PathVariable String username,
            @PathVariable int postId) {
        return service.getApplicationsForAdmin(username, postId);
    }

    @GetMapping("/resume/{id}")
    public ResponseEntity<org.springframework.core.io.Resource> downloadResume(@PathVariable Long id) throws IOException {
        JobApplication app = service.getApplicationById(id);
        if (app == null || app.getResumePath() == null) {
            return ResponseEntity.notFound().build();
        }
        org.springframework.core.io.Resource file = new UrlResource(Paths.get(app.getResumePath()).toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }



}
