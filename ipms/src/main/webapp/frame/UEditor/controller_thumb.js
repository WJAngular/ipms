/**
 * 新闻列表 & 新闻详细页控制
 */
'use strict';

var pichose = angular.module('pichose', []);

/**
 * 数据池
 * */
pichose.factory('getThumbData',function($http,$q){
	var service ={};
	service.loadThumb = function(pagenum){
		var thumbData = $q.defer();	// 声明延后执行，表示要去监控后面的执行
		$http({
			url:'/Mtd/wechat/media/getMaterialList/IMAGE/'+pagenum+'.do', 
			method:'get',
			cache: true					// 设置缓存,详细页不用重新向同一个地址读数据,而直接使用缓存
		}).success(function(data, status, headers, config){	
			thumbData.resolve(data);	// 声明执行成功，即http请求数据成功，可以返回数据了
			return thumbData;
		}).error(function(data, status, headers, config) {  
			thumbData.reject(data);   // 声明执行失败，即服务器返回错误  
		});
		return thumbData.promise;   	// 返回承诺，这里并不是最终数据，而是访问最终数据的API
	}
	return service;
})

/**
 * 控制器
 * */
pichose.controller('picRadio', function ($scope,getThumbData){
	$scope.page = 1;		// 初始页码
	getPage();				// 初始请求第一页
	
	/** 点下一页发起请求 **/
	$scope.nextPage = function(){		
		$scope.page += 1;	// 页码加1
		getPage();
	};
	
	/** 点上一页发起请求 **/
	$scope.prePage =function(){
		$scope.page -= 1;	// 页码减1
		getPage();
	};
	
	/** 选中的样式 **/
	$scope.thumbCheck = function(event){	
		var oThumbs = $('.uk-thumbnav .uk-thumbnail');
		var oCurrent = $(event.target);
		oThumbs.removeClass('active');
		oCurrent.addClass('active');
	}
	
	/** 数据请求 **/
	function getPage (){
		var promise = getThumbData.loadThumb($scope.page);	// 同步调用，获得承诺接口
		promise.then(function(data) {  		// 调用承诺API获取数据 .resolve  
			$scope.thumbs  = data;
		}, function(data) {  				// 处理错误 .reject  
			console.log('未读取到数据');
		}); 
	};
})