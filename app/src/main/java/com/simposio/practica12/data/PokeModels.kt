package com.simposio.practica12.data

data class NamedResource(val name: String, val url: String) {
    // Extraer ID de la URL
    val id: String get() = url.trim('/').substringAfterLast('/')
}

// Lista de Tipos
data class TypeListResponse(val results: List<NamedResource>)

// Pokemones de la categoria seleccionada
data class PokemonSlot(val pokemon: NamedResource)
data class TypeDetailResponse(val pokemon: List<PokemonSlot>)

data class StatSlot(val base_stat: Int, val stat: NamedResource)
data class TypeSlot(val type: NamedResource)

// Detalles del Pokemon seleccionado
data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val stats: List<StatSlot>,
    val types: List<TypeSlot>
)