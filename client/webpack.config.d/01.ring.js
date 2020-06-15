// wrap is useful, because declaring variables in module can be already declared
// module creates own lexical environment
config.module.rules.pop()

;(function (config) {
    const ringConfig = require('@jetbrains/ring-ui/webpack.config').config;

    config.module.rules.push(...ringConfig.module.rules);
})(config);