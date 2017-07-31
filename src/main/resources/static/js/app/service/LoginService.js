/**
 * Created by ksaleh on 7/29/17.
 */
'use strict';
mainApp.service('LoginService', function() {
    var service = {};
    service.login = function (username, password) {
        console.log("hiiiii" + username + password);
        return 10;
    }
});
