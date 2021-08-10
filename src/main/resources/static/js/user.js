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

                    var value = '<tr>' + '<td>' + data.id + '</td>' + '<td>' + data.description  + '</td> ' +
                        '<td class="'+ data.id +'"' + '>' + data.todoStatus + '</td>' +
                        ' <td>' + newDate + '</td>' +
                        '<td>'+
                        '<a class="btn btn-danger" data-code='+ data.id+ ' type="button" id="deletetodo">DELETE</a>'+
                        '<button class="btn btn-success" data-code=' + data.id + ' type="button" id="donetodo">DONE</button>'+
                        '<button class="btn btn-primary" data-code=' + data.id + ' type="button" id="delaytodo">DELAY</button>'+
                        '</td>'+ '</tr>';
                    $('#todos').append(value);
                }
            });
    });

    $('#filtertodo').submit(function (event){
        event.preventDefault();

        var filtertodo = {};
        filtertodo["datestart"] = $('input[name=datestart]').val();
        filtertodo["dateend"] = $('input[name=dateend]').val();

        var dateobj = {
            dateStart: filtertodo.datestart,
            dateEnd: filtertodo.dateend
        }

        var url = $(location).attr('pathname');
        url.split("/");
        var id = url[url.length - 1];
        $.ajax("/user/home/"+id.toString(),
            {
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(dateobj),
                success: function (data){
                    $('#todos tbody').empty();
                    $.each(data, function (index, data){
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
                    });
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

    $('#todos').on('click', '#donetodo', function (event){
        event.preventDefault();
        var id =$(this).attr("data-code");
        var obj = {
            id: id,
            status: 'DONE'
        }
        console.error(obj)
        $.ajax("/user/home/updatetodo",
            {
                method: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(obj),
                success: function (data){
                    $("."+obj.id).text("DONE");
                    console.log("name: "+$(this).name);
                }
            });
    });

    $('#todos').on('click', '#delaytodo', function (event){
        event.preventDefault();
        var id =$(this).attr("data-code");
        var obj = {
            id: id,
            status: 'DELAY'
        }
        $.ajax("/user/home/updatetodo",
            {
                method: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(obj),
                success: function (data){
                    $("."+obj.id).text("DELAY");
                }
            });
    });

});