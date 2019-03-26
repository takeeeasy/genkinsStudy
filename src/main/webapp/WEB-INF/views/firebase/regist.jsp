<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="/resources/js/jquery-3.3.1.min.js"></script>
<script src="/resources/js/jquery.validate.min.js"></script>
<script src="/resources/js/additional-methods.min.js"></script>
<script src ="/resources/js/messages_ko.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-json/2.6.0/jquery.json.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
$(function(){
var isPassPW = false;	
	
var url = '/google/registProc';
var userid = $('#userid').val();
var username = $('#username').val();
var userpass = $('#userpass').val();
var userpass2 = $('#userpass2').val();
var phone = $('#phone').val();
var email = $('#email').val();


function regBtnClick(){
	document.regFrm.submit();
}

// 	$('#userid, #userpw, #userpw2, #username, #phone, #email').blur(function(){
// 		console.log('in1');
//  		$.ajax({
// 			type:"POST",
// 			url:url,
// 			data: $('#regFrm').serialize(),
// 			dataType:'json',
// 			success:function(data){
// 				console.log("getJSONData: "+data.userid);
// 				jsonObj = data;
// 				console.log("localScope:"+jsonObj.userid);
// 				test(jsonObj);
// 			},
// 			error:function(jqXHR, textStatus, errorThrown){
// 				console.log(jqXHR, textStatus, errorThrown);
// 				test(jsonObj = null);
// 			}	
// 		})
// 	})
	function test(jsonObj){
		overlapData(jsonObj || function (){
		}
		);
	}
	
// 	function overlapData(data){
// 		$('#feedback').append('<p>중복된 '+data+'입니다.');
// 	}
	function pass1KeyUp(){
		var pass = this.value;
		
	}
	$('form').validate({
		rangelength : function(d,b,e){
			var c = a.isArray(d) ? d.length : this.getLength(a.trim(d),b);
			return this.optional(b)||(c>=e[0]&&c<=e[1])
			},
		rules : {
			userid : {
				required : true,
				minlength : 3,
				remote : "/google/registProc"
			},
			userpass : {
				required : true,
				rangelength : [8, 32]
			},
			userpass2 : {
				equalTo : userpass
			},
			username : {
				required : true,
				rangelength : [2, 30],
				strTrim : true			// 문자열 앞뒤 공백제거
			},
			phone : {
				required : true
			},
			email : {
				email : true
			}
			
		},
		messages : {
			userid : {
				required : "아이디를 입력해 주세요",
				minlength : "조금 더! 아이디는 3자 이상이에요.",
				remote : "이미 사용된 아이디여서  또 사용할 수 없어요. 다른 아이디를 입력해 주세요."
			},
			userpass : {
				required : "비밀번호를 입력해 주세요",
				rangelength : "조금 더! 비밀번호는 8자 이상이에요!"
			},
			userpass2 : {
				equalTo : "비밀번호와 일치하지 않습니다. 다시 입력해 주세요."
			},
			username : {
				required : "이름을 입력해 주세요.",
				rangelength : "성과 이름(2자 또는 3자 이상)을 함께 입력해 주세요.",
				strTrim : "아이디는 영문 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)만 사용할 수 있어요. (단, 마침표(.)는 처음과 끝에 또는 연속으로 사용 불가)"
			},
			phone : {
				required : "휴대폰 번호를 입력해 주세요.",
			},
			email : {
				email : "이메일 주소 형식이 아닙니다. 본인확인이 가능한 이메일 주소를 입력해 주세요."
			}
		}
	})
	
})
</script>
</head>
<body>
<hr>
<h1>Register</h1>
<hr>
<br>
<br>
<form action = "/google/registProc" method = "POST" name = "regFrm" id = "regFrm">
	<fieldset>
	<legend>회원가입</legend>
		<div id = "feedback">
		</div>
		<div>
			<dl>
				<dt>
					<label for = "userid">아이디</label>
				</dt>
				<dd>
					<input type = "text" name = "userid" id = "userid">
				</dd>
				<dt>
					<label for = "userpw">비밀번호</label>
				</dt>
				<dd>
					<input type = "password" name = "userpw" id = "userpw" onkeyup="">
				</dd>
				<dt>
					<label for = "userpw2">비밀번호 확인</label>
				</dt>
				<dd>
					<input type = "password" name = "userpw2" id = "userpw2">
				</dd>
				<dt>
					<label for = "username">이름</label>
				</dt>
				<dd>
					<input type = "text" name = "username" id = "username">
				</dd>
				<dt>
					<label for = "phone">휴대폰</label>
				</dt>
				<dd>
					<input type = "text" name = "phone" id = "phone">
				</dd>
				<dt>
					<label for = "email">이메일</label>
				</dt>
				<dd>
					<input type = "text" name = "email" id = "email">
				</dd>
				<br>
				<dd>
					<input type = "button" name = "regBtn" id = "regBtn" value = "registerNow" onclick = "regBtnClick()">&emsp;<input type = "reset" name = "regCn" id = "regCn" value = "Cancel" onclick = "window.close()">
				</dd>
			</dl>
		</div>
	</fieldset>
</form>

</body>
</html>