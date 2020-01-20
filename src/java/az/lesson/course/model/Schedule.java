package az.lesson.course.model;

public class Schedule extends  CourseModel {

    private Student student;
    private Teacher teacher;
    private Lesson lesson;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "student=" + student +
                ", teacher=" + teacher +
                ", lesson=" + lesson +
                '}';
    }
}
