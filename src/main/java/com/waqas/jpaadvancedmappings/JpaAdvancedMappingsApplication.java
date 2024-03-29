package com.waqas.jpaadvancedmappings;

import com.waqas.jpaadvancedmappings.dao.InstructorDao;
import com.waqas.jpaadvancedmappings.entity.*;
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
//			deleteByFirstName(instructorDao);
//			findInstructorDetailsById(instructorDao);
//			deleteInstructorUsingCascadeInBidirectional(instructorDao);
//			deleteInstructorDetailsOnly(instructorDao);
//			createCourseOneToManyMapping(instructorDao);
//			createInstructorWithCourses(instructorDao);
//			findInstructorWithCoursesUsingEagerFetch(instructorDao);
//			findCoursesByInstructorIdWithoutJoinFetch(instructorDao);
//			findCoursesByInstructorIdUsingJoinFetch(instructorDao);
//			updateInstructor(instructorDao);
//			updateCourse(instructorDao);
//			deleteInstructorById(instructorDao);
//			deleteCourse(instructorDao);
//			saveCourseReviews(instructorDao);
//			findCourseAndCourseReviews(instructorDao);

			//ManyToMany mapping
//			createCourseWithStudent(instructorDao);
//			createStudentWithCourse(instructorDao);
//			findStudentWithCourse(instructorDao);
//			findCourseWithStudent(instructorDao);
//			deleteCourse(instructorDao);
			deleteStudentById(instructorDao);

		};
	}

	private void deleteStudentById(InstructorDao instructorDao) {
		int studentId = 6;

		System.out.println("Deleting student by id: " + studentId);
		instructorDao.deleteStudentById(studentId);
	}

	private void findCourseWithStudent(InstructorDao instructorDao) {
		int courseId = 13;

		System.out.println("Finding course along with student with course id: " + courseId);
		CourseEntity courseWithStudent = instructorDao.findCourseWithStudent(courseId);
		System.out.println("Fetched course with students list: " + courseWithStudent.getStudentsList());
	}

	private void findStudentWithCourse(InstructorDao instructorDao) {
		int studentId = 4;

		System.out.println("Finding student along with course with student id: " + studentId);
		StudentEntity studentWithCourse = instructorDao.findStudentWithCourse(studentId);
		System.out.println("Fetched student with courses list: " + studentWithCourse.getCoursesList());
	}

	private void createStudentWithCourse(InstructorDao instructorDao) {
		StudentEntity student1 = new StudentEntity("Shahzain", "Shahzain@gmail.com");
		StudentEntity student2 = new StudentEntity("Azlaan", "Azlaan@gmail.com");

		System.out.println("Creating new course");
		CourseEntity course = new CourseEntity("Developing projects end to end");

		student1.addCourse(course);
		student2.addCourse(course);

		System.out.println("Saving student : \n" + student1 + " with course :\n" + course);
		System.out.println("Saving student : \n" + student2 + " with course :\n" + course);
		instructorDao.saveStudentWithCourse(student1, student2);
	}

	private void createCourseWithStudent(InstructorDao instructorDao) {
		StudentEntity student1 = new StudentEntity("Shahzain", "Shahzain@gmail.com");
		StudentEntity student2 = new StudentEntity("Azlaan", "Azlaan@gmail.com");

		System.out.println("Saving course...");
		CourseEntity course = new CourseEntity("Developing projects end to end");

		course.addStudent(student1);
		course.addStudent(student2);


		System.out.println("Saving course : \n" + course + " with students");
		instructorDao.saveCourseWithStudent(course);
		System.out.println("Saved course !!");
	}

	private void findCourseAndCourseReviews(InstructorDao instructorDao) {
		int courseId = 3;

		System.out.println("Retrieving course with id : " + courseId);
		CourseEntity courseWithReviews = instructorDao.findCourseAndCourseReviews(courseId);
		System.out.println("Retrieved course : " + courseWithReviews);
	}

	private void saveCourseReviews(InstructorDao instructorDao) {
		CourseEntity newCourse = new CourseEntity("Learn Web Frontend");

		newCourse.add(new ReviewsEntity("Excellent Course !!"));
		newCourse.add(new ReviewsEntity("It was fine but not very thorough...."));
		newCourse.add(new ReviewsEntity("Gained some great knowledge"));

		System.out.println("Saving course : " + newCourse);
		instructorDao.saveCourseWithReview(newCourse);
	}

	private void deleteCourse(InstructorDao instructorDao) {
		int courseId = 13;

		System.out.println("Deleting course by id: " + courseId);
		instructorDao.deleteCourse(courseId);
	}

	private void updateCourse(InstructorDao instructorDao) {
		int courseId = 1;

		System.out.println("Finding course with id: " + courseId);

		CourseEntity course = instructorDao.findCourseById(courseId);

		System.out.println("Found the course: " + course);

		System.out.println("Updating course title ..");
		course.setTitle("Enjoy simple things");

		CourseEntity updatedCourse = instructorDao.updateCourse(course);
		System.out.println("Updated course: " + updatedCourse);
	}

	private void updateInstructor(InstructorDao instructorDao) {
		int instructorId = 1;

		System.out.println("Finding instructor with id: " + instructorId);
		InstructorEntity instructor = instructorDao.find(instructorId);

		if (instructor != null) {
			System.out.println("Found the instructor: \n" + instructor);
			instructor.setLastName("Ahmed");

			InstructorEntity updatedInstructor = instructorDao.updateInstructorUsingId(instructor);
			System.out.println("Updated instructor : " + updatedInstructor);
		} else {
			System.out.println("No instructor found with id : " + instructorId);
		}
	}

	private void findCoursesByInstructorIdUsingJoinFetch(InstructorDao instructorDao) {
		int instructorId = 1;

		InstructorEntity instructor = instructorDao
				.findCoursesByInstructorIdUsingJoinFetch(instructorId);
		System.out.println("Instructor: " + instructor);
		System.out.println("Courses: " + instructor.getCoursesList());
	}

	private void findCoursesByInstructorIdWithoutJoinFetch(InstructorDao instructorDao) {
		int instructorId = 1;
		List<CourseEntity> coursesList = instructorDao.findCoursesByInstructorId(instructorId);
		System.out.println("Courses: " + coursesList);
	}

	private void findInstructorWithCoursesUsingEagerFetch(InstructorDao instructorDao) {
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

	private void deleteInstructorById(InstructorDao instructorDao) {
		int id = 1;

		System.out.println("DELETING instructor with id: " + id);
		instructorDao.deleteInstructorById(id);
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
