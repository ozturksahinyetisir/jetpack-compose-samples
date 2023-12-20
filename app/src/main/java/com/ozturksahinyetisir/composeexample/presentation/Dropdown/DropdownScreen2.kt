package com.ozturksahinyetisir.composeexample.presentation.Dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ozturksahinyetisir.composeexample.data.model.Hero
import com.ozturksahinyetisir.composeexample.data.repository.HeroesRepository

@Composable
fun DropdownScreen2() {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedHero by remember { mutableStateOf<Hero?>(null) }

    val heroes = HeroesRepository.allHeroes

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded }
                .padding(16.dp)
                .background(Color.LightGray)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = selectedHero?.name ?: "Select a hero", color = MaterialTheme.colorScheme.onSurface)

            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
        }

        if (isExpanded) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(Color.LightGray)
            ) {
                LazyColumn {
                    items(heroes) { hero ->
                        DropdownItem(
                            hero = hero,
                            onClick = {
                                selectedHero = hero
                                isExpanded = false
                            }
                        )
                    }
                }
            }
        }

        Text(
            text = "Selected Hero: ${selectedHero?.name ?: "None"}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Composable
fun DropdownItem(hero: Hero, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable { onClick() }
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(text = hero.name)
    }
}
