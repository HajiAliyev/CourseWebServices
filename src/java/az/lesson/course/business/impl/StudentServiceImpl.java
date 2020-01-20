package az.lesson.course.business.impl;

import az.lesson.course.dao.StudentDao;
import az.lesson.course.dao.impl.StudentDaoImpl;
import az.lesson.course.business.StudentService;
import az.lesson.course.model.Student;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    StudentDao studentDao = new StudentDaoImpl();

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getStudentList() throws Exception {
        return studentDao.getStudentList();
    }

    @Override
    public boolean addStudent(Student student) throws Exception {
        return studentDao.addStudent(student);
    }

    @Override
    public Student getStudentById(Long studentId) throws Exception {
        return studentDao.getStudentById(studentId);
    }

    @Override
    public boolean updateStudent(Student student, Long studentId) throws Exception {
        return studentDao.updateStudent(student,studentId);
    }

    @Override
    public boolean deleteStudent(Long studentId) throws Exception {
        return studentDao.deleteStudent(studentId);
    }

    @Override
    public List<Student> searchStudent(String keyword) throws Exception {
        return studentDao.searchStudent(keyword);
    }

}

