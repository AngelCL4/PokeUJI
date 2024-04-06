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
            val speciesResponse = api.getSpecies(id.lowercase())
            val version_list = ArrayList<String>()
            val flavor_text_list = ArrayList<String>()
            for (version in speciesResponse.flavor_text_entries) {
                if(version.language.name == "en") {
                    version_list.add(version.version.name)
                    flavor_text_list.add(version.flavor_text)
                }
            }
            val variety_list = ArrayList<String>()
            for(varieties in speciesResponse.varieties) {
                variety_list.add(varieties.pokemon.name)
            }
            with(speciesResponse) {
                Result.success(Specie(flavor_text_list, name, version_list, variety_list, varieties))
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

}