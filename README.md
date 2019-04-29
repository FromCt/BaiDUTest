# react_mobel 

    react移动端开发。浏览器访问，或 打包app



## 准备
    npm init 初始化项目
    npm install html-webpack-plugin
    npm install react react-dom --save --dev
    babel-loader
    babel-core


    css

        css-loader style-loader
## 使用 antd mobile

## 添加 路由

    react-router-dom

        // 路由器router   路由route  Switch配置404（必须放在switch最下面）  Redirect配置重定向
            import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom'


            console.log("this is index.js")

            const content = (
                <Router>
                    <div>
                        <Switch>
                            <Route exact path="/" component={MyTabBar}></Route>
                            <Route path="/pageA" component={PageA}></Route>
                            <Route path="/pageB" component={PageB}></Route>
                            //重定向
                            <Redirect from="/redirect" to="/pageB" />
                            <Route component={ErrorPage}></Route>
                        </Switch>
                    </div>
                </Router>)
            ReactDom.render(content, document.querySelector("#app"))

    注意： 路由刷新后失效问题。

    参考：https://blog.csdn.net/zwkkkk1/article/details/83411071

    方案一：

        devServer: {  //只适合开发模式
            historyApiFallback: true
        }

    方案二： 

        Hash Router
        把 Browser Router 改为 Hash Router

## 网络访问。

    考虑打包会用到 huilder，集成mui ajax 是可以实现的。开发 需要配置代理，或者打开hbuilder调试。

    调用：

        window._ecsc.get("http://img.cq.ct10000.com/img-manager/apk/updateCommon",
                wgtVer,
                function (jsonData) {
                    window.mui.toast("success")
                    that.setState({
                        message: JSON.stringify(jsonData)
                    })
                    if (jsonData.success) {
                        var record = jsonData.record;
                        var newVersion = parseInt(record.number);
                        console.log(JSON.stringify(wgtVer));
                        console.log(newVersion);

                        that.props.history.push("/newWebView", { name: "ct" })

                    } else {
                        window.mui.toast("升级接口调用失败")
                        return;
                    }
                })





