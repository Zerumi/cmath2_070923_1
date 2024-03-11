package org.example.math

import java.math.BigDecimal
import java.math.RoundingMode

class SLAESolutionChecker(private val slaeSolution: SLAESolution) {

    fun calculateResidualVector(): Array<BigDecimal> {
        assert(slaeSolution.status == SLAESolutionStatus.OK)

        val result = Array<BigDecimal>(slaeSolution.solutionVector.size) { BigDecimal.ZERO }

        for (i in 0..<slaeSolution.solutionVector.size) {
            val slaeVector = slaeSolution.sourceSystem.getMatrixRow(i)
            var solutionResult = BigDecimal.ZERO.setScale(slaeSolution.sourceSystem.getValueScale())
            for (j in 0..<slaeSolution.solutionVector.size) {
                solutionResult = solutionResult.add(
                        slaeVector[j].multiply(slaeSolution.solutionVector[j])
                            .setScale(slaeSolution.sourceSystem.getValueScale(), RoundingMode.HALF_UP)
                    )
            }
            result[i] = slaeVector[slaeSolution.solutionVector.size].subtract(solutionResult).abs()
        }

        return result
    }
}