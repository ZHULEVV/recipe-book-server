package com.recipebook.application.plugin

import com.google.firebase.auth.FirebaseAuth
import com.recipebook.domain.usecase.user.UpsertUserUseCase
import io.ktor.server.application.*
import io.ktor.server.auth.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.context.GlobalContext
import java.util.UUID

data class FirebasePrincipal(val userId: UUID, val firebaseUid: String) : Principal

fun Application.configureAuthentication() {
    install(Authentication) {
        bearer("firebase") {
            authenticate { credential ->
                runCatching {
                    val token = withContext(Dispatchers.IO) {
                        FirebaseAuth.getInstance().verifyIdToken(credential.token)
                    }
                    val upsertUser = GlobalContext.get().get<UpsertUserUseCase>()
                    val user = upsertUser(
                        firebaseUid = token.uid,
                        email = token.email ?: "",
                        displayName = token.name
                    )
                    FirebasePrincipal(userId = user.id, firebaseUid = token.uid)
                }.getOrNull()
            }
        }
    }
}
