package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Role;
import models.User;


/*
    Adapted from lab7 demo
    Credit to owner

    Gets user data from database

*/
public class UserDB {

    public List<User> getAll(String email) throws Exception { //method to get all data from userdb
        List<User> users = new ArrayList<>();
        Role role = new Role();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM userdb WHERE email=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                String userEmail = rs.getString(1);
                Boolean userStatus = rs.getBoolean(2);
                String userFname = rs.getString(3);
                String userLname = rs.getString(4);
                String userPass = rs.getString(5);

                User user = new User(userEmail, userStatus, userFname, userLname, userPass, role.getRole());
                users.add(user);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return users;
    }

    public User get(String email) throws Exception { //method to get user data from userdb
        User user = null;
        Role role = new Role();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM userdb WHERE email=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                Boolean userStatus = rs.getBoolean(2);
                String userFname = rs.getString(3);
                String userLname = rs.getString(4);
                String userPass = rs.getString(5);

                user = new User(email, userStatus, userFname, userLname, userPass, role.getRole());
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return user;
    }

    public void insert(User user) throws Exception { //method to insert a new user into userdb
        Role role = new Role();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO userdb (email, active, first_name, last_name, password, role) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, user.getEmail());
            ps.setBoolean(2, user.getActive());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getPassword());

            switch (role.getRole()) {
                case "System Admin":
                    ps.setInt(6, 1);
                    break;
                case "Regular User":
                    ps.setInt(6, 2);
                    break;
                case "Company Admin":
                    ps.setInt(6, 3);
                    break;
            }

            ps.executeUpdate();

        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void update(User user) throws Exception { //method to update existing user info in userdb
        Role role = new Role();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE userdb SET active=?, first_name=?, last_name=?, password=?, role=? WHERE email=?";

        try {
            ps = con.prepareStatement(sql);

            ps.setBoolean(1, user.getActive());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.setString(5, role.getRole());
            ps.setString(6, user.getEmail());

            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void delete(User user) throws Exception { //method to delete a user from userdb
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM userdb WHERE email=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

}
