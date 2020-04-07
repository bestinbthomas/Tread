package com.treadhill.app.dataTypes

/**
 *
 * @property avatar avatar ResId to be displayed
 * @property name name of the player
 * @property score score of the player
 */
data class LeaderboardItem(val avatar: Int, val name: String, val score: Int)