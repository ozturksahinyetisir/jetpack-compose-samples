package com.ozturksahinyetisir.composeexample.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ozturksahinyetisir.composeexample.MyApp
import com.ozturksahinyetisir.composeexample.data.model.Hero

object HeroesRepository {
    val allHeroes: List<Hero> by lazy {
        readHeroesFromAssets(MyApp.context)
    }
    private fun readHeroesFromAssets(context: Context): List<Hero> {
        val json = context.assets.open("heroes.json").bufferedReader().use { it.readText() }
        val heroesType = object : TypeToken<List<Hero>>() {}.type
        return Gson().fromJson(json, heroesType)
    }
}