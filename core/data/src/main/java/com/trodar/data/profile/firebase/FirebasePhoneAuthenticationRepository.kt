package com.trodar.data.profile.firebase

import android.app.Activity
import android.content.Context
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.trodar.common.Settings
import com.trodar.data.profile.AuthenticationRepository
import com.trodar.data.profile.fakeapi.AuthRequest
import com.trodar.data.profile.fakeapi.AuthenticationService
import com.trodar.model.Profile
import com.trodar.model.Verification
import com.trodar.model.VerificationResult
import com.trodar.theme.R
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FirebasePhoneAuthenticationRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val authenticationService: AuthenticationService,
    private val settings: Settings,
) : AuthenticationRepository {
    private val phonePrefix = "+38"

    override fun signUp(context: Context, profile: Profile): Flow<VerificationResult> = callbackFlow {
        try {

            val errorMessage = context.getString(R.string.verification_failed)
            val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {

                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    this@callbackFlow.trySendBlocking(
                        VerificationResult.Failure(p0.message ?: errorMessage)
                    )
                }

                override fun onCodeSent(
                    verificationId: String,
                    p1: PhoneAuthProvider.ForceResendingToken
                ) {
                    this@callbackFlow.trySendBlocking(
                        VerificationResult.Success(verificationId)
                    )
                }
            }
            sendVerificationCode(
                phoneNumber = phonePrefix + profile.phone,
                auth = auth,
                activity = context as Activity,
                callbacks = callbacks
            )
        } catch (e: Exception) {
            throw (e)

        }
        awaitClose {
            channel.close()
            cancel()
        }
    }

    override fun signIn(
        context: Context,
        profile: Profile,
        verification: Verification,
    ): Flow<Boolean> = callbackFlow {

        try {
            val credential = PhoneAuthProvider.getCredential(
                verification.verificationId,
                verification.otpNumber
            )

            auth.signInWithCredential(credential).await()

            authenticationService.login(
                AuthRequest("mor_2314", "83r5^_")
            ).also {
                if (it.isSuccessful) {
                    settings.setToken(it.body()!!.accessToken)
                }
            }
            this@callbackFlow.trySendBlocking(true)

        } catch (e: Exception) {
            throw (e)
        }
        awaitClose {
            channel.close()
            cancel()
        }
    }

    private fun sendVerificationCode(
        phoneNumber: String,
        auth: FirebaseAuth,
        activity: Activity,
        callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    ) {

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}