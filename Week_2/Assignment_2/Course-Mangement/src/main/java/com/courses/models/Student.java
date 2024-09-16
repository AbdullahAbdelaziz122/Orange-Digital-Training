package com.courses.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "student")
@RequiredArgsConstructor
@Setter
@Getter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 1, message = "Student first name can't be empty")
    private String firstName;

    @Size(min = 1, message = "Student last name can't be empty")
    private String lastName;

    @Min(value = 1000, message = "Roll number must be a four digit number")
    @Column(unique = true)
    private int rollNo;

    @Size(min = 1, message = "Student email can't be empty")
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )

    private List<Course> courses;

}

