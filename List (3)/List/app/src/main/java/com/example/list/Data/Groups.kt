package com.example.list.Data

import com.google.gson.annotations.SerializedName

class Groups {
    @SerializedName("items") lateinit var items: List<Group>
}