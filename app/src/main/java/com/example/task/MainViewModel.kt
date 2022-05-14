package com.example.task

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class MainViewModel:ViewModel() {

    protected val db = FirebaseFirestore.getInstance()
    fun addUsers(user: User){
        FirebaseAuth.getInstance().uid?.let { uid ->
            db.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
            db.collection("User")
                .document()
                .set(user)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        Log.d("Firebase", it.toString())
                    }
                }
                .addOnFailureListener {
                    it.message?.let { it1 -> Log.d("Firebase", it1) }
                }
        }

    }
    val result= MutableLiveData<List<User>>()
    fun getUser():MutableLiveData<List<User>>{
            FirebaseAuth.getInstance().uid?.let { uid ->
                db.collection("User")
                    .get()
                    .addOnSuccessListener {
                        result.value=it.toObjects(User::class.java)
                            Log.d("cuisines","$it")

                    }
                    .addOnFailureListener {
                        Log.d("cuisines","${it.message}")
                    }

            }

           return result
    }

}