package io.github.project.util

import kotlin.math.abs
import kotlin.math.sqrt

class FunctionUtil {
    companion object {
        fun findMaximum(f: (Double) -> Double, left: Double, right: Double, epsilon: Double) : Double {
            val goldenRatio = (1 + sqrt(5.0)) / 2
            val resGoldenRatio = 2 - goldenRatio
            val oneMinusResGR = 1 - resGoldenRatio

            var a = left
            var b = right

            var x1 = a + resGoldenRatio * (b - a)
            var x2 = a + oneMinusResGR * (b - a)

            var y1 = f(x1)
            var y2 = f(x2)

            do {
                if (y1 > y2) {
                    b = x2
                    x2 = x1
                    y2 = y1
                    x1 = a + resGoldenRatio * (b - a)
                    y1 = f(x1)
                } else {
                    a = x1
                    x1 = x2
                    y1 = y2
                    x2 = a + oneMinusResGR * (b - a)
                    y2 = f(x2)
                }
            } while (abs(b - a) > epsilon)

            return (x1 + x2) / 2
        }
    }
}