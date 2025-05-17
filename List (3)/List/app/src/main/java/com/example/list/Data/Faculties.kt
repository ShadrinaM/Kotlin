package com.example.list.Data

import com.google.gson.annotations.SerializedName

class Faculties {
    @SerializedName("items") lateinit var items: List<Faculty>
}