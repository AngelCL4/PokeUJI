package es.uji.al415411.pokeuji.networkclasses

class Evolution (
    val chain: Chain
)

class Chain (
    val species: Info,
    val evolves_to: List<Chain?>
)

