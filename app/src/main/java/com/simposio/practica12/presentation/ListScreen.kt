package com.simposio.practica12.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TypesScreen(viewModel: PokeViewModel, onTypeClick: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(viewModel.types.value) { type ->
            ListItemPoke(text = type.name.uppercase(), onClick = { onTypeClick(type.id)})
        }
    }
}

@Composable
fun PokemonsByTypeScreen(
    viewModel: PokeViewModel,
    typeId: String,
    onPokeClick: (String) -> Unit
) {
    LaunchedEffect(typeId) { viewModel.loadPokemonsByType(typeId) }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(viewModel.pokemonsByType.value) { poke ->
            ListItemPoke(text = "#${poke.id} - ${poke.name.uppercase()}", onClick = { onPokeClick(poke.id)})
        }
    }
}

@Composable
fun ListItemPoke(text: String, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onClick() }) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Pokeball simulada
            Box(
                modifier = Modifier.size(24.dp).background(Color.Red, CircleShape).border(2.dp, Color.Black, CircleShape)
            ) {
                Box( modifier = Modifier.size(10.dp).background(Color.White, CircleShape).align(
                    Alignment.Center))
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(text = text, style = MaterialTheme.typography.titleMedium)
        }
    }
}