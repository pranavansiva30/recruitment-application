'use strict';

angular.module('crudApp').controller('JobDetailController',
    [ '$scope','JobDetailService','urls',  function($scope,JobDetailService,urls) {
        var self = this;
        self.jobdetail = {};
        self.jobdetails=[];
        self.submit = submit;
        self.getAllJobDetails=getAllJobDetails;
        self.createJobDetail = createJobDetail;
        self.updateJobDetail = updateJobDetail;
        self.confirmDelete = confirmDelete;
        self.removeJobDetail = removeJobDetail;
        self.editJobDetail = editJobDetail;
        self.reset = reset;


        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        function submit() {
            console.log('Submitting');
            if (self.jobdetail.id === undefined || self.jobdetail.id === null) {
                console.log('Saving New Job Detail', self.jobdetail);
                createJobDetail(self.jobdetail);
            } else {
                updateJobDetail(self.jobdetail, self.jobdetail.id);
                console.log('Job Detail updated with id ', self.jobdetail.id);
            }
        }

        function createJobDetail(jobdetail) {
            console.log('About to create Job Detail');
            JobDetailService.createData(jobdetail)
                .then(
                    function (response) {
                        console.log('Job Detail created successfully');
                        self.successMessage = 'Job Detail created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.jobdetail={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating Job Detail');
                        self.errorMessage = 'Error while creating Job Detail: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }

        function confirmDelete(id) {

            var confirmation = confirm("Are you sure you want to delete this Job Detail");
            if (confirmation) {

                self.removeJobDetail(id);
            }
        }

        function updateJobDetail(jobdetail, id){
            console.log('About to update Job Detail');
            JobDetailService.updateData(jobdetail,id)
                .then(
                    function (response){
                        console.log('Job Detail updated successfully');
                        self.successMessage='Job Detail updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.jobdetail={};
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating Job Detail');
                        self.errorMessage='Error while updating Job Detail '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }


        function removeJobDetail(id){
            console.log('About to remove Job Detail with id '+id);
            JobDetailService.removeData(id)
                .then(
                    function(){
                        console.log('Job Detail '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing Job Detail '+id +', Error :'+errResponse.data);
                    }
                );
        }



        function getAllJobDetails(){
            return JobDetailService.getAllData();
        }


        function editJobDetail(id) {
            self.successMessage='';
            self.errorMessage='';
            JobDetailService.getData(id).then(
                function (jobdetail) {
                    self.jobdetail = jobdetail;
                },
                function (errResponse) {
                    console.error('Error while getting Job Detail ' + id + ', Error :' + errResponse.data);
                }
            );
        }

        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.jobdetail={};
            $scope.myForm.$setPristine(); //reset Form
        }





    }


    ]);