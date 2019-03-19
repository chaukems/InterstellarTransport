'use strict';

var interstellarApp = angular
        .module('interstellarApp', []);

interstellarApp.controller('interstellarController',
        function ($scope, $http) {

            var $ctrl = this;

            $ctrl.getShortestPath = function (data) {
                $http({
                    method: 'POST',
                    url: 'interstellar/transport/getDistance',
                    dataType: "JSON",
                    data: angular.toJson(data)
                }).then(function successCallback(response) {

                    $ctrl.results = response.data;
                    console.log($ctrl.results);
                }, function errorCallback(error) {
                    console.log(error);
                });
            };
        });