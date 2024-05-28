package com.example.musicappui.Login_data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.google.firebase.auth.FirebaseUser

/*
class UserRepository(private val auth: FirebaseAuth,
                     private val firestore: FirebaseFirestore
){

    suspend fun login(email: String, password: String): Result<Boolean> =
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }




    suspend fun signUp(email: String, password: String, firstName: String, lastName: String): Result<Boolean> =
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            //add user to firestore
            val user=User(firstName,lastName,email)
            saveUserToFirestore(user)
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }

    private suspend fun saveUserToFirestore(user: User) {
        firestore.collection("users").document(user.email).set(user).await()
    }
}

 */




class UserRepository(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    suspend fun login(email: String, password: String): Result<Boolean> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun signUp(email: String, password: String, firstName: String, lastName: String): Result<Boolean> {
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val user = authResult.user ?: throw Exception("User is null")
            val userMap = hashMapOf(
                "firstName" to firstName,
                "lastName" to lastName,
                "email" to email
            )
            firestore.collection("users").document(user.uid).set(userMap).await()
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    suspend fun getUserDetails(userId: String): Result<Map<String, Any>> {
        return try {
            val documentSnapshot = firestore.collection("users").document(userId).get().await()
            if (documentSnapshot.exists()) {
                Result.Success(documentSnapshot.data ?: emptyMap())
            } else {
                Result.Error(Exception("User not found"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
