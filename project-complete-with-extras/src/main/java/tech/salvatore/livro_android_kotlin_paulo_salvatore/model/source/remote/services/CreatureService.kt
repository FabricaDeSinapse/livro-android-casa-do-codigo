package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.remote.services

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.remote.entity.CreatureApiResponse

interface CreatureService {
    @GET("creature")
    fun findAll(): Observable<List<CreatureApiResponse>>
}
