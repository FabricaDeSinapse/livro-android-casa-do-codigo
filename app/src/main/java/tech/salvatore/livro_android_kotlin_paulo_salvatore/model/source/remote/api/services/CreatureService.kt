package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.remote.api.services

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.source.remote.api.domain.CreatureApiResponse

interface CreatureService {
    @GET("creature")
    fun findAll(
        @Query("includeImages") includeImages: Boolean
    ): Observable<List<CreatureApiResponse>>
}
