package services;
import dataaccess.ConnectionPool;
import dataaccess.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Role;

public class RoleService {
    public List<Role> getAll(int role) throws Exception {
        List<Role> roles = new ArrayList<>();
        Role r = new Role();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM user WHERE role=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, role);
            rs = ps.executeQuery();
            while (rs.next()) {
                int roleSelect = rs.getInt(6);
                switch (roleSelect){
                    case 1:
                        r.setRole("System Admin");
                        break;
                    case 2:
                        r.setRole("Regular User");
                        break;
                    case 3:
                        r.setRole("Company Admin");
                        break;
                 }
               // users.add(roleSelect);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return roles;
    }
}
