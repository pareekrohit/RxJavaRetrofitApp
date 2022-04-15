package com.example.rxjavaapp.model

data class CountryStat(val country:String,
                       val population:Int,
                       val cases:Stat,
                       val deaths:Stat,
                       val tests:Stat){

    class Stat(val total:Int){
    }
}