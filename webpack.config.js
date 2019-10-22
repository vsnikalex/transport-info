const path = require('path');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
    entry: {
        map: './frontend_js/map.js'
    },
    mode: 'development',
    plugins: [
        new CleanWebpackPlugin()
    ],
    output: {
        filename: '[name].bundle.js',
        path: path.resolve(__dirname, 'src/main/web/resources/js/webpack'),
    },
};