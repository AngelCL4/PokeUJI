package es.uji.al415411.pokeuji.models

class Specie (
    val flavourTextEntries: List<String>,
    val name: String,
    val version: List<String>,
    val variety: List<String>,
    val varieties: List<Variety>,
    val evolution_chain: ChainURL
)

class FlavourTextEntry (
    val flavor_text: String,
    val language: Info,
    val version: Info
)
class Variety (
    val pokemon: Info,
    val is_default: Boolean
)

class ChainURL (
    val url: String
)
