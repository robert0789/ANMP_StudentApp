package com.robert.studentapp.model

import com.google.gson.annotations.SerializedName

data class Student(
    var id: String?,
    //biar sama kayak di json
    @SerializedName("student_name")
    var name:String?,
    @SerializedName("birth_of_date")
    var dob: String?,
    var phone: String?,
    @SerializedName("photo_url")
    var photoUrl: String?
)