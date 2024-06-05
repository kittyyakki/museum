function ajax(requestUrl, requestBody, callback) {
	if (callback === undefined) {
		callback = defaultAjaxHandler;
	}

	const xhr = new XMLHttpRequest();
	xhr.open("POST", requestUrl, true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.onreadystatechange = function() {
		if (xhr.readyState !== 4) {
			return;
		}

		callback(xhr.status, JSON.parse(xhr.responseText));
	};

	if (typeof requestBody === 'object') {
		requestBody = Object
			.entries(requestBody)
			.filter(([, value]) => value !== null && value !== undefined)
			.map(([key, value]) => `${key}=${value}`).join('&');
	}
	xhr.send(requestBody);
}

function ajaxForm(form, callback) {
	if (!checkForm(form)) {
		return;
	}

	const requestBody = {};
	for (var input of form.elements) {
		if (input.name) {
			requestBody[input.name] = input.value;
		}
	}
	ajax(form.action, requestBody, callback);
}

function checkForm(form) {
	for (var input of form.elements) {
		if (input.required && input.value == "") {
			alert("모든 항목을 입력해 주세요.");
			input.focus();
			return false;
		}
	}

	return true;
}

var defaultAjaxHandler = function(status, response) {
	var url = response.url;
	var message = response.message;
	switch (status) {
		case 200: // OK
		case 201: // Created
		case 202: // Accepted
			break;

		case 400: // Bad Request
			message = message || "잘못된 요청입니다.";
			break;

		case 401: // Unauthorized
			message = message || "로그인이 필요합니다";
			break;

		case 403: // Forbidden
			message = message || "접근 권한이 없습니다";
			break;

		case 204: // No Content
		case 404: // Not Found
			message = message || "요청한 페이지를 찾을 수 없습니다";
			break;

		case 500: // Internal Server Error
			message = message || "서버 오류가 발생했습니다.";
			break;

		default:
			message = message || "알 수 없는 오류가 발생했습니다.";
			break;
	}

	if (url) {
		location.href = url;
	} else if (message) {
		alert(message);
	}
};
