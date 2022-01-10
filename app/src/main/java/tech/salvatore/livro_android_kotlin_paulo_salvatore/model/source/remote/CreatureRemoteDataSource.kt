package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.remote

import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.remote.api.entity.CreatureApiResponse
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.remote.api.services.CreatureService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreatureRemoteDataSource @Inject constructor() {
    companion object {
        // TODO: Extract to external object
        private const val BASE_URL = "https://backend-livro-android-casa-cod.herokuapp.com/"
    }

    private val service: CreatureService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(CreatureService::class.java)
    }

    val creatures: Observable<List<Creature>> =
        service.findAll().map { list -> list.map { it.toDomain() } }

    private fun CreatureApiResponse.toDomain(): Creature {
        return Creature(
            number = number,
            name = name,
            imageUrl = image,
            legendary = legendary,
            evolveTo = evolveTo?.toDomain(),
            canInteract = true,
        )
    }
}
