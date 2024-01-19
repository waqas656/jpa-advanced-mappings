package com.waqas.jpaadvancedmappings.dao;

import com.waqas.jpaadvancedmappings.entity.InstructorDetailsEntity;
import com.waqas.jpaadvancedmappings.entity.InstructorEntity;

public interface InstructorDao {
    void save(InstructorEntity instructorEntity);
    InstructorEntity find(Integer id);
    InstructorEntity updateInstructor(InstructorEntity instructorEntity);
    void deleteById(Integer id);
    void deleteByFirstName(String firstName);
    InstructorDetailsEntity findInstructorDetailsById(int id);
    void deleteInstructorUsingCascadeInBidirectional(int id);
    void deleteInstructorDetailsOnly(int id);

    InstructorEntity findInstructorById(int id);
}
