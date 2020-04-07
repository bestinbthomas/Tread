[data](../../index.md) / [com.treadhill.app.dataTypes](../index.md) / [FilterOptions](./index.md)

# FilterOptions

(JVM) `data class FilterOptions : `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)

Utility to Filter videos in Category Fragment

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | Utility to Filter videos in Category Fragment`FilterOptions(filterTypes: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`> = arrayListOf(), filterDifficulties: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`> = arrayListOf(), filterDuration: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, query: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "")` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [filterDifficulties](filter-difficulties.md) | List of Difficulty level selected [Beginer,Intermediate](#)`var filterDifficulties: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| (JVM) [filterDuration](filter-duration.md) | Maximum Duration`var filterDuration: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [filterTypes](filter-types.md) | List of Types Selected [hitt,zumba](#) etc`var filterTypes: `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| (JVM) [query](query.md) | Search query or string entered by the user`var query: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| Name | Summary |
|---|---|
| (JVM) [checkDifficulty](check-difficulty.md) | Checks if the given difficulty is in the List of Difficulties`fun checkDifficulty(difficulty: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| (JVM) [checkDuration](check-duration.md) | Check if the duration is less then givn duration`fun checkDuration(duration: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| (JVM) [checkQuery](check-query.md) | checks of the Video has the search query in its tittle, description, Creator, Category or Equipment`fun checkQuery(video: `[`VideoItem`](../-video-item/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| (JVM) [checkType](check-type.md) | Checks if the given category is in the List of Types`fun checkType(category: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| (JVM) [filter](filter.md) | Filter the list of videos according to the conditions passed in the constructor`fun filter(videos: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`VideoItem`](../-video-item/index.md)`>?): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`VideoItem`](../-video-item/index.md)`>` |
