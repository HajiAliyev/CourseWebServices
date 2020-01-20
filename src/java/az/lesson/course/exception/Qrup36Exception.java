/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.lesson.course.exception;

/**
 *
 * @author TOSHIBA
 */
public class Qrup36Exception extends RuntimeException {
    public Qrup36Exception(){
        super();
    }
    
    public Qrup36Exception(Integer code, String message){
        super(message);
    }
}
