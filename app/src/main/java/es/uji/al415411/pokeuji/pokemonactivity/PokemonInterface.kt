package es.uji.al415411.pokeuji.pokemonactivity

import android.app.Dialog
import android.os.Bundle
import es.uji.al415411.pokeuji.networkclasses.Pokemon
import es.uji.al415411.pokeuji.networkclasses.Info

interface PokemonInterface {
    fun showPokemonData(pokemon: Pokemon)
    fun showSearchError(error: Throwable)
    fun showImage(pokemon: Pokemon)

    fun showSpeciesView(species: Info)

}