package com.setia.myfootballmatch.helper

import com.setia.myfootballmatch.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class FootballClient private constructor() {
    lateinit var service: FootballService

    init {
        setupRestClient()
    }

    fun get() = service

    fun setupRestClient() {
        val builder = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = builder.create(FootballService::class.java)
    }

    private object Holder { val INSTANCE = FootballClient() }

    companion object {
        val sharedInstance: FootballClient by lazy { Holder.INSTANCE }
    }

}