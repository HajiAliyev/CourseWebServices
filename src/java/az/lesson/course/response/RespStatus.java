/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.lesson.course.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TOSHIBA
 */

@XmlRootElement 
@XmlAccessorType(XmlAccessType.FIELD)
public class RespStatus {
    private Integer statusCode;
    private String statusMessage;
    
    private static final Integer SUCCESS_CODE = 1;
    private static final String SUCCESS_MESSAGE = "SUCCESS";

    public RespStatus() {
    }
       
    public RespStatus(Integer statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public static RespStatus getSuccessMessage(){
        return new RespStatus(SUCCESS_CODE,SUCCESS_MESSAGE);
    }
    
    
    
    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Override
    public String toString() {
        return "RespStatus{" + "statusCode=" + statusCode + ", statusMessage=" + statusMessage + '}';
    }
    
    
}
