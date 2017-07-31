/**
 * Created by ksaleh on 7/30/17.
 */
'use strict';
$(document).ready(function () {
    if (Cookies.get("user")) {
        console.log(Cookies.get("user"));
        window.location.href = 'index.html';
    }
    $("#loginB").click(function () {
        var username = $('#username').val();
        var password = $('#password').val();

        $.ajax({
            type: 'POST',
            url: '/reminder/login',
            data: JSON.stringify({userName: username, password: password}),
            contentType: 'application/json',
            dataType: 'json',
            success: function(data) {
                Cookies.set("user", data);
                window.location.href = 'index.html';
            }
        });

    });
});
