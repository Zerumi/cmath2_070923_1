package io.github.project.parse

val symbolsToAddBackslash = arrayOf("sin", "cos", "tan", "cot", "pi", "sqrt", "log", "ln")

fun parseString(plain : String) : String {
    var result = plain

    for (symbol in symbolsToAddBackslash) {

        val regex = Regex(symbol)
        val matches = regex.findAll(result)
        matches.map { result.addCharAtIndex('\\', it.range.first) }
    }

    result = result.replace("(", "{")
    result = result.replace(")", "}")

    return result
}

fun String.addCharAtIndex(char: Char, index: Int) =
    StringBuilder(this).apply { insert(index, char) }.toString()