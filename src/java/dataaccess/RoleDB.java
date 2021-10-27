package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Role;

/*
    Adapted from lab7 demo
    Credit to owner

    Gets role data from database

*/
public class RoleDB {

    public List<Role> getAll(String role) throws Exception {
        List<Role> roles = new ArrayList<>();
        Role r = new Role();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM userdb WHERE role=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, role);
            rs = ps.executeQuery();
            while (rs.next()) {
                int userRole = rs.getInt(6);
                switch(userRole){
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
                
//                Role setRole = new Role(userRole);
//                roles.add(setRole);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return roles;
    }

}
