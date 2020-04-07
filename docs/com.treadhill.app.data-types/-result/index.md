[data](../../index.md) / [com.treadhill.app.dataTypes](../index.md) / [Result](./index.md)

# Result

(JVM) `class Result<out V>`

wrapper class for response from firebase

### Parameters

`V` - Type of response

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | `Result(value: V?)`<br>`Result(error: `[`Exception`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-exception/index.html)`?)`<br>wrapper class for response from firebase`Result(value: V?, exception: `[`Exception`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-exception/index.html)`?)` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [exception](exception.md) | exception thrown bu the request. null if request was successful`val exception: `[`Exception`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-exception/index.html)`?` |
| (JVM) [value](value.md) | response value. null if request failed`val value: V?` |
