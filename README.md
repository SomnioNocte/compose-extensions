# Compose Extensions

Basically just simple handy functions that replace a couple lines of code. You can live without them, just for myself 🙃

## Install

Add it in your settings.gradle.kts at the end of repositories:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
 ```

Step 2. Add the dependency

```kotlin
dependencies {
    implementation("com.github.SomnioNocte:compose-extensions:0.1.1")
}
```

## Functions

### animatableAs
```kotlin
@Composable
inline fun animatableAs(
    animatable: Animatable<Float, AnimationVector1D>,
    crossinline spec: () -> AnimationSpec<Float> = { spring() },
    crossinline onAnimationFinished: () -> Unit = {  },
    crossinline value: () -> Float
): Animatable<Float, AnimationVector1D>
```

I don't highly recommend using it if you don't know when to use it.

This Composable function provides a powerful way to animate Compose states without triggering recomposition of the entire UI. It works by observing changes in a provided value lambda and animating the given Animatable instance to the new value using the specified animation spec. You can also provide an onAnimationFinished callback to execute code once the animation completes.

### Modifier.clearFocusOnTap

```kotlin
fun Modifier.clearFocusOnTap(enabled: Boolean = true): Modifier
```

An extremely useful Composable Modifier that clears focus from the currently focused component when a tap gesture is detected outside of it. This is ideal for dismissing the software keyboard or deselecting input fields by simply tapping anywhere else on the screen. The enabled parameter allows you to toggle this behavior.

### fractionify

```kotlin
fun fractionify(
    value: Dp,
    from: Dp,
    to: Dp,
    bounded: Boolean = false
): Float
```

Calculates the fractional position of a Dp value within a specified range. This function returns a float between 0.0 and 1.0 (if bounded is true) indicating where value stands between from and to. It's perfect for mapping a UI element's position to a normalized animation progress.

### amplitudeFractional

```kotlin
fun amplitudeFractional(fractional: Float): Float
```

Transforms a linear fractional value into an "amplitude" fraction. Typically, this function takes an input between 0.0f and 1.0f and returns a value between 0.0f and 1.0f that peaks at the beginning and end (0.0f and 1.0f input) and dips in the middle (0.5f input). This is useful for creating specific non-linear animation curves that might resemble a pulse or "bounce" effect.

### getScreenCornerRadius

```kotlin
@Composable
fun getScreenCornerRadius(): Dp
```

A Composable function that retrieves the physical screen corner radius of the device in Dp units. For Android S (API 31) and above, it attempts to get the actual rounded corner radius from the display. For older versions, it returns 0.dp. The result is cached for performance after the first call.

### GraphicsLayerScope.scale

```kotlin
var GraphicsLayerScope.scale: Float
```

An extension property for GraphicsLayerScope that simplifies uniform scaling. When you set this property, both scaleX and scaleY of the graphics layer are set to the same value, ensuring content scales proportionally. Getting the property returns the current scaleX.

### GraphicsLayerScope.blur

```kotlin
fun GraphicsLayerScope.blur(value: Float)
```

An extension function to apply a blur effect to the content within a GraphicsLayerScope. It takes a value (blur radius) and applies a BlurEffect using TileMode.Decal. For smaller values, a non-linear scaling is applied to create a more noticeable effect.

### PredictiveBackState

```kotlin
interface PredictiveBackState {
    val isDragged: Boolean
    val offset: Offset
    val startOffset: Offset
    val progress: Float
}
```

A class that represents the mutable state of a predictive back gesture. It holds crucial information like the progress of the gesture (0f to 1f), the startOffset where the touch began, and the current offset of the touch point. This state can be observed to build dynamic UI reactions to the predictive back animation.

### onPredictiveBack

```kotlin
@Composable
fun onPredictiveBack(
    enabled: Boolean = true,
    onBack: () -> Unit
): PredictiveBackState
```

A Composable function that enables and manages the predictive back gesture for your UI. It provides a PredictiveBackState instance that you can use to observe the gesture's progress and offset, allowing you to create custom predictive back animations. The onBack lambda is invoked when the gesture is successfully completed.
