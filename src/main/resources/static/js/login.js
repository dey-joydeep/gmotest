$(function() {
	$('input').keypress(function(e) {
		if (e.which == 13) {
			$('#login-btn').click();
		}
	});

	$("#login-btn").click(function() {
		sendPostRequest('#login-form')
	});

	$("#reg-btn").click(function() {
		window.location.href = './signup';
	});
});