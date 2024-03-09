let elt;
let calculator;
let newDefaultState;

function drawBatmanByR(r) {
    calculator.setExpression({
        id: '1',
        latex: '\\left(\\frac{x}{r}\\right)^{2}\\cdot\\sqrt{\\frac{\\left|\\left(\\frac{\\left|x\\right|}{\\frac{r}{7}}-3\\right)\\right|}{\\left(\\frac{\\left|x\\right|}{\\frac{r}{7}}-3\\right)}}+\\left(\\frac{\\frac{y}{\\frac{r}{7}}}{3}\\right)^{2}\\cdot\\sqrt{\\frac{\\left|\\frac{y}{\\frac{r}{7}}+\\frac{3}{7}\\cdot\\sqrt{33}\\right|}{\\left(\\frac{y}{\\frac{r}{7}}+\\frac{3}{7}\\cdot\\sqrt{33}\\right)}}-1\\le0\\ \\left\\{\\frac{y}{\\frac{r}{7}}\\ge0\\right\\}',
        color: Desmos.Colors.ORANGE
    });
    calculator.setExpression({
        id: '2',
        latex: '\\left(\\frac{x}{r}\\right)^{2}\\cdot\\sqrt{\\frac{\\left|\\left(\\frac{\\left|x\\right|}{\\frac{r}{7}}-4\\right)\\right|}{\\left(\\frac{\\left|x\\right|}{\\frac{r}{7}}-4\\right)}}+\\left(\\frac{\\frac{y}{\\frac{r}{7}}}{3}\\right)^{2}\\cdot\\sqrt{\\frac{\\left|\\frac{y}{\\frac{r}{7}}+\\frac{3}{7}\\cdot\\sqrt{33}\\right|}{\\left(\\frac{y}{\\frac{r}{7}}+\\frac{3}{7}\\cdot\\sqrt{33}\\right)}}-1\\le0\\ \\left\\{\\frac{y}{\\frac{r}{7}}\\le0\\right\\}\\ ',
        color: Desmos.Colors.ORANGE
    });
    calculator.setExpression({
        id: '3',
        latex: '\\left|\\frac{\\frac{x}{\\frac{r}{7}}}{2}\\right|-(3\\cdot\\sqrt{33}-7)\\cdot\\frac{\\left(\\frac{x}{\\frac{r}{7}}\\right)^{2}}{112}-3+\\sqrt{1-\\left(\\left|\\left(\\frac{\\left|x\\right|}{\\frac{r}{7}}-2\\right)\\right|-1)^{2}\\right)}-\\frac{y}{\\frac{r}{7}}\\le0\\ \\left\\{\\frac{y}{\\frac{r}{7}}\\ \\le0\\right\\}',
        color: Desmos.Colors.ORANGE
    });
    calculator.setExpression({
        id: '4',
        latex: '9-\\frac{8\\left|x\\right|}{\\frac{r}{7}}\\ge\\frac{y}{\\frac{r}{7}}\\ \\left\\{0.75\\le\\frac{\\left|x\\right|}{\\frac{r}{7}}\\le1\\right\\}\\ \\left\\{0\\le\\frac{y}{\\frac{r}{7}}\\le3\\right\\}',
        color: Desmos.Colors.ORANGE
    });
    calculator.setExpression({
        id: '5',
        latex: '\\frac{3\\left|x\\right|}{\\frac{r}{7}}+0.75\\ \\ge\\frac{y}{\\frac{r}{7}}\\ \\left\\{0.75\\ge\\frac{\\left|x\\right|}{\\frac{r}{7}}\\ge0.5\\right\\}\\ \\left\\{\\frac{y}{\\frac{r}{7}}\\ \\ge0\\right\\}',
        color: Desmos.Colors.ORANGE
    });
    calculator.setExpression({
        id: '6',
        latex: '\\frac{y}{\\frac{r}{7}}\\ \\le\\ 2.25\\ \\left\\{\\frac{x}{\\frac{r}{7}}\\le0.5\\right\\}\\ \\left\\{\\frac{x}{\\frac{r}{7}}\\ge-0.5\\right\\}\\ \\left\\{\\frac{y}{\\frac{r}{7}}\\ge0\\right\\}',
        color: Desmos.Colors.ORANGE
    });
    calculator.setExpression({
        id: '7',
        latex: '6\\cdot\\frac{\\sqrt{10}}{7}+\\left(1.5-\\frac{0.5\\left|x\\right|}{\\frac{r}{7}}\\right)\\cdot\\sqrt{\\frac{\\left|\\left(\\frac{\\left|x\\right|}{\\frac{r}{7}}-1\\right)\\right|}{\\frac{\\left|x\\right|}{\\frac{r}{7}}-1}}-6\\cdot\\frac{\\sqrt{10}}{14}\\cdot\\sqrt{\\left(4-\\left(\\frac{\\left|x\\right|}{\\frac{r}{7}}-1\\right)^{2}\\right)}\\ge\\frac{y}{\\frac{r}{7}}\\ \\left\\{\\frac{y}{\\frac{r}{7}}\\ \\ge0\\right\\}',
        color: Desmos.Colors.ORANGE
    });
    calculator.setExpression({id: '8', latex: 'r=' + r, lineOpacity: 0});
}

function drawPoint(x, y, r) {
    drawBatmanByR(r);
    drawPointXY(x, y);
}

function drawPointXY(x, y) {
    calculator.setExpression({
        id: x + '' + y,
        latex: '(' + x + ', ' + y + ')',
        color: Desmos.Colors.RED
    });
}

function drawPointXYRes(x, y, result) {calculator.setExpression({
    id: x + '' + y,
    latex: '(' + x + ', ' + y + ')',
    color: result ? Desmos.Colors.PURPLE : Desmos.Colors.BLUE
});
}

let u_id = 42

function setExpression(expression) {
    calculator.setExpression({
        id: u_id,
        latex: expression,
        color: Desmos.Colors.RED
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
