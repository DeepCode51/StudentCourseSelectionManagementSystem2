/**
 * Created by Qi on 2016/6/15.
 */
$(function () {
    /*
     *思路大概是先为每一个required添加必填的标记，用each()方法来实现。
     *在each()方法中先是创建一个元素。然后通过append()方法将创建的元素加入到父元素后面。
     *这里面的this用的很精髓，每一次的this都对应着相应的input元素，然后获取相应的父元素。
     *然后为input元素添加失去焦点事件。然后进行用户名、邮件的验证。
     *这里用了一个判断is()，如果是用户名，做相应的处理，如果是邮件做相应的验证。
     *在jQuery框架中，也可以适当的穿插一写原汁原味的javascript代码。比如验证用户名中就有this.value，和this.value.length。对内容进行判断。
     *然后进行的是邮件的验证，貌似用到了正则表达式。
     *然后为input元素添加keyup事件与focus事件。就是在keyup时也要做一下验证，调用blur事件就行了。用triggerHandler()触发器，触发相应的事件。
     *最后提交表单时做统一验证
     *做好整体与细节的处理
     */
    $.passwordStrength = function (sValue) {
        var modes = 0;
        if (/\d/.test(sValue)) modes++; //数字
        if (/[a-z]/.test(sValue)) modes++; //小写
        if (/[A-Z]/.test(sValue)) modes++; //大写

        switch (modes) {
            case 1:
                return 1;
                break;
            case 2:
                return 2;
                break;
            case 3:
                return 3;
                break;
        }
    }
    //文本框失去焦点后
    $('form :input').blur(function () {
        var $parent = $(this).parent();
        $parent.find(".prompt").remove();
        $parent.parent().find(".prompt").remove();

        var el = null;//声明正则表达式
        if ($(this).is('.IDNo')) {
            var el = /(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X|x)$/;
        } else if ($(this).is('.fullName') || $(this).is('.major')) {
            var el = /^[\u4e00-\u9fa5 a-zA-Z]+$/;
        } else if ($(this).is('.email')) {
            var el = /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/;
        } else if ($(this).is('.PhoneNo')) {
            var el = /(13|14|15|18)[0-9]{9}$/;
        } else if ($(this).is('.QQNo')) {
            var el = /[1-9][0-9]{4,}/;
        } else if ($(this).is('.vCode')){
            var el = /^[a-zA-Z\d]+$/;
        }else if ($(this).is('.username')){
            var el = /^[a-zA-Z\d]+$/;
        }
		
        if (el != null) {
            if (el.test(this.value)) {
                var promptText = "";
                var promptClass = "success";
            } else if (this.value == "") {
                var promptText = $(this).attr("promptNull");
                var promptClass = "error";
            } else {
                var promptText = $(this).attr("promptError");
                var promptClass = "error";
            }
            $parent.append('<span class="prompt ' + promptClass + '"><em></em><span></span>' + promptText + '</span>');
        } else {
            if ($(this).is('.promptselect')) {
                if (this.value == "") {
                    var promptText = $(this).attr("promptNull");
                    var promptClass = "error";
                    $parent.parent().append('<span class="prompt ' + promptClass + '"><em></em><span></span>' + promptText + '</span>');
                }
            }
        }

        if($(this).is('.passwordOld')){
            if (this.value == ""){
                var promptText = $(this).attr("promptNull");
                var promptClass = "error";
                $parent.append('<span class="prompt ' + promptClass + '"><em></em><span></span>' + promptText + '</span>');
            }
        } else if ($(this).is('.password')) {
            if (this.value.length >= 6 && this.value.length <= 12 && /^[A-Za-z0-9]+$/.test(this.value) && $.passwordStrength(this.value) >= 2) {
                if(this.value == $(".passwordOld").val()){
                    var promptText = "新密码与原密码相同";
                    var promptClass = "error";
                }else{
                    var promptText = "";
                    var promptClass = "success";
                }
            } else if (this.value == "") {
                var promptText = $(this).attr("promptNull");
                var promptClass = "error";
            } else {
                var promptText = $(this).attr("promptError");
                var promptClass = "error";
            }
            $parent.append('<span class="prompt ' + promptClass + '"><em></em><span></span>' + promptText + '</span>');
        } else if ($(this).is('.passwordConfirm')) {
            if (this.value == "") {
                var promptText = $(this).attr("promptNull");
                var promptClass = "error";
            } else if (this.value != $(".password").val()) {
                var promptText = $(this).attr("promptError");
                var promptClass = "error";
            } else if (this.value == $(".password").val()) {
                var promptText = "";
                var promptClass = "success";
            }
            $parent.append('<span class="prompt ' + promptClass + '"><em></em><span></span>' + promptText + '</span>');
        }
    }).focus(function () {
        var $parent = $(this).parent();
        $parent.find(".prompt").remove();
        $parent.parent().find(".prompt").remove();
        if ($(this).is('.password')) {
            var via1, via2;
            if (this.value.length >= 6 && this.value.length <= 12 && /^[A-Za-z0-9]+$/.test(this.value)) {
                via1 = "via";
            }
            if ($.passwordStrength(this.value) >= 2) {
                via2 = "via";
            }
            var safe = ["", "", ""];
            var safeWord = "";
            if ($.passwordStrength(this.value) == 1) {
                var safe = ["pwRed", "", ""];
                var safeWord = "低";
            } else if ($.passwordStrength(this.value) == 2) {
                var safe = ["pwRed", "pwYellow", ""];
                var safeWord = "中";
            } else if ($.passwordStrength(this.value) == 3) {
                var safe = ["pwRed", "pwYellow", "pwGreen"];
                var safeWord = "高";
            }

            var $html = '<span class="prompt warning">';
            var $html = $html + '<em></em><span></span>';
            var $html = $html + '<ul>';
            var $html = $html + '<li>安全程度：<strong class="' + safe[0] + '"></strong><strong class="' + safe[1] + '"></strong><strong class="' + safe[2] + '"></strong><b>' + safeWord + '</b></li>';
            var $html = $html + '<li class="' + via1 + '">只能包含6-12位大小写字母与数字</li>';
            var $html = $html + '<li class="' + via2 + '">大写字母、小写字母与数字至少包含2种</li>';
            var $html = $html + '</ul>';
            var $html = $html + '</span>';
            $parent.append($html);


        }

    }).keyup(function () {
        $(this).triggerHandler("focus");
    });//end blur
    //提交，最终验证。
    
    $('#btnSubmit').click(function () {
        $("form :input").trigger('blur');
        var numError = $('form .error,form .warning').length;
        if (numError) {

            return false;
        }else{
            $(".blackBg").css("display","block");
            $(".popTips").css("display","block");
            $("button.popSubmit").click(function(){
                $("form.popForm").submit();
            });
            $(".blackBg,span.popOut,button.popBack").click(function () {
                $(".blackBg").css("display","none");
                $(".popTips").css("display","none");
            });
        }
    });

    //重置
    $('#res').click(function () {
        $(".prompt").remove();
    });

})
//]]>