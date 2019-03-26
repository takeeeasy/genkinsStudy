package com.com.com.firebase;

import java.io.FileInputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.FirebaseApp;;

public class FirebaseConfig {

	public FirebaseApp init() throws Exception {
		FileInputStream serviceAccount  = new FileInputStream("C:\\Users\\giant\\Desktop\\approval-project-231703-firebase-adminsdk-arv33-ee638dcef6.json");
//		String refreshToken = "762544477250-nqq6pkcnaipch1b7t59fr2d8g57amksq.apps.googleusercontent.com";
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount ))
				.setDatabaseUrl("https://approval-project-231703.firebaseio.com/")
				.build();
		
		return FirebaseApp.initializeApp(options);
	}
}
