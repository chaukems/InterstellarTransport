'use strict';

var interstellarApp = angular
        .module('interstellarApp', []);

interstellarApp.controller('interstellarController',
        function ($scope, $http) {

            var $ctrl = this;
          
            $ctrl.getShortestPath = function (data) {
                $http({
                    method: 'POST',
                    url: 'feign/getDistance',
                    dataType: "JSON",
                    data: angular.toJson(data)
                }).then(function successCallback(response) {

                    $ctrl.results = response.data;
                }, function errorCallback(error) {
                    console.log(error);
                });
            };
        });