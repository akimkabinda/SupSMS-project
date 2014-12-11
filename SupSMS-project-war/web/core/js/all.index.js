angular.module('supsms', [
    'supsms.index',
    'supsms.login',
    'supsms.price',
    'supsms.register'
]).config(["$locationProvider", function($locationProvider){
    $locationProvider.html5Mode(true);//Remove the '#' on the url
}]);


angular.module('supsms.index', [
   'ui.router' 
])
.config(["$stateProvider", "$urlRouterProvider", function($stateProvider, $urlRouterProvider){
    $stateProvider.state('index', {
        url: '/',
        templateUrl: '/core/views/index.home.html',
        controller: 'indexCtrl'
    });
}])
.controller('indexCtrl',function(){
    
});

angular.module('supsms.login', [
   'ui.router' 
])
.config(["$stateProvider", "$urlRouterProvider", function($stateProvider, $urlRouterProvider){
    $stateProvider.state('login', {
        url: '/login',
        templateUrl: '/core/views/index.login.html',
        controller: 'loginCtrl'
    });
}]).controller('loginCtrl',function(){
    
});




angular.module('supsms.price', [
   'ui.router' 
])
.config(["$stateProvider", "$urlRouterProvider", function($stateProvider, $urlRouterProvider){
    $stateProvider.state('price', {
        url: '/price',
        templateUrl: '/core/views/index.price.html'
    });
}]);




angular.module('supsms.register', [
   'ui.router' 
])
.config(["$stateProvider", "$urlRouterProvider", function($stateProvider, $urlRouterProvider){
    $stateProvider.state('register', {
        url: '/register',
        templateUrl: '/core/views/index.register.html',
    });
}]).directive('phoneValidation', function() {
    return {
        require: 'ngModel',
        link: function(scope, elm, attrs, ctrl) {

            var INTEGER_REGEXP = /^\-?\d+$/;
            ctrl.$validators.phoneValidation = function(modelValue, viewValue) {
                if (ctrl.$isEmpty(modelValue)) {
                    return false;
                }
                if (INTEGER_REGEXP.test(viewValue)) {
                    return true;
                }
                return false;
            };
        }
    };
});


