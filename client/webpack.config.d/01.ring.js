const ringConfig = require('@jetbrains/ring-ui/webpack.config').config;

config.module.rules.push(...ringConfig.module.rules);