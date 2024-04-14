package es.uji.al415411.pokeuji.models

class Evolution (
    val chain: Chain
)

class Chain (
    val species: Info,
    val evolves_to: List<Chain?>
)

