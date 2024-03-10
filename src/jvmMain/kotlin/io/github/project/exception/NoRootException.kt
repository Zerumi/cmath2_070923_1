package io.github.project.exception

class NoRootException : CalculationException(
    3,
    "On given [a; b] it's impossible to establish the certainty of the single root's existence. Please, decrease range if there's two or more roots, or increase range if there's no roots"
)