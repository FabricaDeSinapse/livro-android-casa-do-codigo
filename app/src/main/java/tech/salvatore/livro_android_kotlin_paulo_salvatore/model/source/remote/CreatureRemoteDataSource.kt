package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.remote.api.services.CreatureService

object CreatureRemoteDataSource {
    private const val baseUrl = "http://192.168.15.8:3000/"

    private val service: CreatureService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(CreatureService::class.java)
    }

    val creatures by lazy {
        service.findAll(false)
    }
}
