
package az.lesson.course.webservice;


import az.lesson.course.response.RespStatus;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


//menim class-imin xml ve ya json formatinda melumat qaytarmasi ucun 2 annotasiya var: 
//1. @XmlRootElement
//2. @XmlAccessorType(XmlAccessType.FIELD) 
//Bu RootElement xml-de mes: yaratdigimiz class adi RespStudent default olaraq basliq teq-i olur.
//Yox ferqli istesek @XmlRootElement(name = "student") vere bilerik 

//Field(yeni ki property) -lere ad verm ek ucun ise     @XmlElement(name = "no") annotasiyasi isledilir. Bu teq-lerin adini -  XML-de yerlesen elementlerin adlarini deyisir.

//XmlElement-den elave XmlAttribute da var: 
 // 1) <student age = "20">   // burada "age" atribut olur
//2) <student>
//      <age>20</age>         //burada "<age>" sub-element olur
//   </student>


@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class RespStudent {
    
//    @XmlElement(name = "no")
    private Long id;
    
//    @XmlElement(name = "ad") // client-e gorunen "ad" olur
    private String name; // set edeceyim "name" olur
    
//    @XmlElement(name = "soyad") // client-e gorunen "ad" olur
    private String surname;
   
//    @XmlElement(name = "unvan") 
    private String address;
    
//    private Date dob;
    private String dob;
    
    private RespStatus status; 
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public RespStatus getStatus() {
        return status;
    }

    public void setStatus(RespStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RespStudent{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", address=" + address + ", dob=" + dob + ", status=" + status + '}';
    }
    
    

    
    
    
}

