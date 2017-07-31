/**
 * Created by ksaleh on 7/29/17.
 */
'use strict';
$(document).ready(function () {
    var user;
   if (Cookies.get("user")) {
       user = Cookies.get("user");
       console.log(user);
   } else {
       window.location.href = 'login.html';
   }

    $.ajax({
        type: "POST",
        url: "/reminder/fetchReminders",
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
                    "<span class=\"pull-right\"><i class=\"fa fa-arrow-circle-right\"></i></span> " +
                    "<div class=\"clearfix\"></div> " +
                    "</div> " +
                    "</a> " +
                    "</div> " +
                    "</div>";

                $('#reminderId').append(elem);

            }
        }
    });
    
    
    $('#logoutB').click(function () {
        Cookies.remove("user");
        window.location.href = 'login.html';
    })
});
