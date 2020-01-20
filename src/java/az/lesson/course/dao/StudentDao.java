package az.lesson.course.dao;

import az.lesson.course.model.Student;

import java.util.List;

public interface StudentDao {
    List<Student> getStudentList() throws Exception;

    boolean addStudent(Student student) throws Exception;

    boolean updateStudent(Student student, Long studentId) throws Exception;

    boolean deleteStudent(Long studentId) throws Exception;

    Student getStudentById(Long studentId) throws Exception;

    List<Student> searchStudent(String keyword) throws Exception;

}
