$(document).ready(function (){
    $('#addtodo').submit(function (event){
        event.preventDefault();

        var todoForm = {};
        todoForm["description"] = $("#description").val();
        todoForm["todoStatus"] = $('#todoStatus :selected').text();
        todoForm["date"] = $('input[name=date]').val();
        var obj = {
            description: todoForm.description,
            todoStatus: todoForm.todoStatus,
            date: todoForm.date
        }

        $.ajax("/user/home/addtodo",
            {
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(obj),
                success: function (data){
                    console.log(this.data);
                    $("#addtodo")[0].reset();//resetting the form
                    var nonFormatDate = new Date(data.date);
                    yr      = nonFormatDate.getFullYear(),
                    month   = nonFormatDate.getMonth() < 10 ? '0' + nonFormatDate.getMonth() : nonFormatDate.getMonth(),
                    day     = nonFormatDate.getDate()  < 10 ? '0' + nonFormatDate.getDate()  : nonFormatDate.getDate(),
                    newDate = yr + '-' + month + '-' + day;

                    var value = '<tr>' + '<td>' + data.id + '</td>' + '<td>' + data.description  + '</td> <td>' + data.todoStatus + '</td> <td>' + newDate + '</td>' +
                        '<td>'+
                            '<a class="btn btn-danger" type="button" id="deletetodo">Delete</a>'+
                            '<button class="btn btn-success" type="button" id="edittodo">Edit</button>'+
                        '</td>'+ '</tr>';
                    $('#todos').append(value);
                }
            });
    });

    $('#todos').on('click', '#deletetodo', function (event){
        event.preventDefault();
        var id =$(this).attr("data-code");
        $.ajax("/user/deletetodo/"+id.toString(),
        {
            method: 'DELETE',
            contentType: 'application/json',
            success: function (){
                $('.todos').each(function(){
                    $(id).remove();
                });
                window.alert("Todo Deleted");
                location.reload();
            }
        });
    });

    $('#todos').on('click', '#edittodo', function (event){
        event.preventDefault();
        var id =$(this).attr("data-code");
        window.alert(id);
    });

});