package io.github.project.exception

import org.example.math.SLAESolutionStatus

class SLAENotSolvedException(slaeState: SLAESolutionStatus) : CalculationException(
    8,
    "This system isn't solved correctly: ${slaeState.name}"
)