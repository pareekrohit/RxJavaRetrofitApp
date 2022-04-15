package com.example.rxjavaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.rxjavaapp.databinding.ActivityMainBinding
import com.example.rxjavaapp.model.CountryStatResponse
import com.example.rxjavaapp.retrofit.ApiClient
import io.reactivex.ObservableEmitter
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var animalArr: List<String> = listOf("This", "Is", "Totally", "Immutable")
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRxJava()
    }

    private fun setRxJava() {
        /* val observable: Observable<List<String>=Observable.just(animalArr)*/

        Toast.makeText(this, "api calling", Toast.LENGTH_LONG).show()
        ApiClient().getCountriesStats()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
    }

    private var observer = object :
        DisposableObserver<CountryStatResponse>() {
        override fun onNext(t: CountryStatResponse) {
            Log.d("Main", "RESULT $t")
            Toast.makeText(this@MainActivity, "Data : $t.toString()", Toast.LENGTH_LONG).show()
            binding.TvRetrofit.text = t.toString()
        }

        override fun onError(e: Throwable) {
            Log.d("Main", "OnError $e")
        }

        override fun onComplete() {
            Log.d("Main", "onComplete")
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        observer.dispose()
    }
}