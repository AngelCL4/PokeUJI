package es.uji.al415411.pokeuji.networkclasses

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object CachedAPI {
    private val api: PokeApi = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(PokeApi::class.java)

    private val pokemonMap = mutableMapOf<String, PokemonResponse>()
    private val specieMap = mutableMapOf<String, SpeciesResponse>()

    suspend fun getPokemon(id: String): PokemonResponse {
        return if (pokemonMap.containsKey(id)) {
            pokemonMap[id] ?: api.getPokemon(id).also { pokemonMap[id] = it }
        } else {
            api.getPokemon(id).also { pokemonMap[id] = it }
        }
    }

    suspend fun getSpecies(id: String): SpeciesResponse {
        return if (specieMap.containsKey(id)) {
            specieMap[id] ?: api.getSpecies(id).also { specieMap[id] = it }
        } else {
            api.getSpecies(id).also { specieMap[id] = it }
        }
    }
}