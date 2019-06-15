
function openUpdateUser(id,name,login,password,userRole) {
    var updateUserForm = $("#update_user_form");
    $("#update_user_form").empty();
    // updateUserForm.append("<label for=\"name\">Name:</label>");
    // updateUserForm.append("<input type=\"text\" class=\"form-control\" id=\"name\" name=\"name\" value=\""+ name + "\"><br>");
    // updateUserForm.append("<label for=\"login\">Login:</label>");
    // updateUserForm.append("<input type=\"text\" class=\"form-control\" id=\"login\" name=\"login\" value=\""+ login + "\"><br>");
    // updateUserForm.append("<label for=\"password\">Password:</label>");
    // updateUserForm.append("<input type=\"text\" class=\"form-control\" id=\"password\" name=\"password\" value=\""+ password + "\"><br>");
    // updateUserForm.append("<label for=\"role\">Role:</label>");
    // updateUserForm.append("<select class=\"form-control\" id=\"role\" rows=\"2\" name=\"role\" ><option>user</option><option>admin</option></select>");


    $("#update_user_form").append("<a>Name:     <input type=\"text\" name=\"name\" value=\""+ name + "\"/><br><br></a>");
    $("#update_user_form").append("<a>Login:    <input type=\"text\" name=\"login\" value=\""+ login + "\"/><br><br></a>");
    $("#update_user_form").append("<a>Password: <input type=\"text\" name=\"password\" value=\""+ password + "\"/><br><br></a>");
    $("#update_user_form").append("<input type=\"hidden\" name=\"id\" value=\""+ id + "\"/>");
    $("#update_user_form").append("<input class='btn btn-primary' type=\"submit\" value=\"Update\">");
    $("#update_user_form").modal('show');
}

function openAddUserWindow() {
    $("#add_user_Modal").modal('show');
}

function deleteUser(userID) {
    $.ajax({
        url: "/user/delete",
        method: "get",
        async: true,
        data:{userID: userID},
        error: function(message) {
            console.log(message);
        },
        success: function(data) {
            getDataForUsersList();
        }
    });
}