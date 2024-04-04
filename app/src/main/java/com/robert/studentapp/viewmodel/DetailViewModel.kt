package com.robert.studentapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.robert.studentapp.model.Student

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()
    private var queue: RequestQueue?=null
    val TAG = "volleyFilmTag"

    fun fetch(studentID : String) {

        val url = "http://adv.jitusolution.com/student.php?id=$studentID"
        queue = Volley.newRequestQueue(getApplication())

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                //it berisi json string
                Log.d("show_volley", it)
                //object untuk gson
                val sType = object : TypeToken<Student>() {}.type
                val result = Gson().fromJson<Student>(it, sType)

                studentLD.value = result
                Log.d("show_student", it.toString())


            },
            {
                Log.d("show_volley", it.toString())

            }

        )

        stringRequest.tag =TAG
        queue?.add(stringRequest)
//        val student1 = Student("16055","Nonie","1998/03/28","5718444778",
//            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
//        studentLD.value = student1
    }

}