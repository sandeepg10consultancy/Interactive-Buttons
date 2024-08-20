package com.example.sample

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class CountriesViewModel : ViewModel(){
    var state by mutableStateOf(CountriesScreenState())
    private var searchJob: Job? =null
    fun onAction(userAction: UserAction){
        when(userAction){
            is UserAction.TextFieldInput->{
                state = state.copy(
                    searchText = userAction.text
                )
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500)
                    searchCountriesInList(userAction.text)
                }

                //searchCountriesInList(userAction.text)
            }
        }
    }
    private fun searchCountriesInList(
        searchQuery:String
    ){
        val newList = countrieslist.filter{
            it.contains(searchQuery,ignoreCase = true)
        }
        state = state.copy(list = newList)
    }

}



sealed class UserAction{
    data class TextFieldInput(val text:String):UserAction()
}


data class CountriesScreenState (
    val searchText:String = "",
    val list:List<String> = countrieslist
)