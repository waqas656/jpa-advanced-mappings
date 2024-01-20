package com.waqas.jpaadvancedmappings.dao.impl;

import com.waqas.jpaadvancedmappings.dao.InstructorDao;
import com.waqas.jpaadvancedmappings.entity.CourseEntity;
import com.waqas.jpaadvancedmappings.entity.InstructorDetailsEntity;
import com.waqas.jpaadvancedmappings.entity.InstructorEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class InstructorDaoImpl implements InstructorDao {

    private final EntityManager entityManager;

    public InstructorDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(InstructorEntity instructorEntity) {
        entityManager.persist(instructorEntity);
    }

    @Override
    public InstructorEntity find(Integer id) {
        return entityManager.find(InstructorEntity.class, id);
    }

    @Override
    @Transactional
    public InstructorEntity updateInstructor(InstructorEntity instructorEntity) {
        return entityManager.merge(instructorEntity);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        InstructorEntity instructor = entityManager.find(InstructorEntity.class, id);
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteByFirstName(String firstName) {
        TypedQuery<InstructorEntity> query = entityManager
                .createQuery("FROM InstructorEntity WHERE firstName = ?1",
                        InstructorEntity.class)
                .setParameter(1, firstName);
//                .setParameter("firstName", firstName);

        List<InstructorEntity> instructorsList = query.getResultList();

        for (InstructorEntity instructor : instructorsList) {
            entityManager.remove(instructor);
        }
    }

    @Override //Demo of Bidirectional mapping
    public InstructorDetailsEntity findInstructorDetailsById(int id) {
        InstructorDetailsEntity instructorDetails = entityManager.find(InstructorDetailsEntity.class, id);
        return instructorDetails;
    }

    @Override
    @Transactional
    public void deleteInstructorUsingCascadeInBidirectional(int id) {
        InstructorDetailsEntity instructorDetails = entityManager.find(InstructorDetailsEntity.class, id);
        entityManager.remove(instructorDetails);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailsOnly(int id) {
        InstructorDetailsEntity instructorDetails = entityManager.find(InstructorDetailsEntity.class, id);

        //breaking bidirectional link from 'instructor' entity
        instructorDetails.getInstructor().setInstructorDetails(null);

        entityManager.remove(instructorDetails);
    }

    @Override
    public InstructorEntity findInstructorById(int id) {
        return entityManager.find(InstructorEntity.class, id);
    }

    @Override
    public List<CourseEntity> findCoursesByInstructorId(int instructorId) {
        TypedQuery<CourseEntity> query = entityManager
                .createQuery("FROM CourseEntity WHERE instructor.id = ?1", CourseEntity.class);
        query.setParameter(1, instructorId);

        List<CourseEntity> coursesList = query.getResultList();

        return coursesList;
    }

    @Override
    public InstructorEntity findCoursesByInstructorIdUsingJoinFetch(int instructorId) {
        TypedQuery<InstructorEntity> query = entityManager
                .createQuery("FROM InstructorEntity i " +
                        "JOIN FETCH i.coursesList " +
                        "JOIN FETCH i.instructorDetails " +
                        "WHERE i.id = :id", InstructorEntity.class);
        query.setParameter("id", instructorId);

        InstructorEntity instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public InstructorEntity updateInstructorUsingId(InstructorEntity instructor) {
        return entityManager.merge(instructor);
    }

    @Override
    public CourseEntity findCourseById(int courseId) {
        return entityManager.find(CourseEntity.class, courseId);
    }

    @Override
    @Transactional
    public CourseEntity updateCourse(CourseEntity course) {
        return entityManager.merge(course);
    }
}
