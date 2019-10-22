import _ from 'lodash';
import printMe from './print.js';

function component() {
    const element = document.createElement('strong');
    const btn = document.createElement('button');

    element.innerHTML = _.join(['Hello', 'transport-info', 'from', 'webpack'], ' ');

    btn.innerHTML = 'Click me and check the console!';
    btn.onclick = printMe;

    element.appendChild(btn);

    return element;
}

document.getElementById("test_js").appendChild(component());