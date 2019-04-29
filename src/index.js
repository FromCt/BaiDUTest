
import React from 'react';
import ReactDom from 'react-dom'
import MyTabBar from './components/myTabBar.jsx'
import PageA from './pages/pageA.jsx'
import NewWebView from './pages/newWebview.jsx'
import PageB from './pages/pageB.jsx'
import ErrorPage from './pages/error.jsx'
// 路由器router   路由route  Switch配置404（必须放在switch最下面）  Redirect配置重定向
import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom'
import ajax from './static/ajax.js'

console.log("this is index.js")

const content = (
    <Router>
        <div>
            <Switch>
                <Route exact path="/" component={MyTabBar}></Route>
                <Route exact path="/newWebView" component={NewWebView}></Route>
                <Route path="/pageA" component={PageA}></Route>
                <Route path="/pageB" component={PageB}></Route>
                // 
                <Redirect from="/redirect" to="/pageB" />
                <Route component={ErrorPage}></Route>

            </Switch>

        </div>

    </Router>)

ReactDom.render(content, document.querySelector("#app"))