package com.example.telusko.JobAppNew.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_apply")
@Entity
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int postId;
    private String applicantName;
    private String applicantEmail;
    private String resumePath; // store file path on server
    @Column(length = 2000)
    private String coverLetter;

    @ManyToOne
    @JoinColumn(name = "postId", referencedColumnName = "postId", insertable = false, updatable = false)
    private JobPost jobPost;

}