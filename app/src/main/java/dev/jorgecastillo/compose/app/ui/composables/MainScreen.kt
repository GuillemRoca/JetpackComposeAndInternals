package dev.jorgecastillo.compose.app.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.jorgecastillo.compose.app.data.FakeSpeakerRepository

@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Speakers") })
        },
    ) { contentPadding ->
        val navController = rememberNavController()
        NavHost(
            modifier = Modifier.padding(contentPadding),
            navController = navController,
            startDestination = "speakers"
        ) {
            composable(("speakers")) {
                SpeakerFeed() { speaker ->
                    navController.navigate("speaker/${speaker.id}") {
                        popUpTo("speakers")
                    }
                }
            }
            composable(("speaker/{speakerId}")) { backStackEntry ->
                SpeakerProfileScreen(
                    backStackEntry.arguments?.getString("speakerId")
                )
            }
        }
    }
}
