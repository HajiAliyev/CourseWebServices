package az.lesson.course.dao.impl;

import az.lesson.course.dao.DBHelper;
import az.lesson.course.dao.LoginDao;
import az.lesson.course.model.Login;
import az.lesson.course.model.Role;
import az.lesson.course.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoginDaoImpl implements LoginDao {
    @Override
    public Login login(String username, String password) throws Exception {
        Login login = new Login();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT L.ID,L.USERNAME,R.ID ROLE_ID,R.ROLE_NAME,L.NAME,L.SURNAME,L.LOGIN_DATE FROM LOGIN L " +
                " INNER JOIN ROLE R ON L.ROLE_ID = R.ID" +
                " WHERE L.ACTIVE =1 AND L.USERNAME = ? AND L.PASSWORD = ? ";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    login.setId(rs.getLong("ID"));
                    login.setUsername(rs.getString("USERNAME"));
                    login.setName(rs.getString("NAME"));
                    login.setSurname(rs.getString("SURNAME"));
                    Role r = new Role();
                    r.setId(rs.getLong("ROLE_ID"));
                    r.setRoleName(rs.getString("ROLE_NAME"));
                    login.setRole(r);
                } else {
                    login = null;
                }
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return login;
    }

    @Override
    public List<Role> getRoleList() throws Exception {
        List<Role> roleList = new ArrayList<Role>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ID,ROLE_NAME FROM ROLE WHERE ACTIVE = 1 ";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Role role = new Role();
                    role.setId(rs.getLong("ID"));
                    role.setRoleName(rs.getString("ROLE_NAME"));
                    roleList.add(role);
                }
            } else {
                System.out.println("Connection is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return roleList;
    }

    @Override
    public boolean register(Login login) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO LOGIN(ID,USERNAME,PASSWORD,ROLE_ID,NAME,SURNAME) " +
                " VALUES(LOGIN_SEQ.NEXTVAL,?,?,?,?,?)";
        try {
            c = DBHelper.getConnection();
            if(c!=null){
                ps = c.prepareStatement(sql);
                ps.setString(1,login.getUsername());
                ps.setString(2,login.getPassword());
                ps.setLong(3,login.getRole().getId());
                ps.setString(4,login.getName());
                ps.setString(5,login.getSurname());
                ps.execute();
                result = true;
            }else {
                System.out.println("Connection is null!!!");
                result = false;
            }
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, null);
        }

        return result;
    }
}
