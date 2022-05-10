package com.huyhuynh.mypokedex.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "account")
data class Account constructor(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idlocal")
    @SerializedName("idlocal")
    var idlocal: Int? = null,

    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: String? = null,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String? = null,

    @ColumnInfo(name = "imageurl")
    @SerializedName("imageurl")
    var imageurl: String? = null,

    @ColumnInfo(name = "xdescription")
    @SerializedName("xdescription")
    var xdescription: String? = null,
    )