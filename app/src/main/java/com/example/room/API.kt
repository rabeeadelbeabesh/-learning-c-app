package com.example.room

import retrofit2.Call
import retrofit2.http.GET

interface API {
   @GET("index/")
 //  @GET("comments/")
fun getedata ():Call<ArrayList<data>>


}