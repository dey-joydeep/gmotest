$(function() {
	$('input').keypress(function(e) {
		if (e.which == 13) {
			$('#reg-btn').click();
		}
	});

	$("#reg-btn").click(function() {
		sendPostRequest('#signup-form');
	});
});
