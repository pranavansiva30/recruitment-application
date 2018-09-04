'use strict';

angular.module('crudApp').controller('HeadhunterController',
    [ '$scope','HeadhunterService','urls',  function($scope,HeadhunterService,urls) {
        var self = this;
        self.headhunter = {};
        self.headhunters=[];
        self.submit = submit;
        self.getAllHeadhunters=getAllHeadhunters;
        self.createHeadhunter = createHeadhunter;
        self.updateHeadhunter = updateHeadhunter;
        self.confirmDelete = confirmDelete;
        self.removeHeadhunter = removeHeadhunter;
        self.editHeadhunter = editHeadhunter;
        self.reset = reset;


        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        function submit() {
            console.log('Submitting');
            if (self.headhunter.id === undefined || self.headhunter.id === null) {
                console.log('Saving New Headhunter', self.headhunter);
                createHeadhunter(self.headhunter);
            } else {
                updateHeadhunter(self.headhunter, self.headhunter.id);
                console.log('Headhunter updated with id ', self.headhunter.id);
            }
        }

        function createHeadhunter(headhunter) {
            console.log('About to create headhunter');
            HeadhunterService.createData(headhunter)
                .then(
                    function (response) {
                        console.log('Headhunter created successfully');
                        self.successMessage = 'Headhunter created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.headhunter={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Headhunter');
                        self.errorMessage = 'Error while creating Headhunter: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }

        function confirmDelete(id) {

            var confirmation = confirm("Are you sure you want to delete this Headhunter");
            if (confirmation) {

                self.removeHeadhunter(id);
            }
        }

        function updateHeadhunter(headhunter, id){
            console.log('About to update headhunter');
            HeadhunterService.updateData(headhunter,id)
                .then(
                    function (response){
                        console.log('Headhunter updated successfully');
                        self.successMessage='Headhunter updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.headhunter={};
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Headhunter');
                        self.errorMessage='Error while updating Headhunter '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeHeadhunter(id){
            console.log('About to remove Headhunter with id '+id);
            HeadhunterService.removeData(id)
                .then(
                    function(){
                        console.log('Headhunter '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing headhunter '+id +', Error :'+errResponse.data);
                    }
                );
        }



        function getAllHeadhunters(){
            return HeadhunterService.getAllData();
        }


        function editHeadhunter(id) {
            self.successMessage='';
            self.errorMessage='';
            HeadhunterService.getData(id).then(
                function (headhunter) {
                    self.headhunter = headhunter;
                },
                function (errResponse) {
                    console.error('Error while getting headhunter ' + id + ', Error :' + errResponse.data);
                }
            );
        }

        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.headhunter={};
            $scope.myForm.$setPristine(); //reset Form
        }





    }


    ]);