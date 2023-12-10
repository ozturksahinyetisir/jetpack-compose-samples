package com.ozturksahinyetisir.composeexample.presentation.Slider

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SliderScreen(viewModel: SliderViewModel = hiltViewModel()) {
    var sliderValue by rememberSaveable { mutableStateOf(viewModel.sliderValue.value) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(40.dp))
        Text(text = "Result: ${sliderValue.toInt()}")
        Spacer(modifier = Modifier.padding(20.dp))
        Slider(
            value = sliderValue,
            onValueChange = {
                sliderValue = it
                viewModel.onSliderValueChanged(it)
            },
            valueRange = 0f..100f,
            modifier = Modifier.padding(all = 25.dp),
            colors = SliderDefaults.colors(
                thumbColor = Color.LightGray,
                activeTrackColor = Color.Green,
                inactiveTrackColor = Color.Red
            )
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Button(onClick = {
            viewModel.saveSliderValue()
            viewModel.onSliderValueChanged(sliderValue)
        }) {
            Text("Save")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Recent Values:")

        viewModel.getRecentSliderValues().takeIf { it.size >= 3 }?.let { recentValues ->
            Column {
                for ((index, recentValue) in recentValues.take(5).withIndex()) {
                    Text(text = "${index + 1}. ${recentValue.toInt()}")
                }
            }
        }
    }
}