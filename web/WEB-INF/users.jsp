<%-- 
    Document   : users
    Created on : Oct 26, 2021, 6:32:13 PM
    Author     : 771684
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" rel="stylesheet" type="text/css">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="background">
            
        
        <div class="column">
            <div class="card">
                <form method="post" action="users">
                        
                
                    <h1 class="headers">Add User</h1>
                <input class="input" type="email" placeholder="Email"><br>
                <input class="input" type="text" placeholder="First Name"><br>
                <input class="input" type="text" placeholder="Last Name"><br>
                <input  class="input"type="password" placeholder="Password"><br>
                <select  class="dropdown"name="roles" id="roles">
                <option value="System Admin">System Admin</option>
                <option value="Regular User">Regular User</option>
                <option value="Company Admin">Company Admin</option>
                </select><br>
                <input type="submit" name="Add" value="Add" id="saveBtn"><br>
                </form>
            </div>
        </div>
        <div class="column">
            <div class="card">
                <h1 class="headers">Manage Users</h1>
                
                <c:forEach items="${users}" var="User">
                    <p class="input">
                    </p>
                    
                </c:forEach>
                
                <input class="input" type="text" placeholder="Email"><br>
                <input  class="input"type="text" placeholder="First Name"><br>
                <input class="input"type="text" placeholder="Last Name"><br>
                <input class="input"type="password" placeholder="Password"><br>
              <input class="input"type="text" placeholder="Role">
            </div>
        </div>
        <div class="column">
            <div class="card">
                <form method="post" action="users">
                <h1 class="headers">Edit Users</h1>
                <input class="input"type="email" placeholder="Email"><br>
                <input class="input"type="text" placeholder="First Name"><br>
                <input class="input"type="text" placeholder="Last Name"><br>
                <select class="dropdown"name="roles" id="roles">
                <option value="System Admin">System Admin</option>
                <option value="Regular User">Regular User</option>
                <option value="Company Admin">Company Admin</option>
                </select><br>
                <input type="submit"name="save" value="Save" id="saveBtn"><br>
                <input type="submit"name="cancel" value="Cancel" id="cancelBtn"><br>
                </form>
            </div>
        </div>
     </div>
    </body>
</html>
