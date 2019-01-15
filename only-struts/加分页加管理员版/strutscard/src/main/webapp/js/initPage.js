/**
 * 所有插件
 */

/**
 * 兼容IE等游览器的事件绑定插件
 */
if(typeof jQuery ==='undefined'){
	console.warn('[警告]缺少插件jQuery,部分功能使用受到影响！请导入jQuery插件！');
}
if(typeof layui ==='undefined' && typeof layer ==='undefined'){
	console.warn('[警告]缺少插件layui，所以有部分功能使用会受到影响，建议导入layui.all.js和css。')
}
(function(window,undefined){
	/*
	 * 兼容IE8-游览器事件添加对象
	 */
	var EventUtil = {};
	EventUtil.addEvent = function(element,type,handler){
		if(element.addEventListener){
			element.addEventListener(type,handler,false);
		}else if(element.attachtEvent){
			var scope = function(){
				handler.call(element);
			};
			element.attachtEvent("on"+type,scope);
			this[handler.name] = scope;
		}else{
			element["on"+type] = handler;
		}
	};
	EventUtil.removeEvent = function(element,type,handler){
		if(element.removeEventListener){
			element.removeEventListener(type,handler,false);
		}else if(element.detachEvent){
			if(this[handler.name]){
				element.detachEvent("on"+type,this[handler.name]);
				delete this[handler.name];
			}else{
				element["on"+type] = null;
			}
		}
	};

	EventUtil.stopPropagation = function(){
		if(event.stopPropagation){
			event.stopPropagation();
		}else if(typeof event.cancelBubble == "boolean"){
			event.cancelBubble = true;
		}else{
			throw new Error("element can not stopPropagation");
		}
	};

	//不兼容火狐游览器。火狐游览器中全局的event不存在，必须要在回调函数中传入
	EventUtil.preventDefault = function(){
		var hasReturnValue = function(){
			for(var key in event){
				if(key == "returnValue")
					return true;
			}
			return false;
		};
		if(event.preventDeafult){
			event.preventDeafult();
		}else if(hasReturnValue()){
			event.returnValue = false;
		}else{
			throw new Error("element can not preventDefault");
		}
	};

	EventUtil.getTarget = function(){
		return event.target ? event.target : event.srcElement;
	};

	//获取当鼠标点击时候用户是否同时按下 ctrl,shift,meta,alt键
	EventUtil.getClickKeys = function(){
		var keys = new Array();
		if(event){
			if(event.shiftKey){
				keys.push("shift");
			}
			if(event.ctrlKey){
				keys.push("ctrl");
			}
			if(event.metaKey){
				keys.push("meta");
			}
			if(event.altKey){
				keys.push("alt");
			}
		}
		return keys;
	};

	//获得相关元素。只有在mouseout和mouseover中才存在该属性
	EventUtil.getRelatedTarget = function(){
		if(event.relatedTarget){
			return event.relatedTarget;
		}else if(event.fromElement){
			return event.fromElement;
		}else if(event.toElement){
			return event.toElement;
		}else{
			return null;
		}
	};

	//获取鼠标点击时候的键位，0表示点击鼠标主按钮（左键）,1表示中间按钮，2表示鼠标次按钮
	EventUtil.getMouseButton = function(){
		if(document.implementation.hasFeature("MouseEvents","2.0")){
			if(typeof event.button == "number")
				return event.button;
			else
				return -1;
		}else{
			var type = event.button || -1;
			switch(type){
				case 0:
				case 1:
				case 3:
				case 5:
				case 7:
					return 0;
				case 2:
				case 6:
					return 2;
				case 4:
					return 1;
				default:
					return -1;
			}
		}
	};
	window ? window.eventUtil = EventUtil : eventUtil = EventUtil;
})(window);

/**
 * 纯JS分页插件
 */
(function(window,undefined){
	var params = {};
	//清空某节点下的所有子节点，默认从后面删除。因为某些游览器可能排序会默认上跳
	function removeChildNodes(node){
		if(!(node instanceof Element)){
			throw new Error('不能对非元素节点进行删除子节点功能！');
		}
		var i,length,childs = node.childNodes;
		for(length = childs.length,i=length-1;i>=0;i--){
			node.removeChild(childs[i]);
		}
	}
	function initPage(id,option){
		option = option || {};
		params.option = option;
		var page = document.getElementById(id);
		page.style.fontSize='14px';
		page.style.color='#333';
		page.style.display='inline-block';
		page.style.margin='0 auto';
		//removeChildNodes(page);
		var totalCount = parseInt(option.totalCount) >=0 ?
			parseInt(option.totalCount) : 0;//总记录条数
		var pageSizeList = option.pageSizeList || [5,10,15,20,50],
			defaultPageSize = !!(option.defaultPageSize && 
				pageSizeList.indexOf(option.defaultPageSize) >=0)?
				option.defaultPageSize : pageSizeList[0];//默认的分页数
		var totalPage = Math.ceil(totalCount / defaultPageSize); //总页数
			totalPage = totalPage <= 0 ? 1 : totalPage; 
			curPage = option.curPage && option.curPage >=1 ?
				(option.curPage && option.curPage <= totalPage ? 
					option.curPage : totalPage) : 1,//当前页
			showCount = option.showCount && option.showCount >=1 ? 
				option.showCount : 5;//每页显示
		var ul = createPageList(curPage,totalPage,showCount);			
		
		//加入每页页数，默认是5-25页选择，可以自定义页数
		var isPageNum = option.isPageNum || false;
		if(isPageNum){
			var select = document.createElement('select') , 						
			sel , tempOption ,pageDiv,span1 ,span2;
			select.style.height='30px';
			select.style.border='1px solid #ddd';
			select.style.color='#333';
			select.style.outline='none';
			pageDiv = document.createElement('div');
			pageDiv.id = 'pageDiv';
			pageDiv.style.display='inline-block';						
			span1 = document.createElement('span');
			span2 = document.createElement('span');
			span1.innerHTML = '每页显示';
			span1.style.marginRight='5px';
			span1.style.verticalAlign='middle';
			span2.innerHTML = '条';
			span2.style.verticalAlign='middle';
			span2.style.marginLeft='5px';
			span2.style.marginRight='15px';
			for(sel in pageSizeList){
				tempOption = document.createElement('option');
				tempOption.setAttribute('value',pageSizeList[sel]);
				tempOption.innerHTML = '' + pageSizeList[sel];
				if(pageSizeList[sel] == defaultPageSize){
					tempOption.setAttribute('selected','selected');
				}
				select.appendChild(tempOption);
			}
			pageDiv.appendChild(span1);
			pageDiv.appendChild(select);
			pageDiv.appendChild(span2);
			page.appendChild(pageDiv);//添加页数选择
			//改变页数下拉事件
			eventUtil.addEvent(select,'change',function(e){
				var num = +this.options[this.selectedIndex].innerHTML;
				totalPage = Math.ceil(totalCount / num);
				var newUl = createPageList(1,totalPage,showCount),
				label = document.getElementById('show_label');								
				reflash(page,newUl,option,1);
				if(label){
					label.innerHTML = '共 '+ totalPage + ' 页';
				}
			});
		}
		page.appendChild(ul);//添加每页列表
		//跳转相关代码
		var isJump = option.isJump || false;
		if(isJump){
			var input = document.createElement('input');
			input.style.height='28px';
			input.style.border='1px solid #ddd';
			input.style.padding='0px';
			input.style.width='37px';
			input.style.textAlign='center';
			input.style.marginRight='5px';
			input.style.outlineColor='#388E3C';
			input.style.verticalAlign='middle';
			input.setAttribute('type','text');
			input.setAttribute('value',curPage);
			var button = document.createElement('button'),
				label = document.createElement('label'),
				jumpDiv = document.createElement('div');
			button.style.border='1px solid #ddd';
			button.style.verticalAlign='middle';
			button.style.height='30px';
			button.style.lineHeight='30px';
			button.style.backgroundColor='red';
			button.style.color='#333';
			button.style.outline='none';
			button.style.cursor='pointer';
			jumpDiv.style.display='inline-block';
			jumpDiv.style.marginLeft='5px';						
			jumpDiv.id='jumpDiv';
			button.id = 'jump';
			button.innerHTML = '跳转';
			label.style.marginRight='15px';	
			label.style.verticalAlign='middle';
			label.id = 'show_label';
			label.innerHTML = '共 '+totalPage+' 页';
			jumpDiv.appendChild(label);
			jumpDiv.appendChild(input);
			jumpDiv.appendChild(button);
			page.appendChild(jumpDiv);
			
			//跳转点击事件
			eventUtil.addEvent(button,'click',function(e){
				var curPage = parseInt(input.value);
				if(curPage && curPage >=1 && curPage <= totalPage){
					var newUl = createPageList(curPage,totalPage,showCount);
					reflash(page,newUl,option,curPage,true);
				}
			});
			hover(page);
			button.onmouseover=function(){
				button.style.color='#388E3C';	
			};
			button.onmouseout=function(){
				button.style.color='#333';	
			};
		}
		/**
		 * 页面相关点击事件
		 */
		eventUtil.addEvent(page,'click',function(e){
			var target = e.target,
			nodeName = target.nodeName.toLocaleLowerCase();
			if(nodeName != 'li' && nodeName !='a') return;
			target = nodeName == 'li' ? target : target.parentNode;
			if(!hasClass(target,'no-active') && 
				!hasClass(target,'active')){
				switch(true){
					case hasClass(target,'first'):
						curPage = 1;
						break;
					case hasClass(target,'last'):
						curPage = totalPage;
						break;
					case hasClass(target,'next'):
						curPage = curPage == totalPage ? 
							curPage : curPage + 1;
						break;
					case hasClass(target,'prev'):
						curPage = curPage == 1 ? 1 : curPage - 1;
						break;
					default:
						curPage = parseInt(target.firstElementChild.innerHTML);
				}
				var newUl = createPageList(curPage,totalPage,showCount);
				reflash(page,newUl,option,curPage);
			}
		});
	};				
	//hover事件
	function hover(page){
		//cm add
		page.onmouseover=function(e){
			if(e.target.nodeName.toLocaleLowerCase()=='a'
				&&!hasClass(e.target.parentNode,'active')&&!hasClass(e.target.parentNode,'no-active')){
				e.target.style.color='#249C8D';
				e.target.style.backgroundColor='#eee';
			}
		};
		//cm add
		page.onmouseout=function(e){
			if(e.target.nodeName.toLocaleLowerCase()=='a'
				&&!hasClass(e.target.parentNode,'active')&&!hasClass(e.target.parentNode,'no-active')){
				e.target.style.color='#333';
				e.target.style.backgroundColor='red';
			}
		};
	}
	
	//移除旧的pageList，添加新的pageList并调用回调函数
	function reflash(page,newUl,option,curPage,isClick){
		page.removeChild(page.firstElementChild.id != 'pageDiv' ?
			page.firstElementChild : page.firstElementChild.nextElementSibling);
		if(!page.firstElementChild || page.firstElementChild.id == 'jumpDiv'){
			page.insertBefore(newUl,page.firstElementChild);
		}else{
			page.insertBefore(newUl,page.firstElementChild.nextElementSibling);	
		}
		if(option.jump){
			var select = page.getElementsByTagName('select'),
				pageSize = 0,selectedIndex;
				
			if(select && select[0]){
				select = select[0];
				selectedIndex = select.selectedIndex;
				pageSize = +select.options[selectedIndex].innerHTML;
			}
			option.jump.call(Object.create(null),curPage,pageSize);
		}
		var isJump = option.isJump || false,
			input;
		if(isJump && !isClick){//如果开启跳转，则输入框中的值改变。如果是点击跳转的则不需要再次更改
			input = page.getElementsByTagName('input')[0];
			input.value = curPage;
		}
	}
	/**
	 * 判断节点中是否含有某个class
	 * @param {Object} node
	 * @param {Object} className
	 */
	function hasClass(node,className){
		if( !(node instanceof Element)){
			throw new Error('不能对非Element节点进行判断！');
		}
		var classList = node.classList,
			length = classList.length,i;
		for(i=0;i<length;i++){
			if(classList[i] == className){
				return true;
			}
		}
		return false;
	}
	function changeColor(node){
		node.style.backgroundColor='#249C8D';
	}
	
	function createPageList(curPage,totalPage,showCount){
		var option = params.option || {};
		var isFL = option.isFL === true ? true : false,
			isPN = option.isPN === true ? true : false;
		var ul = document.createElement('ul'),
			first = document.createElement('li'),
			last = document.createElement('li'),
			i,tempLi,yz = Math.ceil(showCount / 2);
		ul.style.margin='0';
		ul.style.display='inline-block';
		ul.style.verticalAlign='middle';
		ul.style.padding='0px';
		ul.style.lineHeight='18px';
		var next = document.createElement('li'),
			prev = document.createElement('li');
		next.className = 'next';
		prev.className = 'prev';
		next.innerHTML = '下一页';
		prev.innerHTML = '上一页';
		first.className = 'first';
		last.className = 'last';
		first.innerHTML = '首页';
		last.innerHTML = '末页';
		isFL ? ul.appendChild(first): '';
		isPN ? ul.appendChild(prev): '';
		for(i=0;i<Math.min(totalPage,showCount);i++){
			tempLi = document.createElement('li');
			if(totalPage <= showCount){
				tempLi.innerHTML = (i+1) + '';
				if((i+1) == curPage){
					tempLi.className = 'active';
				}
			}else{
				if(curPage <= yz || curPage > (totalPage - yz)){//前面几个和后面几个
					if(curPage <= yz){
						tempLi.innerHTML = (i+1) + '';
						if(i+1 == curPage){
							tempLi.className = 'active';
						}
					}else{
						tempLi.innerHTML = (totalPage - showCount + i + 1);
						if((totalPage - showCount + i + 1) == curPage){
							tempLi.className = 'active';
						}
					}
				}else{
					tempLi.innerHTML = (curPage - yz + i + 1);
					if((i + 1) == yz){
						tempLi.className = 'active';
					}
				}
			}
			ul.appendChild(tempLi);
		}
		isPN ? ul.appendChild(next) : '';
		isFL ?  ul.appendChild(last) : '';
		
		var lis = ul.getElementsByTagName('li'),
			tempLi;
		for(i=0;i<lis.length;i++){
			tempLi = lis[i];
			tempLi.innerHTML = createLink(tempLi.innerHTML);
			tempLi.style.listStyle = 'none';						
			tempLi.style.display='inline';
			tempLi.childNodes[0].style.cssFloat = 'left';
			tempLi.childNodes[0].style.cursor = 'pointer';
			tempLi.childNodes[0].style.padding='5px 11px';
			tempLi.childNodes[0].style.backgroundColor='rgb(235,235,235)';
			tempLi.firstElementChild.style.color = '#333';
			if(tempLi.className == 'active'){
				tempLi.childNodes[0].style.backgroundColor = '#249C8D';
				tempLi.childNodes[0].style.color='#fff';
			}
			tempLi.childNodes[0].style.border = '1px solid #ddd';
			tempLi.childNodes[0].style.marginLeft = '-1px';
			tempLi.firstElementChild.style.textDecoration = 'none';
			
		}
		
		if(curPage == 1 || curPage == totalPage){
			if(curPage == 1){
				disable([first,prev]);
			}
			if(curPage == totalPage){
				disable([last,next]);
			}
		}
		var clear1=document.createElement('div');
		clear1.style.clear='left';
		ul.appendChild(clear1);
		return ul;
	}
	
	function createLink(str){
		return "<a href='#'>" + str + "</a>";
	}
	
	function disable(arr){
		arr = Array.isArray(arr) ? arr : [arr];
		var i;
		for(i in arr){
			arr[i].classList.add('no-active');
			if(arr[i].firstElementChild){//有时候first为false时候不会添加到ul，所以a连接
				arr[i].firstElementChild.style.color = 'gainsboro';
				arr[i].firstElementChild.style.cursor = 'not-allowed';
			}
			arr[i].style.cursor = 'not-allowed'
		}
	}
	//添加到全局环境
	pageUtil = {
		initPage:initPage
	}
})(window);




