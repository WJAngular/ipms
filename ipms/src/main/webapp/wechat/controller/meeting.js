(function(){
	/*
	* 新闻列表 & 新闻详细页控制
	* */
	'use strict';

	var meetingMod = angular.module('meetingMod', ['ionic','ngSanitize']);

	/*
	* 初始化设置加载动画的样式和内容
	* */
	meetingMod.constant('$ionicLoadingConfig', {
		template: '<ion-spinner icon="spiral" class="spinner-energized"></ion-spinner><div>数据加载中...</div>',
		showBackdrop: true,
	});

	/*
	* 路由视图配置
	* */
	meetingMod.config(function($stateProvider,$urlRouterProvider) {
		$stateProvider
			.state('meetlist', {
				url: "/meetlist",
				views: {
					'meetingDetail': {
						templateUrl: "templates/meetlist.html",
						controller:'listCtrl'
					}
				}
			})
			.state('detail', {
				url: "/detail/:id",		//路由中可带参数,在控制器中用$stateParams获取,id的值不需与视图一致,但要与$stateParams一致
				views: {
					'meetingDetail': {
						templateUrl: "templates/detail.html",
						controller:'detailCtrl'
					}
				}
			})
		$urlRouterProvider.otherwise("/meetlist");
	})

	/*
	* 数据池 Factory / Service / Providers
	* */
	meetingMod.factory('initData',function($http,$ionicLoading,$q){
		var service = {};					
		$ionicLoading.show(); 				//启动加载动画
		
		service.getData = function(){		// 请求数据
			var contentData = $q.defer();	// 声明延后执行，表示要去监控后面的执行
			$http({
				url:'/Mtd/cms_content/wechat/getWechatContentList.do', 
				method:'GET',
				cache: true					// 设置缓存,详细页不用重新向同一个地址读数据,而直接使用缓存
			}).success(function(data, status, headers, config){	
				$ionicLoading.hide(); 		// 加载动画隐藏
				contentData.resolve(data);	// 声明执行成功，即http请求数据成功，可以返回数据了
				return contentData;
			}).error(function(data, status, headers, config) {  
				contentData.reject(data);   // 声明执行失败，即服务器返回错误  
			});
			return contentData.promise;   	// 返回承诺，这里并不是最终数据，而是访问最终数据的API
		}
		return service;
	})

	/*
	* 控制器 controller
	* */
	meetingMod.controller('listCtrl', function($scope,initData){
		var promise = initData.getData();	// 同步调用，获得承诺接口
		promise.then(function(data) {  		// 调用承诺API获取数据 .resolve  
			$scope.meetData = data;
		}, function(data) {  				// 处理错误 .reject  
			console.log('未读取到数据');
		});
	})

	meetingMod.controller('detailCtrl', function($scope,initData,$stateParams,$ionicPopup){ 
		//刷新视图时,只执行了对应视图里面有的控制器,所以详细页也必须重新加载数据;
		var promise = initData.getData();	// 同步调用，获得承诺接口
		promise.then(function(data) {  		// 调用承诺API获取数据 .resolve  
			console.log(data);
			$scope.detail = data[$stateParams.id];	// 此处的id不是对应文章的id,是对应列表的索引
		}, function(data) {  				// 处理错误 .reject  
			console.log('未读取到数据');
		});
		
		//  参与确认 对话框
		$scope.showConfirm = function() {
			var confirmPopup = $ionicPopup.confirm({
				title: '是否报名参与此次活动',
				buttons: [
					{
						text: '取消',
						onTap: function(e) {
							console.log('不参与');
						}
					},
					{	
						text: '确定',
						type: 'button-balanced',
						onTap: function(e) {
							console.log($scope.detail.id);
							$.post('abc.do', $scope.detail.id, function(status) {
								console.log(status);
							});
							$ionicPopup.alert({
								title: '报名成功！'
							});
						}
					}
				]
			});
		};
	})

	/*
	* 指令 directive - 把DOM操作用指令来操作
	* */
	meetingMod.directive('kind',function(){
		return{
			restrict:'AE',
			replace:true,
			template:'<img src="" class="kind" width="40" height="40">',
			link:function(scope,element,attr){
				attr.valid == '活动通知' ? element.attr('src','img/m-activity.png') : element.attr('src','img/m-meeting.png');
			}
		}
	})

	/*
	* 指令 directive - 把DOM操作用指令来操作
	* */
	meetingMod.directive('type',function(){
		return{
			restrict:'AE',
			replace:true,
			template:'<span class=""></span>',
			link:function(scope,element,attr){
				attr.valid == '活动通知' ? element.addClass('type_a').html('活动通知') : element.addClass('type_m').html('会议通知');
			}
		}
	})

	/*
	* 字符串转义
	* */
	meetingMod.filter('to_trusted',function ($sce){
		return function (text) {
			return $sce.trustAsHtml(text);
		}
	})	
})()



