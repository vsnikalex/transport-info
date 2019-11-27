const path = require('path');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
    entry: {
        map: './frontend_js/map.js',
        DeliveryEditor: './frontend_js/manager/DeliveryEditor.js',
        deliveryEditorMap: './frontend_js/manager/deliveryEditorMap.js',
        DriverApp: './frontend_js/driver/DriverApp.js'
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
                        presets: ["@babel/preset-env", "@babel/preset-react"]
                    }
                }]
            },
            {
                test: /\.(png|svg|jpg|gif)$/,
                use: [
                    'file-loader',
                ],
            }
        ]
    }
};
