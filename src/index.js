
import React from 'react';
import ReactDom from 'react-dom'

import MyButton from './components/button.jsx';


console.log("this is index.js")

const content = <div>this is content

    <p><MyButton></MyButton></p>
</div>

const myApp = React.createElement("div",null,content)


ReactDom.render(myApp,document.querySelector("#app"))