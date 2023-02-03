package com.mvk.starfield.ui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Constraints
import com.mvk.starfield.utils.lerp
import kotlin.random.Random

data class Star(
    val x: Float,
    val y: Float,
    val z: Float,
    var pz: Float,
) {

    companion object {

        /**
         * Create a [Star] object
         */
        private fun createStar(constraints: Constraints): Star {
            val cz = Random.nextInt(constraints.maxWidth).toFloat()
            return Star(
                x = Random.nextInt(-constraints.maxWidth, constraints.maxWidth).toFloat(),
                y = Random.nextInt(-constraints.maxHeight, constraints.maxHeight).toFloat(),
                z = cz,
                pz = cz,
            )
        }

        /**
         * Create a list of [Star]
         */
        fun createStars(count: Int, constraints: Constraints): List<Star> {
            return buildList {
                repeat(
                    times = count,
                    action = { add(createStar(constraints = constraints)) }
                )
            }
        }
    }

    fun update(speed: Float, constraints: Constraints): Star {

        val updatedZ = z - speed

        if (updatedZ < 1) {
            return this.copy(
                x = Random.nextInt(-constraints.maxWidth, constraints.maxWidth).toFloat(),
                y = Random.nextInt(-constraints.maxHeight, constraints.maxHeight).toFloat(),
                z = constraints.maxWidth.toFloat(),
                pz = constraints.maxWidth.toFloat(),
            )
        }
        return this.copy(
            z = updatedZ,
        )
    }

    fun show(scope: DrawScope, constraints: Constraints, index: Int) {

        scope.apply {

            val sx = lerp(
                from = 0f..1f,
                to = 0f..constraints.maxWidth.toFloat(),
                value = x / z
            )

            val sy = lerp(
                from = 0f..1f,
                to = 0f..constraints.maxHeight.toFloat(),
                value = y / z
            )

            val r = lerp(
                from = 0f..constraints.maxWidth.toFloat(),
                to = 16f..0f,
                value = z
            )

            drawCircle(
                color = Color.White,
                radius = r,
                center = Offset(x = sx, y = sy)
            )

            val px = lerp(
                from = 0f..1f,
                to = 0f..constraints.maxWidth.toFloat(),
                value = x / pz
            )

            val py = lerp(
                from = 0f..1f,
                to = 0f..constraints.maxHeight.toFloat(),
                value = y / pz
            )

            pz = z

            drawLine(
                color = Color.LightGray,
                start = Offset(x = sx, y = sy),
                end = Offset(x = px, y = py),
                strokeWidth = 2f,
            )
        }
    }
}