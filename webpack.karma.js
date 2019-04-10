'use strict';

const path = require("path");

var config = {
    "mode": "development",
    "context": path.resolve(__dirname, 'classes/kotlin/js/test'),
    "entry": {
        "test": "./kotlin-full-stack-application-demo_test"
    },
    "output": {
        "path": path.resolve(__dirname, 'bundle'),
        "filename": "[name].bundle.js",
        "chunkFilename": "[id].bundle.js",
        "publicPath": "/"
    },
    "module": {
        "rules": [
            
        ]
    },
    "resolve": {
        "modules": [
            "classes/kotlin/js/main",
            "classes/kotlin/js/test",
            "resources/main",
            path.resolve(__dirname, 'node_modules'),
            "node_modules"
        ]
    },
    "plugins": [
        
    ]
};

module.exports = config;

// from file /Users/mkraynov/Projects/kotlin-fullstack-one/webpack.config.d/ring.js
const ringConfig = require('@jetbrains/ring-ui/webpack.config').config;

config.module.rules.push(...ringConfig.module.rules);
// from file /Users/mkraynov/Projects/kotlin-fullstack-one/webpack.config.d/svg.js

config.module.rules.push(
    {
        test: /\.svg$/,
        loader: "svg-inline-loader",
        options: {removeSVGTagAttrs: false},
        include: [
            path.resolve(__dirname, 'node_modules/@jetbrains/logos')
        ]
    }
);
