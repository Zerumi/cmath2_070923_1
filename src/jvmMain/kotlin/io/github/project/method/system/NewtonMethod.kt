package io.github.project.method.system

import io.github.project.EquationSystemService
import io.github.project.data.system.EquationSystemParams
import io.github.project.data.system.EquationSystemResult
import io.github.project.data.system.EquationSystemSolvingMethod
import io.github.project.exception.IncorrectParametersException
import io.github.project.exception.SLAENotSolvedException
import io.github.project.method.system.error.calculateErrorVector
import io.github.project.util.FunctionArgumentDerivative
import org.example.math.ExtendedMatrix
import org.example.math.Matrix
import org.example.math.SLAE
import org.example.math.SLAESolutionStatus
import java.math.BigDecimal
import kotlin.math.abs

class NewtonMethod : ISystemSolvingMethod {

    override fun solveSystem(equationSystemParams: EquationSystemParams): EquationSystemResult {
        try {
            var iterations = 0u

            val foundSymbols = mutableSetOf<String>()
            equationSystemParams.equations.map { it ->
                val regex = Regex("""[a-z](_[0-9]*|)""")
                val matches = regex.findAll(it)
                for (match in matches) {
                    foundSymbols.add(it.substring(match.range))
                }
            }
            val immutableFoundSymbols = foundSymbols.toList()
            val jacobMatrix = mutableListOf<List<FunctionArgumentDerivative>>()

            for (equation in equationSystemParams.equations) {
                val jacobVector = mutableListOf<FunctionArgumentDerivative>()
                for (symbol in immutableFoundSymbols) {
                    val functionWithArgumentDerivative = FunctionArgumentDerivative(equation, symbol)
                    jacobVector.add(functionWithArgumentDerivative)
                }
                jacobMatrix.add(jacobVector)
            }

            val slaeMatrix = Matrix(equationSystemParams.equations.size, immutableFoundSymbols.size)
            val result = equationSystemParams.startApproximation.toMutableMap()
            var flag = true
            while (flag) {
                for (i in 0..<equationSystemParams.equations.size) {
                    for (j in immutableFoundSymbols.indices) {
                        slaeMatrix.setMatrixElement(
                            i, j, jacobMatrix[i][j].calculateDerivative(result).toString()
                        )
                    }
                }
                val extendedVector = mutableListOf<BigDecimal>()
                for (equation in equationSystemParams.equations) {
                    extendedVector.add(
                        BigDecimal(
                            -EquationSystemService.calculateFunctionWithMultipleArguments(
                                equation, result
                            )
                        )
                    )
                }
                val slaeExtendedMatrix = ExtendedMatrix(slaeMatrix)
                slaeExtendedMatrix.setExtendedVector(extendedVector.toTypedArray())

                val slae = SLAE(slaeExtendedMatrix)
                val solution = slae.solveSLAE()
                if (solution.status != SLAESolutionStatus.OK) throw SLAENotSolvedException(solution.status)

                val solutionVector = solution.solutionVector
                var nextIterationRequired = false
                for (i in immutableFoundSymbols.indices) {
                    if (abs(solutionVector[i].toDouble()) > equationSystemParams.epsilon) {
                        nextIterationRequired = true
                    }
                    result[immutableFoundSymbols[i]] = result[immutableFoundSymbols[i]]!! + solutionVector[i].toDouble()
                }
                flag = nextIterationRequired
                iterations++
            }

            return EquationSystemResult.ok(
                EquationSystemSolvingMethod.NEWTON_METHOD,
                result.map { it.value }.toList(),
                iterations,
                calculateErrorVector(equationSystemParams.equations, result)
            )
        } catch (e : Throwable) {
            throw IncorrectParametersException()
        }
    }
}