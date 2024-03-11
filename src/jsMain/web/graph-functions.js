let elt;
let calculator;
let newDefaultState;

let u_id = 42

let su_id1 = 43
let su_id2 = 443

function setExpression(expression) {
    calculator.setExpression({
        id: u_id, latex: expression, color: Desmos.Colors.RED
    })
}

function setSystemExpression1(expression) {
    calculator.setExpression({
        id: su_id1, latex: expression, color: Desmos.Colors.ORANGE
    })
}

function setSystemExpression2(expression) {
    calculator.setExpression({
        id: su_id2, latex: expression, color: Desmos.Colors.ORANGE
    })
}

let currentRborder = 5;
let currentLborder = -5;
let currentTborder = 5;
let currentBborder = -5;

function setLeftBorder(a) {
    currentLborder = a;
    currentTborder = 5 + (Math.abs(a) - 5) / 2;
    currentBborder = -5 - (Math.abs(a) - 5) / 2;
    setCalculatorState()
}

function setRightBorder(b) {
    currentRborder = b;
    currentTborder = 5 + (Math.abs(b) - 5) / 2;
    currentBborder = -5 - (Math.abs(b) - 5) / 2;
    setCalculatorState()
}

function setCalculatorState() {
    calculator.setMathBounds({
        left: currentLborder, right: currentRborder, bottom: currentBborder, top: currentTborder
    });

    console.log(currentLborder)
    console.log(currentRborder)
    console.log(currentTborder)
    console.log(currentBborder)

    newDefaultState = calculator.getState();
    calculator.setDefaultState(newDefaultState);
}
