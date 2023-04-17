function check(value,pattern,message,element) {
	if(value=="") {
		element.text("필수입력입니다").attr("class","fail");
		return false;
	}
	if(pattern.test(value)==false) {
		element.text(message).attr("class","fail");
		return false;
	}
	return true;
}

function nameCheck() {
	$('#name_msg').text("");
	const pattern = /^[가-힣]{2,10}$/;
	return check($('#name').val(),pattern,"이름은 한글 2~10글자입니다",$("#name_msg"));
}

function personalIdCheck() {
	$('#personalId_msg').text("");
	const pattern = /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1])([1-8][0-9]{6}))$/
	return check($('#personalId').val(),pattern,"주민등록번호 형식에 맞게 입력해주세요",$("#personalId_msg"));
}

function emailCheck() {
	$('email_msg').text("");
	const pattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	return check($('#email').val(),pattern,"이메일을 정확하게 입력하세요",$('#email_msg'));
}

function telCheck() {
	$('tel_msg').text("");
	const pattern = /^01([0|1|6|7|8|9])-([0-9]{4})-([0-9]{4})$/;
	return check($('#tel').val(),pattern,"휴대전화 형식에 맞게 입력하세요",$('#tel_msg'));
}









function usernameCheck() {
	const value = $("#username").val();
	$('#username').val(value);
	return check(value,/^[a-zA-Z0-9]{7,10}$/, "아이디는 문자, 숫자의 조합 7~10자입니다",$('#username_msg'));
}












$(document).ready(function() {
	$('#name').blur(nameCheck);
	$('#personalId').blur(personalIdCheck);
	
	$('#email').blur(async function() {
		if(emailCheck()==false) {
			return false;
		}
		try {
			await $.ajax('/hotel/member/check/email?email='+$('#email').val());
			$("#email_msg").text("사용가능한 이메일입니다.").attr("class","success");
		} catch(err) {
			$("#email_msg").text("사용중인 이메일입니다.").attr("class","fail");
		}
	});
	
	$('#tel').blur(telCheck);
		
	
	
	
	
	$('#username').blur(async function() {
		if(usernameCheck()==false) {
			return false;
		}
		try {
			await $.ajax('/hotel/member/check/username?username='+$('#username').val());
			$("#username_msg").text("좋은아이디네요.").attr("class","success");
		} catch(err) {
			$("#username_msg").text("이미사용중입니다.").attr("class","fail");
		}
	})
	
	
	
	
	$('#join').click(function() {
		$('#join_form').submit();
	})
})