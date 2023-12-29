package com.ozturksahinyetisir.composeexample.presentation.TopAppBar
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.res.stringResource
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.unit.dp
import com.ozturksahinyetisir.composeexample.R
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarScreen() {
    val enterScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(enterScrollBehavior.nestedScrollConnection),
        topBar = {
           TopAppBar(enterScrollBehavior)

        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(35) {
                Text(text = stringResource(id = R.string.lorem_text),
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp))

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    TopAppBar(
        title = { Text(text = "TopAppBar") },
        navigationIcon = {
            IconButton(onClick = { /* TODO: Change type of text or change text*/ }) {
                Icon(Icons.Default.Menu, contentDescription = "menu")
            }
        },
        actions = {
            IconButton(onClick = { /* TODO: Search content */ }) {
                Icon(Icons.Default.Search, contentDescription = "search")
            }
            IconButton(onClick = { /* TODO: Credits or copy all*/ }) {
                Icon(Icons.Default.MoreVert, contentDescription = "more")
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Black,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White,
            navigationIconContentColor = Color.White,
        )
    )
}

