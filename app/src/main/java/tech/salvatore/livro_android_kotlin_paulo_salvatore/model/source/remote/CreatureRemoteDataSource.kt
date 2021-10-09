package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.remote

import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.remote.api.domain.CreatureApiResponse
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

    val creatures: Observable<List<Creature>> =
        service.findAll().map { creatureApiResponseList ->
            creatureApiResponseList.map { creatureApiResponse ->
                creatureApiResponse.toDomain()
            }
        }

    private fun CreatureApiResponse.toDomain(): Creature {
        return Creature(
            number = number,
            name = name,
            imageUrl = image,
            evolveTo = evolveTo?.toDomain(),
        )
    }
}
