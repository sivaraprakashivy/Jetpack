package com.example.jetpackcompose

data class Players(val createdAt:String,val name:String,val avatar:String,
                   val score:String,val content:String,val id:String)


data class PlayerResponse(
    val results: List<Players>
)
