package com.example.kmp.shared.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kmp.shared.ui.screens.CharacterDetailScreen
import com.example.kmp.shared.ui.screens.CharacterListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "characters",
    ) {
        composable("characters") {
            CharacterListScreen(
                onCharacterClick = { characterId ->
                    navController.navigate("character/$characterId")
                },
            )
        }
        composable(
            route = "character/{characterId}",
            arguments = listOf(
                navArgument("characterId") { type = NavType.IntType },
            ),
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId") ?: return@composable
            CharacterDetailScreen(
                characterId = characterId,
                onBackClick = { navController.popBackStack() },
            )
        }
    }
}
