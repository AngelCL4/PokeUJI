package es.uji.al415411.pokeuji.networkclasses

class PokemonResponse (
    val name: String,
    val weight: Int,
    val height: Int,
    val species: Info,
    val sprites: Sprites,
    val abilities: List<Ability>,
    val types: List<Type>
)
