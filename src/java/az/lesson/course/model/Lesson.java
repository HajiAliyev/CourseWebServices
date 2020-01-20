package az.lesson.course.model;

public class Lesson extends CourseModel{

    private String lessonName;
    private Integer lessonTime;
    private double lessonPrice;

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Integer getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(Integer lessonTime) {
        this.lessonTime = lessonTime;
    }

    public double getLessonPrice() {
        return lessonPrice;
    }

    public void setLessonPrice(double lessonPrice) {
        this.lessonPrice = lessonPrice;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonName='" + lessonName + '\'' +
                ", lessonTime=" + lessonTime +
                ", lessonPrice=" + lessonPrice +
                '}';
    }
}
