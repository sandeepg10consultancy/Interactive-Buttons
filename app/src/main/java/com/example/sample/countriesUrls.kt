package com.example.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import okhttp3.HttpUrl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesUrls(navController: NavController,name:String){
    Scaffold (
        topBar ={
            TopAppBar(title = { Text(text = "Second Page", color = Color.White,fontSize = 20.sp)},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.teal_700),
                    navigationIconContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft)


                    }
                }
            )
        },
        content = { paddingValue ->
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(paddingValue),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                AsyncImage(model = imageurls[name], contentDescription = "countryImage")
                Text(text = "You Selected The $name Country")
            }

        }
    )

}

fun <ImageVector> Icon(arrowBack: ImageVector) {

}
