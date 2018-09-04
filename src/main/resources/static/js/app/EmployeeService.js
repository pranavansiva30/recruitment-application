'use strict';

angular.module('crudApp').factory('EmployeeService',
    ['$localStorage', '$http', '$q', 'urls','BaseService',
        function ($localStorage, $http, $q, urls,BaseService) {
            var child = Object.create(BaseService);
            var headhunters=[];
            var jobdetails=[];
            child.controllerApi=urls.EMPLOYEE_CONTROLLER_API;
            child.serviceApi=urls.EMPLOYEE_SERVICE_API;

            child.loadAllHeadhunters=function() {
                console.log('Fetching all headhunters');
                var deferred = $q.defer();
                $http.get(urls.HEADHUNTER_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all headhunters');
                            headhunters = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading headhunters');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            child.getAllHeadhunters=function(){
                return headhunters;
            }
            child.loadAllJobDetails=function() {
                console.log('Fetching all jobdetails');
                var deferred = $q.defer();
                $http.get(urls.JOBDETAIL_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all jobdetails');
                            jobdetails = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading jobdetails');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            child.getAllJobDetails=function(){
                return jobdetails;
            }

            return child;


        }




    ]);