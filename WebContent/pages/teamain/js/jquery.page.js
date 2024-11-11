(function($){
	var ms = {
		init:function(obj,args){
			return (function(){
				ms.fillHtml(obj,args);
				ms.bindEvent(obj,args);
			})();
		},
		//填充html
		fillHtml:function(obj,args){
			return (function(){
				obj.empty();
				//上一页
				if(args.current > 1){
					obj.append('<a href="javascript:;" class="prevPage" firstpage="homepage">首页</a>');
					obj.append('<a href="javascript:;" class="prevPage">上一页</a>');
				}else{
					obj.remove('.prevPage');
					obj.append('<span class="disabled">首页</span>');
					obj.append('<span class="disabled">上一页</span>');
				}
				//中间页码
				if(args.current != 1 && args.current >= 4 && args.pageCount != 4){
					obj.append('<a href="javascript:;" class="tcdNumber">'+1+'</a>');
				}
				if(args.current-2 > 2 && args.current <= args.pageCount && args.pageCount > 5){
					obj.append('<span>...</span>');
				}
				var start = args.current -2,end = args.current+2;
				if((start > 1 && args.current < 4)||args.current == 1){
					end++;
				}
				if(args.current > args.pageCount-4 && args.current >= args.pageCount){
					start--;
				}
				for (;start <= end; start++) {
					if(start <= args.pageCount && start >= 1){
						if(start != args.current){
							obj.append('<a href="javascript:;" class="tcdNumber">'+ start +'</a>');
						}else{
							obj.append('<span class="current">'+ start +'</span>');
						}
					}
				}
				if(args.current + 2 < args.pageCount - 1 && args.current >= 1 && args.pageCount > 5){
					obj.append('<span>...</span>');
				}
				if(args.current != args.pageCount && args.current < args.pageCount -2  && args.pageCount != 4){
					obj.append('<a href="javascript:;" class="tcdNumber">'+args.pageCount+'</a>');
				}
				//下一页
				if(args.current < args.pageCount){
					obj.append('<a href="javascript:;" class="nextPage" >下一页</a>');
					obj.append('<a href="javascript:;" class="nextPage" shadowe="lastpage">尾页</a>');
				}else{
					obj.remove('.nextPage');
					obj.append('<span class="disabled">下一页</span>');
					obj.append('<span class="disabled">尾页</span>');
				}
			})();
		},
		
		//绑定事件
		bindEvent:function(obj,args){
			return (function(){
				obj.off("click", "a.tcdNumber");
				obj.off('click', "a.prevPage");
				obj.off('click', "a.nextPage");
				obj.unbind("a.tcdNumber");
				obj.unbind("a.prevPage");
				obj.unbind("a.nextPage");
				obj.one("click","a.tcdNumber",function(){
					var current = parseInt($(this).text());
					ms.fillHtml(obj,{"current":current,"pageCount":args.pageCount});
					if(typeof(args.backFn)=="function"){
						args.backFn(current);
					}
				});
				//上一页
				
				obj.one("click","a.prevPage",function(){
					var current = parseInt(obj.children("span.current").text());
					if($(this).attr("firstpage")=="homepage"){
						ms.fillHtml(obj,{"current":1,"pageCount":args.pageCount});
						if(typeof(args.backFn)=="function"){
							args.backFn(1);
						}
					}else{
						ms.fillHtml(obj,{"current":current-1,"pageCount":args.pageCount});
						if(typeof(args.backFn)=="function"){
							args.backFn(current-1);
						}
						}
					
				});
				//下一页
			
				obj.one("click","a.nextPage",function(){
					
					var current = parseInt(obj.children("span.current").text());
					//alert(args.current);
					if($(this).attr("shadowe")=="lastpage"){
					ms.fillHtml(obj,{"current":args.pageCount,"pageCount":args.pageCount});
					if(typeof(args.backFn)=="function"){
						args.backFn(args.pageCount);
					}
					}else{
						ms.fillHtml(obj,{"current":current+1,"pageCount":args.pageCount});
						if(typeof(args.backFn)=="function"){
							args.backFn(current+1);
						}
						
					}
				});
				
			})();
		}
	}
	$.fn.createPage = function(options){
		var args = $.extend({
			pageCount : 10,
			current : 1,
			backFn : function(){}
		},options);
		ms.init(this,args);
	}
})(jQuery);

var $body = $("body");
