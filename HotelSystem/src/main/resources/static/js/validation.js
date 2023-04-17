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

function usernameCheck() {
	const value = $("#username").val();
	$('#username').val(value);
	return check(value,/^[A-Z0-9]{8,10}$/, "아이디는 대문자와 소문자 8~10자입니다",$('#username_msg'));
}



$(document).ready(function() {
	$('#username').blur(async function() {
		if(usernameCheck()==false) {
			return false;
		}
		try {
			await $.ajax('/member/check/username?username='+$('#username').val());
			$("#username_msg").text("좋은아이디네요.").attr("class","success");
		} catch(err) {
			$("#username_msg").text("이미사용중입니다.").attr("class","success");
		}
	})
	
	
	
	
	$('#join').click(function() {
		$('#join_form').submit();
	})
})