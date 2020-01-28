console.log("Running ktor in background...")
// $ROOT/build/js/packages/kotlin-full-stack-application-demo-client
const rootProject = require('path').resolve(__dirname, '../../../../')
console.log(rootProject)
require('child_process').exec(
    "./gradlew :server:devServer", {
        "cwd": rootProject
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