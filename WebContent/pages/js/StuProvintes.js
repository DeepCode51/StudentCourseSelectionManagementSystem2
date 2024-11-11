/**
 * 省市级联查询
 */
 $(document).ready(function () {
	$.ajax({
		url:"../QueryProvintes.do",
		type:"post",
		dataType:"json",
		success:function(data){
			var area1 = $(".area1").clone();
			$(".area1").remove();
			$.each(data,function(i,dt){
				area2 = area1.clone();
				area2.html(dt);
				area2.val(dt);
				$("#province").append(area2);
			});
		}
	});
	
	$("#province").change(function(){
		var provinte = $("#province").val();
		$.ajax({
			url:"../QueryCity.do",
			type:"post",
			dataType:"json",
			data:"provinte="+provinte,
			success:function(data){
				var area1 = $(".area2:first").clone();
				$("#city").empty();
				$.each(data,function(i,dt){
					area2 = area1.clone();
					area2.html(dt);
					area2.val(dt);
					$("#city").append(area2);
				});
			}
		});
	});
	
	$("#city").change(function(){
		var city = $("#city").val();
		$.ajax({
			url:"../QueryArea.do",
			type:"post",
			dataType:"json",
			data:"city="+city,
			success:function(data){
				var area1 = $(".area3:first").clone();
				$("#area").empty();
				$.each(data,function(i,dt){
					area2 = area1.clone();
					area2.html(dt);
					area2.val(dt);
					$("#area").append(area2);
				});
			}
		});
	});
})