package com.example.demoappwithrxtech.retrofit

import com.example.demoappwithrxtech.model.PostModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface RestApi {
    @Headers("Content-Type: application/json")
    @GET("posts")
    fun getPost(): Observable<PostModel>
}