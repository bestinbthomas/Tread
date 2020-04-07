[data](../../index.md) / [com.treadhill.app.utilities](../index.md) / [FirebaseUtils](index.md) / [getWeakMeta](./get-weak-meta.md)

# getWeakMeta

(JVM) `suspend fun getWeakMeta(date: `[`Calendar`](https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html)`): `[`Result`](../../com.treadhill.app.data-types/-result/index.md)`<`[`WeakInfo`](../../com.treadhill.app.data-types/-weak-info/index.md)`>`

get the metadata for weak

PATH : firestore.collection("users").document(auth.currentUser!!.uid).collection(weak).document("metadata").get()

### Parameters

`date` - 