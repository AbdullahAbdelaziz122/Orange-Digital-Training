package com.taskMangement.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {


    private Long id;

    private String title;

    private String description;

    private LocalDate dueDate;

    private String status;
}
