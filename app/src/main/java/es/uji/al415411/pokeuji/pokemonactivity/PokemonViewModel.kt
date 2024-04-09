package es.uji.al415411.pokeuji.pokemonactivity

import es.uji.al415411.pokeuji.networkclasses.Pokemon
import es.uji.al415411.pokeuji.networkclasses.PokemonRepository
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel() {
    fun onPokemonSearchRequested(id: String) {
        viewModelScope.launch(Dispatchers.Main) {
            PokemonRepository.getPokemon(id)
                .onSuccess { pokemon = it }
                .onFailure { view?.showSearchError(it) }
        }
    }

    var view: PokemonInterface? = null
        set(value) {
            field = value
            if (value != null)
                pokemon?.let { displayPokemon(it) }
        }
    var pokemon: Pokemon? = null
        set(value) {
            field = value
            if (value != null)
                displayPokemon(value)
        }

    private fun displayPokemon(pokemon: Pokemon) = view ?. apply {
        showPokemonData(pokemon)
        showImage(pokemon)
    }

    fun speciesSwitch() {
        view?.showSpeciesView(pokemon!!.species)
    }

    fun onAbilityDialog() {
        view?.showAbilities()
    }

    fun onTypeDialog(){
        view?.showTypes()
    }
}