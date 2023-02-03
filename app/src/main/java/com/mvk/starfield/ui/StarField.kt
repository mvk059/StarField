package com.mvk.starfield.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import com.mvk.starfield.ui.Star.Companion.createStars
import com.mvk.starfield.ui.theme.StarFieldTheme
import com.mvk.starfield.utils.pxToDp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun StarField(
    modifier: Modifier = Modifier,
) {

    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
        content = {

            val stars = remember { mutableStateOf(createStars(500, constraints)) }
            val speed = remember { mutableStateOf(50f) }
            val pause = remember { mutableStateOf(false) }

            LaunchedEffect(
                key1 = true,
                key2 = pause.value,
                block = {
                    while (this.isActive && !pause.value) {
                        delay(16.milliseconds)
                        stars.value = stars.value.map {
                            it.update(
                                speed = speed.value,
                                constraints = constraints
                            )
                        }
                    }
                }
            )

            Canvas(
                modifier = Modifier
                    .size(
                        width = constraints.maxWidth.pxToDp(),
                        height = constraints.maxHeight.pxToDp()
                    )
                    .background(color = Color.Black),
                onDraw = {

                    translate(
                        left = size.width / 2,
                        top = size.height / 2,
                        block = {

                            stars.value.forEachIndexed { index, it ->

                                it.show(scope = this@Canvas, constraints = constraints, index)
                            }
                        })

                }
            )

            Slider(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.BottomCenter),
                value = speed.value,
                valueRange = 0f..100f,
                onValueChange = { speed.value = it }
            )
        }
    )
}

@Preview(
    name = "StarField",
    showBackground = true,
)
@Composable
private fun StarFieldPreview() {
    StarFieldTheme {
        StarField()
    }
}