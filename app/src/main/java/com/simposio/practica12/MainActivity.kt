package com.simposio.practica12

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.simposio.practica12.presentation.PokeViewModel
import com.simposio.practica12.presentation.PokemonDetailScreen
import com.simposio.practica12.presentation.PokemonsByTypeScreen
import com.simposio.practica12.presentation.TypesScreen
import com.simposio.practica12.ui.theme.Practica12Theme

class MainActivity : ComponentActivity() {
    private val viewModel: PokeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica12Theme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                    contentWindowInsets = WindowInsets.safeDrawing
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "tipos",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("tipos") {
                            TypesScreen(viewModel) { id ->
                                navController.navigate("pokemons/$id")
                            }
                        }

                        composable("pokemons/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""

                            PokemonsByTypeScreen(viewModel, id) { pokeId ->
                                navController.navigate("detalle/$pokeId")
                            }
                        }

                        composable("detalle/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id") ?: ""
                            PokemonDetailScreen(viewModel, id)
                        }
                    }
                }
            }
        }
    }
}