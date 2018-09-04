/*AngularJS has a set of built-in directives which offers functionality to your applications.
*/
 //bootstrap datepicker
 angular.module('crudApp').directive('datepicker-boot', function($parse){
		return {
            require: '?ngModel',
            restrict: 'A',
            link: function (scope, element, attrs, ngModel) {

                if (!ngModel) return; // do nothing if no ng-model

                ngModel.$render = function () {
                    element.find('input').val(ngModel.$viewValue || '');
                }

                element.datepicker({
                    format: 'yyyy-mm-dd',
                    autoclose: true
                });

                element.on('dp.change', function () {
                    scope.$apply(read);
                });

                read();

                function read() {
                    var value = element.find('input').val();
                    ngModel.$setViewValue(value);
                }

                ngModel.$viewChangeListeners.push(function () {
                    // $parse(attrs.ngModel).assign(scope, ngModel.$viewValue);

                });
            }
        };
	});

//jquery datepicker
angular.module('crudApp').directive('datepicker', function() {

    var linker = function(scope, element, attrs,ngModelCtrl) {

        element.datepicker({

            dateFormat: "yy-mm-dd"

        });
        element.on('changeDate', function(){
            console.log(scope);
            ngModelCtrl.$setViewValue(value);//value is datepicker selected date;
            $(".datepicker").hide();
        });
    }
    return {
        require: 'ngModel',
        restrict: 'A',
        link: linker
    };
});
 
 
