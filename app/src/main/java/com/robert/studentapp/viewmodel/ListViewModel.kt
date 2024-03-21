package com.robert.studentapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.robert.studentapp.model.Student

class ListViewModel(application: Application):AndroidViewModel(application) {
    //life data berupa arr data supaya adapter bisa nerima data
    val studentsLD = MutableLiveData<ArrayList<Student>>()
    val loadingLD = MutableLiveData<Boolean>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue:RequestQueue ?=null

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    //namanya bisa fetch, load
    fun refresh(){

        loadingLD.value = true
        studentLoadErrorLD.value = false

        val url = "http://adv.jitusolution.com/student.php"
        queue = Volley.newRequestQueue(getApplication())

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                loadingLD.value = false
                //it berisi json string
                Log.d("show_volley", it)
                //object untuk gson
                val sType = object:TypeToken<List<Student>>() {}.type
                val result = Gson().fromJson<List<Student>>(it, sType)

                studentsLD.value = result as ArrayList<Student>


            },
            {
                Log.d("show_volley", it.toString())

            }

        )

        stringRequest.tag =TAG
        queue?.add(stringRequest)

    }
}