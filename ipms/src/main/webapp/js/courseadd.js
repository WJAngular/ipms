/**
 * 社区调查
 */
 ;(function($){
	/**
	**获取需要的dom元素
	**/
	var surveyAddBtn  = $('#surveyadd'),	//新增按钮
		itemUl		  = $('#itemul'),		//左边项的外盒子
		surveyUl	  = $('#mysurvey');		//右边话题内容的外盒子
	
	var surveyHtml = '<li>'+
					 '<ul class="uk-list uk-list-striped">'+
					 '<li>'+
					 '<label>题目</label><input type="text" name="question" class="uk-form-success" size="45" placeholder="问题内容"/>'+
					 '<button type="button" class="uk-button surveydel uk-button-danger"><i class="uk-icon-trash"></i></button>'+
					 '<div class="uk-margin-small-top"><label>答案</label><input type="text" name="answer" class="uk-form-danger" size="6"/><label style="margin-left:40px;">分数</label><input type="text" name="score" class="uk-form-danger" size="6"/></div>'+
 					 '</li>'+
					 '<li><label>A</label><input type="text" name="QA" size="35"/></li>'+
					 '<li><label>B</label><input type="text" name="QB" size="35"/></li>'+
					 '<li><label>C</label><input type="text" name="QC" size="35"/></li>'+
					 '<li><label>D</label><input type="text" name="QD" size="35"/></li>'+
					 '<li><label>E</label><input type="text" name="QE" size="35"/></li>'+
					 '<li><label>F</label><input type="text" name="QF" size="35"/></li>'+
					 '</ul>'+
					 '</li>';
		
	// 增加按钮
	surveyAddBtn.click(function(){
		itemAdd();
		surveyAdd();
	})
	
	// 左边话题号码重置
	function resetNum () {
		var itemLi = getItems();
		itemLi.each(function(index){
			console.log(index);
			$(this).children().html('问题'+(index+1));
		})
	}
	
	function getItems(){
		return itemUl.find('li').not('.uk-hidden');
		
	}
	// 左边插入item
	function itemAdd () {
		$('<li><a href="">问题</a></li>').appendTo(itemUl);
		resetNum();
	};

	// 右边插入话题
	function surveyAdd () {
		var survey = $(surveyHtml);		// 将字符串转换为jquery对象
		var surveyDelBtn = survey.find('.surveydel'); // 获取新创的删除按钮
		surveyDelBtn.on('click',function(){
			surveyDel($(this));
		})
		surveyUl.append(survey);
	}
	
	// 删除当前survey
	function surveyDel (elem) {
		var curItem = getItems().filter('.uk-active');
		var eParent = elem.parents('.uk-active');
		eParent.prev().addClass('uk-active');
		curItem.prev().addClass('uk-active');
		eParent.remove();
		curItem.remove();
	}
 })(jQuery)