import React from 'react';
import { NavLink } from 'react-router-dom';

import NewWebView from '../pages/newWebview.jsx'
export default class jsdemo extends React.Component {
    render() {
        return (
            <div>A默认页面

                <NavLink to='/redirect' activeClassName='active'>Redirect</NavLink>
                <p>  <NavLink to='/newWebView' activeClassName='active'>NewWebView</NavLink></p>
                <p>
                    <a href="http://www.baidu.com">baidu</a>
                </p>
            </div>
        );
    }
}