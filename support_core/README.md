Droid KTX - Core
===

## Context

```Kotlin
// resources
val color = context.color(R.color.colorAccent)
val text = context.string(R.string.app_name)
val icon = context.drawable(R.string.icon)

// dimens
val size = context.fromDp(4F)
val size = context.fromSp(12F)
val actionBarSize = context.actionBarSize()

context.getWindowSize().also {
    print("width: ${it.x} height: ${it.y}")
}
```

## Activity

```Kotlin
val contentView = activity.contentView()

// post runnable
activity.postDelayed({ print("perform delayed.") }, 400L)
```

## View

```Kotlin
view.detachParent()  // detach from parent

val visible = view.isVisible()
// toggle
view.visible()
view.visible(false)
view.gone()

// click
view.onClick { print("$it clicked.") }
view.onClick(600L) { print("$it click delayed.") } // click delayed
```

## preferences

```Kotlin
class SampleActivity : Activity() {
    …

    private var samplePref: String by Preference(this, "prefs_commons", "key_sample", "")

    …
}
```
