package com.example.sample

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun CountriesScreen(
    viewModel: CountriesViewModel,
    navController: NavController
){
    val state = viewModel.state
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val imageurl = remember {
        mutableStateOf("")
    }
    //val countriesArray = RetriveCountries()
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp)
    ){
        SearchAppBar(searchText = state.searchText,
            onTextChange = {newText ->
                viewModel.onAction(UserAction.TextFieldInput(newText))
            },
            onkeyboardSearchClicked = {
                keyboardController?.hide()
                focusManager.clearFocus()
            })
        HorizontalDivider(
            color = Color.Red,
            thickness = 3.dp,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        LazyColumn {
                items(state.list) {country ->
                    //val country = countriesArray[index]
                    
                    Row (modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween){
                        Text(text = country,
                            fontSize = 50.sp,
                            color = Color.White)
                        Spacer(modifier = Modifier//.width(70.dp)
                            .padding(50.dp)
                        )

                        Button(onClick = {
                            navController.navigate("CountriesUrls/${country}")
                        },colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            //border = BorderStroke(2.dp, Color.Red)
                        ) {
                            Icon(
                                Icons.AutoMirrored.Rounded.ArrowForward,
                                contentDescription = "Details",
                                tint = Color.Red
                            )

                        }
                    }
                }
        }

        /*

        LazyColumn (modifier = Modifier.clickable {
            navController.navigate(route = "CountriesUrls")
        }){
            items(state.list) {country ->
                singleItemCard(countryName = country)
                //imageurl.value = countriesurls[it]
                Spacer(modifier = Modifier.size(20.dp))
            }
        }

         */
    }
}

@Composable
fun SearchAppBar(
    searchText: String,
    onTextChange:(String)->Unit,
    onkeyboardSearchClicked:()->Unit
){
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = searchText,
        onValueChange = {
            onTextChange(it)
        },
        textStyle = TextStyle(
            color = Color.White,
            fontSize = 20.sp
        ),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search,
                contentDescription = "searchIcon",
                tint = Color.White)
        },
        trailingIcon = {
            IconButton(onClick = {
                if(searchText.isNotEmpty()){
                    onTextChange("")
                }
            }) {
                Icon(imageVector = Icons.Filled.Close,
                    contentDescription = "CloseIcon",
                    tint = Color.White)
                
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.White,
            focusedLabelColor = Color.Gray,
            cursorColor = Color.Gray
        ),
        placeholder = {
            Text(text = "Search..",
                color = Color.White)
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onkeyboardSearchClicked()
            }
        )
    )
}

@Composable
fun TopBar(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 7.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = "Countries List",
            color = Color.White,
            fontSize = 40.sp,
            fontFamily = FontFamily.Cursive
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Rounded.Search,
                contentDescription ="Search Icon",
                tint = Color.White,
                modifier = Modifier.size(30.dp))

        }
    }
}

@Composable
fun singleItemCard(countryName: String){
    Text(text = countryName,
        color = Color.White,
        modifier = Modifier
            .padding(10.dp))

}

/*
@Preview
@Composable
fun Preview(){
    CountriesScreen(
        viewModel = CountriesViewModel(),
        //navController = rememberNavController()
    )
}


 */
