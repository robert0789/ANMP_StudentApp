package com.robert.studentapp.model

data class Film(
    val id: Int,
    val title: String,
    val director: Director,
    val year: Int,
    val genre: ArrayList<String>,
    val images: ArrayList<String>

)


