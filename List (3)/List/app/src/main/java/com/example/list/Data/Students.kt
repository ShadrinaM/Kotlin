package com.example.list.Data

import com.google.gson.annotations.SerializedName

class Students {
    @SerializedName("items") lateinit var items: List<Student>
}