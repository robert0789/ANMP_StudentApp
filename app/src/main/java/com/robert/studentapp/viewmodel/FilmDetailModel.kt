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
import com.robert.studentapp.model.Film
import com.robert.studentapp.model.Student

class FilmDetailModel(application: Application): AndroidViewModel(application) {
    val filmLD = MutableLiveData<ArrayList<Film>>()
    val loadingLD = MutableLiveData<Boolean>()
    val filmLoadErrorLD = MutableLiveData<Boolean>()

    val TAG = "volleyFilmTag"
    private var queue: RequestQueue?=null

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    //namanya bisa fetch, load
    fun refresh(){

        loadingLD.value = true
        filmLoadErrorLD.value = false

        val url = "http://10.0.2.2/anmp/w6/film.json"
        queue = Volley.newRequestQueue(getApplication())

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                loadingLD.value = false
                //it berisi json string
                Log.d("show_volley", it)
                //object untuk gson
                val sType = object: TypeToken<List<Film>>() {}.type
                val result = Gson().fromJson<List<Film>>(it, sType)

                filmLD.value = result as ArrayList<Film>


            },
            {
                Log.d("show_volley", it.toString())

            }

        )

        stringRequest.tag =TAG
        queue?.add(stringRequest)

    }



}