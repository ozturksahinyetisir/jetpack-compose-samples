package com.ozturksahinyetisir.composeexample.presentation.SearchScreen
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.ozturksahinyetisir.composeexample.MyApp
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

@OptIn(FlowPreview::class)
class SearchViewModel : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _heroes = MutableStateFlow(allHeroes)
    val heroes = searchText
        .debounce(400L)
        .onEach { _isSearching.value = true }
        .combine(_heroes) { text, heroes ->
            if (text.isBlank()) {
                heroes
            } else {
                delay(400L)
                heroes.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.value = false }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(800),
            _heroes.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    companion object {
        val allHeroes: List<Hero> by lazy {
            readHeroesFromAssets(MyApp.context)
        }
        private fun readHeroesFromAssets(context: Context): List<Hero> {
            val json = context.assets.open("heroes.json").bufferedReader().use { it.readText() }
            val heroesType = object : TypeToken<List<Hero>>() {}.type
            return Gson().fromJson(json, heroesType)
        }
    }
}

data class Hero(val name: String) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$name",
            "$name ",
            "${name.first()}",
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}