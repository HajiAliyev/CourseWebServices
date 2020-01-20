package az.lesson.course.webservice;

import az.lesson.course.dao.StudentDao;
import az.lesson.course.dao.impl.StudentDaoImpl;
import az.lesson.course.request.ReqStudent;
import az.lesson.course.response.RespStatus;
import az.lesson.course.business.StudentService;
import az.lesson.course.business.impl.StudentServiceImpl;
import az.lesson.course.service.GeneralService;
import az.lesson.course.service.GeneralServiceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 *  
 */
@Path("/webServices")
public class WebServices {
 
    GeneralService generalService = new GeneralServiceImpl();

    
    StudentDao studentDao = new StudentDaoImpl();
    StudentService studentService = new StudentServiceImpl(studentDao);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    
    @GET
    @Path("/hello")
    @Produces("text/html")
    public String hello() {
        return "<html><body><h1>Hello Qrup36!</h1></body></html>";
    }

    @GET
    @Path("/getStudentList")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<RespStudent> getStudentList() {
        return generalService.getStudentList(); 

    }
    
    @GET 
    @Path("/getStudentById")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RespStudent getStudentById(@QueryParam("studentId") Long studentId){
//    public RespStudent getStudentById(@PathParam("studentId") Long studentId){
        return generalService.getStudentById(studentId);
    }
    
    @POST
    @Path("/addStudent")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_XML)
    public RespStatus addStudent(ReqStudent reqStudent) {   
        return generalService.addStudent(reqStudent);
    }
    
    @PUT
    @Path("/updateStudent")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_XML)
    public RespStatus updateStudent (ReqStudent reqStudent) {   
        return generalService.updateStudent(reqStudent);
    }
    
    
    @PUT
    @Path("/deleteStudent/{studentId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(MediaType.APPLICATION_XML)
    public RespStatus deleteStudent (@PathParam("studentId") Long studentId) {  
        return generalService.deleteStudent(studentId);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    

//    @GET
//    @Path("/getStudent/{studentId}")
//    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
//    public RespStudent getStudent(@PathParam("studentId") Long studentId) {
//        RespStudent response = new RespStudent();
//        response.setId(studentId);
//        response.setName("Haci");
//        response.setSurname("Aliyev");
//        response.setAddress("Baku");
//        return response;
//    }
}
