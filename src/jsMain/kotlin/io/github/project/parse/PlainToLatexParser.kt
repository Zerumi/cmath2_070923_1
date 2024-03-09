package io.github.project.parse

val symbolsToAddBackslash = arrayOf("sin", "cos", "tan", "cot", "pi", "sqrt", "log", "ln")

fun parseString(plain : String) : String {
    var result = plain

    result = result.replace("\\", "")

    for (symbol in symbolsToAddBackslash) {
        result = result.replace(symbol, "\\${symbol}")
    }

    result = result.replace("(", "{")
    result = result.replace(")", "}")

    return result
}
