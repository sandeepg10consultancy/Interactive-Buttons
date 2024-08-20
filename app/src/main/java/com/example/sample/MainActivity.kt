package com.example.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sample.ui.theme.SampleTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleTheme {
                Mynavigation()

            }
        }
    }
}
@Composable
fun Mynavigation() {
    val viewModel1 = viewModel<CountriesViewModel>()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "CountriesScreen") {
        composable(route = "CountriesScreen") {
            CountriesScreen(viewModel = viewModel1, navController)
        }
        composable(route = "CountriesUrls/{name}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
            ))
        { navBackStackEntry ->
            val name = navBackStackEntry.arguments?.getString("name")
            name?.let { name ->CountriesUrls(navController, name)

            }

        }
    }

}



val countrieslist = listOf(
    "India", "Russia","Japan", "Finland", "Australia", "Italy", "China",
    "Pakistan", "Brazil","Chile"
)

val imageurls = mapOf("India" to "https://flagcdn.com/120x90/in.png","Russia" to "https://flagcdn.com/120x90/ru.png","Japan" to "https://flagcdn.com/120x90/jp.png",
    "Finland" to "https://flagcdn.com/120x90/fi.png", "Australia" to "https://flagcdn.com/120x90/au.png", "Italy" to "https://flagcdn.com/120x90/it.png", "China" to "https://flagcdn.com/120x90/cn.png",
    "Pakistan" to "https://flagcdn.com/120x90/pk.png", "Brazil" to "https://flagcdn.com/120x90/br.png","Chile" to "https://flagcdn.com/120x90/cl.png")
/*
val countriesurls = listOf("https://flagcdn.com/120x90/in.png","https://flagcdn.com/120x90/ca.png")
var countryname = ""


 */

