/**
 * Created by ksaleh on 7/29/17.
 */
'use strict';
$(document).ready(function () {
    var user;
   if (Cookies.get("user")) {
       user = JSON.parse(Cookies.get("user"));
       console.log(user);
   } else {
       window.location.href = 'login.html';
   }

    $.ajax({
        type: "POST",
        url: "/reminder/fetchAllReminders",
        data: JSON.stringify({userName:user.userName}),
        contentType: 'application/json',
        dataType: 'json',
        success: function(data) {
            console.log(data);
            for(var i = 0; i < data.length; i++) {
                var elem = "<div class=\"col-lg-3 col-md-6\"> " +
                    "<div class=\"panel panel-primary\"> " +
                    "<div class=\"panel-heading\"> " +
                    "<div class=\"row\"> " +
                    "<div class=\"col-xs-3\"> " +
                    "<i class=\"fa fa-comments fa-5x\"></i> " +
                    "</div> " +
                    "<div class=\"col-xs-9 text-right\"> " +
                    "<div class=\"huge\">26</div> " +
                    "<div>" + data[i].reminderName + "</div> " +
                    "</div> " +
                    "</div> " +
                    "</div> " +
                    "<a href=\"#\"> " +
                    "<div class=\"panel-footer\"> " +
                    "<span class=\"pull-left\">View Details</span> " +
                    "<span class=\"pull-right\">" + "<input id=\"toggle_"+ data[i].reminderName +"\" class=\"toggleClassNew\" type=\"checkbox\" checked data-toggle=\"toggle\" data-size=\"mini\"> " +
                    "</input></span>" +
                    "<div class=\"clearfix\"></div> " +
                    "</div> " +
                    "</a> " +
                    "</div> " +
                    "</div>";

                $('#reminderId').append(elem);
                $('#toggle_' +data[i].reminderName).bootstrapToggle();

            }
        }
    });
    doPoll();

    function doPoll(){
        $.ajax({
            type: "POST",
            url: "/reminder/getActiveNotification",
            data: JSON.stringify({userName:user.userName}),
            contentType: 'application/json',
            dataType: 'json',
            success: function(data) {
                for(var i = 0; i < data.length; i++) {
                    new PNotify({
                        title: ''+ data[i].reminderName +'',
                        text: ''+ data[i].description +'',
                        type: 'success'
                    });
                }

                setTimeout(doPoll,10000);
            }
        });
    }

    $(function() {
        $('.toggleClassNew').change(function() {
            console.log("inside check");
            console.log($(this).prop('checked'));
            console.log(user.userName);
            var enableNotificaton = $(this).prop('checked');
            var idNamewithtoggle= ($(this).attr('id'));
            var reminderNamee = idNamewithtoggle.substring(idNamewithtoggle.lastIndexOf("_")+1);
            console.log(reminderNamee);
            $.ajax({
                        type: "POST",
                         url: "/reminder/updateReminder",
                         data: JSON.stringify({userName:user.userName, reminderName:reminderNamee, enableNotificaton:enableNotificaton}),
                         contentType: 'application/json',
                         dataType: 'json',
                         success: function(data) {

                         }
                     });


        })
    })

    // $('.toggleClassNew').change(function () {
    //     var enableNotificaton = $(this).prop('checked');
    //
    //     $.ajax({
    //         type: "POST",
    //         url: "/reminder/updateReminder",
    //         data: JSON.stringify({userName:user.userName, reminderName:reminderName, enableNotificaton:enableNotificaton}),
    //         contentType: 'application/json',
    //         dataType: 'json',
    //         success: function(data) {
    //
    //         }
    //     });
    //
    // })


    $('#logoutB').click(function () {
        Cookies.remove("user");
        window.location.href = 'login.html';
    })
});
