/**
 * Created by ksaleh on 7/29/17.
 */
'use strict';
mainApp.controller('LoginController', function($scope, LoginService) {
    $scope.login = function () {
        console.log($scope.username + $scope.password);
        LoginService.login($scope.username, $scope.password);
    }
});
