package com.example.coursetaskk.controller;

import com.example.coursetaskk.dto.request.CourseDtoRequest;
import com.example.coursetaskk.dto.response.CourseDtoResponse;
import com.example.coursetaskk.dto.response.ResponseModel;
import com.example.coursetaskk.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {


    private final CourseService courseService;

    @GetMapping("/list")
    public ResponseModel<List<CourseDtoResponse>> list(@RequestParam(value = "status", required = false) Long statusId){
        return courseService.list(statusId);
    }

    @SneakyThrows
    @PostMapping
    public ResponseModel<CourseDtoResponse> add(@RequestBody CourseDtoRequest courseDtoRequest){
        return courseService.add(courseDtoRequest);
    }

}
