package com.huyhuynh.mypokedex.data.model

import com.google.gson.annotations.SerializedName

class MutilTestCase {

    @SerializedName("name")
    var name: String? = null

    @SerializedName("account")
    var account: Account? = null

    @SerializedName("listAccount")
    var listAccount: List<Account>? = null

    @SerializedName("xdescription")
    var xdescription: String? = null

    constructor(
        name: String?,
        account: Account?,
        listAccount: List<Account>?,
        xdescription: String?
    ) {
        this.name = name
        this.account = account
        this.listAccount = listAccount
        this.xdescription = xdescription
    }
}