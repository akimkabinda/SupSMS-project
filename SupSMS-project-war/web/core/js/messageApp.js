angular.module('sup.sms.message',['restangular'])
.config(function(RestangularProvider){
    RestangularProvider.setBaseUrl('/api');
})
.controller('messageCtrl', function($scope, Restangular){
    $scope.interlocuror = window.interlocuror;
    
    if(window.interlocuror != ""){
        //We load the conversation
        Restangular.one('message/'+window.userId+'/'+window.interlocuror).get().then(function(result){
            $scope.messages = result;
        },function(){
            $scope.error = "An error occured. Please contact the administrator if the problem persists."
        });
    }else{
        //New conversation
    }
    
    $scope.receiverOrTransmitter = function(message){
        if(message.receiver == $scope.interlocuror){
            return 'content iamSender';
        }else{
            return 'content iamReceiver';
        }
    };
    
    
    $scope.sendMessage = function(){
        Restangular.one('message/'+window.userId).post(window.interlocuror,{message : $scope.messageToSend}).then(function(result){
            $scope.messages.push(result);
            $scope.messageToSend = '';
        },function(){
            $scope.error = "An error occured. Please contact the administrator if the problem persists.";
        });
    };

})
.directive('scrollBottom', function () {
  return {
    scope: {
      scrollBottom: "="
    },
    link: function (scope, element) {
      scope.$watchCollection('scrollBottom', function (newValue) {
        if (newValue)
        {
          $(element).scrollTop($(element)[0].scrollHeight);
        }
      });
    }
  }
})


