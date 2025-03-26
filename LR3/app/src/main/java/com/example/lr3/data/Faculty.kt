package com.example.lr3.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import  java.util.UUID

//структура бд
@Entity(
    indices = [Index("id")]
)
data class Faculty(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "faculty_name") var name: String = ""
)