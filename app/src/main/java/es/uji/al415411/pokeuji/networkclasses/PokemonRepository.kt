package es.uji.al415411.pokeuji.networkclasses

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object PokemonRepository {
    suspend fun getPokemon(id: String) = try {
        withContext(Dispatchers.IO) {
            val pokemonResponse = api.getPokemon(id.lowercase())
            with(pokemonResponse) {
                Result.success(Pokemon(id, name, weight, height, species, sprites, abilities, types))
            }
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getSpecies(id: String) = try {
        withContext(Dispatchers.IO) {
            val speciesResponse = api2.getSpecies(id.lowercase())
            with(speciesResponse) {
                Result.success(Specie(name, varieties))
            }
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    private val api: PokeApi
    init {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        api = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(PokeApi::class.java)
    }

    private val api2: PokeApiV2
    init {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        api2 = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(PokeApiV2::class.java)
    }
}