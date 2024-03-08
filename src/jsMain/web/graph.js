if (!activated) {
    const elt = document.getElementById('graph');
    const calculator = Desmos.GraphingCalculator(elt, {
        keypad: false,
        expressions: false,
        settingsMenu: false,
        invertedColors: true,
        xAxisLabel: 'x',
        yAxisLabel: 'y',
        xAxisStep: 1,
        yAxisStep: 1,
        xAxisArrowMode: Desmos.AxisArrowModes.POSITIVE,
        yAxisArrowMode: Desmos.AxisArrowModes.POSITIVE
    });

    calculator.setMathBounds({
        left: -5, right: 5, bottom: -5, top: 5
    });

    let newDefaultState = calculator.getState();
    calculator.setDefaultState(newDefaultState);

    function setExpression(expression) {
        calculator.setExpression({
            id: u_id,
            latex: expression,
            color: Desmos.Colors.RED
        })
    }
}
