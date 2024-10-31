package com.luv2code.cruddemo123.dao;

import com.luv2code.cruddemo123.entity.Course;
import com.luv2code.cruddemo123.entity.Instructor;
import com.luv2code.cruddemo123.entity.InstructorDetail;

import java.util.List;

public interface AppDao {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetail findInstructorDetailById(int theId);
    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    void update(Instructor instructor);
    void update(Course course);

    Course findCourseById(int theId);

    void deleteCourseById(int theId);

    void save(Course theCourse);

    Course findCourseAndReviewsByCourseId(int theId);



}
