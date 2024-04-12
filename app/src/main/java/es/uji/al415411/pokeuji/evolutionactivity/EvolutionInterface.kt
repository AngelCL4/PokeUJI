package es.uji.al415411.pokeuji.evolutionactivity

import es.uji.al415411.pokeuji.networkclasses.Evolution

interface EvolutionInterface {

    fun cambiaTitulo(evolution: Evolution)
    fun showSearchError(error: Throwable)
}