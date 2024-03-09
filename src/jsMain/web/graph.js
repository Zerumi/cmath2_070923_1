elt = document.getElementById('graph');
calculator = Desmos.GraphingCalculator(elt, {
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
    left: -5*1.1, right: 5*1.1, bottom: -5 - ((5*1.1 - 5) / 2), top: 5 + ((5*1.1 - 5) / 2)
});

let newDefaultState = calculator.getState();
calculator.setDefaultState(newDefaultState);