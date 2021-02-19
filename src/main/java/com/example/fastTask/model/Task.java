package com.example.fastTask.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class Task {
    private int id;
    private String title;
    private String description;
    private TaskStatus taskStatus;
    private Date createdDate;
    private Date deadline;
    private User user;
}
