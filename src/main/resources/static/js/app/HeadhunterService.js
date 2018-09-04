'use strict';

angular.module('crudApp').factory('HeadhunterService',
    ['$localStorage', '$http', '$q', 'urls', 'BaseService',
        function ($localStorage, $http, $q, urls, BaseService) {
            var child = Object.create(BaseService);
            child.controllerApi = urls.HEADHUNTER_CONTROLLER_API;
            child.serviceApi = urls.HEADHUNTER_SERVICE_API;
            return child;
        }


    ]);