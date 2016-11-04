$(function(){
	/*
	 * 图文管理部分
	 * ------------------------------------
	 * Add By Credo  2015年11月19日22:22:14
	 * ------------------------------------
	 **/
	var ue = UE.getEditor('item-main');
	var modal = UIkit.modal('#my-id');		// 弹窗实例化

	/*
	 * 添加子新闻 & 表单
	 * */
	var oAdd 	   = $('#add'),			// 获取新增新闻按钮
		oNewUl	   = $('#newsbox'),		// 子新闻列表外盒
		FormChange = $('#formChange'),	// 右边表单存放的盒子
		itemCount  = 0,					// 为新增的元素加上标签
		newsLength = 0,					// 用来判断新增新闻的总数量
		
		liTemplate = '<li class="pluglist uk-overlay uk-overlay-hover"><div class="uk-float-left item_title">标题</div><div class="uk-float-right"><img src="../img/placeholder_600x400.svg" style="height:50px"></div><figcaption class="uk-overlay-panel uk-flex uk-flex-middle uk-flex-center uk-overlay-background uk-text-center"><span class="fun_btn new_edite"><i class="uk-icon-pencil"></i></span><span class="fun_btn new_delete"><i class="uk-icon-trash"></i></span></figcaption></li>',
		
		formTemp   = '<form class="uk-form uk-form-stacked myform" name=""><div class="uk-form-row"><label class="uk-form-label" for="title">标题</label><div class="uk-form-controls"><div class="uk-form-icon uk-form-icon-flip"><input type="text" name="title" size="64" placeholder="文章标题" required></div></div></div><div class="uk-form-row"><label class="uk-form-label" for="author">作者(选填)</label><div class="uk-form-controls"><div class="uk-form-icon uk-form-icon-flip"><input type="text" name="author" size="64" placeholder="文章作者/发布人"></div></div></div><div class="uk-form-row"><label class="uk-form-label" for="covers">封面</label><div class="uk-form-controls"><div class="uk-form-icon uk-form-icon-flip fileupload"><button type="button" data-uk-modal="{target:'+"'#my-id'"+'}">选择图片</button> <input type="text" name="picId" class="picurl" readonly></div></div></div><div class="uk-form-row"><label class="uk-form-label" for="abstract">摘要</label><div class="uk-form-controls"><textarea rows="3" name="abstracts" cols="67" style="resize:none"></textarea></div></div><div class="uk-form-row"><label class="uk-form-label" for="content">内容</label><div class="uk-form-controls"><script id="" name="contents" type="text/plain"></script></div></div><div class="uk-form-row"><label class="uk-form-label" for="title">原文链接</label><div class="uk-form-controls"><div class="uk-form-icon uk-form-icon-flip"><input type="text" size="64" name="url" placeholder="原文链接"></div></div></div></form>';

	oAdd.on('click',function(){
		if (newsLength <4) {
			// 字符串转对象,将模板转对象可直接操作改对象
			var $liTemplate = $(liTemplate);
			var $formTemp   = $(formTemp);

			// 插入小新闻列表和对应表单，添加自定义属性data-item
			oNewUl.append($liTemplate.attr('data-item','item-'+itemCount));
			FormChange.prepend($formTemp.attr('name','item-'+itemCount).css('display','none'));

			// 为每个编辑器设置对应ID,并实例化
			$formTemp.find('script').attr('id','item'+itemCount);
			var ue = UE.getEditor('item'+itemCount);

			itemCount  +=1;		// 标签自增
			newsLength +=1; 	// 数量自增

			/*
			 * 编辑按钮功能
			 * */
			$liTemplate.find('.new_edite').on('click',function(){
				hideALlForm();
				$formTemp.show();
			});

			/*
			 * 删除按钮功能
			 * */
			$liTemplate.find('.new_delete').on('click',function(){
				$(this).parents('li').remove();
				showMainForm();
				$formTemp.remove();
				newsLength -=1;
			});
		};
	});

	var mainEdite = $('.main_edite');			// 主新闻编辑
	mainEdite.on('click',function(){
		hideALlForm()
		showMainForm();
	});

	function showMainForm(){					// 显示主新闻表单
		FormChange.find('form[name="item-main"]').show();
	};

	function hideALlForm(){						// 隐藏所有表单
		FormChange.children('form').hide();
	};

	function getCurrentForm(){					// 取当前表单
		return FormChange.children(':visible');
	}

	function getCurrentFormName(){				// 取当前表单 name值
		return getCurrentForm().attr('name');
	}

	function getCurrentLi(){					// 取当前新闻 对应 li
		var currentFormName = getCurrentFormName();
		return oNewUl.children('[data-item="'+currentFormName+'"]');
	}

	/*
	 * 监听保存按钮
	 * */
	$('#save').click(function(){
		var data = getCurrentForm().find('input[name="title"]');  // 取当前表单的标题
		var title = data.val();
		getCurrentFormName() == 'item-main' ? $('.main_title').html(title) : getCurrentLi().find('.item_title').html(title)
	})

	/*
	 * 图片库选中样式
	 * */
	var $thum = $('.uk-thumbnav');
	var $thumLi = $thum.find('li');

	$thumLi.on('click',function(){
		$thumLi.find('img').removeClass('active');
		$(this).children('img').addClass('active');
	})

	$('#choseAlbum').on('click',function(){
		var src = $thum.find('.active').attr('src');
		var mid = $thum.find('.active').attr('alt');

		//获取显示的表单对应的name
		if( getCurrentFormName() == 'item-main'){
			$('.item-main').attr('src',src);
		}

		getCurrentLi().find('img').attr('src',src);
		getCurrentForm().find('.picurl').val(mid);
		modal.hide();
	})
	
//	$('#sendBtn').on('click',function(){
//		alert(123);
//		send();
//	})
	
	/**获取所有表单数据，ajax提交至后台*/
	function send(){
		var p=getParam();
		alert(p);
		$.post('/Mtd/wechat/media/addMaterialNews.do', 
			{'param': p}, 
			function(data) {
			console.log('rs:'+data);
		});
	}

	/** 获取表单数据，拼接成json字符串*/
	function getParam(){
		var forms = $('form');
		var s="[";//json数组
		forms.each(function(){
			var a='{';//单个json对象
			$.each($(this).serializeArray(),function(index, el) {
				var value=el.value;
				a=a+'\"'+el.name+'\"'+":"+'\"'+value.replace(/\"/g,'\'')+'\"'+',';
			});
			a=a.substring(0,a.lastIndexOf(','))+'}';//去掉最后一个逗号,并结尾
			console.log(a);
			s=s+a+',';//json数组拼接	
		});
		s=s.substring(0,s.lastIndexOf(','))+']';//去掉最后一个逗号
		console.log(s);
		return s;
	}
})