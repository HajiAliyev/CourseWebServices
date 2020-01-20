package az.lesson.course.business;

import az.lesson.course.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> getStudentList() throws Exception;

    boolean addStudent(Student student) throws Exception;

    Student getStudentById(Long studentId) throws Exception;

    boolean updateStudent(Student student,Long studentId) throws Exception;

    boolean deleteStudent(Long studentId) throws  Exception;

    List<Student> searchStudent(String keyword) throws Exception;
}
