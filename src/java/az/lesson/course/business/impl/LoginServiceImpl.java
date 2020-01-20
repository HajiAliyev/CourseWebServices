package az.lesson.course.business.impl;

import az.lesson.course.dao.LoginDao;
import az.lesson.course.business.LoginService;
import az.lesson.course.model.Login;
import az.lesson.course.model.Role;

import java.util.List;

public class LoginServiceImpl implements LoginService {

    private LoginDao loginDao;

    public LoginServiceImpl(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    @Override
    public Login login(String username, String password) throws Exception {
        return loginDao.login(username,password);
    }

    @Override
    public List<Role> getRoleList() throws Exception {
        return loginDao.getRoleList();
    }

    @Override
    public boolean register(Login login) throws Exception {
        return loginDao.register(login);
    }
}
