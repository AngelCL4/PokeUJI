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

    fun evolutionSwitch() {
        val url = specie?.evolution_chain!!.url
        var digito = false
        var evo_chain: String = ""
        var i = 0
        while (i < url.length){
            println(evo_chain)
            if (digito){
                evo_chain += url[i]
            }
            if ((url[i] == '/') && (i+1 != url.length)){
                if (url[i+1].isDigit()){
                    digito = true
                }

            }
            else if ((url[i].isDigit()) && (url[i+1] == '/')){
                digito = false
            }
            i += 1
        }
        println(evo_chain)
        view?.showEvolutionView(evo_chain)
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
