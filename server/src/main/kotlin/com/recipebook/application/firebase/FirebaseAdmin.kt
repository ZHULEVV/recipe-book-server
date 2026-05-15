package com.recipebook.application.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.slf4j.LoggerFactory
import java.io.FileInputStream

object FirebaseAdmin {
    private val log = LoggerFactory.getLogger(FirebaseAdmin::class.java)

    fun init(credentialsPath: String) {
        if (FirebaseApp.getApps().isNotEmpty()) return
        try {
            val credentials = FileInputStream(credentialsPath)
            val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(credentials))
                .build()
            FirebaseApp.initializeApp(options)
            log.info("Firebase initialized from $credentialsPath")
        } catch (e: Exception) {
            log.warn("Firebase not initialized: ${e.message}")
        }
    }
}
