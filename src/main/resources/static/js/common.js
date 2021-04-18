var convertToJsonString = function (formArray) {
    var val, data = {};
    formArray.forEach( (fv)=> {
        val = fv.value.trim();
        if(val.length === 0)
            return;
        if(fv.name in data) {
            var v = data[fv.name];
            if(typeof v === 'object')
                v.push(val);
            else
                v = [v, val];
            data[fv.name] = v;
        } else {
            data[fv.name] = val;
        }
    });
    return JSON.stringify(data);
}

var sendPostRequest = function(formId) {
	$('#msg-div').empty();
	$('.field-error.error').remove();
	var form = $(formId);
	var sendData = convertToJsonString(form.serializeArray());
	var formURL = form.attr("action");
	var type = form.attr("method");
	$.ajax({
		url : formURL,
		type : type,
		data : sendData,
		contentType : 'application/json; charset=utf-8',
		success : function(response) {
			if (response.success) {
				window.location.replace('./');
			}

			var vErrors = response.errors;
			if (isPresent(vErrors)) {
				evaluateValidationError(formId, vErrors);
			} else {
				$('#msg-div').text(response.message);
			}
		},
		error : function(jqXHR) {
			var response = eval("(" + jqXHR.responseText + ")");
			if (typeof response === 'undefined')
				$('#msg-div').text('サーバーが停止しています。');
			else
				$('#msg-div').text(response.message);
		}
	});
};

var evaluateValidationError = function (inputParent, errors, processHidden = false) {
    var inputs = $(inputParent).find('input:not([type="button"])')
    if (inputs.length === 0)
        return;
    var errorField = '<span class="error field-error"></span>';
    $.each(errors, function (f, m) {
        for (var i = 0; i < inputs.length; i++) {
            if ($(inputs[i]).attr('name') === f) {
                $(inputs[i]).addClass('is-invalid');
                var parent = $(inputs[i]).closest('.row-block');
                parent.prepend(errorSpan);
                var errorSpan = parent.find('.error.field-error');
                if (errorSpan.length === 0) {
                    errorSpan = $(errorField);
                    errorSpan.insertBefore(parent); 
                }
                errorSpan.text(m);
                break;
            }
        }
    });
};

function isPresent(data) {
    return (typeof (data) !== 'undefined' && data !== null);
}

$(function () {
    var btnDisState = [];
    $(document).on({
        ajaxStart : function() {
            btnDisState = [];
            $.each($('button'), function(){
                var btn = {
                        id : $(this).attr('id'),
                        disState : isPresent($(this).attr('disabled'))
                    };
                btnDisState.push(btn)
                $(this).attr('disabled','disabled');
            });
        },
        ajaxStop : function() {
            btnDisState.forEach((btn) => {
                if(!btn.disState)
                    $('#' + btn.id).removeAttr('disabled');
            });
            btnDisState = null;
        }
    });
});
