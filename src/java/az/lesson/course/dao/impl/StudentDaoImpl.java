package az.lesson.course.dao.impl;

import az.lesson.course.dao.DBHelper;
import az.lesson.course.dao.StudentDao;
import javafx.scene.input.KeyCode;
import az.lesson.course.model.Student;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;
import az.lesson.course.util.JdbcUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> getStudentList() throws Exception {
        List<Student> studentList = new ArrayList<Student>();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{? = call DBLESSON1.MAIN_PACK.GET_STUDENT_LIST}";
//            String sql =" SELECT ID, NAME, SURNAME, ADDRESS, DOB  FROM STUDENT\n" +
//" WHERE ACTIVE = 1 "; 
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.registerOutParameter(1, OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getLong("ID"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setAddress(rs.getString("ADDRESS"));
                    student.setDob(rs.getDate("DOB"));
                    student.setPhone(rs.getString("PHONE"));
                    student.setEmail(rs.getString("EMAIL"));
                    studentList.add(student);
                }

            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, cs, rs);
        }
        return studentList;
    }

    @Override
    public boolean addStudent(Student student) throws Exception {
        boolean result = false;
        Connection c = null;
        CallableStatement cs = null;
        String sql = "{call DBLESSON1.MAIN_PACK.ADD_STUDENT(?,?,?,?,?,?)}";
        try {
            c = DBHelper.getConnection();

            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setString(1, student.getName());
                cs.setString(2, student.getSurname());
                cs.setString(3, student.getAddress());
                cs.setDate(4, new java.sql.Date(student.getDob().getTime()));
                cs.setString(5, student.getPhone());
                cs.setString(6, student.getEmail());
                cs.execute();
                result = true;
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            c.commit();
            JdbcUtility.close(c, cs, null);
        }
        return result;
    }

    @Override
    public Student getStudentById(Long studentId) throws Exception {
        Student student = new Student();
        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "{call DBLESSON1.GET_STUDENT_BY_ID_PROC(?,?)}";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                cs = c.prepareCall(sql);
                cs.setLong(1, studentId);
                cs.registerOutParameter(2, OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(2);
                if (rs.next()) {
                    student.setId(rs.getLong("ID"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setAddress(rs.getString("ADDRESS"));
                    student.setDob(rs.getDate("DOB"));
                    student.setPhone(rs.getString("PHONE"));
                    student.setEmail(rs.getString("EMAIL"));
                } else {
                    student = null;
                }

            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, cs, rs);
        }
        return student;
    }

    @Override
    public List<Student> searchStudent(String keyword) throws Exception {
        List<Student> studentList = new ArrayList<Student>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ID,NAME,SURNAME,ADDRESS,DOB,PHONE,EMAIL FROM STUDENT " +
                " WHERE ACTIVE = 1 AND (LOWER(NAME) LIKE LOWER('%" + keyword + "%') OR " +
                " LOWER(SURNAME) LIKE LOWER('%" + keyword + "%') OR LOWER(ADDRESS) LIKE LOWER('%" + keyword + "%'))";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getLong("ID"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setAddress(rs.getString("ADDRESS"));
                    student.setDob(rs.getDate("DOB"));
                    student.setPhone(rs.getString("PHONE"));
                    student.setEmail(rs.getString("EMAIL"));
                    studentList.add(student);
                }

            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return studentList;
    }

    @Override
    public boolean updateStudent(Student student, Long studentId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE  STUDENT SET NAME=? , SURNAME=?,ADDRESS=?,DOB=?,PHONE=?,EMAIL=? " +
                "WHERE ID = ?";
        try {
            c = DBHelper.getConnection();

            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, student.getName());
                ps.setString(2, student.getSurname());
                ps.setString(3, student.getAddress());
                ps.setDate(4, new java.sql.Date(student.getDob().getTime()));
                ps.setString(5, student.getPhone());
                ps.setString(6, student.getEmail());
                ps.setLong(7, studentId);
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            c.commit();
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public boolean deleteStudent(Long studentId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE STUDENT SET ACTIVE = 0 WHERE ID = ? ";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, studentId);
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            c.commit();
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }


}
