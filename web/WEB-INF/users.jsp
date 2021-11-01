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
        <link href="newcss.css" rel="stylesheet" type="text/css">
        <title>Manage User</title>
    </head>
    <body style="" >
        <div id="background">


            <div class="column">
                <div class="card">
                    <form method="post" action="users">


                        <h1 class="headers" style="">Add User</h1>
                        <input class="input" type="email" name="email" placeholder="Email"><br>
                        <input class="input" type="text" name="fName" placeholder="First Name"><br>
                        <input class="input" type="text" name="lName" placeholder="Last Name"><br>

                        <input  class="input"type="password" name="password" placeholder="Password"><br>
                        <select  class="dropdown" name="roles" id="roles">
                            <option value="System Admin">System Admin</option>
                            <option value="Regular User">Regular User</option>
                            <option value="Company Admin">Company Admin</option>
                        </select><br>
                        <a class="checkbox" style="">Active<input style="" class="checkbox" type="checkbox" name="active" placeholder="" value=""></a> <br>
                        <input type="submit" name="Add" value="Add" id="saveBtn" style=""><br>
                    </form>
                </div>
            </div>
            <div class="column">
                <div class="card">
                    <h1 class="headers" style="">Manage Users</h1>

                    <table class="manage" style="">
                        <tr style="">
                            <th style="">Role</th>
                            <th style="">Email</th>
                            <th style="">First</th>
                            <th style="">Last</th>  
                            <th style="">Action</th> 
                        </tr>

                        <c:forEach items="${users}" var="user">
                            <c:if test="${user.getActive()}">
                                <tr style="color: white" >
                                    <td class="manage" style="">
                                        ${user.getRole()}
                                    </td>
                                    <td class="manage" style="">
                                        ${user.getEmail()}
                                    </td>
                                    <td class="manage" style="">
                                        ${user.getFirstname()}
                                    </td>
                                    <td class="manage" style="">
                                        ${user.getLastname()}
                                    </td>
                                    <td class="manage" style="">
                                        <a href="edit?action=edit&email=${user.getEmail()}" style="" id="edit"><image src="edit_icon_better.png" width="20px"> </a> 
                                        <a href="delete?action=delete&email=${user.getEmail()}"><image src="delete icon.png" width="20px"> </a> 
                                    </td>
                                </tr>
                            </c:if>

                        </c:forEach>
                    </table>

                    <h1 class="headers" style="">
                        Inactive Users
                    </h1>
                    <table class="manage" style="">
                        <tr style="">
                            <th style="">Role</th>
                            <th style="">Email</th>
                            <th style="">First</th>
                            <th style="">Last</th>  
                            <th style="">Action</th> 
                        </tr>

                        <c:forEach items="${users}" var="user">
                            <c:if test="${!user.getActive()}">
                                <tr style="color: white" >
                                    <td class="manage" style="">
                                        ${user.getRole()}
                                    </td>
                                    <td class="manage" style="">
                                        ${user.getEmail()}
                                    </td>
                                    <td class="manage" style="">
                                        ${user.getFirstname()}
                                    </td>
                                    <td class="manage" style="">
                                        ${user.getLastname()}
                                    </td>
                                    <td class="manage" style="">
                                        <a href="edit?action=edit&email=${user.getEmail()}" style=""><image src="edit_icon_better.png" width="20px"> </a> 
                                        <a href="delete?action=delete&email=${user.getEmail()}"><image src="delete icon.png" width="20px"> </a> 
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </table>


                </div>
            </div>
            <div class="column">
                <div class="card">
                    <form method="post" action="users">
                        <h1 class="headers" style="" >Edit Users</h1>
                        <input class="input"type="email" placeholder="Email" name="emailEdit" value="${emailEdit}" readonly=""><br>
                        <input class="input"type="text" placeholder="First Name" name="firstnameEdit" value="${firstnameEdit}" > <br>    
                        <input class="input"type="text"placeholder="Last Name" name="lastnameEdit" value="${lastnameEdit}"><br>
                        <input class="input"type="password"placeholder="Password" name="passwordEdit" value="${passwordEdit}"><br>
                        <select class='dropdown' name="roleEdit" id="roles" value="${dropdownEdit}" name="dropdownEdit">
                            <option value="System Admin">System Admin</option>
                            <option value="Regular User">Regular User</option>
                            <option value="Company Admin">Company Admin</option>
                        </select><br>
                        <a class="checkbox" name="activeEdit" style="">Active<input style="" class="checkbox" type="checkbox" name="activeEdit" placeholder="" value=""style="padding: 1% 1% 1% 1%;"></a> <br>
                        <input type="submit"name="save" value="Save" id="saveBtn" style="padding: 2% 2% 2% 2%;"><br>
                        <input type="submit"name="cancel" value="Cancel" id="cancelBtn" style="padding: 2% 2% 2% 2%;"><br>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
