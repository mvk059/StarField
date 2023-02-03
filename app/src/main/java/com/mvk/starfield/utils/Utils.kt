package com.mvk.starfield.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalDensity

fun lerp(
    from: ClosedFloatingPointRange<Float>,
    to: ClosedFloatingPointRange<Float>,
    value: Float,
): Float {
    val percentage: Float = (value - from.start) / (from.endInclusive - from.start)
    return ((to.endInclusive - to.start) * percentage) + to.start
}

@Composable
@ReadOnlyComposable
fun Int.pxToDp() = with(LocalDensity.current) { this@pxToDp.toDp() }