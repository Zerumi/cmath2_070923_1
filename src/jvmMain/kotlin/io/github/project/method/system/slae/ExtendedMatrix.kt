package org.example.math

import java.math.BigDecimal
import java.math.RoundingMode

class ExtendedMatrix(
    matrix: Matrix,
) : Matrix(matrix) {
    private fun extendMatrix(array: Array<BigDecimal>) {
        this.addMatrixCol(array)
    }

    init {
        if (matrix !is ExtendedMatrix) {
            extendMatrix(Array(matrix.getDimension()) {
                BigDecimal("0").setScale(matrix.getValueScale())
            })
        }
    }

    constructor(matrix: ExtendedMatrix) : this(matrix as Matrix)

    fun setExtendedVector(vector: Array<BigDecimal>) {
        for (i in vector.indices) vector[i] = vector[i].setScale(this.getValueScale(), RoundingMode.HALF_UP)
        this.setMatrixCol(this.getDimension(), vector)
    }

    override fun applyVectorToRow(
        row: Int, vector: Array<BigDecimal>, operation: (BigDecimal, BigDecimal) -> BigDecimal
    ) {
        setMatrixElement(
            row,
            this.getDimension(),
            operation(this.getMatrixElement(row, this.getDimension()), vector[this.getDimension()])
        )
        super.applyVectorToRow(row, vector, operation)
    }

    override fun printMatrix() {
        for (i in 0..<this.getDimension()) {
            for (j in 0..this.getDimension()) {
                print("${this.getMatrixElement(i, j).setScale(2, RoundingMode.HALF_UP).toPlainString()} ")
            }
            println()
        }
    }
}