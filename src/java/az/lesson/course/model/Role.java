package az.lesson.course.model;

public class Role extends CourseModel{

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleNmae='" + roleName + '\'' +
                '}';
    }
}
