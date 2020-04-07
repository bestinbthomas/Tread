[data](../../index.md) / [com.treadhill.app.views](../index.md) / [ScanDeviceFragment](./index.md)

# ScanDeviceFragment

(JVM) `open class ScanDeviceFragment : `[`DialogFragment`](https://developer.android.com/reference/android/app/DialogFragment.html)`, DiscoveryCallbacks`

Created by Saurabh on 03-01-2018.

 This dialog start scan for 10 seconds and list all the available BLE devices. Selecting any device would initiate callback for the parent activity/fragment and connection can be performed.

### Types

| Name | Summary |
|---|---|
| (JVM) [LeDeviceListAdapter](-le-device-list-adapter/index.md) | `open class LeDeviceListAdapter : `[`BaseAdapter`](https://developer.android.com/reference/android/widget/BaseAdapter.html) |
| (JVM) [ScanDeviceListener](-scan-device-listener/index.md) | `interface ScanDeviceListener` |
| (JVM) [ViewHolder](-view-holder/index.md) | `open class ViewHolder` |

### Constructors

| Name | Summary |
|---|---|
| (JVM) [&lt;init&gt;](-init-.md) | Created by Saurabh on 03-01-2018. `ScanDeviceFragment()` |

### Properties

| Name | Summary |
|---|---|
| (JVM) [bluetoothManager](bluetooth-manager.md) | `var bluetoothManager: BluetoothManager<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`!>!` |
| (JVM) [listView](list-view.md) | `var listView: `[`ListView`](https://developer.android.com/reference/android/widget/ListView.html)`!` |
| (JVM) [mLeDeviceListAdapter](m-le-device-list-adapter.md) | `var mLeDeviceListAdapter: LeDeviceListAdapter!` |
| (JVM) [scanDeviceListener](scan-device-listener.md) | `var scanDeviceListener: ScanDeviceListener!` |

### Functions

| Name | Summary |
|---|---|
| (JVM) [onActivityCreated](on-activity-created.md) | `open fun onActivityCreated(savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onCreateView](on-create-view.md) | `open fun onCreateView(inflater: `[`LayoutInflater`](https://developer.android.com/reference/android/view/LayoutInflater.html)`!, container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`?, savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`View`](https://developer.android.com/reference/android/view/View.html)`?` |
| (JVM) [onDeviceDiscovered](on-device-discovered.md) | `open fun onDeviceDiscovered(device: `[`BluetoothDevice`](https://developer.android.com/reference/android/bluetooth/BluetoothDevice.html)`!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [onDeviceDiscoveryStopped](on-device-discovery-stopped.md) | `open fun onDeviceDiscoveryStopped(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setDialog](set-dialog.md) | `open fun setDialog(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| (JVM) [setScanDeviceListener](set-scan-device-listener.md) | `open fun setScanDeviceListener(scanDeviceListener: ScanDeviceListener!): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
