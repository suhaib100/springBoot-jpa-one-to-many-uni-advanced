package com.luv2code.cruddemo123.dao;

import com.luv2code.cruddemo123.entity.Course;
import com.luv2code.cruddemo123.entity.Instructor;
import com.luv2code.cruddemo123.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDao{

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager){
this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class,theId);

    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor tempInstructor = entityManager.find(Instructor.class,theId);

        List<Course> courses = tempInstructor.getCourses();
        for (Course tempCourse: courses){
            tempCourse.setInstructor(null);
        }

        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail temp = entityManager.find(InstructorDetail.class,theId);
        temp.getInstructor().setInstructorDetail(null);
        entityManager.remove(temp);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data", theId);
        List<Course> courses = query.getResultList();
        return courses;



    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i "+
                "JOIN FETCH i.courses "+"JOIN FETCH i.instructorDetail "+
                "where i.id = :data",Instructor.class);

        query.setParameter("data",theId);
        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class,theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course tempCourse = entityManager.find(Course.class,theId);
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    @Transactional
    public Course findCourseAndReviewsByCourseId(int theId) {
        TypedQuery<Course>  query = entityManager.createQuery("select c from Course c "+
                "JOIN FETCH c.reviews "+
                "where c.id = :data ", Course.class);

        query.setParameter("data",theId);
        Course course = query.getSingleResult();
        return course;
    }
}
