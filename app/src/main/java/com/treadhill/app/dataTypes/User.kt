package com.treadhill.app.dataTypes

data class User(val name: String = "",
                val gender: String = "",
                val dateOfBirth: String = "",
                val dobMillis: Long = 0,
                val height: Int = 0,
                val weight: Int = 0,
                val frequencyOfExercise: String = "",
                val fitnessLevel: String = "",
                val fitnessGoals: List<String> = listOf())