package com.simposio.practica12.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
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
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TypesScreen(viewModel: PokeViewModel, onTypeClick: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (viewModel.isLoadingTypes.value) {
            items(10) { SkeletonItem() }
        } else {
            items(viewModel.types.value) { type ->
                ListItemPoke(text = type.name.uppercase(), onClick = { onTypeClick(type.id) })
            }
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

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (viewModel.isLoadingPokemons.value) {
            items(10) { SkeletonItem() }
        } else {
            items(viewModel.pokemonsByType.value) { poke ->
                ListItemPoke(text = "#${poke.id} - ${poke.name.uppercase()}", onClick = { onPokeClick(poke.id) })
            }
        }
    }
}

@Composable
fun ListItemPoke(text: String, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().clickable { onClick() }) {
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

// Skeleton simple: tarjeta con pulso de opacidad
@Composable
fun SkeletonItem() {
    val alpha by rememberInfiniteTransition(label = "skeleton").animateFloat(
        initialValue = 0.3f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(tween(800), RepeatMode.Reverse),
        label = "skeletonAlpha"
    )
    val boneColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.15f)

    Card(modifier = Modifier.fillMaxWidth().alpha(alpha)) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(24.dp).background(boneColor, CircleShape))
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier.height(16.dp).fillMaxWidth(0.5f)
                    .background(boneColor, RoundedCornerShape(4.dp))
            )
        }
    }
}
