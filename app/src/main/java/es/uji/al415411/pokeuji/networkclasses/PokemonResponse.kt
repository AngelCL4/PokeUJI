package es.uji.al415411.pokeuji.networkclasses

class PokemonResponse (
    val name: String,
    val weight: Int,
    val height: Int,
    val species: Species,
    val sprites: Sprites,
    val abilities: List<Ability>
)

class Species (
    val name: String,
    val url: String
)

class Sprites (
    val back_default: String? = null,
    val back_female: String? = null,
    val back_shiny: String? = null,
    val back_shiny_female: String? = null,
    val front_default: String? = null,
    val front_female: String? = null,
    val front_shiny: String? = null,
    val front_shiny_female: String? = null,

)

class Ability (
    val ability: AbilityInfo
)

class AbilityInfo (
    val name: String
)