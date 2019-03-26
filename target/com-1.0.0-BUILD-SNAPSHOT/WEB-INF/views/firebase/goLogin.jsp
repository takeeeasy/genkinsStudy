<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://www.gstatic.com/firebasejs/5.8.3/firebase.js"></script>
<script src="https://www.gstatic.com/firebasejs/5.5.2/firebase-app.js"></script>
<script src="https://www.gstatic.com/firebasejs/5.5.2/firebase-auth.js"></script>
<script src="https://cdn.firebase.com/libs/firebaseui/3.1.1/firebaseui.js"></script>
<link type="text/css" rel="stylesheet" href="https://cdn.firebase.com/libs/firebaseui/3.1.1/firebaseui.css" />
<script>
// Initialize Firebase
// TODO: Replace with your project's customized code snippet
  var config = {
    apiKey: "AIzaSyAr7oKz5BioWcNRMrARqr9yGLI1xKscOj4",
    authDomain: "approval-project-231703.firebaseapp.com",
    databaseURL: "https://approval-project-231703.firebaseio.com",
    projectId: "approval-project-231703",
    storageBucket: "approval-project-231703.appspot.com",
    messagingSenderId: "762544477250"
  };
  firebase.initializeApp(config);
  
  // Initialize the FirebaseUI Widget using Firebase.
  var ui = new firebaseui.auth.AuthUI(firebase.auth());
//Temp variable to hold the anonymous user data if needed.
  var data = null;
  // Hold a reference to the anonymous current user.
  var anonymousUser = firebase.auth().currentUser;
  
  var uiConfig = {
		  // Whether to upgrade anonymous users should be explicitly provided.
		  // The user must already be signed in anonymously before FirebaseUI is
		  // rendered.
		  autoUpgradeAnonymousUsers: true,
		  callbacks: {
			    // signInFailure callback must be provided to handle merge conflicts which
			    // occur when an existing credential is linked to an anonymous user.
		     signInFailure: function(error) {
		      // For merge conflicts, the error.code will be
		      // 'firebaseui/anonymous-upgrade-merge-conflict'.
		      if (error.code != 'firebaseui/anonymous-upgrade-merge-conflict') {
		        return Promise.resolve();
		      }
		      // The credential the user tried to sign in with.
		      var cred = error.credential;
		      // Copy data from anonymous user to permanent user and delete anonymous
		      // user.
		      // ...
		      // Finish sign-in after data is copied.
		      return firebase.auth().signInWithCredential(cred);
		    },
		    signInSuccessWithAuthResult: function(authResult, redirectUrl) {
		      // User successfully signed in.
		      // Return type determines whether we continue the redirect automatically
		      // or whether we leave that to developer to handle.
		       
				var password = "@3qglkjasdf";
				var user = firebase.auth().currentUser;
		      
		        firebase.auth().onAuthStateChanged(function(user) {
				  if (user) {
				    // User is signed in.
			     	var displayName = user.displayName;
				    var email = user.email;
				    var emailVerified = user.emailVerified;
				    var photoURL = user.photoURL;
				    var isAnonymous = user.isAnonymous;
				    var uid = user.uid;
				    var providerData = user.providerData;
				  firebase.auth().signInWithEmailAndPassword(email, password)
				  .then(function(user){
					  alert('1');
					  console.log('success');
				  }).catch(function(error) {
					  // Handle Errors here.
					  var errorCode = error.code;
					  var errorMessage = error.message;
					  alert('2');
					  console.log(errorCode+": " + errorMessage);
					  // ...
					});
				  } else {
				    // No user is signed in.
				    alert('error');
				  }
				});
		      return true;
		    },
		    uiShown: function() {
		      // The widget is rendered.
		      // Hide the loader.
		      document.getElementById('loader').style.display = 'none';
		    }
		  },
		  // Will use popup for IDP Providers sign-in flow instead of the default, redirect.
		  signInFlow: 'popup',
		  signInSuccessUrl: '<url-to-redirect-to-on-success>',
		  signInOptions : [
			    {
			      provider: firebase.auth.GoogleAuthProvider.PROVIDER_ID,
			      scopes: [
			        'https://www.googleapis.com/auth/contacts.readonly'
			      ],
			      customParameters: {
			        // Forces account selection even when one account
			        // is available.
			        prompt: 'select_account'
			      }
			    },
			    {
			      provider: firebase.auth.FacebookAuthProvider.PROVIDER_ID,
			      scopes: [
			        'public_profile',
			        'email',
			        'user_likes',
			        'user_friends'
			      ],
			      customParameters: {
			        // Forces password re-entry.
			        auth_type: 'reauthenticate'
			      }
			    },
			    {
			        provider: firebase.auth.PhoneAuthProvider.PROVIDER_ID,
			        recaptchaParameters: {
			          type: 'image', // 'audio'
			          size: 'normal', // 'invisible' or 'compact'
			          badge: 'bottomleft' //' bottomright' or 'inline' applies to invisible.
			        },
			        defaultCountry: 'GB', // Set default country to the United Kingdom (+44).
			        // For prefilling the national number, set defaultNationNumber.
			        // This will only be observed if only phone Auth provider is used since
			        // for multiple providers, the NASCAR screen will always render first
			        // with a 'sign in with phone number' button.
			        defaultNationalNumber: '1234567890',
			        // You can also pass the full phone number string instead of the
			        // 'defaultCountry' and 'defaultNationalNumber'. However, in this case,
			        // the first country ID that matches the country code will be used to
			        // populate the country selector. So for countries that share the same
			        // country code, the selected country may not be the expected one.
			        // In that case, pass the 'defaultCountry' instead to ensure the exact
			        // country is selected. The 'defaultCountry' and 'defaultNationaNumber'
			        // will always have higher priority than 'loginHint' which will be ignored
			        // in their favor. In this case, the default country will be 'GB' even
			        // though 'loginHint' specified the country code as '+1'.
			        loginHint: '+11234567890'
			      },
			      
			    firebase.auth.TwitterAuthProvider.PROVIDER_ID, // Twitter does not support scopes.
			    firebase.auth.EmailAuthProvider.PROVIDER_ID, // Other providers don't need to be given as object.
			  ],
		  // Terms of service url.
		  tosUrl: '<your-tos-url>',
		  // Privacy policy url.
		  privacyPolicyUrl: '<your-privacy-policy-url>'
		};
  
//The start method will wait until the DOM is loaded.
  ui.start('#firebaseui-auth-container', uiConfig);
  

  firebase.auth().languageCode = 'kor';
//To apply the default browser preference instead of explicitly setting it.
//firebase.auth().useDeviceLanguage();
  
  
function openRegister(){
	
var popupX = (window.screen.width / 2) - (600 / 2);
// 만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음

var popupY= (window.screen.height /2) - (400 / 2);
// 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음
window.open('/google/regist', 'Register', 'status=no, height=400, width=600, left='+ popupX + ', top='+ popupY + ', screenX='+ popupX + ', screenY= '+ popupY);
}  

</script>

</head>
<body>
<h3>google Login Test Page.</h3>
<!-- The surrounding HTML is left untouched by FirebaseUI.
     Your app may use that space for branding, controls and other customizations.-->
<h1>Welcome to My Awesome App</h1>
<div id="firebaseui-auth-container">
<input type = "button" name = "regist" id = "regist" value = "Regist" onclick = "openRegister()">
</div>
<div id="loader">Loading...</div>
</body>
</html>