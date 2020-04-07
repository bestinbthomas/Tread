package com.treadhill.app.dataTypes

/**
 * Info of the Week for Graph in User Dashboard
 *
 * stored in "metadata" document in the week collection in firebase
 *
 * @property scores List (size 7) of scores denoting scores from Sunday to Saturday
 * @property duration List (size 7) of time spent in milliseconds denoting time spent from Sunday to Saturday
 * @property calories List (size 7) of calories burnt denoting calories burnt from Sunday to Saturday
 */
data class WeakInfo(var scores: List<Long> = List<Long>(7) { 0 },
                    var duration: List<Long> = List<Long>(7) { 0 },
                    var calories: List<Long> = List<Long>(7) { 0 })