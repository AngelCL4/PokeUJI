package es.uji.al415411.pokeuji.networkclasses

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PokeApiV2 {
    @Headers("Accept: application/json")
    @GET("pokemon-species/{id}")
    suspend fun getSpecies(@Path("id") name: String): SpeciesResponse
}