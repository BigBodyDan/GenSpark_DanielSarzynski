package service;

import dao.FileManager;
import model.Course;

import java.util.*;

public class CourseService {
    private HashMap<String, Course> courses;

    public CourseService(){
        this.courses = new HashMap<>();
        loadCourses();
    }

    private void loadCourses() { courses = FileManager.loadCourses(); }
    public void saveCourses() {  FileManager.saveCourses(courses); }

    public Course getCourse(String courseId) {return courses.get(courseId); }
    public int getCourseCount() { return courses.size(); }

    /**
     * @param course course to add
     * @return return true if the course was added successfully, false otherwise
     */
    public boolean addCourse(Course course) {
        if (courses.containsKey(course.getCourseId())) {
            return false; // Course already exists
        }
        courses.put(course.getCourseId(), course);
        saveCourses();
        return true;
    }

    /**
     * @param courseId ID of course to update
     * @return return true if the course was updated successfully, false otherwise
     */
    public boolean updateCourse(String courseId, Course updatedCourse) {
        if (!courses.containsKey(courseId)) {
            return false;
        }
        courses.put(courseId, updatedCourse);
        saveCourses();
        return true;
    }

    /**
     * @param courseId ID of course to delete
     * @return return true if the course was deleted successfully, false otherwise
     */
    public boolean deleteCourse(String courseId) {
        if (!courses.containsKey(courseId)) {
            return false;
        }
        courses.remove(courseId);
        saveCourses();
        return true;
    }

    public List<Course> getAllCourses() { return new ArrayList<>(courses.values()); }

}
