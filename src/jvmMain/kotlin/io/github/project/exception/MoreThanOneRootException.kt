package io.github.project.exception

class MoreThanOneRootException : CalculationException(
    4, "On given [a; b] there's more than one root. Please, decrease interval"
)