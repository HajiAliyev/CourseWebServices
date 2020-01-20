package az.lesson.course.util;

public class SqlConstants {

    public static final String GET_SCHEDULE_LIST = "SELECT SC.ID,S.ID STUDENT_ID,S.NAME STUDENT_NAME,S.SURNAME STUDENT_SURNAME,\\n\" +\n" +
            "                \" L.ID LESSON_ID,L.LESSON_NAME LESSON_NAME,\\n\" +\n" +
            "                \" T.ID TEACHER_ID,T.NAME TEACHER_NAME,T.SURNAME TEACHER_SURNAME,SC.DATA_DATE FROM SCHEDULE SC\\n\" +\n" +
            "                \" INNER JOIN STUDENT S ON SC.S_ID = S.ID\\n\" +\n" +
            "                \" INNER JOIN TEACHER T ON SC.T_ID = T.ID\\n\" +\n" +
            "                \" INNER JOIN LESSON L ON SC.L_ID = L.ID\\n\" +\n" +
            "                \" WHERE SC.ACTIVE = 1 AND S.ACTIVE = 1 AND T.ACTIVE = 1 AND L.ACTIVE = 1 ";


    public static final String SEARCH_SCHEDULE = "SELECT SC.ID,S.ID STUDENT_ID,S.NAME STUDENT_NAME,S.SURNAME STUDENT_SURNAME, " +
            " L.ID LESSON_ID,L.LESSON_NAME LESSON_NAME, " +
            " T.ID TEACHER_ID,T.NAME TEACHER_NAME,T.SURNAME TEACHER_SURNAME,SC.DATA_DATE FROM SCHEDULE SC " +
            " INNER JOIN STUDENT S ON SC.S_ID = S.ID " +
            " INNER JOIN TEACHER T ON SC.T_ID = T.ID " +
            " INNER JOIN LESSON L ON SC.L_ID = L.ID " +
            " WHERE SC.ACTIVE = 1 AND S.ACTIVE = 1  AND (LOWER(S.NAME) LIKE LOWER(?)) ";


}
