/**
 * 新闻列表 & 新闻详细页控制
 */
'use strict';

var news = angular.module('news', ['ionic','ngSanitize']);

news.controller('newsData', function ($scope,$http){
	$scope.items = [
		{
			'href':'article.jsp',
			'img':'img/sucai1.jpg',
			'title':'习近平对办好北京冬奥会作出重要指示',
			'description':'据中国之声《新闻和报纸摘要》报道,中共中央总书记、国家主席、中央军委主席习近平日前对办好北京冬奥会作出重要指示强调',
			"date":"2015-11-26",
			"newstype":"党建新闻"
		},
		{
			'href':'article.jsp',
			'img':'img/sucai2.jpg',
			'title':'海南17个市县33家公立医院已取消药品加成',
			'description':'记者24日从海南省有关方面获悉，目前全省共17个市县33家公立医院(含人民医院和中医院)取消了药品加成，海口、三亚...',
			"date":"2015-11-26",
			"newstype":"党建新闻"
		}
	];
	$scope.doRefresh = function(){
		$http.get('controller/news.json')
		.success(function(newItems) {
			$scope.items = $scope.items.concat(newItems);
		})
		.finally(function() {
			// Stop the ion-refresher from spinning
			$scope.$broadcast('scroll.refreshComplete');
		});
	}
})