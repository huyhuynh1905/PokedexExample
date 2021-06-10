package com.huyhuynh.mypokedex.data.model

import com.google.gson.annotations.SerializedName

class Account {

    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("imageurl")
    var imageurl: String? = null

    @SerializedName("xdescription")
    var xdescription: String? = null


    constructor(id: String?, name: String?, imageurl: String?, xdescription: String?) {
        this.id = id
        this.name = name
        this.imageurl = imageurl
        this.xdescription = xdescription
    }
}