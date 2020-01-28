// wrap is useful, because declaring variables in module can be already declared
// module creates own lexical environment
;(function (config) {
    const path = require("path");
    config.module.rules.push(
        {
            test: /\.svg$/,
            loader: "svg-inline-loader",
            options: {removeSVGTagAttrs: false},
            include: [
                path.resolve(require.resolve("@jetbrains/logos"), "..", "..")
            ]
        }
    );
})(config);