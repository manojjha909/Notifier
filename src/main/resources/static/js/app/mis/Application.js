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
                    "<span class=\"pull-right\"><input id=\"toggle_"+ data[i].id +"\"  type=\"checkbox\" checked data-toggle=\"toggle\" data-size=\"mini\"> " +
                    "</input></span>" +
                    "<div class=\"clearfix\"></div> " +
                    "</div> " +
                    "</a> " +
                    "</div> " +
                    "</div>";

                $('#reminderId').append(elem);
                if (data[i].enableNotificaton) {
                    $('#toggle_' + data[i].id).bootstrapToggle('on');
                } else {
                    $('#toggle_' + data[i].id).bootstrapToggle('off');
                }

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
    
    $('#logoutB').click(function () {
        Cookies.remove("user");
        window.location.href = 'login.html';
    })

    $('#fromDateId').datepicker({
        format:'yyyy-mm-dd'
    });
    
    
    $('#registerB').click(function () {
        var title = $('#title').val();
        var description = $('#description').val();
        var enabled = $('#enabled').prop('checked');
        var date = $('#fromDateId').val();
        var interval = $('#interval').val();
        console.log(title);
        console.log(description);
        console.log(enabled);
        console.log(date);
        console.log(interval);

        $.ajax({
            type: "POST",
            url: "/reminder/addreminder",
            data: JSON.stringify({userName:user.userName, reminderName:title, description:description,
                                enableNotificaton:enabled, scheduler:date, interval:interval}),
            contentType: 'application/json',
            dataType: 'json',
            success: function(data) {
                $('#resetB').click();
                var stack_topleft = {"dir1": "down", "dir2": "right"};
                new PNotify({
                    title: 'Alert',
                    text: 'Reminder Added Successfully.',
                    icon: 'glyphicon glyphicon-envelope',
                    addclass: "stack-topleft",
                    stack: stack_topleft
                });
            }
        });

    });
    
});
