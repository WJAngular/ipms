(function(){
	var roleUl      = $('.uk-list'),
		roleLi      = roleUl.find('li'),
		roleUpdate  = $('#role_update'),
		roleReset   = $('#role_reset');

	// 权限列表样式切换
	roleLi.on('click',function () {
		if( $(this).hasClass('openup') ){
			$(this).removeClass('openup');
			$(this).attr('data-status', false);
		}else{
			$(this).addClass('openup');
			$(this).attr('data-status', true);
		}
	})
	
	// 发送到服务器
	roleUpdate.click(function(){ 
		// getArrayRole();
		// console.log(aRole);
		getJsonRole();
		var Data = JSON.parse(sRole);
		console.log(Data);
	});


	// 数组获取方式
	var aRole = [];
	function getArrayRole() {
		roleUl.each(function() {	
			var roles = []; // 放外面会被最后一个覆盖
			$(this).children('li').each(function() {
				roles[$(this).attr('name')] = $(this).attr('data-status');
			});
			aRole[$(this).attr('id')] = roles;
		});
	}
	
	// json获取方式
	var strAll = '[';
	function getJsonRole(){
		roleUl.each(function() {
			var strEach ='{';
			$(this).children('li').each(function() {
				strEach += '"'+ $(this).attr('name') +'":'+ $(this).attr('data-status')+',';
			});
			singRole = strEach.substring(0,strEach.length-1);
			singRole += '}';
			strAll += '{"'+$(this).attr('id')+'":'+singRole+'},';	
		});
		sRole = strAll.substring(0,strAll.length-1);
		sRole += ']'; 
	}
})()