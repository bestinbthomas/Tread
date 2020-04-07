[data](../../index.md) / [com.treadhill.app.dataTypes](../index.md) / [WeakInfo](./index.md)

# WeakInfo

(JVM) `data class WeakInfo`

Info of the Week for Graph in User Dashboard

stored in "metadata" document in the week collection in firebase

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | Info of the Week for Graph in User Dashboard`WeakInfo(scores: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`> = List<Long>(7) { 0 }, duration: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`> = List<Long>(7) { 0 }, calories: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`> = List<Long>(7) { 0 })` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [calories](calories.md) | List (size 7) of calories burnt denoting calories burnt from Sunday to Saturday`var calories: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`>` |
| (JVM) [duration](duration.md) | List (size 7) of time spent in milliseconds denoting time spent from Sunday to Saturday`var duration: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`>` |
| (JVM) [scores](scores.md) | List (size 7) of scores denoting scores from Sunday to Saturday`var scores: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`>` |
