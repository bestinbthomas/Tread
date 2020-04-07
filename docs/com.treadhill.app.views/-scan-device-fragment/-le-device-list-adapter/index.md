[data](../../../index.md) / [com.treadhill.app.views](../../index.md) / [ScanDeviceFragment](../index.md) / [LeDeviceListAdapter](./index.md)

# LeDeviceListAdapter

(JVM) `private open class LeDeviceListAdapter : `[`BaseAdapter`](https://developer.android.com/reference/android/widget/BaseAdapter.html)

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | `LeDeviceListAdapter()` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [mInflator](m-inflator.md) | `var mInflator: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`!` |
| (JVM) [mLeDevices](m-le-devices.md) | `var mLeDevices: `[`ArrayList`](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)`<`[`BluetoothDevice`](https://developer.android.com/reference/android/bluetooth/BluetoothDevice.html)`!>!` |

### Functions

| Name | Summary |
|---|---|
| (JVM) [addDevice](add-device.md) | `open fun addDevice(device: `[`BluetoothDevice`](https://developer.android.com/reference/android/bluetooth/BluetoothDevice.html)`!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [getCount](get-count.md) | `open fun getCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| (JVM) [getDevice](get-device.md) | `open fun getDevice(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`BluetoothDevice`](https://developer.android.com/reference/android/bluetooth/BluetoothDevice.html)`!` |
| (JVM) [getItem](get-item.md) | `open fun getItem(i: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`!` |
| (JVM) [getItemId](get-item-id.md) | `open fun getItemId(i: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| (JVM) [getView](get-view.md) | `open fun getView(i: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, view: `[`View`](https://developer.android.com/reference/android/view/View.html)`!, viewGroup: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`!): `[`View`](https://developer.android.com/reference/android/view/View.html)`!` |
