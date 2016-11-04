/**
 * 总控制
 */
'use strict';

var mtd = angular.module('mtd', ['ionic','ngAnimate']);

/*------ 初始化设置加载动画的样式和内容 ----------
==================================================*/
mtd.constant('$ionicLoadingConfig', {
	template: '<ion-spinner icon="spiral" class="spinner-energized"></ion-spinner><div>数据加载中...</div>',
	showBackdrop: true,
});

/*------ 数据池 ---------
=========================*/
/**
 * 其他链接的数据请求
 */
mtd.factory('othLinkData', function ($http,$q) {
	return {
		getLink : function(){
			var deferred = $q.defer(); // 延后执行
			$http({
				method:'GET',
				url:'/Mtd/cms_link/wechat/getWechatLinkList.do',
				cache:true
			}).success(function(data){
				deferred.resolve(data);	// 执行成功
			}).error(function(data,status,headers,config) {
				deferred.reject(data);	// 执行失败
			});
			return deferred.promise;
		}
	}
})

/*------ 控制器 ---------
=========================*/
mtd.controller('picLoader', function($scope){
	$scope.photo = 'img/photo.png';
	$scope.paylevel = '50';
})

mtd.controller('navBtn', function($scope){
	$scope.lists = [ 
		[
			{ 'title':'党员发展流程' , 'icon':'ion-ios-flag', 'colorset':'process','src':'#' },
			{ 'title':'党费缴纳' , 'icon':'ion-social-yen', 'colorset':'pay','src':'payment.html' },
			{ 'title':'社区行政事务' , 'icon':'ion-ios-bookmarks', 'colorset':'stuff','src':'#' }
		],
		[
			{ 'title':'服务机构地图' , 'icon':'ion-map', 'colorset':'map','src':'#'},
			{ 'title':'其他链接' , 'icon':'ion-link', 'colorset':'otherlink','src':'#'},
			{ 'title':'个人信息' , 'icon':'ion-clipboard', 'colorset':'personal','src':'personal.html'}
		],
		[
			{ 'title':'支部信息' , 'icon':'ion-ribbon-b', 'colorset':'info','src':'#'},
			{ 'title':'党员查询' , 'icon':'ion-person-stalker', 'colorset':'search', 'src':'#'},
		]
	];
})

mtd.controller('topicPop',['$scope',function($scope){
	$scope.wallstatu = false;
	$scope.modalHide = function(){
		$scope.wallstatu = false;
	};
	$scope.modalShow = function(){
		$scope.wallstatu = true;
	}
}]);

mtd.controller('myCheckBox', ['$scope', function($scope){
	$scope.getAnswer = function(){
		console.log($scope.answer);
	}
}]);

mtd.controller('linkCtrl',['$scope','othLinkData', function($scope,othLinkData){
	var promise = othLinkData.getLink();
	promise.then(function(data){
		console.log(data);
		$scope.othlinks = data;
	},function(data){
		console.log('未请求到数据');
	})
}]);