function userRoles(array) {
    let string = "";
    const length = array.length;
    for (let index = 0; index < length; index++) {
        string = string + " " + array[index].name;
    }
    return string;
}

$(document).ready(function () {
    loadTable();
})

function loadTable() {
    $.ajax("rest/all", {
        method: "GET",
        dataType: "json",
        success: function (msg) {
            $("#allUsers").children().remove();
            msg.forEach(function (user) {
                $("#allUsers").append(
                    '<tr id=' + user.id + '>' +
                    '<td>' + user.id + '</td>' +
                    '<td>' + user.firstName + '</td>' +
                    '<td>' + user.lastName + '</td>' +
                    '<td>' + user.age + '</td>' +
                    '<td>' + user.email + '</td>' +
                    '<td>' + userRoles(user.roles) + '</td>' +
                    '<td>' + '<button type="button" name="buttonEdit" onclick="userForEdit(this)" data-target="#myModal" value=' + user.id + ' ' +
                    'class="btn btn-info" data-toggle="modal">' + 'Edit' + '</button>' + '</td>' +
                    '<td>' + '<button type="button" name="buttonDelete" onclick="userForDelete(this)" data-target="#myModalDelete" value=' + user.id + ' ' +
                    ' class="btn btn-danger" data-toggle="modal">' +
                    'Delete' + '</button>' + '</td>' +
                    '</tr>');
            });
        }
    })
}


function editUser() {
    var user = new Object();
    user.firstName = $("#firstname").val();
    user.lastName = $("#lastname").val();
    user.age = $("#age").val();
    user.email = $("#email").val();
    user.password = $("#password").val();
    user.id = $("#editId").val();
    user.roles = $("#roles1").val(),
        $.ajax("/rest/edit", {
            method: "PUT",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(user),

            success: function (data) {
                console.log("SUCCESS: ", data);
                loadTable();
                $(".modal").modal('hide');
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
            done: function (e) {
                console.log("DONE");
            }
        })
}

function userForEdit(obj) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/rest/paste",
        data: obj.value,
        dataType: 'json',
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            var user = JSON.parse(JSON.stringify(data));
            $("#firstname").val(user.firstName);
            $("#lastname").val(user.lastName);
            $("#age").val(user.age);
            $("#email").val(user.email);
            $("#password").val("");
            $("#roles1").val(user.roles);
            $("#editId").val(user.id);
            $("#editButton").val(user.id);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        },
        done: function (e) {
            console.log("DONE");
        }
    });
}
function userForDelete(obj) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/rest/paste",
        data: obj.value,
        dataType: 'json',
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            var user = JSON.parse(JSON.stringify(data));
            $("#fName2").val(user.firstName);
            $("#lName2").val(user.lastName);
            $("#age2").val(user.age);
            $("#email2").val(user.email);
            $("#roles2").val(user.role);
            $("#deleteId").val(user.id);
            $("#deleteButton").val(user.id);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        },
        done: function (e) {
            console.log("DONE");
        }
    });
}
function deleteUser() {

    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "/rest/delete/" + $("#deleteId").val(),
        data: $("#deleteId").val(),
        dataType: 'json',
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            $(".modal").modal('hide');
            loadTable();
        },
        error: function (e) {
            console.log("ERROR: ", e);
        },
        done: function (e) {
            console.log("DONE");
        }
    });
}




function addUser() {
    $.ajax("/rest/add", {
        method: "post",
        contentType: "application/json",
        data: JSON.stringify(
            {
                firstName: $("#inputFirstName").val(),
                lastName: $("#inputLastName").val(),
                age: $("#inputAge").val(),
                email: $("#inputEmail").val(),
                password: $("#inputPassword").val(),
                roles: $("#roles").val()
            }),


        dataType: "json",
        success: function (data) {
            console.log("SUCCESS: ", data);
            loadTable();
            document.getElementById('addForm').reset();
        },
        error: function (e) {
            console.log("ERROR: ", e);
            $(".modal").modal('hide');
        },
        done: function (e) {
            console.log("DONE");
            $(".modal").modal('hide');
        }
    })
}






