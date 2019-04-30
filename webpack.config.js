var path = require("path")
var HtmlWbpackPlugin = require("html-webpack-plugin");

const htmlplugin = new HtmlWbpackPlugin({
    template: path.join(__dirname, "./src/index.html"),
    filename: "index.html"
})



module.exports = {

    mode: "development",

    plugins: [htmlplugin],



    module: {

        rules: [
            {
                test: /\.js|jsx/,
                use: 'babel-loader',
                exclude: /node_modules/
            },
            {
                test: /\.(css|less)$/,
                use: [
                    {
                        loader: 'style-loader'
                    },
                    {
                        loader: 'css-loader',

                    },
                    {
                        loader: require.resolve('less-loader')
                    }
                ]
            }

        ]

    },

    devServer: {
        historyApiFallback: true
    }

}