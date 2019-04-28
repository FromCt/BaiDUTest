
import React from 'react'
import { Button } from 'antd-mobile';
import 'antd-mobile/lib/button/style/css';//手动按需引入样式


class MyButton extends React.Component{

    render(){

        return (
            <Button type='primary' size="small" inline> button123</Button>
        )
    }
}

export default MyButton;