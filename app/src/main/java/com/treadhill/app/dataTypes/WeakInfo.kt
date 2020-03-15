package com.treadhill.app.dataTypes

data class WeakInfo(var scores: List<Long> = List<Long>(7) { 0 },
                    var duration: List<Long> = List<Long>(7) { 0 },
                    var calories: List<Long> = List<Long>(7) { 0 }) {
}