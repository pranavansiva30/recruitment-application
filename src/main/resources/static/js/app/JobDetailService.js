'use strict';

angular.module('crudApp').factory('JobDetailService',
    ['$localStorage', '$http', '$q', 'urls', 'BaseService',
        function ($localStorage, $http, $q, urls, BaseService) {
            var child = Object.create(BaseService);
            child.controllerApi = urls.JOBDETAIL_CONTROLLER_API;
            child.serviceApi = urls.JOBDETAIL_SERVICE_API;


            return child;


        }


    ]);