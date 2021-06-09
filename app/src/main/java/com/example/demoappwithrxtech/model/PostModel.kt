package com.example.demoappwithrxtech.model

class PostModel : ArrayList<PostModelItem>()
data class PostModelItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)