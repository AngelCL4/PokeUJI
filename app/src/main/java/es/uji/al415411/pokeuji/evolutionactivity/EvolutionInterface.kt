package es.uji.al415411.pokeuji.evolutionactivity

import es.uji.al415411.pokeuji.networkclasses.Evolution
import es.uji.al415411.pokeuji.networkclasses.Specie

interface EvolutionInterface {

    fun showSearchError(error: Throwable)

    fun setListener(pokemonS: Evolution)

    fun showEvolution(evol: Evolution)
}