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
                    var value = '<td>' + data.id + '</td>' + '<td>' + data.username  + '</td> <td>' + data.role + '</td> <td>' + data.password + '</td>';

                    $('#users').append(value);
                }
            });

    });
});