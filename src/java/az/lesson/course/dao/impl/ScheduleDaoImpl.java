package az.lesson.course.dao.impl;

import az.lesson.course.model.Lesson;
import az.lesson.course.model.Schedule;
import az.lesson.course.model.Teacher;
import az.lesson.course.model.AdvancedSearch;
import az.lesson.course.model.Student;
import az.lesson.course.dao.DBHelper;
import az.lesson.course.dao.ScheduleDao;
//import com.sun.org.apache.xpath.internal.objects.XNull;
import az.lesson.course.util.JdbcUtility;
import az.lesson.course.util.SqlConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDaoImpl implements ScheduleDao {

    @Override
    public boolean addSchedule(Schedule schedule) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO SCHEDULE(ID,S_ID,T_ID,L_ID) " +
                " VALUES(SCHEDULE_SEQ.NEXTVAL,?,?,?) ";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, schedule.getStudent().getId());
                ps.setLong(2, schedule.getTeacher().getId());
                ps.setLong(3, schedule.getLesson().getId());
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null");
            }

        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public List<Schedule> getScheduleList() throws Exception {
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT SC.ID,S.ID STUDENT_ID,S.NAME STUDENT_NAME,S.SURNAME STUDENT_SURNAME,  +\n" +
                "  L.ID LESSON_ID,L.LESSON_NAME LESSON_NAME,  +\n" +
                "  T.ID TEACHER_ID,T.NAME TEACHER_NAME,T.SURNAME TEACHER_SURNAME,SC.DATA_DATE FROM SCHEDULE SC \n" +
                " INNER JOIN STUDENT S ON SC.S_ID = S.ID  \n" +
                "  INNER JOIN TEACHER T ON SC.T_ID = T.ID  \n" +
                " INNER JOIN LESSON L ON SC.L_ID = L.ID  \n" +
                " WHERE SC.ACTIVE = 1 AND S.ACTIVE = 1 AND T.ACTIVE = 1 AND L.ACTIVE = 1 ";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Schedule schedule = new Schedule();
                    schedule.setId(rs.getLong("ID"));
                    Student student = new Student();
                    student.setId(rs.getLong("STUDENT_ID"));
                    student.setName(rs.getString("STUDENT_NAME"));
                    student.setSurname(rs.getString("STUDENT_SURNAME"));
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("TEACHER_ID"));
                    teacher.setName(rs.getString("TEACHER_NAME"));
                    teacher.setSurname(rs.getString("TEACHER_SURNAME"));
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("LESSON_ID"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    schedule.setStudent(student);
                    schedule.setTeacher(teacher);
                    schedule.setLesson(lesson);
                    scheduleList.add(schedule);
                }
            } else {
                System.out.println("Connection is null!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return scheduleList;
    }

    @Override
    public List<Schedule> searchSchedule(String keyword) throws Exception {
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = SqlConstants.SEARCH_SCHEDULE;
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Schedule schedule = new Schedule();
                    schedule.setId(rs.getLong("ID"));
                    Student student = new Student();
                    student.setId(rs.getLong("STUDENT_ID"));
                    student.setName(rs.getString("STUDENT_NAME"));
                    student.setSurname(rs.getString("STUDENT_SURNAME"));
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("TEACHER_ID"));
                    teacher.setName(rs.getString("TEACHER_NAME"));
                    teacher.setSurname(rs.getString("TEACHER_SURNAME"));
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("LESSON_ID"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    schedule.setStudent(student);
                    schedule.setTeacher(teacher);
                    schedule.setLesson(lesson);
                    scheduleList.add(schedule);
                }
            } else {
                System.out.println("Connection is null!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return scheduleList;
    }

    @Override
    public List<Schedule> advancedSearchScheduleList(AdvancedSearch advancedSearch) throws Exception {
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        DateFormat  df = new SimpleDateFormat("yyyy-MM-dd");
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT SC.ID,S.ID STUDENT_ID,S.NAME STUDENT_NAME,S.SURNAME STUDENT_SURNAME,  +\n" +
                "  L.ID LESSON_ID,L.LESSON_NAME LESSON_NAME,  +\n" +
                "  T.ID TEACHER_ID,T.NAME TEACHER_NAME,T.SURNAME TEACHER_SURNAME,SC.DATA_DATE FROM SCHEDULE SC \n" +
                " INNER JOIN STUDENT S ON SC.S_ID = S.ID  \n" +
                "  INNER JOIN TEACHER T ON SC.T_ID = T.ID  \n" +
                " INNER JOIN LESSON L ON SC.L_ID = L.ID  \n" +
                " WHERE SC.ACTIVE = 1 AND S.ACTIVE = 1 AND T.ACTIVE = 1 AND L.ACTIVE = 1  ";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                if (advancedSearch.getLessonId() != 0 )
                    sql += " AND L.ID = " + advancedSearch.getLessonId();
                if (advancedSearch.getTeacherId() != 0 )
                    sql += " AND T.ID = " + advancedSearch.getTeacherId();
                if (advancedSearch.getBeginDate() != null && !advancedSearch.getBeginDate().isEmpty())
                    sql += " AND S.DOB >= TO_DATE('"+new java.sql.Date(df.parse(advancedSearch.getBeginDate()).getTime())+"','YYYY-MM_DD')";
                if (advancedSearch.getEndDate() != null && !advancedSearch.getEndDate().isEmpty())
                    sql += " AND S.DOB < TO_DATE('"+new java.sql.Date(df.parse(advancedSearch.getEndDate()).getTime())+"','YYYY-MM_DD')";
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Schedule schedule = new Schedule();
                    schedule.setId(rs.getLong("ID"));
                    Student student = new Student();
                    student.setId(rs.getLong("STUDENT_ID"));
                    student.setName(rs.getString("STUDENT_NAME"));
                    student.setSurname(rs.getString("STUDENT_SURNAME"));
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("TEACHER_ID"));
                    teacher.setName(rs.getString("TEACHER_NAME"));
                    teacher.setSurname(rs.getString("TEACHER_SURNAME"));
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("LESSON_ID"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    schedule.setStudent(student);
                    schedule.setTeacher(teacher);
                    schedule.setLesson(lesson);
                    scheduleList.add(schedule);
                }
            } else {
                System.out.println("Connection is null!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return scheduleList;
    }
}
