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
        var currentRow = $(this).closest("tr");
        var id = currentRow.id;
        window.alert(id);
        $.ajax("/admin/home/"+id,
            {
                method: 'DELETE',
                contentType: 'application/json',
                success: function (data){
                    $(this).remove();
                    window.alert("kullanici silindi");
                }
            });
    });

    $('#users').on('click', '#edituser', function (event){
        event.preventDefault()
        window.alert("edituser");
    });
});