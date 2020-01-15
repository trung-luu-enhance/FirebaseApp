package com.trunghtluu.firebaseapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DatabaseReference
import com.trunghtluu.firebaseapp.model.Message
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.FirebaseDatabase
import com.trunghtluu.firebaseapp.utils.Constants

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var messageReference: DatabaseReference
    private val messageMutableLiveData = MutableLiveData<Message>()

    init {
        messageReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH)

        messageReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (currentData in dataSnapshot.children) {
                    messageMutableLiveData.setValue(currentData.getValue(Message::class.java))
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

                Log.d("TAG_X", " " + databaseError.message)
                //              TODO: Show error
            }
        })
    }

    fun sendRealMessage(message: Message) {
        val childKey = messageReference.push().key
        if (childKey != null)
            messageReference.child(childKey).setValue(message)
    }

    fun getLiveData(): MutableLiveData<Message> {
        return messageMutableLiveData
    }
}