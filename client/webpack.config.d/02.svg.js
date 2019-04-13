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