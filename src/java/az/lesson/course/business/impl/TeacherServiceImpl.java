package az.lesson.course.business.impl;

import az.lesson.course.dao.TeacherDao;
import az.lesson.course.dao.impl.TeacherDaoImpl;
import az.lesson.course.business.TeacherService;
import az.lesson.course.model.Teacher;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    TeacherDao teacherDao = new TeacherDaoImpl();

    public TeacherServiceImpl(TeacherDao teacherDao) {

        this.teacherDao = teacherDao;
    }

    @Override
    public List<Teacher> getTeacherList() throws Exception {
        return teacherDao.getTeacherList();
    }

    @Override
    public boolean addTeacher(Teacher teacher) throws Exception {
        return teacherDao.addTeacher(teacher);
    }

    @Override
    public List<Teacher> getTeacherListByLessonId(Long lessonId) throws Exception {
        return teacherDao.getTeacherListByLessonId(lessonId);
    }
}
