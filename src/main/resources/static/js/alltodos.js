$(document).ready(function (){
    $('#filtertodo').submit(function (event){
        event.preventDefault();

        var filtertodo = {};
        filtertodo["datestart"] = $('input[name=datestart]').val();
        filtertodo["dateend"] = $('input[name=dateend]').val();

        var dateobj = {
            dateStart: filtertodo.datestart,
            dateEnd: filtertodo.dateend
        }

        $.ajax("/admin/todos/filter",
            {
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(dateobj),
                success: function (data){
                    $('#alltodos tbody').empty();
                    $.each(data, function (index, data){
                        console.log("date: "+data.date);
                        var nonFormatDate = new Date(data.date);
                        yr      = nonFormatDate.getFullYear(),
                        month   = nonFormatDate.getMonth() < 10 ? '0' + nonFormatDate.getMonth() : nonFormatDate.getMonth(),
                        day     = nonFormatDate.getDate()  < 10 ? '0' + nonFormatDate.getDate()  : nonFormatDate.getDate(),
                        newDate = yr + '-' + month + '-' + day;
                        var row = '<tr>' + '<td>' + data.id + '</td>' +
                            '<td>' + data.description  + '</td> ' +
                            '<td>' + data.todoStatus + '</td> ' +
                            ' <td>' +data.userId+ '</td> ' +
                            ' <td>' + newDate + '</td>' +
                             '</tr>';
                        $('#alltodos').append(row);
                    });
                }
            });

    });
});