package com.waqas.jpaadvancedmappings.dao;

import com.waqas.jpaadvancedmappings.entity.CourseEntity;
import com.waqas.jpaadvancedmappings.entity.InstructorDetailsEntity;
import com.waqas.jpaadvancedmappings.entity.InstructorEntity;

import java.util.List;

public interface InstructorDao {
    void save(InstructorEntity instructorEntity);
    InstructorEntity find(Integer id);
    InstructorEntity updateInstructor(InstructorEntity instructorEntity);
    void deleteInstructorById(Integer id);
    void deleteByFirstName(String firstName);
    InstructorDetailsEntity findInstructorDetailsById(int id);
    void deleteInstructorUsingCascadeInBidirectional(int id);
    void deleteInstructorDetailsOnly(int id);

    InstructorEntity findInstructorById(int id);

    List<CourseEntity> findCoursesByInstructorId(int instructorId);

    InstructorEntity findCoursesByInstructorIdUsingJoinFetch(int instructorId);

    InstructorEntity updateInstructorUsingId(InstructorEntity instructor);

    CourseEntity findCourseById(int courseId);

    CourseEntity updateCourse(CourseEntity course);

    void deleteCourse(int courseId);
}
