package es.uji.al415411.pokeuji.networkclasses


import com.squareup.moshi.Json



class Pokemon (
    val id: String,
    val name: String,
    val weight: Int,
    val height: Int,
    val species: Info,
    val sprites: Sprites,
    val abilities: List<String>,
    val types: List<String>
)


class Info (
    val name: String
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
    val other: Other
)

class Other (
    val home: Home,
    @Json(name="official-artwork")
    val officialartwork: Official,
    val showdown: Showdown
)

class Home (
    val front_default: String? = null,
    val front_female: String? = null,
    val front_shiny: String? = null,
    val front_shiny_female: String? = null
)

class Official(
    val front_default: String? = null,
    val front_shiny: String? = null
)

class Showdown (
    val back_default: String? = null,
    val back_female: String? = null,
    val back_shiny: String? = null,
    val back_shiny_female: String? = null,
    val front_default: String? = null,
    val front_female: String? = null,
    val front_shiny: String? = null,
    val front_shiny_female: String? = null
)

class Ability (
    val ability: AbilityInfo
)

class AbilityInfo (
    val name: String
)

class Type(
    val type: TypeInfo
)

class TypeInfo(
    val name: String
)