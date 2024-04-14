package es.uji.al415411.pokeuji.pokemonactivity

import es.uji.al415411.pokeuji.models.Pokemon
import es.uji.al415411.pokeuji.models.Info

interface PokemonInterface {
    fun showPokemonData(pokemon: Pokemon)
    fun showSearchError(error: Throwable)
    fun showImage(pokemon: Pokemon)
    fun showTypes()
    fun showAbilities()
    fun showSpeciesView(species: Info)

}