package io.github.project.parse

import kotlin.text.Regex.Companion.escape

val symbolsToAddBackslash = arrayOf("sin", "cos", "tan", "cot", "pi", "sqrt", "log", "ln")
val symbolsToReplaceBrackets = arrayOf("^")

fun parseStringDesmosLatex(plain: String): String {
    var result = plain

    result = result.replace("\\", "")

    for (symbol in symbolsToAddBackslash) {
        result = result.replace(symbol, "\\${symbol}")
    }

    for (symbol in symbolsToReplaceBrackets) {
        val regex = Regex("""${escape(symbol)}.*?(?<=\))""")
        val occurs = regex.findAll(result)
        for (occur in occurs) {
            val sourceStr = result.substring(occur.range)
            var editedString = sourceStr
            editedString = editedString.replace("(", "{")
            editedString = editedString.replace(")", "}")

            result = result.replace(sourceStr, editedString)
        }
    }

    return result
}
