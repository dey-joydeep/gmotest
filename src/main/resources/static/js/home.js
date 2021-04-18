$(function() {
	$("#logout-btn").click(function() {
		$.ajax({
			url : "logout",
			type : "get",
			success : function() {
				window.location.replace('./');
			},
		});
	});
});