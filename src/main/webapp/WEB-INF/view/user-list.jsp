<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Users List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="col-md-offset-1 col-md-10">
        <h2>User Manager</h2>
        <hr />

        <input type="button" value="Add User"
               onclick="window.location.href='showForm'; return false;"
               class="btn btn-primary" />
        <br/><br/>
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Users List</div>
            </div>
            <div class="panel-body">
                <table class="table table-striped table-bordered">
                    <tr>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Role</th>
                        <th>Action</th>
                    </tr>

                    <!-- loop over and print our customers -->
                    <c:forEach var="tempUser" items="${users}">

                        <!-- construct an "update" link with customer id -->
                        <%--<c:url var="updateLink" value="/updateForm">--%>
                            <%--<c:param name="userId" value="${tempUser.id}" />--%>
                        <%--</c:url>--%>

                        <%--<!-- construct an "delete" link with customer id -->--%>
                        <c:url var="deleteLink" value="/user/delete">
                            <c:param name="userId" value="${tempUser.id}" />
                        </c:url>

                        <tr>
                            <td>${tempUser.username}</td>
                            <td>${tempUser.password}</td>
                            <td>${tempUser.roleSet}</td>

                            <td>
                                <!-- display the update link --> <a href="${updateLink}">Update</a>
                                | <a href="${deleteLink}"
                                     onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
                            </td>

                        </tr>

                    </c:forEach>

                </table>

            </div>
        </div>
    </div>

</div>
</body>
</html>
