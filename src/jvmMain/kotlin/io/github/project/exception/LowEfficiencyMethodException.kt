package io.github.project.exception

class LowEfficiencyMethodException : CalculationException(
    6, "Provided method can't efficiently solve this equation. Please, choose another method"
)