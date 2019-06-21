package com.example.bulletjournal.enums

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "work_table")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val word: String
)
