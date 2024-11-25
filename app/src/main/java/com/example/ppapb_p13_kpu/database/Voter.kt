package com.example.ppapb_p13_kpu.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "voter_table")
data class Voter(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "nik")
    val nik: String,
    @ColumnInfo(name = "gender")
    val gender: String,
    @ColumnInfo(name = "address")
    val address: String
)