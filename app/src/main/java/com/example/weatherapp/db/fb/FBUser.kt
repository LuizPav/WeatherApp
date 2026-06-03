package com.example.weatherapp.db.fb

import com.example.weatherapp.model.User

class FBUser {
    var name: String? = null;
    var email: String? = null;
    fun toUser() = User(name!!, email!!);
}

fun User.toTBUser() : FBUser {
    val fbUser = FBUser();

    fbUser.name = this.name;
    fbUser.email = this.email;

    return fbUser;
}