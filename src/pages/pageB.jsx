import React from 'react';

export default class jsdemo extends React.Component {

    state = {
        message: "this is messagethis is messagethis is messagethis is messagethis is messagethis is messagethis is message"
    }

    onLeftClick = () => {
        console.log("onLeftClick", this);

        window.history.back();
    }


    clickRight = () => {
        console.log("clickRight", window._ecsc);

        const that = this;
        if (window._ecsc) {

            var wgtVer = {
                type: 'cljyWgt',
                versionCode: 106 //外呼云app新增语音验证结果
            };

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


        }

    }


    toWb = () => {
        console.log("toweb", this)

        this.props.history.push("/newWebView", { name: "ct" })
    }

    render() {
        return (
            <div>

                <button onClick={this.clickRight}>网络请求</button>
                <p style={{ width: "100%" }}>
                    {this.state.message}
                </p>

                <p>
                    <button onClick={this.toWb}>to webView</button>
                </p>

            </div>
        );
    }
}
