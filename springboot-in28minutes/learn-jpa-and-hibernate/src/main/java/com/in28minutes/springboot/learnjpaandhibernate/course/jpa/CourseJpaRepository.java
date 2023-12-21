package com.in28minutes.springboot.learnjpaandhibernate.course.jpa;

import com.in28minutes.springboot.learnjpaandhibernate.course.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CourseJpaRepository {

    // @PersistenceContext
    private EntityManager entityManager;

    public CourseJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Course insert(Course course){
        return entityManager.merge(course);
    }

    public Course update(Course course){
        return entityManager.merge(course);
    }

    public void deleteById(long id) {
        entityManager.remove(findById(id));
    }


    public Course findById(long id) {
        return entityManager.find(Course.class, id);
    }

    public List<Course> findAllCourse() {
        TypedQuery<Course> allCourses = entityManager.
                createNamedQuery("find_all_courses", Course.class);
        return allCourses.getResultList();
    }

    public List<Course> findAllCourseName() {
        TypedQuery<Course> allCourses = entityManager.
                createNamedQuery("find_name_courses", Course.class);
        return allCourses.getResultList();
    }
}
