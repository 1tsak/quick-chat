package com.akbros.quickchat.models

data class User (
    val userId: String,
    val username: String,
    val email: String,
    val profilePictureUrl: String? = null
)