package az.lesson.course.webservice;

import az.lesson.course.request.ReqStudent;
import az.lesson.course.response.RespStatus;
import az.lesson.course.service.GeneralService;
import az.lesson.course.service.GeneralServiceImpl;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;




@WebService(serviceName = "SoapWebService")
public class SoapWebService {

    /**
     * tekce POST olur deye yazilmir
     */
    
    GeneralService generalService = new GeneralServiceImpl();
    
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "getStudentList")   
    public List<RespStudent> getStudentList() {
        return generalService.getStudentList();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getStudentById")
    public RespStudent getStudentById(@WebParam(name = "studentId") Long studentId) {
        return generalService.getStudentById(studentId);
    }
    
    @WebMethod(operationName = "addStudent")
    public RespStatus addStudent(@WebParam(name = "reqStudent") ReqStudent reqStudent) {
        return generalService.addStudent(reqStudent);
    }
    
    @WebMethod(operationName = "updateStudent")
    public RespStatus updateStudent(@WebParam(name = "reqStudent") ReqStudent reqStudent){
        return generalService.updateStudent(reqStudent);
    }
    
    @WebMethod(operationName = "deleteStudent")
    public RespStatus deleteStudent(@WebParam(name = "studentId") Long studentId){
        return generalService.deleteStudent(studentId);
    }
    

    /**
     * Web service operation
     */
    
}
