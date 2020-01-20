/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.lesson.course.service;

import az.lesson.course.business.StudentService;
import az.lesson.course.business.impl.StudentServiceImpl;
import az.lesson.course.dao.StudentDao;
import az.lesson.course.dao.impl.StudentDaoImpl;
import az.lesson.course.exception.Qrup36Exception;
import az.lesson.course.exception.Qrup36ExceptionConstant;
import az.lesson.course.model.Student;
import az.lesson.course.request.ReqStudent;
import az.lesson.course.response.RespStatus;
import az.lesson.course.webservice.RespStudent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 *
 * @author TOSHIBA
 */
public class GeneralServiceImpl implements GeneralService {

    StudentDao studentDao = new StudentDaoImpl();
    StudentService studentService = new StudentServiceImpl(studentDao);

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public List<RespStudent> getStudentList() {
        List<RespStudent> response = new ArrayList<RespStudent>();

        try {
            System.out.println("try  student : " + response);
            List<Student> studentList = studentService.getStudentList();
            for (Student student : studentList) {
                System.out.println("webservices student : " + student);
                RespStudent respStudent = new RespStudent();
                respStudent.setId(student.getId());
                respStudent.setName(student.getName());
                respStudent.setSurname(student.getSurname());
                respStudent.setAddress(student.getAddress());
                respStudent.setDob(df.format(student.getDob()));
                response.add(respStudent);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response; // hemise response-u try-dan cole cixardin :) 
    }

    public RespStudent getStudentById(Long studentId) {
        RespStudent response = new RespStudent();
        try {
            if (studentId == null) {
                throw new Qrup36Exception(Qrup36ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request Data"); //studentId burada request data- dir!  
            }
            Student student = studentService.getStudentById(studentId);
            if (student == null) {
                RespStatus status = new RespStatus(Qrup36ExceptionConstant.NO_RESULT_FOUND, "No Result Found");
                response.setStatus(status);
                return response;
            }
            response.setName(student.getName());
            response.setSurname(student.getSurname());
            response.setAddress(student.getAddress());
            response.setDob(df.format(student.getDob()));
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (Qrup36Exception ex1) {
            response.setStatus(new RespStatus(Qrup36ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request Data"));
            ex1.printStackTrace();
        } catch (Exception e) {
            response.setStatus(new RespStatus(Qrup36ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception"));
            e.printStackTrace();
        }
        return response;
    }

    public RespStatus addStudent(ReqStudent reqStudent) {
        RespStatus response = new RespStatus();
        try {
            if (reqStudent.getName() == null || reqStudent.getSurname() == null
                    || reqStudent.getDob() == null) {
                throw new Qrup36Exception(Qrup36ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request Data"); //studentId burada request data- dir!  
            }
            Student student = new Student();
            student.setName(reqStudent.getName());
            student.setSurname(reqStudent.getSurname());
            student.setAddress(reqStudent.getAddress());
            student.setDob(reqStudent.getDob());
            boolean isAdded = studentService.addStudent(student);
            if (isAdded) {
                response = RespStatus.getSuccessMessage();
            } else {
                response = new RespStatus(Qrup36ExceptionConstant.ADD_FAILED, "Add Student failed");
            }
        } catch (Qrup36Exception ex1) {
            response = new RespStatus(Qrup36ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request Data");
            ex1.printStackTrace();
        } catch (Exception e) {
            response = new RespStatus(Qrup36ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception");
            e.printStackTrace();
        }
        return response;
    }

    public RespStatus updateStudent(ReqStudent reqStudent) {
        RespStatus response = new RespStatus();
        try {
            if (reqStudent.getName() == null || reqStudent.getSurname() == null
                    || reqStudent.getDob() == null) {
                throw new Qrup36Exception(Qrup36ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request Data"); //studentId burada request data- dir!  
            }
            Student student = new Student();
            student.setName(reqStudent.getName());
            student.setSurname(reqStudent.getSurname());
            student.setAddress(reqStudent.getAddress());
            student.setDob(reqStudent.getDob());

            boolean isUpdated = studentService.updateStudent(student, reqStudent.getStudentId());
            if (isUpdated) {
                response = RespStatus.getSuccessMessage();
            } else {
                response = new RespStatus(Qrup36ExceptionConstant.UPDATE_FAILED, "Update Student failed");
            }

        } catch (Qrup36Exception ex1) {
            response = new RespStatus(Qrup36ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request Data");
            ex1.printStackTrace();
        } catch (Exception e) {
            response = new RespStatus(Qrup36ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception");
            e.printStackTrace();
        }
        return response;
    }

    public RespStatus deleteStudent(Long studentId) {
        System.out.println("Deleted studentId = " + studentId);
        RespStatus response = new RespStatus();
        try {
            if (studentId == null) {
                throw new Qrup36Exception(Qrup36ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request Data"); //studentId burada request data- dir!  
            }

            boolean isDeleted = studentService.deleteStudent(studentId);
            if (isDeleted) {
                response = RespStatus.getSuccessMessage();
            } else {
                response = new RespStatus(Qrup36ExceptionConstant.DELETE_FAILED, "Delete Student failed");
            }
        } catch (Qrup36Exception ex1) {
            response = new RespStatus(Qrup36ExceptionConstant.INVALID_REQUEST_DATA, "Invalid request Data");
            ex1.printStackTrace();
        } catch (Exception e) {
            response = new RespStatus(Qrup36ExceptionConstant.INTERNAL_EXCEPTION, "Internal Exception");
            e.printStackTrace();
        }
        return response;
    }

    
    
}
