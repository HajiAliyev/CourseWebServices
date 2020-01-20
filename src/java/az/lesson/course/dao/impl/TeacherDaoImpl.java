package az.lesson.course.dao.impl;

import az.lesson.course.dao.DBHelper;
import az.lesson.course.dao.TeacherDao;
import az.lesson.course.model.Teacher;
import az.lesson.course.util.JdbcUtility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {
    @Override
    public List<Teacher> getTeacherList() throws Exception {
        List<Teacher> teacherList = new ArrayList<Teacher>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ID,NAME,SURNAME,ADDRESS,DOB,PHONE,EMAIL FROM TEACHER WHERE ACTIVE = 1";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("ID"));
                    teacher.setName(rs.getString("NAME"));
                    teacher.setSurname(rs.getString("SURNAME"));
                    teacher.setAddress(rs.getString("ADDRESS"));
                    teacher.setDob(rs.getDate("DOB"));
                    teacher.setPhone(rs.getString("PHONE"));
                    teacher.setEmail(rs.getString("EMAIL"));
                    teacherList.add(teacher);
                }
            } else {
                System.out.println("Connection is null.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return teacherList;
    }

    @Override
    public boolean addTeacher(Teacher teacher) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO TEACHER(ID,NAME,SURNAME,ADDRESS,DOB,PHONE,EMAIL) " +
                " VALUES(TEACHER_SEQ.NEXTVAL,?,?,?,?,?,?)";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, teacher.getName());
                ps.setString(2, teacher.getSurname());
                ps.setString(3, teacher.getAddress());
                ps.setDate(4, new java.sql.Date(teacher.getDob().getTime()));
                ps.setString(5, teacher.getPhone());
                ps.setString(6, teacher.getEmail());
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null!");
            }


        } catch (Exception e) {
            result = true;
            e.printStackTrace();
        } finally {
            c.commit();
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public List<Teacher> getTeacherListByLessonId(Long lessonId) throws Exception {

        List<Teacher> teacherList = new ArrayList<Teacher>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT DISTINCT T.ID,T.NAME,T.SURNAME FROM SCHEDULE SC " +
                " INNER JOIN TEACHER T ON SC.T_ID = T.ID " +
                " INNER JOIN LESSON L ON SC.L_ID = L.ID " +
                " WHERE L.ID = ? ";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1,lessonId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("ID"));
                    teacher.setName(rs.getString("NAME"));
                    teacher.setSurname(rs.getString("SURNAME"));
                    teacherList.add(teacher);
                }
            } else {
                System.out.println("Connection is null.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return teacherList;
    }
}
