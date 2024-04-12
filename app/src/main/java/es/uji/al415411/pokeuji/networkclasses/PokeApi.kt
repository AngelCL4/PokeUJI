package es.uji.al415411.pokeuji.networkclasses

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PokeApi {
    @Headers("Accept: application/json")
    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") name: String): PokemonResponse

    @GET("pokemon-species/{id}")
    suspend fun getSpecies(@Path("id") name: String): SpeciesResponse

    @GET("evolution-chain/{id}")
    suspend fun getEvolution(@Path("id") name: String): EvolutionResponse
}