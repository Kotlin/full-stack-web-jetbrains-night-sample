console.log("Running ktor in background...")
require('child_process').exec(
    "./gradlew :server:devServer", {
        "cwd": "/Users/Sergey.Rostov/kotlin-full-stack-application-demo"
    }, (err, stdout, stderr) => {
         if (err) {
             console.log("Cannot run ktor server: " + err);
             return;
         }

         // the *entire* stdout and stderr (buffered)
         console.log(`stdout: ${stdout}`);
         console.log(`stderr: ${stderr}`);
     }
 )

config.devServer = {
    proxy: {
      '/': {
        target: 'http://localhost:8081',
        secure: false,
        bypass: function(req, res, proxyOptions) {
          if (req.headers.accept.indexOf('client.js') !== -1) {
            return req.headers.accept;
          }
        }
      }
    }
}