package com.recipebook.application.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.slf4j.LoggerFactory
import java.io.ByteArrayInputStream
import java.io.FileInputStream

object FirebaseAdmin {
    private val log = LoggerFactory.getLogger(FirebaseAdmin::class.java)

    fun init(credentialsPath: String) {
        if (FirebaseApp.getApps().isNotEmpty()) return
        try {
            val json = System.getenv("FIREBASE_CREDENTIALS_JSON")
            val stream = if (!json.isNullOrBlank()) {
                log.info("Firebase initializing from FIREBASE_CREDENTIALS_JSON env var")
                ByteArrayInputStream(json.toByteArray())
            } else {
                log.info("Firebase initializing from $credentialsPath")
                FileInputStream(credentialsPath)
            }
            val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(stream))
                .build()
            FirebaseApp.initializeApp(options)
            log.info("Firebase initialized successfully")
        } catch (e: Exception) {
            log.warn("Firebase not initialized: ${e.message}")
        }
    }
}
