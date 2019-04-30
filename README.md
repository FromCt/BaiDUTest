
# need do

    1> less 集成。css hash
    


# react


## 虚拟DOM   document object model

js 模拟的dom结构

## diff 算法 
新旧 dom树的对比过程，

diff tree

    每层对比的过程

component diff

    每层中组件级别的对比 （不同则更新）
    
element diff

    组件相同 则对比元素

## webpack.4x 创建项目

npm init -y 快速创建项目

创建 src 目录
创建 dist
src 下创建 index.htm 
src 下创建 main.js  webpack 入口
安装 webpack 
安装 webpack-cli  
创建 weback.config.js  设置mode:development or production 
    
    webpack 4.0+ 约定entry 为 ./src/index.js  output为 dist 下main.js
    
安装 webpack-dev-server

    package.json 中配置 dev:"webpack-dev-server" 文件变化自动打包
    //(--open打开浏览器 --port 3000(指定端口)  --host 127.0.0.1 指定域名 )
     dev:"webpack-dev-server --open --hot --port 3000 --host 127.0.0.1"

安装 html-webpack-plugin

    将模板 html 文件生成到开发服务器内存。
    修改webpack.confing.js
    添加plugin 

安装 react react-dom
    
    npm install react react-dom --save -dev
    
    demo
    
    // 创建 react 组件   
    //参数1 dom节点类型 
    //参数2 虚拟dom 属性
    //参数2 子组件 或者文本节点
    const MyH1 = React.createElement("h1", null, "我是 h1 react 组件")
    
    // 将 react 组件挂载到 dom 节点中 
    //参数1 react组件 虚拟dom
    //参数2 真实dom 节点容器
    ReactDOM.render(MyH1, document.querySelector("#app"));

## jsx
    
js 中可以写入html标签 的语法。js 文件中默认不能 写html 语法。 通过 babel 来转化 html 标签为 reactElement
    
    所以说 jsx 是React.createElement()的语法糖。
    如 const test=<h1>123</h1>
    test 就是ReactElement 对象(通过 babel 转化)
    render 就是 将reactElement 转化为dom 元素。
    
    依赖包
    
        babel-core babel-loader babel-plugin-transform-runtime
        babel-preset-env babel-preset-stage-0 babel-preset-react
        
        webpack.config.js 添加 module 
        
        // 所有第三方 模块配置规则
        module: {
            rules: [//第三方匹配规则
                {
                    test: /\.js|jsx/,
                    use: 'babel-loader',
                    exclude: /node_modules/  //排除 node_modules
                }
    
            ]
    
        }
        
        添加 .babelrc  配置文件
        
        {
            "presets": [
                "env",
                "stage-0",
                "react"
            ],
            "plugins": [
                "transform-runtime"
            ]
        }
        
    注意：
        jsx( class   for  不能使用) class==>className   for===>htmlFor

## map  方法添加 key

    经常会发现渲染列表的时候 会警告添加唯一的key .
    因为如果没 key 的话 没行的状态 会混乱难以管理。（如checkbox） 所以要为每一行使用key
    
## 组件创建

方法一：
    
    //定义 （大写首字母）
    function Hello(props) { // props 只读属性
        return <div>this is Hello {props.name} </div>
    }
    //使用：
    <Hello name={"formct"} />
    
    抽离单独组件
    
        import React from 'react';// 必须导入 React 必须大写
    
        function Hello(props) {
        
            console.log(props)
        
            return <h1>this is Hello</h1>
        }
        
        // es6 暴露 模块
        export default Hello;
        
        //使用
        import Hello from './components/Hello.jsx'
        
        <Hello />
    
第二种方法：

    // 第二种方式 使用class  继承 React.Component  并实现 render 方法。
    class Hello4 extends React.Component {

        render() {
            return <div>this is  Hello4</div>
        }

    }
    export default Hello4;





# react_mobile 

    react移动端开发。浏览器访问，或 打包app

## 准备

    详细见上方webpack 创建 react 项目

    npm init 初始化项目
    npm install html-webpack-plugin
    npm install react react-dom --save --dev
    babel-loader
    babel-core

    css

        css-loader style-loader

### less 模块配置 https://www.jianshu.com/p/98f30231872b

    安装less:  npm install less less-loader --save-dev

    第一：改webpack.config.dev.js文件：找到module的rules规则配置

            1）test: /\.css$/改成 test: /\.(css|less)$/
            2）在use数组末尾添加一项： { loader: require.resolve('less-loader')}


    第二：改 webpack.config.prod.js文件：找到module的rules规则配置
                
            1）test: /\.css$/改成 test: /\.(css|less)$/
            2）在use数组末尾添加一项： { loader: require.resolve('less-loader')}


    less语法：https://www.cnblogs.com/haoyijing/p/5793788.html

    注： 没有支持到局部样式。 cssmodule


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








