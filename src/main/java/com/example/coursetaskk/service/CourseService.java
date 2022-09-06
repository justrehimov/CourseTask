package com.example.coursetaskk.service;

import com.example.coursetaskk.dto.request.CourseDtoRequest;
import com.example.coursetaskk.dto.response.CourseDtoResponse;
import com.example.coursetaskk.dto.response.ResponseModel;
import com.example.coursetaskk.dto.response.StatusDtoResponse;
import com.example.coursetaskk.entity.Course;
import com.example.coursetaskk.entity.Status;
import com.example.coursetaskk.repository.CourseRepository;
import com.example.coursetaskk.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final StatusRepository statusRepository;

    public ResponseModel<List<CourseDtoResponse>> list(Long statusId) {
        try{
            List<Course> courseList;
            List<CourseDtoResponse> courseDtoResponseList = new ArrayList<>();
            if(statusId!=null){
                courseList = courseRepository.findByStatusId(statusId);
            }else{
                courseList = courseRepository.findAll();
            }

            for(Course course: courseList){
                CourseDtoResponse courseDtoResponse = new CourseDtoResponse(course);
                courseDtoResponseList.add(courseDtoResponse);
            }

            ResponseModel<List<CourseDtoResponse>> responseModel = new ResponseModel<>();
            responseModel.setData(courseDtoResponseList);
            responseModel.setError(false);
            responseModel.setMessage("Success");
            responseModel.setCode(200);

            return responseModel;

        }catch (RuntimeException ex){

            ResponseModel<List<CourseDtoResponse>> responseModel = new ResponseModel<>();
            responseModel.setData(new ArrayList<>());
            responseModel.setError(true);
            responseModel.setMessage(ex.getMessage());
            responseModel.setCode(400);

            return responseModel;
        }
    }

    public ResponseModel<CourseDtoResponse> add(CourseDtoRequest courseDtoRequest) throws ParseException{
        try{
            Course course = new Course();
            course.setName(courseDtoRequest.getName());
            course.setDescription(courseDtoRequest.getDescription());
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            Date endDate = df.parse(courseDtoRequest.getEndDate());
            Date startDate = df.parse(courseDtoRequest.getStartDate());
            course.setEndDate(endDate);
            course.setStartDate(startDate);
            Status status = getById(courseDtoRequest.getStatusId());
            course.setStatus(status);
            Course savedCourse = courseRepository.save(course);

            CourseDtoResponse courseDtoResponse = new CourseDtoResponse(savedCourse);

            ResponseModel<CourseDtoResponse> responseModel = new ResponseModel<>();
            responseModel.setData(courseDtoResponse);
            responseModel.setError(false);
            responseModel.setCode(200);
            responseModel.setMessage("Success");
            return responseModel;

        }catch (RuntimeException ex){
            ResponseModel<CourseDtoResponse> responseModel = new ResponseModel<>();
            responseModel.setError(true);
            responseModel.setCode(400);
            responseModel.setMessage(ex.getMessage());

            return responseModel;
        }
    }

    private Status getById(Long statusId){
        Optional<Status> optionalStatus = statusRepository.findById(statusId);
        if(optionalStatus.isPresent()){
            return optionalStatus.get();
        }else{
            throw new RuntimeException("Status not found");
        }
    }

}
