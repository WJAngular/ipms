(function(){
	'use strict';
	var contentdata = angular.module('contentdata', ['ionic']);
	
	/** 初始化设置加载动画的样式和内容 * */
	contentdata.constant('$ionicLoadingConfig', {
		template: '<ion-spinner icon="spiral" class="spinner-energized"></ion-spinner><div>数据加载中...</div>',
		showBackdrop: true,
	});
	
	/** 获取 @课程@ 数据 */
	contentdata.factory('myData',function($http,$q,$ionicLoading){
		$ionicLoading.show(); 				//启动加载动画
		return {
			getContentData : function(){
				var deferred = $q.defer(); // 延后执行
				$http({
					method:'GET',
					url:'/Mtd/cms_content/wechat/getWechatNewList.do?topic=dwzs',
					cache:true
				}).success(function(data){
					$ionicLoading.hide(); 	// 加载动画隐藏
					deferred.resolve(data);	// 执行成功
				}).error(function(data,status,headers,config) {
					deferred.reject(data);	// 执行失败
				});
				return deferred.promise;
			}
		}
	})
	
	/** @ 课程 - 视图控制器 * */
	contentdata.controller('myCtrl',['$scope','$http','myData',function($scope,$http,myData){
		var promise = myData.getContentData();	// 同步调用,获得承诺接口
		promise.then(function(data){
			$scope.items = data;
		},function(data){
			console.log('未请求到数据');
		});
	}]);

})()