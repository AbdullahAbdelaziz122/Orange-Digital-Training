package com.courses.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "course")
@Getter
@Setter
@RequiredArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Course name cannot be null")
    @Size(min = 2, max = 100, message = "Course name must be between 2 and 100 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Description cannot be null")
    @Size(min = 10, message = "Description must be at least 10 characters")
    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private List<Student> students;
}