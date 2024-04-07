package es.uji.al415411.pokeuji.speciesactivity

import es.uji.al415411.pokeuji.networkclasses.Specie
import es.uji.al415411.pokeuji.networkclasses.PokemonRepository
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.uji.al415411.pokeuji.networkclasses.Pokemon
import kotlinx.coroutines.launch
class SpeciesViewModel: ViewModel() {
    fun onBeginning(id: String) {
        viewModelScope.launch(Dispatchers.Main) {
            PokemonRepository.getSpecies(id)
                .onSuccess {
                    specie = it
                    view?.setListener(it)
                }
                .onFailure { view?.showSearchError(it) }
        }
    }

    var view: SpeciesInterface? = null
        set(value) {
            field = value
            if (value != null) {
                specie?.let {displaySpecie(it)}
            }
        }

    var specie: Specie? = null
        set(value) {
            field = value
            if(value != null){
                displaySpecie(value)

            }
        }

    var numVersion: Int = 2
        set

    private fun displaySpecie(specie: Specie?) = view ?. apply {
        if(specie != null)
        {
            view?.showSpeciesInfo(specie, numVersion)
        }
    }



    fun changeVersion(position: Int) {
        numVersion = position
        val descriptions = specie?.flavourTextEntries
        if(descriptions != null)
        {
            view?.showVersion(descriptions[numVersion])
        }
    }

}
