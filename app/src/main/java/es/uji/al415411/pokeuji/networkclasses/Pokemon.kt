package es.uji.al415411.pokeuji.networkclasses

class Pokemon (
    val id: String,
    val name: String,
    val weight: Int,
    val height: Int,
    val species: Species,
    val sprites: Sprites,
    val abilities: List<Ability>,
    val types: List<Type>
)