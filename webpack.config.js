const path = require('path');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

module.exports = {
    entry: {
        map: './frontend_js/map.js',
        depot: './frontend_js/depot.js'
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
    }
};