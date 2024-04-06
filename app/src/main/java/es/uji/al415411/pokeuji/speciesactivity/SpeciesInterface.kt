package es.uji.al415411.pokeuji.speciesactivity

import es.uji.al415411.pokeuji.networkclasses.Specie

interface SpeciesInterface {

    fun showSpeciesInfo(pokemonS: Specie, numVersion: Int)
    fun showVersion(description: String)
    fun showVarietiesData(specie: Specie)
    fun showSearchError(error: Throwable)
}
