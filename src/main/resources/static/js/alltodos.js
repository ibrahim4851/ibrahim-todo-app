$(document).ready(function (){
    $('#alltodos').on('click', '#deletetodo' , function (event){
        event.preventDefault()
        var id = $(this).attr("data-code");
        $.ajax("/user/deletetodo/"+id.toString(),
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
});