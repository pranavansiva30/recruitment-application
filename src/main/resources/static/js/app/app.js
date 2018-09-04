'use strict';
var $stateProviderRef = null;
var $urlRouterProviderRef = null;


var app = angular.module('crudApp', ['ui.router', 'ngStorage',
    'ngCookies',
    'ui.utils', 'ui.event', 'checklist-model','textAngular']);


app.run(['$rootScope', '$state', '$stateParams',
    function($rootScope, $state, $stateParams) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;
    }
])
app.constant('urls', {
    BASE: 'http://localhost:8080/',
    HEADHUNTER_CONTROLLER_API: 'http://localhost:8080/headhunter/',
    HEADHUNTER_SERVICE_API: 'http://localhost:8080/api/headhunter/',
    JOBDETAIL_CONTROLLER_API: 'http://localhost:8080/jobdetail/',
    JOBDETAIL_SERVICE_API: 'http://localhost:8080/api/jobdetail/',
    EMPLOYEE_CONTROLLER_API: 'http://localhost:8080/employee/',
    EMPLOYEE_SERVICE_API: 'http://localhost:8080/api/employee/',
   });


app.config(['$stateProvider', '$urlRouterProvider', '$httpProvider',
    function ($stateProvider, $urlRouterProvider, $httpProvider) {
        $stateProvider
            .state('headhunter', {
            url: '/headhunter',
            templateUrl: 'headhunter',
            controller: 'HeadhunterController',
            controllerAs: 'ctrl',
            resolve: {
                headhunters: function ($q, HeadhunterService) {
                    console.log('Load all headhunters');
                    var deferred = $q.defer();
                    HeadhunterService.loadAllData().then(deferred.resolve, deferred.resolve);
                    return deferred.promise;
                }
            }


        })
            .state('employee', {
                url: '/employee',
                templateUrl: 'employee',
                controller: 'EmployeeController',
                controllerAs: 'ctrl',
                resolve: {
                    employees: function ($q, EmployeeService) {
                        console.log('Load all employees');
                        var deferred = $q.defer();
                        EmployeeService.loadAllData().then(deferred.resolve, deferred.resolve);
                        EmployeeService.loadAllHeadhunters().then(deferred.resolve, deferred.resolve);
                        EmployeeService.loadAllJobDetails().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;

                    }
                }

            })
            .state('jobdetail', {
                url: '/jobdetail',
                templateUrl: 'jobdetail',
                controller: 'JobDetailController',
                controllerAs: 'ctrl',
                resolve: {
                    jobdetails: function ($q, JobDetailService) {
                        console.log('Load all jobdetails');
                        var deferred = $q.defer();
                        JobDetailService.loadAllData().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;

                    }

                }
            });

        $stateProvider
            .state('report', {
                url: '/',
                templateUrl: 'report',
                controller: 'ReportController',
                controllerAs: 'ctrl',
                resolve: {
                    headhunters: function ($q, ReportService) {
                        console.log('Load all headhunters');
                        var deferred = $q.defer();
                        ReportService.loadAllData().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }


            })

        $stateProviderRef = $stateProvider;
        $urlRouterProviderRef = $urlRouterProvider;

        $urlRouterProvider.otherwise('/');
    }]);




