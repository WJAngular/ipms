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
	var opt;
	var surveyHtml = '<li>'+
					 '<ul class="uk-list uk-list-striped">'+
					 '<li>'+
					 '<label>题目</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="question" class="uk-form-success" size="45" placeholder="问题内容"/>'+
					 '选择个数<input type="text" name="choiceCount"   size="2"/>'+
					 '<button type="button" class="uk-button surveydel uk-button-danger"><i class="uk-icon-trash"></i></button>'+
					 '</li>'+
					 '<li><label>A</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="QA" size="46"/></li>'+
					 '<li><label>B</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="QB" size="46"/></li>'+
					 '<li><label>C</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="QC" size="46"/></li>'+
					 '<li><label>D</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="QD" size="46"/></li>'+
					 '<li><label>E</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="QE" size="46"/></li>'+
					 '<li><label>F</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="QF" size="46"/></li>'+
					 '<li><label>G</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="QG" size="46"/></li>'+
					 '<li><label>H</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="QH" size="46"/></li>'+
					 '<li><label>I</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="QI" size="46"/></li>'+
					 '<li><label>J</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="QJ" size="46"/></li>'+
					 '<li><label>K</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="QK" size="46"/></li>'+
					 '<li><label>L</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="QL" size="46"/></li>'+
					 '<li><label>M</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="QM" size="46"/></li>'+
					 '<li><label>N</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="QN" size="46"/></li>'+
					 '<li><label>O</label>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="QO" size="46"/></li>'+
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