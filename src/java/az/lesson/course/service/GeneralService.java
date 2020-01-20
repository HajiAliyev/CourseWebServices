/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.lesson.course.service;

import az.lesson.course.request.ReqStudent;
import az.lesson.course.response.RespStatus;
import az.lesson.course.webservice.RespStudent;
import java.util.List;
import javax.ws.rs.PathParam;

/**
 *
 * @author TOSHIBA
 */
public interface GeneralService {
    
    List<RespStudent> getStudentList();
    
    RespStudent getStudentById(Long studentId);
    
    RespStatus addStudent(ReqStudent reqStudent);
    
    RespStatus updateStudent (ReqStudent reqStudent);
    
    RespStatus deleteStudent (@PathParam("studentId") Long studentId);
}
