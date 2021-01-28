// wrap is useful, because declaring variables in module can be already declared
// module creates own lexical environment
;(function (config) {
    const shouldRunServer = config.mode !== "production"
    const serverTaskName = ":server:devServer"
    const serverUrl = 'http://localhost:8081'

    if (shouldRunServer) {
        console.log("Running " + serverTaskName + " in background...")
        const platform = require('os').platform()
        // __dirname = $ROOT/build/js/packages/$PACKAGE_NAME
        // rootProject = $ROOT
        const rootProject = require('path').resolve(__dirname, '../../../../')
        let prefix = ''
        if (platform !== 'win32') {
            prefix = './'
        }
        const child = require('child_process').exec(
            `${prefix}gradlew ` + serverTaskName,
            {
                "cwd": rootProject
            },
            (err, stdout, stderr) => {
                if (err) {
                    console.log("Cannot run " + serverTaskName + " server: " + err);
                }
            }
        )

        let isBackendRun = false

        config.devServer = config.devServer || {}
        config.devServer.before = function (app, server, compiler) {
            if (isBackendRun) return

            isBackendRun = true

            const originalClose = server.middleware.close;
            server.middleware.close = function () {
                child.kill('SIGINT');
                originalClose(arguments)
            }
        }
        config.devServer.proxy = {
            '/': {
                target: serverUrl,
                secure: false,
                bypass: function (req, res, proxyOptions) {
                    if (req.headers.accept.indexOf('.js') !== -1) {
                        return req.headers.accept;
                    }
                }
            }
        }
    }
})(config);