Droid KTX - Commons
===

## Toast

```Kotlin
// call from context
context.toast("test")
context.toast(R.string.test)
context.toast("test", Toast.LENGTH_LONG)
```

## Text

```Kotlin
var str: String? = null
val length = str.length()  // length: 0

val value = str.value()  // value: ""
val value = str.value("default")  // value: "default"
val length = value.length()  // length: 7
```

## Others

> To be continue …
