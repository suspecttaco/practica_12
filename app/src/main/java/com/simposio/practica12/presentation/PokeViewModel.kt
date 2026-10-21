package com.simposio.practica12.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simposio.practica12.data.NamedResource
import com.simposio.practica12.data.PokeRepository
import com.simposio.practica12.data.PokemonDetailResponse
import kotlinx.coroutines.launch

class PokeViewModel : ViewModel() {
    private val repository = PokeRepository()

    var types = mutableStateOf<List<NamedResource>>(emptyList())
    var pokemonsByType = mutableStateOf<List<NamedResource>>(emptyList())
    var pokemonDetail = mutableStateOf<PokemonDetailResponse?>(null)

    init {
        // Cargar categorias al iniciar
        viewModelScope.launch {
            try {
                types.value = repository.getTypes()
            } catch (e: Exception) { }
        }
    }

    fun loadPokemonsByType(id: String) {
        viewModelScope.launch {
            try {
                pokemonsByType.value = repository.getPokemonsByType(id)
            } catch (e: Exception ) { }
        }
    }

    fun loadPokemonDetail(id: String) {
        pokemonDetail.value = null
        viewModelScope.launch {
            try {
                pokemonDetail.value = repository.getPokemonDetail(id)
            } catch (e: Exception) { }
        }
    }
}