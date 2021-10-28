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
                
                <table class="manage" style="border: solid white 2px; background-color: rgb(50,50,50);">
                    <tr style="">
                    <th style="padding-left: 0%; color: lightcyan">Email</th>
                    <th style="padding-left: 3%; color: lightcyan">First </th>
                    <th style="padding-left: 2%; color: lightcyan">Last </th>  
                    <th style="padding-left: 0%; color: lightcyan">Edit and Delete</th> 
                    </tr>
                    
                <c:forEach items="${users}" var="user">
                    <tr style="color: white" >
                        <td class="manage" style="padding:0 0 3% 2% ">
                            ${user.getEmail()}
                        </td>
                        <td class="manage" style="padding: 0% 0% 4% 3%">
                            ${user.getFirstname()}
                        </td>
                        <td class="manage" style="padding: 0% 0% 3% 4%">
                             ${user.getLastname()}
                        </td>
                        <td class="manage" style="padding: 0% 0% 3% 4%">
                             ${user.getLastname()}
                        </td>
                        
                    </tr>
                    
                    <p class="input"> </p> 
                </c:forEach>
                    </table>
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
