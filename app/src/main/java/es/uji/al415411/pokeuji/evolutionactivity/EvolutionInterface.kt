package es.uji.al415411.pokeuji.evolutionactivity

import es.uji.al415411.pokeuji.models.Evolution

interface EvolutionInterface {

    fun showSearchError(error: Throwable)

    fun setListener(pokemonS: Evolution)

    fun showEvolution(evol: Evolution)

    fun firstListener(name: String)
}