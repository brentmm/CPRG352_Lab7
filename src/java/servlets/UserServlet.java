package servlets;

import dataaccess.UserDB;
import java.io.IOException;
//import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;

/**
 *
 * @author Dynamic Duo
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        UserDB udb = new UserDB();

        try {
            List<User> usersList = udb.getAll();
            request.setAttribute("users", usersList);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        String action = request.getParameter("action");
        String emailIn = request.getParameter("email");
        
        if(action != null){
        if(action.equals("delete")){
            
            
            try {
                udb.delete(emailIn);
                List<User> usersList = udb.getAll(); 
            request.setAttribute("users", usersList);
                
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            if(emailIn.contains(" ")){
            String emailArray[]  = emailIn.split(" ");
            String emailMake = emailArray[0] + "+" + emailArray[1];
            try {
                
                udb.delete(emailMake);
        try {
            List<User> usersList = udb.getAll(); 
            request.setAttribute("users", usersList);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            }

        }if(action.equals("edit")){
            User user;
            try {
            user = udb.get(emailIn);
            request.setAttribute("emailEdit", user.getEmail());
            request.setAttribute("firstnameEdit", user.getFirstname());
            request.setAttribute("lastnameEdit", user.getLastname());
            request.setAttribute("roleEdit", user.getRole());
            request.setAttribute("activeEdit", user.getActive());
            request.setAttribute("passwordEdit", user.getPassword());
             
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //if the email containts a space (default)
            if(emailIn.contains(" ")){
            String emailArray[]  = emailIn.split(" ");
            String emailMake = emailArray[0] + "+" + emailArray[1];
            try {
            user= udb.get(emailMake);
            request.setAttribute("emailEdit", user.getEmail());
            request.setAttribute("firstnameEdit", user.getFirstname());
            request.setAttribute("lastnameEdit", user.getLastname());
            request.setAttribute("roleEdit", user.getRole());
            request.setAttribute("activeEdit", user.getActive());
            request.setAttribute("passwordEdit", user.getPassword());
        try {
            List<User> usersList = udb.getAll(); 
            request.setAttribute("users", usersList);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
            
            
            
        }
        
        
        
        }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);  //loads Shopping list page
        return;

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        UserDB udb = new UserDB();
        
        if(request.getParameter("save")!=null){
        
            try {
                String email = request.getParameter("emailEdit");
                String firstName = request.getParameter("firstnameEdit");
                String lastName = request.getParameter("lastnameEdit");
                String role = request.getParameter("roleEdit");
                String active = request.getParameter("activeEdit");
                String password = request.getParameter("passwordEdit");
                
                if(email.length() <= 40 && firstName != null && !firstName.equals("") && firstName.length() <=20 && lastName != null && !lastName.equals("") && lastName.length() <=20 && password != null && !password.equals("") && password.length() <=20){
                boolean activeEdit;
                
                if(active != null){
                     activeEdit = true;
                }else{
                     activeEdit = false;
                }
                
                User user;
                int newRole = 0;
                
                switch (role){
                case "System Admin":             
                newRole = 1;
                break;
                case "Regular User":
                newRole = 2;
                break;
                case "Company Admin":
                newRole = 3;
                break;    
            }
                user = new User (email, activeEdit, firstName, lastName, password, newRole);
                
                udb.update(user);
                List<User> usersList = udb.getAll();
                request.setAttribute("users", usersList);
                }else{
                try {
                List<User> usersList1;
                usersList1 = udb.getAll();
                request.setAttribute("users", usersList1);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            
        }
        
        
        
        if(request.getParameter("Add")!= null){
        String action = request.getParameter("Add");
        String email = request.getParameter("email");
        
        
        
        
        String fName = request.getParameter("fName");
        String lName = request.getParameter("lName");
        String password = request.getParameter("password");
        String role = request.getParameter("roles");
        String activated = request.getParameter("active");
        
        
        if(email.length() <= 40 && fName != null && !fName.equals("") && fName.length() <=20 && lName != null && !lName.equals("") && lName.length() <=20 && password != null && !password.equals("") && password.length() <=20){
        boolean activation = false;
        if(activated != null){
        if(request.getParameter("active")==null){
             activation = false;
        }else{
            activation = true;
            }
        }
        int newRole = 0;
        switch (role){
                case "System Admin":             
                newRole = 1;
                break;
                case "Regular User":
                newRole = 2;
                break;
                case "Company Admin":
                newRole = 3;
                break;    
            }
         User user = new User (email, activation, fName, lName, password, newRole);
        
        try {
            switch (action) {
                case "Add":
                    udb.insert(user);
                    List<User> usersList = udb.getAll();
                    request.setAttribute("users", usersList);
                    break;
                
            }
            request.setAttribute("message", action);
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        try {
                udb.getAll();
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                List<User> usersList1;
                usersList1 = udb.getAll();
                request.setAttribute("users", usersList1);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        }
        if(request.getParameter("cancel") != null){
                    request.setAttribute("email", null);
                    request.setAttribute("firstnameEdit", null);
                    request.setAttribute("lastnameEdit", null);
                    
                    List<User> usersList1;
            try {
                usersList1 = udb.getAll();
                request.setAttribute("users", usersList1);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
    }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);  //loads Shopping list page
        return;
    }

}
