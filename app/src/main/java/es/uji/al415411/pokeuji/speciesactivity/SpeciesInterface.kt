package es.uji.al415411.pokeuji.speciesactivity

import es.uji.al415411.pokeuji.models.Specie

interface SpeciesInterface {

    fun showSpeciesInfo(pokemonS: Specie, numVersion: Int)
    fun showVersion(description: String)
    fun showVarietiesData()
    fun showSearchError(error: Throwable)
    fun setListener(pokemonS: Specie)
    fun showEvolutionView(species: String)
}
