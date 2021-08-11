$(document).ready(function (){
    $('#adduser').submit(function (event){
        event.preventDefault();

        var userform = {};
        userform["username"] = $("#username").val();
        userform["password"] = $("#password").val();
        userform["role"] = $('#role :selected').text();//getting the dropdown text
        if (userform.role == "ADMIN"){
            userform.role = "ROLE_ADMIN";
        }
        else{
            userform.role = "ROLE_USER";
        }

        var obj = {
            username: userform.username,
            password: userform.password,
            role: userform.role
        }

        $.ajax("/admin/home/adduser",
            {
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(obj),
                success: function (data){
                    console.log(this.data);
                    $("#adduser")[0].reset();
                    var value = '<tr>' + '<td>' + data.id + '</td>'
                        + '<td>' + '<input type="text" value="' + data.username +'">'+ '</td>' +
                        ' <td>' + '<input type="text" value="' +data.role + '">' + '</td>' +
                        ' <td>' + '<input type="text" value="' + data.password + '">' + '</td>'
                        + '<td>'+
                            '<button class="btn btn-danger" type="button" data-code="' + data.id + '" id="deleteuser">Delete</button>'+
                            '<button class="btn btn-success" type="button" data-code="' + data.id + '" id="edituser">Edit</button>'+
                        '</td>' + '</tr>';

                    $('#users').append(value);
                }
            });

    });
    $('#users').on('click', '#deleteuser' , function (event){
        event.preventDefault()
        var id = $(this).attr("data-code");
        $.ajax("/admin/home/deleteuser/"+id.toString(),
            {
                method: 'DELETE',
                contentType: 'application/json',
                success: function (){
                    $('.todos').each(function(){
                        $(id).remove();
                    });
                    window.alert("User Deleted");
                    location.reload();
                }
            });
    });

    $('#users').on('click', '#edituser', function (event){
        event.preventDefault()
        var id = $(this).attr("data-code");
        var row = $(this).closest("tr")
        var name = row.find("td:nth-child(2) input").val();
        var role = row.find("td:nth-child(3) input").val();
        var password = row.find("td:nth-child(4) input").val();
        if (role || name || password == ""){
            window.alert("Fields must not be null");
        }
        else {
            console.log("idvalue: ", id);
            console.log("namevalue: " + name);
            console.log("password: " + password);
            console.log("role: " + role);

            var obj = {
                username: name,
                password: password,
                role: role
            }

            $.ajax("/admin/home/edituser/" + id,
                {
                    method: 'PUT',
                    contentType: 'application/json',
                    data: JSON.stringify(obj),
                    success: function (data) {
                        row.find("td:nth-child(2) input").text(obj.username);
                        row.find("td:nth-child(3) input").text(obj.role);
                        row.find("td:nth-child(4) input").text(obj.password);
                        window.alert("Changes Saved");
                    }
                });
        }
    });
});