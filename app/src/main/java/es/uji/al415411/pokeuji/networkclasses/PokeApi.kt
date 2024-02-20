package es.uji.al415411.pokeuji.networkclasses

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PokeApi {
    @Headers("Accept: application/json")
    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") name: String): PokemonResponse
}