[data](../../index.md) / [com.treadhill.app.utilities](../index.md) / [BluetoothUtils](./index.md)

# BluetoothUtils

(JVM) `class BluetoothUtils`

### Types

| Name | Summary |
|---|---|
| (JVM) [BluetoothStatus](-bluetooth-status/index.md) | `enum class BluetoothStatus` |

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | `BluetoothUtils(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`)` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [ACTION_DATA_AVAILABLE](-a-c-t-i-o-n_-d-a-t-a_-a-v-a-i-l-a-b-l-e.md) | `val ACTION_DATA_AVAILABLE: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| (JVM) [ACTION_GATT_CONNECTED](-a-c-t-i-o-n_-g-a-t-t_-c-o-n-n-e-c-t-e-d.md) | `val ACTION_GATT_CONNECTED: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| (JVM) [ACTION_GATT_DISCONNECTED](-a-c-t-i-o-n_-g-a-t-t_-d-i-s-c-o-n-n-e-c-t-e-d.md) | `val ACTION_GATT_DISCONNECTED: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| (JVM) [ACTION_GATT_SERVICES_DISCOVERED](-a-c-t-i-o-n_-g-a-t-t_-s-e-r-v-i-c-e-s_-d-i-s-c-o-v-e-r-e-d.md) | `val ACTION_GATT_SERVICES_DISCOVERED: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| (JVM) [adapter](adapter.md) | `val adapter: `[`BluetoothAdapter`](https://developer.android.com/reference/android/bluetooth/BluetoothAdapter.html)`?` |
| (JVM) [context](context.md) | `val context: `[`Context`](https://developer.android.com/reference/android/content/Context.html) |
| (JVM) [devices](devices.md) | `val devices: LiveData<`[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`BluetoothDevice`](https://developer.android.com/reference/android/bluetooth/BluetoothDevice.html)`>>` |
| (JVM) [EXTRA_DATA](-e-x-t-r-a_-d-a-t-a.md) | `val EXTRA_DATA: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| (JVM) [mutableDevices](mutable-devices.md) | `val mutableDevices: MutableLiveData<`[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<`[`BluetoothDevice`](https://developer.android.com/reference/android/bluetooth/BluetoothDevice.html)`>>` |
| (JVM) [STATE_CONNECTED](-s-t-a-t-e_-c-o-n-n-e-c-t-e-d.md) | `val STATE_CONNECTED: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [STATE_CONNECTING](-s-t-a-t-e_-c-o-n-n-e-c-t-i-n-g.md) | `val STATE_CONNECTING: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [STATE_DISCONNECTED](-s-t-a-t-e_-d-i-s-c-o-n-n-e-c-t-e-d.md) | `val STATE_DISCONNECTED: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [TAG](-t-a-g.md) | `val TAG: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| (JVM) [UUID_HEART_RATE_MEASUREMENT](-u-u-i-d_-h-e-a-r-t_-r-a-t-e_-m-e-a-s-u-r-e-m-e-n-t.md) | `val UUID_HEART_RATE_MEASUREMENT: `[`UUID`](https://docs.oracle.com/javase/8/docs/api/java/util/UUID.html)`!` |

### Functions

| Name | Summary |
|---|---|
| (JVM) [checkBluetooth](check-bluetooth.md) | `fun checkBluetooth(): BluetoothStatus` |
| (JVM) [scanBle](scan-ble.md) | `fun scanBle(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
