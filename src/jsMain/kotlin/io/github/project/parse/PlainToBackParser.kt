package io.github.project.parse

val symbolsToCheck = arrayOf("sin", "cos", "tan", "cot")

fun plainToBackParse(plain: String): String {
    var result = plain
    result = result.replace("\\", "")
    result = result.replace("{", "(")
    result = result.replace("}", ")")

    for (symbol in symbolsToCheck) {
        val regex = Regex("""${Regex.escape(symbol)} *?\^ *?\( *?-1 *?\)""")
        val occurs = regex.findAll(result)
        for (occur in occurs) {
            val sourceStr = result.substring(occur.range)
            result = result.replace(sourceStr, "a$symbol")
        }
    }

    return result
}
