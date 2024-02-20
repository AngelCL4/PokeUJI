package es.uji.al415411.pokeuji.pokemonactivity

import es.uji.al415411.pokeuji.networkclasses.Pokemon

interface PokemonInterface {
    fun showPokemonData(pokemon: Pokemon)
    fun showSearchError(error: Throwable)
    //fun showImage(pokemon: Pokemon)
}