package com.example.rxjavaapp.retrofit

import com.example.rxjavaapp.model.CountryStatResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {

    @GET("/statistics")
    fun getCountriesStats() : Observable<CountryStatResponse>
}