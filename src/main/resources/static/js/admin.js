$(document).ready(function (){
    $('#adduser').submit(function (event){
        event.preventDefault();

        var userform = {};
        userform["username"] = $("#username").val();
        userform["password"] = $("#password").val();
        userform["role"] = $('#role :selected').text();//getting the dropdown text

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
                    var value = '<tr>' + '<td>' + data.id + '</td>' + '<td>' + data.username  + '</td> <td>' + data.role + '</td> <td>' + data.password + '</td>'
                        + '<td>'+
                            '<button class="btn btn-danger" type="button" id="deleteuser">Delete</button>'+
                            '<button class="btn btn-success" type="button" id="edituser">Edit</button>'+
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
        window.alert("edituser");
    });
});