const path = require('path');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
    entry: {
        app: './frontend_js/index.js',
        print: './frontend_js/print.js'
    },
    devtool: 'sourcemaps',
    cache: true,
    mode: 'development',
    plugins: [
        new CleanWebpackPlugin()
    ],
    output: {
        filename: '[name].bundle.js',
        path: path.resolve(__dirname, 'src/main/web/resources/js/webpack'),
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-env"]
                    }
                }]
            }
        ]
    }
};