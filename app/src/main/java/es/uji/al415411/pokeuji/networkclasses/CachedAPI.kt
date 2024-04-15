package es.uji.al415411.pokeuji.networkclasses

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import es.uji.al415411.pokeuji.models.EvolutionResponse
import es.uji.al415411.pokeuji.models.PokemonResponse
import es.uji.al415411.pokeuji.models.SpeciesResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Path

object CachedAPI {
    val api: PokeApi
    init{
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        api = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(PokeApi::class.java)
    }

    private val pokemonMap : MutableMap<String, PokemonResponse> = mutableMapOf()
    private val specieMap : MutableMap<String, SpeciesResponse> = mutableMapOf()
    private val evolutionMap : MutableMap<String, EvolutionResponse> = mutableMapOf()

    suspend fun getPokemon(@Path("id") name: String): PokemonResponse {
        if (pokemonMap[name] == null) pokemonMap[name] = api.getPokemon(name)
        return pokemonMap[name]!!
    }

    suspend fun getSpecies(@Path("id") name: String): SpeciesResponse {
        if (specieMap[name] == null) specieMap[name] = api.getSpecies(name)
        return specieMap[name]!!
    }

    suspend fun getEvolution(@Path("id") name: String): EvolutionResponse {
        if (evolutionMap[name] == null) evolutionMap[name] = api.getEvolution(name)
        return evolutionMap[name]!!
    }
}