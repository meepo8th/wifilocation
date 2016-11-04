// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
angular.module('saigo', ['ionic'])
  .run(['$ionicPlatform', '$ionicPopup', '$rootScope', function ($ionicPlatform, $ionicPopup, $rootScope) {
    //主页面显示退出提示框
    $ionicPlatform.ready(function () {
      //启动极光推送服务
      if (window.plugins && window.plugins.jPushPlugin) {
        window.plugins.jPushPlugin.init();
        //调试模式
        window.plugins.jPushPlugin.setDebugMode(true);
      }
    });
    $ionicPlatform.registerBackButtonAction(function (e) {

      e.preventDefault();

      function showConfirm() {
        var confirmPopup = $ionicPopup.confirm({
          title: '<strong>退出应用?</strong>',
          template: '你确定要退出应用吗?',
          okText: '退出',
          cancelText: '取消'
        });

        confirmPopup.then(function (res) {
          if (res) {
            ionic.Platform.exitApp();
          } else {
          }
        });
      }

      if ($rootScope.backButtonPressedOnceToExit) {
        showConfirm();
      } else {
        goback();
      }
      $rootScope.backButtonPressedOnceToExit = true;
      setTimeout(function () {
        $rootScope.backButtonPressedOnceToExit = false;
      }, 500);

      return false;
    }, 101);

  }])

