import React from 'react';
import { NavBar, Icon } from 'antd-mobile';

//import styles from './test.less'
import './test.less'

//console.log("styles========", styles)

export default class jsdemo extends React.Component {

    state = {
        message: "this is messagethis is messagethis is messagethis is messagethis is messagethis is messagethis is message"
    }

    onLeftClick = () => {
        console.log("onLeftClick");

        window.history.back();

    }
    componentDidMount() {
        //设置页面返回 不可关闭应用
        window._ecsc.quitAble = false
    }



    render() {
        return (
            // <div className={styles.tanstionDemo}>
            <div className="tanstionDemo">
                <NavBar
                    mode="light"
                    icon={<Icon type="left" />}
                    onLeftClick={() => this.onLeftClick()}
                    rightContent={[
                        <Icon key="0" type="search" style={{ marginRight: '16px' }} />,
                        <Icon key="1" type="ellipsis" />,
                    ]}
                >newWebView</NavBar>

                <iframe
                    style={{ width: "100%", height: "100%" }}
                    src="http://www.baidu.com" frameBorder={0} ></iframe>

            </div>
        );

    }

}
