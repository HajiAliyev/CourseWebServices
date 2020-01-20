package az.lesson.course.dao;

import az.lesson.course.model.Login;
import az.lesson.course.model.Role;

import java.util.List;

public interface LoginDao {

    Login login(String username, String password) throws Exception;

    List<Role> getRoleList() throws Exception;

    boolean register(Login login) throws Exception;
}
