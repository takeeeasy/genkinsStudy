<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://www.gstatic.com/firebasejs/5.5.2/firebase-app.js"></script>
<script src="https://www.gstatic.com/firebasejs/5.5.2/firebase-auth.js"></script>
<script src="https://www.gstatic.com/firebasejs/5.5.2/firebase-database.js"></script>
<script src="https://www.gstatic.com/firebasejs/5.5.2/firebase-firestore.js"></script>
<script src="https://www.gstatic.com/firebasejs/5.5.2/firebase-messaging.js"></script>
<script src="https://www.gstatic.com/firebasejs/5.5.2/firebase-functions.js"></script>

<!-- Comment out (or don't include) services that you don't want to use -->
<!-- <script src="https://www.gstatic.com/firebasejs/5.5.2/firebase-storage.js"></script> -->
<script>
var firebase = require("firebase/app");

require("firebase/auth");

var email = document.getElementById('email').value;



// Initialize Firebase
var config = {
  apiKey: "AIzaSyAr7oKz5BioWcNRMrARqr9yGLI1xKscOj4",
  authDomain: "approval-project-231703.firebaseapp.com",
  databaseURL: "https://approval-project-231703.firebaseio.com",
  projectId: "approval-project-231703",
  storageBucket: "approval-project-231703.appspot.com",
  messagingSenderId: "762544477250"
};
firebase.initializeApp(config);

var actionCodeSettings = { // email 링크 만드는 객체
  // URL you want to redirect back to. The domain (www.example.com) for this
  // URL must be whitelisted in the Firebase Console.
  url: '/google/login',
  // This must be true.
  handleCodeInApp: true,
  iOS: {
    bundleId: 'com.example.ios'
  },
  android: {
    packageName: 'com.example.android',
    installApp: true,
    minimumVersion: '12'
  },
  dynamicLinkDomain: 'example.page.link'
};


firebase.auth().sendSignInLinkToEmail(email, actionCodeSettings)
  .then(function() {
    // The link was successfully sent. Inform the user.
    // Save the email locally so you don't need to ask the user for it again
    // if they open the link on the same device.
    window.localStorage.setItem('emailForSignIn', email);
  })
  .catch(function(error) {
    // Some error occurred, you can inspect the code: error.code
  });

</script>
<script>

</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
이메일 : <input type = "text" name = "email" id = "email" ><br>
직급: <input type = "text" name = "memRank" id = "memRank" ><br>
이름: <input type = "text" name = "memName" id = "memName" ><br>
아이디: <input type = "text" name = "memId" id = "memId" ><br>
비밀번호: <input type = "password" name = "memPass" id = "memPass" ><br>
비밀번호 확인: <input type = "password" name = "memPass2" id = "memPass2"><br>

<input type = "submit" name = "regSubmit" id = "regSubmit" onclick = "sendSignInLinkToEmail()"><br>
<input type = "reset" name = "regCancle" id = "regCancle"><br> 

</body>
</html>