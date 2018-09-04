'use strict';

angular.module('crudApp').controller('ReportController',
    [ '$scope','ReportService','urls',  function($scope,ReportService,urls) {
        var self = this;
        self.report = {'headhunter':null,'year':null,'month':null};
        self.totalCost= null;
        self.headhunters=[];
        self.employees=null;
        self.getAllHeadhunters=getAllHeadhunters;
        self.getReport=getReport;
         self.reset = reset;


        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;




        function getAllHeadhunters(){
            return ReportService.getAllData();
        }



        function getReport() {
            self.successMessage='';
            self.errorMessage='';
            ReportService.getReport(self.report.headhunter.id,self.report.year,self.report.month).then(
                function (totalCost) {
                    self.totalCost = totalCost;

                },
                function (errResponse) {
                    console.error('Error while getting report ' + self.report.headhunterId + ', Error :' + errResponse.data);
                }
            );

            ReportService.getEmployees(self.report.headhunter.id,self.report.year,self.report.month).then(
                function (employees) {
                    self.employees = employees;


                },
                function (errResponse) {
                    console.error('Error while getting employees by head hunter ' + self.report.headhunterId + ', Error :' + errResponse.data);
                }
            );
        }

        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.report = {'headhunter':null,'year':null,'month':null};
            self.totalCost= null;
            self.employees=null;
            $scope.myForm.$setPristine(); //reset Form
        }





    }


    ]);