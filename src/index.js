
import React from 'react';
import ReactDom from 'react-dom'


console.log("this is index.js")

const content = <div>this is content</div>

const myApp = React.createElement("div",null,content)


ReactDom.render(myApp,document.querySelector("#app"))