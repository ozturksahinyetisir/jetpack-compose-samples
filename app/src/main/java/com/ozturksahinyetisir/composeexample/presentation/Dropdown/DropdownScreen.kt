package com.ozturksahinyetisir.composeexample.presentation.Dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ozturksahinyetisir.composeexample.data.model.Hero
import com.ozturksahinyetisir.composeexample.data.repository.HeroesRepository

@Composable
fun DropdownScreen() {
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
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
                    modifier = Modifier
                        .background(Color.LightGray)
                        .width(IntrinsicSize.Max)
                        .offset(y = 4.dp)
        ) {
            heroes.forEach { hero ->
                DropdownMenuItem(
                    onClick = {
                        selectedHero = hero
                        isExpanded = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    text = { Text(text = hero.name) },

                )
            }
        }
        Text(
            text = "Selected Hero: ${selectedHero?.name ?: "None"}",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 40.dp)
                .background(Color.LightGray)
        )
    }
}