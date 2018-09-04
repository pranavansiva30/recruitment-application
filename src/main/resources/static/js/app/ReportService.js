'use strict';

angular.module('crudApp').factory('ReportService',
    ['$localStorage', '$http', '$q', 'urls', 'BaseService',
        function ($localStorage, $http, $q, urls, BaseService) {
            var child = Object.create(BaseService);
            child.controllerApi = urls.HEADHUNTER_CONTROLLER_API;
            child.serviceApi = urls.HEADHUNTER_SERVICE_API;

            child.getReport=function (id,year,month) {

                console.log('get report');
                var deferred = $q.defer();
                $http.get(urls.EMPLOYEE_SERVICE_API+"getRecruitedCostByHeadhunterAndRecruitedDate/"+id+"/"+year+"/"+month).then(function(response) {

                        deferred.resolve(response.data);
                    })
                    .catch(function(error) {
                        console.error('Error while get report : '+error.data.errorMessage);
                        deferred.reject(error);
                    });
                return deferred.promise;
            }
            child.getEmployees=function (id,year,month) {

                console.log('get employees by headhunter');
                var deferred = $q.defer();
                $http.get(urls.EMPLOYEE_SERVICE_API+"getEmployeesByHeadhunterAndRecruitedDate/"+id+"/"+year+"/"+month).then(function(response) {

                    deferred.resolve(response.data);
                })
                    .catch(function(error) {
                        console.error('Error while get employees by headhunter : '+error.data.errorMessage);
                        deferred.reject(error);
                    });
                return deferred.promise;
            }
            return child;
        }


    ]);

