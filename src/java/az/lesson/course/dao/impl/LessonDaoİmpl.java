package az.lesson.course.dao.impl;

import az.lesson.course.dao.DBHelper;
import az.lesson.course.dao.LessonDao;
import az.lesson.course.model.Lesson;
import az.lesson.course.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LessonDaoİmpl implements LessonDao {


    @Override
    public List<Lesson> getLessonList() throws Exception {
        List<Lesson> lessonList = new ArrayList<Lesson>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ID,LESSON_NAME,LESSON_TIME,LESSON_PRICE FROM LESSON  WHERE ACTIVE = 1";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("ID"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    lesson.setLessonPrice(rs.getDouble("LESSON_PRICE"));
                    lesson.setLessonTime(rs.getInt("LESSON_TIME"));
                    lessonList.add(lesson);
                }
            } else {
                System.out.println("Connection is null");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }


        return lessonList;
    }

    @Override
    public boolean addLesson(Lesson lesson) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps =  null;
        String sql = "INSERT INTO LESSON(ID,LESSON_NAME,LESSON_TIME,LESSON_PRICE)" +
                " VALUES(LESSON_SEQ.NEXTVAL,?,?,?)";
        try{
            c = DBHelper.getConnection();
            if(c!=null){
                ps = c.prepareStatement(sql);
                ps.setString(1,lesson.getLessonName());
                ps.setInt(2,lesson.getLessonTime());
                ps.setDouble(3,lesson.getLessonPrice());
                ps.execute();
                result = true;
            }else {
                System.out.println("Connection is null");
            }
        }catch (Exception e){
            c.commit();
            e.printStackTrace();
            result = false;
        }finally {
            JdbcUtility.close(c,ps,null);
        }
        return result;
    }


}
