var _ecsc = {
    test: true,
    baseUrl: "http://222.180.175.21/api/v1/server/",

    post: function (datas, callback) {
        var context = this;
        if (this.test) {
            this.baseUrl = "http://222.180.221.85:8088/api/v1/server/"
        }

        datas.apiKey = "Ab91nDUJpmJldW7LbC5cg3gwCswpH4dq";

        var time = new Date();
        var month = time.getMonth() + 1;
        if (month < 10) {
            month = "0" + month;
        }

        var date = time.getDate(); //获取当前日(1-31)
        if (date < 10) {
            date = "0" + date;
        }
        var hours = time.getHours(); //获取当前小时数(0-23)
        if (hours < 10) {
            hours = "0" + hours;
        }
        var minutes = time.getMinutes(); //获取当前分钟数(0-59)
        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        var seconds = time.getSeconds(); //获取当前秒数(0-59)
        if (seconds < 10) {
            seconds = "0" + seconds;
        }

        var timeStr = time.getFullYear() + "" + month + date + hours + minutes + seconds + "";
        datas.timestamp = timeStr;
        datas.exchangeId = timeStr;
        var secretKey = "QD587EFogSopdqhUYFqvdFbknN5iwSQE";
        //var sign=MD5(encodeURIComponent(data+secretKey+timeStr+timeStr))
        var sign = hex_md5(secretKey + timeStr + timeStr);
        datas.sign = sign;

        if (context.test) {
            console.log("request======" + JSON.stringify(datas))

        }

        mui.ajax(this.baseUrl, {
            data: datas,
            dataType: 'json', //服务器返回json格式数据
            type: 'post', //HTTP请求类型
            timeout: 10000, //超时时间设置为10秒；
            headers: {
                'Content-Type': 'application/json'
            },
            success: function (data) {
                if (context.test) {
                    console.log("response======" + JSON.stringify(data))
                }

                if (!data.success) {
                    mui.toast(data.desc);
                    //return;
                }
                if (!data.dataSuccess) {
                    mui.toast(data.dataDesc);
                    //return;
                }
                callback(data);
            },
            error: function (xhr, type, errorThrown) {
                //异常处理；

                console.log(errorThrown)
                console.log(JSON.stringify(xhr))
                console.log(type);
            }
        });

    },

    postUtil: function (url, datas, callback) {

        //console.log('requsetData=' + JSON.stringify(datas));
        mui.ajax(url, {
            data: datas,
            dataType: 'json', //服务器返回json格式数据
            type: 'post', //HTTP请求类型
            timeout: 10000, //超时时间设置为10秒；
            headers: {
                'Content-Type': 'application/json'
            },
            success: function (data) {
                //服务器返回响应，根据响应结果，分析是否登录成功；
                console.log(JSON.stringify(data));
                callback(data);
            },
            error: function (xhr, type, errorThrown) {
                //异常处理；

                console.log(errorThrown)
                console.log(JSON.stringify(xhr))
                console.log(type);
            }
        });
    },
    get: function (url, datas, callback) {

        mui.ajax(url, {
            dataType: "json",
            type: 'get',
            data: datas,
            success: function (data) {
                //服务器返回响应，根据响应结果，分析是否登录成功；
                console.log(JSON.stringify(data));
                callback(data);
            },
            error: function (xhr, type, errorThrown) {
                //异常处理；

                console.log(errorThrown)
                console.log(JSON.stringify(xhr))
                console.log(type);
            }
        });
    },

    showWindow: function (obj) {
        console.log("showWindow");
        if (!mui) {
            console.log("mui null");
            return;
        }

        var index = obj.url.indexOf("http");
        console.log("index=======" + index);
        if (index < 0) {
            console.log("showWindow url error==" + obj.url);
            mui.toast('内容正在更新中，敬请期待...');
            return;
        }

        mui.openWindow({
            url: 'newWebView',
            id: 'new',
            extras: {
                url: obj.url,
                title: obj.title

            }
        });

    }


}

if (window) {
    window._ecsc = _ecsc
}