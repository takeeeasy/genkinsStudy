package com.com.com.firebase;

import java.net.URI;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

public class FirebaseUserConfig {

	public void getUser(String uid) throws FirebaseAuthException {
		UserRecord user = FirebaseAuth.getInstance().getUser(uid);
		if (user != null) {
			// User is signed in
			String name = user.getDisplayName();
			String email = user.getEmail();
			String photoUrl = user.getPhotoUrl();
			
		    boolean emailVerified = user.isEmailVerified();

		    // The user's ID, unique to the Firebase project. Do NOT use this value to
		    // authenticate with your backend server, if you have one. Use
		    // FirebaseUser.getIdToken() instead.
		    String userid = user.getUid();
		} else {
			// No user is signed in
		}
	}
}
