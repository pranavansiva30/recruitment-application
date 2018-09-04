'use strict';

angular.module('crudApp').controller('EmployeeController',
    [ '$scope','EmployeeService','urls',  function($scope,EmployeeService,urls) {
        var self = this;
        self.employee = {};
        self.employees=[];
        self.submit = submit;
        self.getAllEmployees=getAllEmployees;
        self.getAllHeadhunters=getAllHeadhunters;
        self.getAllJobDetails=getAllJobDetails;
        self.createEmployee = createEmployee;
        self.updateEmployee = updateEmployee;
        self.confirmDelete = confirmDelete;
        self.removeEmployee = removeEmployee;
        self.editEmployee = editEmployee;
        self.reset = reset;


        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        function submit() {
            console.log('Submitting');
            if (self.employee.id === undefined || self.employee.id === null) {
                console.log('Saving New Employee', self.employee);
                createEmployee(self.employee);
            } else {
                updateEmployee(self.employee, self.employee.id);
                console.log('Employee updated with id ', self.employee.id);
            }
        }

        function createEmployee(employee) {
            console.log('About to create employee');
            EmployeeService.createData(employee)
                .then(
                    function (response) {
                        console.log('Employee created successfully');
                        self.successMessage = 'Employee created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.employee={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Employee');
                        self.errorMessage = 'Error while creating Employee: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }

        function confirmDelete(id) {

            var confirmation = confirm("Are you sure you want to delete this Employee");
            if (confirmation) {

                self.removeEmployee(id);
            }
        }

        function updateEmployee(employee, id){
            console.log('About to update employee');
            EmployeeService.updateData(employee,id)
                .then(
                    function (response){
                        console.log('Employee updated successfully');
                        self.successMessage='Employee updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.employee={};
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Employee');
                        self.errorMessage='Error while updating Employee '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeEmployee(id){
            console.log('About to remove Employee with id '+id);
            EmployeeService.removeData(id)
                .then(
                    function(){
                        console.log('Employee '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing employee '+id +', Error :'+errResponse.data);
                    }
                );
        }



        function getAllEmployees(){
            return EmployeeService.getAllData();
        }
        function getAllHeadhunters(){
            return EmployeeService.getAllHeadhunters();
        }
        function getAllJobDetails(){
            return EmployeeService.getAllJobDetails();
        }


        function editEmployee(id) {
            self.successMessage='';
            self.errorMessage='';
            EmployeeService.getData(id).then(
                function (employee) {
                    self.employee = employee;
                },
                function (errResponse) {
                    console.error('Error while getting employee ' + id + ', Error :' + errResponse.data);
                }
            );
        }

        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.employee={};
            $scope.myForm.$setPristine(); //reset Form
        }





    }


    ]);