let graph_click_enabled = false;
const enable_graph_button = document.getElementById('enable-graph');
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
    left: -5,
    right: 5,
    bottom: -5,
    top: 5
});

let newDefaultState = calculator.getState();
calculator.setDefaultState(newDefaultState);

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

function inRectangle(point, rect) {
    return (
        point.x >= rect.left &&
        point.x <= rect.right &&
        point.y <= rect.top &&
        point.y >= rect.bottom
    )
}

function enable_graph() {
    if (graph_click_enabled) {
        elt.removeEventListener('click', handleGraphClick);
        graph_click_enabled = false;
        enable_graph_button.textContent = "Enable graph aiming";
    } else {
        elt.addEventListener('click', handleGraphClick);
        graph_click_enabled = true;
        enable_graph_button.textContent = "Disable graph aiming";
    }
}

function handleGraphClick (evt) {

    let tempIsSelected = false;
    let tempR;

    radioButtons.forEach(rb => {
        if (rb.checked) {
            tempIsSelected = true;
            tempR = rb.value;
        }
    });

    const isSelectedR = tempIsSelected;

    if (!isSelectedR) {
        r_error.textContent = "For this, choose some option above!";
        r_error.className = ERROR_CLASS_ID_ACTIVATE;
        return;
    }

    const r = tempR;

    const rect = elt.getBoundingClientRect();
    const x = evt.clientX - rect.left;
    const y = evt.clientY - rect.top;
    // Note, pixelsToMath expects x and y to be referenced to the top left of
    // the calculator's parent container.
    const mathCoordinates = calculator.pixelsToMath({x: x, y: y});

    if (!inRectangle(mathCoordinates, calculator.graphpaperBounds.mathCoordinates)) return;

    console.log('setting expression...');
    console.log(mathCoordinates);

    send_intersection_rq(mathCoordinates.x, mathCoordinates.y, r);
}