package az.lesson.course.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcUtility {


    public static void close(Connection c, PreparedStatement ps, ResultSet rs) throws Exception {
        if (c != null)
            c.close();
        if (ps != null)
            ps.close();
        if (rs != null)
            rs.close();

    }
    public static void close(Connection c, CallableStatement cs, ResultSet rs) throws Exception {
        if (c != null)
            c.close();
        if (cs != null)
           cs.close();
        if (rs != null)
            rs.close();

    }

}
