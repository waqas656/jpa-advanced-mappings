package com.waqas.jpaadvancedmappings;

import com.waqas.jpaadvancedmappings.dao.InstructorDao;
import com.waqas.jpaadvancedmappings.entity.CourseEntity;
import com.waqas.jpaadvancedmappings.entity.InstructorDetailsEntity;
import com.waqas.jpaadvancedmappings.entity.InstructorEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JpaAdvancedMappingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaAdvancedMappingsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(InstructorDao instructorDao) {
		return runner -> {
//			createInstructor(instructorDao);
//			findInstructor(instructorDao);
//			deleteById(instructorDao);
//			deleteByFirstName(instructorDao);
//			findInstructorDetailsById(instructorDao);
//			deleteInstructorUsingCascadeInBidirectional(instructorDao);
//			deleteInstructorDetailsOnly(instructorDao);
//			createCourseOneToManyMapping(instructorDao);
//			createInstructorWithCourses(instructorDao);
//			findInstructorWithCourses(instructorDao);
			findCoursesByInstructorId(instructorDao);
		};
	}

	private void findCoursesByInstructorId(InstructorDao instructorDao) {
		int instructorId = 1;
		List<CourseEntity> coursesList = instructorDao.findCoursesByInstructorId(instructorId);
		System.out.println("Courses: " + coursesList);
	}

	private void findInstructorWithCourses(InstructorDao instructorDao) {
		int id = 1;
		InstructorEntity instructor = instructorDao.findInstructorById(id);
		System.out.println("Instructor email : " + instructor.getEmail());
		System.out.println("Instructor courses : " + instructor.getCoursesList());
	}

	private void createCourseOneToManyMapping(InstructorDao instructorDao) {
		int instructorId = 1;

		System.out.println("Finding instructor with id: " + instructorId);
		InstructorEntity instructor = instructorDao.find(instructorId);

		CourseEntity course1 = createCourse("JPA and Hibernate");
		CourseEntity course2 = createCourse("Microservice, Docker and Kubernetes");

		List<CourseEntity> courseEntityList = new ArrayList<>();
		courseEntityList.add(course1);
		courseEntityList.add(course2);

		for (CourseEntity course : courseEntityList) {
			//this method is adding bi-directional link between both course & instructor entity
			instructor.addCourse(course);
		}

		System.out.println("Updating instructor: Adding courses to the instructor");
		InstructorEntity updateInstructor = instructorDao.updateInstructor(instructor);

		System.out.println("Updated instructor: " + updateInstructor);
	}

	private void createInstructorWithCourses(InstructorDao instructorDao) {
		//first Instructor
		InstructorDetailsEntity instructorDetails = createInstructorDetails();
		InstructorEntity instructor = new InstructorEntity("Azlaan", "Shah",
				"azlaan@gmail.com");
		instructor.setInstructorDetails(instructorDetails);

		CourseEntity course1 = new CourseEntity("How to cry");
		CourseEntity course2 = new CourseEntity("How to be cute");

		//adding courses to instructor and forming bi-directional relationship between both
		instructor.addCourse(course1);
		instructor.addCourse(course2);

		System.out.println("Saving instructor with courses");
		//saving instructor
		instructorDao.save(instructor);

	}

	private void deleteInstructorDetailsOnly(InstructorDao instructorDao) {
		int id = 2;
		System.out.println("Deleting instructor only");
		instructorDao.deleteInstructorDetailsOnly(id);
	}

	private void deleteInstructorUsingCascadeInBidirectional(InstructorDao instructorDao) {
		int id = 1;

		System.out.println("Deleting instructor details along with instructor");
		instructorDao.deleteInstructorUsingCascadeInBidirectional(id);
	}

	private void findInstructorDetailsById(InstructorDao instructorDao) {
		int id  = 1;

		System.out.println("Finding instructor details by ID : " + id);

		InstructorDetailsEntity instructorDetails = instructorDao.findInstructorDetailsById(id);

		System.out.println("Found instructor details: " + instructorDetails);
		System.out.println("Found instructor: " + instructorDetails.getInstructor());

	}

	private void deleteById(InstructorDao instructorDao) {
		int id = 2;

		System.out.println("DELETING instructor with id: " + id);
		instructorDao.deleteById(id);
	}

	private void deleteByFirstName(InstructorDao instructorDao) {
		String firstName = "Waqas";

		System.out.println("DELETING instructor with first name: " + firstName);
		instructorDao.deleteByFirstName(firstName);
	}

	private void findInstructor(InstructorDao instructorDao) {
		int instructorId = 1;

		System.out.println("Finding instructor with id: " + instructorId);
		InstructorEntity instructor = instructorDao.find(instructorId);

		if (instructor != null) {
			System.out.println("Found the instructor: \n" + instructor);
		} else {
			System.out.println("No instructor found with id : " + instructorId);
		}
	}

	private void createInstructor(InstructorDao instructorDao) {
		//first Instructor
		InstructorDetailsEntity instructorDetails = createInstructorDetails();
		InstructorEntity instructor = new InstructorEntity("Waqas", "Ahmed",
				"waqasmlik565@gmail.com");
		instructor.setInstructorDetails(instructorDetails);

		System.out.println("Saving Instructor");
		instructorDao.save(instructor);


		//Second Instructor
		InstructorDetailsEntity instructorDetails2 = createInstructorDetails();
		InstructorEntity instructor2 = new InstructorEntity("Shahzain", "Khan",
				"shahzain@gmail.com");
		instructor2.setInstructorDetails(instructorDetails2);

		System.out.println("Saving Instructor 2");
		instructorDao.save(instructor2);
	}

	private InstructorDetailsEntity createInstructorDetails() {
		return new InstructorDetailsEntity("ShahzainGames",
				"www.youtube.com/shahzain",
				"watching cartoons");
	}

	private CourseEntity createCourse(String courseTitle) {
		return new CourseEntity(courseTitle);
	}

}
