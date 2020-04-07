[data](../../index.md) / [com.treadhill.app.views.customViews](../index.md) / [Slider](./index.md)

# Slider

(JVM) `class Slider : `[`View`](https://developer.android.com/reference/android/view/View.html)

This this the Slider view that is inflated on the Player and Custom workout

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | `Slider(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`?)`<br>`Slider(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`?, attrs: `[`AttributeSet`](https://developer.android.com/reference/android/util/AttributeSet.html)`?)`<br>`Slider(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`?, attrs: `[`AttributeSet`](https://developer.android.com/reference/android/util/AttributeSet.html)`?, defStyleAttr: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)`<br>`Slider(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`?, attrs: `[`AttributeSet`](https://developer.android.com/reference/android/util/AttributeSet.html)`?, defStyleAttr: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, defStyleRes: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [base](base.md) | `var base: `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`?` |
| (JVM) [basepath](basepath.md) | `var basepath: `[`Path`](https://developer.android.com/reference/android/graphics/Path.html) |
| (JVM) [borderpaint](borderpaint.md) | `val borderpaint: `[`Paint`](https://developer.android.com/reference/android/graphics/Paint.html) |
| (JVM) [borderPath](border-path.md) | `var borderPath: `[`Path`](https://developer.android.com/reference/android/graphics/Path.html) |
| (JVM) [bulge](bulge.md) | `var bulge: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| (JVM) [colors](colors.md) | `val colors: `[`IntArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html) |
| (JVM) [cutpath](cutpath.md) | `var cutpath: `[`Path`](https://developer.android.com/reference/android/graphics/Path.html) |
| (JVM) [endx](endx.md) | `val endx: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) |
| (JVM) [extraPath](extra-path.md) | `var extraPath: `[`Path`](https://developer.android.com/reference/android/graphics/Path.html) |
| (JVM) [heartPos](heart-pos.md) | `val heartPos: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) |
| (JVM) [heartRate](heart-rate.md) | `var heartRate: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) |
| (JVM) [maxRate](max-rate.md) | `var maxRate: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [mHeight](m-height.md) | `var mHeight: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [minRate](min-rate.md) | `var minRate: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [mWidth](m-width.md) | `var mWidth: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [paint](paint.md) | `val paint: `[`Paint`](https://developer.android.com/reference/android/graphics/Paint.html) |
| (JVM) [sliderBitmap](slider-bitmap.md) | `lateinit var sliderBitmap: `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html) |
| (JVM) [smallHeart](small-heart.md) | `val smallHeart: `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html) |
| (JVM) [startx](startx.md) | `val startx: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) |
| (JVM) [xpaint](xpaint.md) | `val xpaint: `[`Paint`](https://developer.android.com/reference/android/graphics/Paint.html) |
| (JVM) [zoneEnd](zone-end.md) | `var zoneEnd: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) |
| (JVM) [zoneStart](zone-start.md) | `var zoneStart: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html) |

### Functions

| Name | Summary |
|---|---|
| (JVM) [disableBulge](disable-bulge.md) | hide the target zone`fun disableBulge(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [moveSlider](move-slider.md) | move the target zone to given zone`fun moveSlider(zone: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onDraw](on-draw.md) | `fun onDraw(canvas: `[`Canvas`](https://developer.android.com/reference/android/graphics/Canvas.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onMeasure](on-measure.md) | `fun onMeasure(widthMeasureSpec: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, heightMeasureSpec: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setZone](set-zone.md) | set the target zone`fun setZone(zoneStart: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)`, zoneEnd: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
