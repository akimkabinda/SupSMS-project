angular.module('sup.sms.message',['restangular'])
.config(function(RestangularProvider){
    RestangularProvider.setBaseUrl('/api');
})
.controller('messageCtrl', function($scope, Restangular, $location){
    $scope.interlocuror = window.interlocuror;
    
    if(window.interlocuror != ""){
        //We load the conversation
        Restangular.one('message/'+window.userId+'/'+window.interlocuror).get().then(function(result){
            $scope.messages = result;
        },function(){
            toastr.error("An error occured. Please contact the administrator if the problem persists.");
        });
        $scope.isEnabled = true;
    }else{
        //New conversation
        $scope.isEnabled = false;
        toastr.info("Please select a contact before try to send a message.");
    }
    
    $scope.receiverOrTransmitter = function(message){
        if(message.receiver == $scope.interlocuror){
            return 'content iamSender';
        }else{
            return 'content iamReceiver';
        }
    };
    
    $scope.changeContact = function(){
        //Je comptai changer reload la page à chaque changement de contact mais 
        //vu que les infos ne viennent pas d'une api et donc d'un objet JS je ne peux pas récupèrer le num aussi facilement...
        //$location.path('/app/message?interlocutor=...')
    }
    
    $scope.sendMessage = function(){
        if(window.interlocutor == ''){
            toastr.error('Please select a contact before try to send a message.');
        }else{
            Restangular.one('message/'+window.userId).post(window.interlocuror,{message : $scope.messageToSend}).then(function(result){
                $scope.messages.push(result);
                $scope.messageToSend = '';
            },function(){
                toastr.error("An error occured. Please contact the administrator if the problem persists.");
            });
        }
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


