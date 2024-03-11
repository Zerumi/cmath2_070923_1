package org.example.math

import java.math.BigDecimal

data class SLAESolution(
    val status: SLAESolutionStatus, val sourceSystem: ExtendedMatrix, val solutionVector: Array<BigDecimal>
) {
    companion object {
        fun ok(
            sourceSystem: ExtendedMatrix, solution: Array<BigDecimal>
        ): SLAESolution {
            return SLAESolution(SLAESolutionStatus.OK, sourceSystem, solution)
        }

        fun incompatible(
            sourceSystem: ExtendedMatrix
        ): SLAESolution {
            return SLAESolution(SLAESolutionStatus.INCOMPATIBLE, sourceSystem, emptyArray())
        }

        fun infiniteSolutions(
            sourceSystem: ExtendedMatrix
        ): SLAESolution {
            return SLAESolution(SLAESolutionStatus.INFINITE_SOLUTIONS, sourceSystem, emptyArray())
        }

        fun invalidMatrix(sourceSystem: ExtendedMatrix): SLAESolution {
            return SLAESolution(SLAESolutionStatus.INVALID_MATRIX, sourceSystem, emptyArray())
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SLAESolution) return false

        if (status != other.status) return false
        if (!solutionVector.contentEquals(other.solutionVector)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + solutionVector.contentHashCode()
        return result
    }
}