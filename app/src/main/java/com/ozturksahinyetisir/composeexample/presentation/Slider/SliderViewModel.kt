package com.ozturksahinyetisir.composeexample.presentation.Slider

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ozturksahinyetisir.composeexample.MyApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SliderViewModel @Inject constructor() : ViewModel() {
    private val _sliderValue = mutableStateOf(0f)
    private val sharedPreferences = MyApp.context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    val sliderValue: State<Float> get() = _sliderValue

    init {
        loadSliderValue()
    }

    fun onSliderValueChanged(value: Float) {
        _sliderValue.value = value
    }

    fun saveSliderValue() {
        sharedPreferences?.let { prefs ->
            val editor = prefs.edit()
            for (i in 5 downTo 2) {
                val prevValue = prefs.getFloat("recentSliderValue${i - 1}", 0f)
                editor.putFloat("recentSliderValue$i", prevValue)
            }
            val currentSliderValue = _sliderValue.value
            editor.putFloat("recentSliderValue1", currentSliderValue)
            editor.putFloat("sliderValue", currentSliderValue)
            editor.apply()
        }
    }
        private fun loadSliderValue() {
        sharedPreferences?.let {
            _sliderValue.value = it.getFloat("sliderValue", 0f)
        }
    }

    fun getRecentSliderValues(): List<Float> {
        return sharedPreferences?.run {
            (1..5).mapNotNull { i ->
                val value = getFloat("recentSliderValue$i", 0f)
                if (value != 0f) value else null
            }
        } ?: emptyList()
    }
}

