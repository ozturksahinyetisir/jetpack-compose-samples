package com.ozturksahinyetisir.composeexample.presentation.Search
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozturksahinyetisir.composeexample.data.repository.HeroesRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

@OptIn(FlowPreview::class)
class SearchViewModel : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _heroes = MutableStateFlow(HeroesRepository.allHeroes)
    val heroes = searchText
        .debounce(400L)
        .onEach { _isSearching.value = true }
        .combine(_heroes) { text, heroes ->
            if (text.isBlank()) {
                heroes
            } else {
                delay(400L)
                heroes.filter {
                    doesMatchSearchQuery(it.name,text)
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
    private fun doesMatchSearchQuery(name: String, query: String): Boolean {
        val matchingCombinations = listOf(
            name,
            "$name ",
            "${name.first()}",
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}