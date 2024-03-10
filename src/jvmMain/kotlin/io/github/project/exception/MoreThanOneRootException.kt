package io.github.project.exception

class MoreThanOneRootException : Exception(
    "On given [a; b] there's more than one root. Please, decrease interval"
)