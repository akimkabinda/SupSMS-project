angular.module('supsmsapp', [
	'supsms.app.conversations',
	'supsms.app.contacts',
	'supsms.app.message'
]).config(["$locationProvider", function($locationProvider){
    $locationProvider.html5Mode(true);//Remove the '#' on the url
}]);
angular.module('supsms.app.contacts', [
   'ui.router' 
])
.config(["$stateProvider", "$urlRouterProvider", function($stateProvider, $urlRouterProvider){
    $stateProvider.state('contacts', {
        url: '/app/contacts',
        templateUrl: '/core/views/app.contacts.html'
    });
}]);
angular.module('supsms.app.conversations', [
   'ui.router' 
])
.config(["$stateProvider", "$urlRouterProvider", function($stateProvider, $urlRouterProvider){
    $stateProvider.state('conversations', {
        url: '/app',
        templateUrl: '/core/views/app.conversations.html'
    });
}]);
angular.module('supsms.app.message', [
   'ui.router' 
])
.config(["$stateProvider", "$urlRouterProvider", function($stateProvider, $urlRouterProvider){
    $stateProvider.state('message', {
        url: '/app/message',
        templateUrl: '/core/views/app.message.html'
    });
}]);