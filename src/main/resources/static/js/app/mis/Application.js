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

    var reminders;
    $.ajax({
        type: "POST",
        url: "/reminder/fetchReminders",
        data: JSON.stringify({userName:user.userName}),
        contentType: 'application/json',
        dataType: 'json',
        success: function(data) {
            reminders = data;
        }
    });
    
    reminders.each(function (data) {
        
    })
    
    $('#logoutB').click(function () {
        Cookies.remove("user");
        window.location.href = 'login.html';
    })
});
