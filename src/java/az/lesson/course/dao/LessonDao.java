package az.lesson.course.dao;

import az.lesson.course.model.Lesson;

import java.util.List;

public interface LessonDao {

    List<Lesson> getLessonList() throws Exception;

    boolean addLesson(Lesson lesson) throws Exception;


}
