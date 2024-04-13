package es.uji.al415411.pokeuji.evolutionactivity

import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.uji.al415411.pokeuji.networkclasses.Evolution
import es.uji.al415411.pokeuji.networkclasses.PokemonRepository
import es.uji.al415411.pokeuji.networkclasses.Specie
import es.uji.al415411.pokeuji.speciesactivity.SpeciesInterface
import kotlinx.coroutines.launch
class EvolutionViewModel: ViewModel() {
    fun onBeginning(id: String) {
        viewModelScope.launch(Dispatchers.Main) {
            PokemonRepository.getEvolution(id)
                .onSuccess {
                    evolution = it
                }
                .onFailure { view?.showSearchError(it) }


        }
    }

    var view: EvolutionInterface? = null
        set(value) {
            field = value
            if (value != null) {

            }
        }

    var evolution: Evolution? = null
        set(value) {
            field = value
            if(value != null){

            }
        }
}