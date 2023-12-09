package com.ozturksahinyetisir.composeexample.presentation.SearchScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

@OptIn(FlowPreview::class)
class SearchViewModel: ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _heroes = MutableStateFlow(allHeroes)
    val heroes = searchText
        .debounce(400L)
        .onEach { _isSearching.update { true } }
        .combine(_heroes) { text, heroes ->
            if(text.isBlank()) {
                heroes
            } else {
                delay(400L)
                heroes.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(800),
            _heroes.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
    companion object {
        private val heroNames = listOf(
            "Abaddon",
            "Alchemist",
            "Ancient Apparition",
            "Anti-Mage",
            "Arc Warden",
            "Bane",
            "Batrider",
            "Beastmaster",
            "Bloodseeker",
            "Bounty Hunter",
            "Brewmaster",
            "Bristleback",
            "Broodmother",
            "Centaur Warrunner",
            "Chaos Knight",
            "Chen",
            "Clinkz",
            "Clockwerk",
            "Crystal Maiden",
            "Dark Seer",
            "Dazzle",
            "Death Prophet",
            "Disruptor",
            "Doom",
            "Dragon Knight",
            "Drow Ranger",
            "Earth Spirit",
            "Earthshaker",
            "Elder Titan",
            "Ember Spirit",
            "Enchantress",
            "Enigma",
            "Faceless Void",
            "Grimstroke",
            "Gyrocopter",
            "Huskar",
            "Invoker",
            "Io",
            "Jakiro",
            "Juggernaut",
            "Keeper of the Light",
            "Kunkka",
            "Legion Commander",
            "Leshrac",
            "Lich",
            "Lifestealer",
            "Lina",
            "Lion",
            "Lone Druid",
            "Luna"
        )

        val allHeroes = heroNames.map { Hero(name = it) }
        val allHeroesFlow = MutableStateFlow(allHeroes)
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
