package com.example.coursetaskk.dto.response;

import com.example.coursetaskk.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDtoResponse {
    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private StatusDtoResponse statusDtoResponse;

    public CourseDtoResponse(Course course){
        this.id = course.getId();
        this.name = course.getName();
        this.description = course.getDescription();
        this.endDate = course.getEndDate();
        this.startDate = course.getStartDate();
        this.statusDtoResponse = new StatusDtoResponse(course.getStatus());
    }
}
