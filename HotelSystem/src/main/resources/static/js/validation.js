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
	const pattern = /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))$/;
	return check($('#personalId').val(),pattern,"주민등록번호 형식에 맞게 입력해주세요",$("#personalId_msg"));
}

function personalId2Check() {
	$('#personalId_msg').text("");
	const pattern = /^[1-8][0-9]{6}$/;
	return check($('#personalId2').val(),pattern,"주민등록번호 형식에 맞게 입력해주세요",$("#personalId_msg"));
}

function emailCheck() {
	$('email_msg').text("");
	const pattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	return check($('#email').val(),pattern,"이메일을 정확하게 입력하세요",$('#email_msg'));
}

function telCheck() {
	$('tel_msg').text("");
	const pattern = /^01([0|1|6|7|8|9])(?:[0-9]{4})(?:[0-9]{4})$/;
	return check($('#tel').val(),pattern,"휴대전화 형식에 맞게 입력하세요",$('#tel_msg'));
}

function usernameCheck() {
	const value = $("#username").val();
	$('#username').val(value);
	return check(value,/^[a-zA-Z0-9]{7,10}$/, "아이디는 문자, 숫자의 조합 7~10자입니다",$('#username_msg'));
}

function passwordCheck() {
	$('#password_msg').text("");
	const pattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
	return check($('#password').val(), pattern, "비밀번호는 문자, 숫자, 특수문자의 조합 최소 8자리입니다.", $("#password_msg"));
}

function password2Check() {
	$('#password2_msg').text("");
	const value =$('#password2').val();
	if(value=="")	{
		$('#password2_msg').text("필수입력입니다").attr("class","fail");
		return false;
	}
	if(value!=$('#password').val()) {
		$('#password2_msg').text("비밀번호가 일치하지 않습니다.").attr("class","fail");
		return false;
	}
}

$(document).ready(function() {
	$('#name').blur(nameCheck);
	$('#personalId').blur(personalIdCheck);
	$('#personalId2').blur(personalId2Check);
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
			$("#username_msg").text("사용가능한아이디입니다.").attr("class","success");
		} catch(err) {
			$("#username_msg").text("이미사용중입니다.").attr("class","fail");
		}
	});
	$('#password').blur(passwordCheck);
	$('#password2').blur(password2Check);
	$('#join').click(async function() {
		const result = nameCheck() && personalIdCheck() && personalId2Check() && emailCheck() && telCheck() && usernameCheck() && passwordCheck() && password2Check();
		if(result==false) {
			alert("입력사항을 다시한번 확인해주세요.");
			return false;
		}
		try {
			await $.ajax('/hotel/member/check/email?email=' + $('#email').val());
			await $.ajax('/hotel/member/check/username?username=' + $('#username').val());
			$('#join_form').submit();
		} catch(err) {
			console.log(err);
			alert("아이디나 이메일이 사용 중입니다");
		}
	});
	
		$('#changepasswordbtn').click(function() {
			const changeresult = passwordCheck() && password2Check();
			console.log(changeresult);
			if(changeresult==false) {
				alert('비밀번호를 확인해주세요.');
				return false;
			}
				alert('비밀번호가 변경되었습니다.');
				$('#change_password').submit();
		});
})