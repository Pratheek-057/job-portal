package com.example.telusko.JobAppNew.controller;

import com.example.telusko.JobAppNew.model.JobPost;
import com.example.telusko.JobAppNew.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Jobrestcontroller {

    @Autowired
    private JobService service;

    @GetMapping("jobPosts")
    @ResponseBody
    public List<JobPost> alljobs(){
        return service.getAllJobs();
    }

    @GetMapping("jobPosts/keyword/{keyword}")
    public List<JobPost> searchbyname(@PathVariable("keyword") String keyword){
      return service.search(keyword);
    }

    @GetMapping("/jobPost/{postId}")
    public JobPost getJob(@PathVariable("postId") int postId) {
        return service.getJob(postId);
    }

    @PostMapping("jobPost")
    public JobPost addJob(@RequestBody JobPost jobPost) {
        service.addJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }
    @PutMapping("jobPost")
    public JobPost updateJob(@RequestBody JobPost jobPost) {
        service.updateJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }


    @DeleteMapping("jobPost/{postId}")
    public String deleteJob(@PathVariable int postId)
    {
        service.deleteJob(postId);
        return "Deleted";
    }

    @GetMapping("load")
    public String loadData() {
        //service.load();
        return "success";
    }

    @GetMapping("jobPosts/admin/{username}")
    public List<JobPost> getJobsByAdmin(@PathVariable String username) {
        return service.getJobsByAdmin(username);
    }
}
