
import React from 'react';
import ReactDom from 'react-dom'

import MyButton from './components/button.jsx';
import MyTabBar from './components/myTabBar.jsx'


console.log("this is index.js")

const content = <MyTabBar></MyTabBar>

const myApp = React.createElement("div", null, content)


ReactDom.render(content, document.querySelector("#app"))