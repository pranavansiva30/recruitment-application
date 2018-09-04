'use strict';

angular.module('crudApp').factory('BaseService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
            var datas=[];
            var factory={};
            factory.controllerApi="/";
            factory.serviceApi="/";


            function makeRequestForService(method, url,data) {
                var deferred = $q.defer();
                $http({
                    method: method,
                    url: url,
                    data: data,
                    })
                    .then(function makeRequestSuccess(resp) {
                        console.log('Request Success for '+url +' and method '+method);
                        deferred.resolve(resp.data);

                    }, function makeRequestFailed(errResponse) {
                        console.log('Request Failed for '+url +' and method '+method);
                        deferred.reject(errResponse.data);
                    });
                return deferred.promise;
            }
            function makeRequestForController(method, url,data) {
                var deferred = $q.defer();
                $http({
                    method: method,
                    url: url,
                    data: data,
                })
                    .then(function makeRequestSuccess(resp) {
                        console.log('Request Success for '+url +' and method '+method);
                        factory.setAllData(resp.data.result);
                        deferred.resolve(resp.data);

                    }, function makeRequestFailed(errResponse) {
                        console.log('Request Failed for '+url +' and method '+method);
                        deferred.reject(errResponse.data);
                    });
                return deferred.promise;
            }
            factory.setAllData=function(data){
                datas=data;

            }
            factory.loadAllData= function() {
                console.log('Fetching all data');
                var deferred = $q.defer();
                makeRequestForService('GET',this.serviceApi,null).then(
                    function (response) {
                        console.log('Fetched successfully all data');
                        factory.setAllData(response);
                        deferred.resolve(response);
                    },
                    function (errResponse) {
                        console.error('Error while loading data');
                        deferred.reject(errResponse);
                    }
                );
                return deferred.promise;
            }

            factory.getAllData=function () {
                return datas;
            }
            factory.getData=function (id) {
                console.log('Fetching Data with id :'+id);
               return makeRequestForService('GET',this.serviceApi+ id,null);
            }

            factory.createData=function(data) {
                console.log('Creating Data');
                return makeRequestForController('POST',this.controllerApi,data);
            }






            factory.updateData=function (data, id) {
                console.log('Updating Data with id '+id);
                return makeRequestForController('PUT',this.controllerApi+ id,data);
            }

            factory.removeData=function (id) {
                console.log('Removing Data with id '+id);
                return makeRequestForController('DELETE',this.controllerApi+ id,null);
            }

            return factory;

        }
    ]);