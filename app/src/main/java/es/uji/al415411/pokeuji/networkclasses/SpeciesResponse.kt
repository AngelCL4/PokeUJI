package es.uji.al415411.pokeuji.networkclasses

class SpeciesResponse (
    val name: String,
    val varieties: List<Variety>
)

class Variety (
    val variety: VarietyInfo
)

class VarietyInfo (
    val name: String
)
