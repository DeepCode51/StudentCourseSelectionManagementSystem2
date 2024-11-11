/**
 * Created by Qi on 2016/6/22.
 */
$(function () {
    //框架宽高
    $("section,section > div.left,section > div.right").height($(window).height());
    $("section").width($(window).width());
    $("section > div.right,iframe").width($(window).width() - 205);
    $("nav").height($(window).height() - 75 - 170);
    $("iframe").height($(window).height() - 8);
    //左边栏展开、收缩
    $("nav div.classA").click(function () {
        if($(this).children("ul.classB").css("display") == "block"){
            $(this).children("a").removeClass("on");
        }else{
            $(this).children("a").addClass("on");
        }
        $(this).children("ul.classB").slideToggle("slow");
        $(this).siblings("div.classA").children("ul.classB").slideUp("slow");
        $(this).siblings("div.classA").children("a").removeClass("on")
    })
    $("nav ul.classB > li").click(function (event) {
        event.stopPropagation();
        if($(this).children("a").attr("href") == undefined){
            if($(this).children("ul.classC").css("display") == "block"){
                $(this).children("a").removeClass("on");
            }else{
                $(this).children("a").addClass("on");
            }
            $(this).children("ul.classC").slideToggle("slow");
            $(this).siblings().children("ul.classC").slideUp("slow");
            $(this).siblings().children("a").removeClass("on")
        }else{
            $("nav a").removeClass("selected");
            $(this).children("a").addClass("selected");
        }
    })
    $("nav ul.classC > li").click(function (event) {
        event.stopPropagation();
        $("nav a").removeClass("selected");
        $(this).children("a").addClass("selected");
    })
	$("#exit").click(function(){
		window.top.location="../main/main.html"
	});
})