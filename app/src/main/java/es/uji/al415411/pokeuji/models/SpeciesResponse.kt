package es.uji.al415411.pokeuji.models

class SpeciesResponse (
    val name: String,
    val flavor_text_entries: List<FlavourTextEntry>,
    val varieties: List<Variety>,
    val evolution_chain: ChainURL
)


