package com.treadhill.app.dataTypes

/**
 * User Class Same format to be followed in Firebase DB
 *
 * @property name Name of user
 * @property gender Gender of user
 * @property dateOfBirth Date of Birth of user as "10/3/2020" format
 * @property dobMillis Date of birth in Epoch time milliseconds
 * @property height Height of user
 * @property weight Weight of user
 * @property frequencyOfExercise Frequency of exercise selected
 * @property fitnessLevel Fitness level of exercise selected
 * @property fitnessGoals List of fitness Goals Selected
 */
data class User(val name: String = "",
                val gender: String = "",
                val dateOfBirth: String = "",
                val dobMillis: Long = 0,
                val height: Int = 0,
                val weight: Int = 0,
                val frequencyOfExercise: String = "",
                val fitnessLevel: String = "",
                val fitnessGoals: List<String> = listOf())