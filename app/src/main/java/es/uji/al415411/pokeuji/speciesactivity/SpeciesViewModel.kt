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
                    displaySpecie(it)
                }
                .onFailure { view?.showSearchError(it) }
        }
    }

    var view: SpeciesInterface? = null

    var specie: Specie? = null

    private fun displaySpecie(specie: Specie) = view ?. apply {
        showVarietiesData(specie)
    }

}
