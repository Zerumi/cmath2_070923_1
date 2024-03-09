package io.github.project.parse

val symbolsToAddBackslash = arrayOf("sin", "cos", "tan", "cot", "pi", "sqrt", "log", "ln")
val symbolsToReplaceBrackets = arrayOf("sin", "cos", "tan", "cot", "pi", "sqrt", "log", "ln")

fun parseStringDesmosLatex(plain: String): String {
    var result = plain

    result = result.replace("\\", "")

    for (symbol in symbolsToAddBackslash) {
        result = result.replace(symbol, "\\${symbol}")
    }

    for (symbol in symbolsToReplaceBrackets) {
        val regex = Regex("""(${symbol}|\^).*?(?<=\))""")
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
