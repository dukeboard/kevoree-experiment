package org.kevoree.microsandbox.script

import java.io.BufferedWriter
import java.io.FileWriter
import java.io.File

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 10/09/13
 * Time: 19:07
 *
 * @author Erwan Daubert
 * @version 1.0
 */

class ScriptGeneratorSmallFaulty : FileManipulation {
    val scriptGenerator = ScriptGenerator()

    fun generateDefault(folder : String) {
        var faultyComponent = ""
        var  script = scriptGenerator.generate(true, "2.0.12", "2.0.4", "1.0-SNAPSHOT", "1.0.0-SNAPSHOT", faultyComponent, faultyComponent, global = true, allComponent = true)
        save(script, folder + File.separator  + "default-global" + ".kevs")

        script = scriptGenerator.generate(true, "2.0.12", "2.0.4", "1.0-SNAPSHOT", "1.0.0-SNAPSHOT", faultyComponent, faultyComponent, global = false, allComponent = true)
        save(script, folder + File.separator  + "default-adaptive-all" + ".kevs")

        script = scriptGenerator.generate(true, "2.0.12", "2.0.4", "1.0-SNAPSHOT", "1.0.0-SNAPSHOT", faultyComponent, faultyComponent, global = false, allComponent = false)
        save(script, folder + File.separator  + "default-adaptive-single" + ".kevs")
    }

    fun generate(folder : String, suffix : String) {

        val extension = "globalMonitoring.kevs"

        val faultyComponents = array("WsServer", "WebbitHTTPServer", "ResourcesPage", "FollowManager", "JavaFXWebBrowser", "WebSocketClientHandler", "JavaFXVideoDisplay")

        faultyComponents.forEach{
            faultyComponent ->
            var script = scriptGenerator.generate(true, "2.0.12", "2.0.4", "1.0-SNAPSHOT", "1.0.0-SNAPSHOT", faultyComponent, suffix, global = true, allComponent = true)
            save(script, folder + File.separator  + faultyComponent + suffix + "-global" + extension)
            script = scriptGenerator.generate(true, "2.0.12", "2.0.4", "1.0-SNAPSHOT", "1.0.0-SNAPSHOT", faultyComponent, suffix, global = false, allComponent = true)
            save(script, folder + File.separator  + faultyComponent + suffix + "-adaptive-all" +extension)
            script = scriptGenerator.generate(true, "2.0.12", "2.0.4", "1.0-SNAPSHOT", "1.0.0-SNAPSHOT", faultyComponent, suffix, global = false, allComponent = false)
            save(script, folder + File.separator  + faultyComponent + suffix + "-adaptive-single" + extension)
        }

        /*var faultyComponent = "WsServer"
        var script = scriptGenerator.generate(true, "2.0.12", "2.0.4", "1.0-SNAPSHOT", "1.0.0-SNAPSHOT", faultyComponent, suffix, global = true, allComponent = true)
        save(script, folder + File.separator  + faultyComponent + suffix + "-global" + extension)
        script = scriptGenerator.generate(true, "2.0.12", "2.0.4", "1.0-SNAPSHOT", "1.0.0-SNAPSHOT", faultyComponent, suffix, global = false, allComponent = true)
        save(script, folder + File.separator  + faultyComponent + suffix + "-adaptive-all" +extension)
        script = scriptGenerator.generate(true, "2.0.12", "2.0.4", "1.0-SNAPSHOT", "1.0.0-SNAPSHOT", faultyComponent, suffix, global = false, allComponent = false)
        save(script, folder + File.separator  + faultyComponent + suffix + "-adaptive-single" + extension)

        faultyComponent = "WebbitHTTPServer"
        script = scriptGenerator.generate(true, "2.0.12", "2.0.4", "1.0-SNAPSHOT", "1.0.0-SNAPSHOT", faultyComponent, suffix)
        save(script, folder + File.separator  + faultyComponent + suffix + extension)

        faultyComponent = "ResourcesPage"
        script = scriptGenerator.generate(true, "2.0.12", "2.0.4", "1.0-SNAPSHOT", "1.0.0-SNAPSHOT", faultyComponent, suffix)
        save(script, folder + File.separator  + faultyComponent + suffix + extension)

        faultyComponent = "FollowManager"
        script = scriptGenerator.generate(true, "2.0.12", "2.0.4", "1.0-SNAPSHOT", "1.0.0-SNAPSHOT", faultyComponent, suffix)
        save(script, folder + File.separator  + faultyComponent + suffix + extension)

        faultyComponent = "JavaFXWebBrowser"
        script = scriptGenerator.generate(true, "2.0.12", "2.0.4", "1.0-SNAPSHOT", "1.0.0-SNAPSHOT", faultyComponent, suffix)
        save(script, folder + File.separator  + faultyComponent + suffix + extension)

        faultyComponent = "WebSocketClientHandler"
        script = scriptGenerator.generate(true, "2.0.12", "2.0.4", "1.0-SNAPSHOT", "1.0.0-SNAPSHOT", faultyComponent, suffix)
        save(script, folder + File.separator  + faultyComponent + suffix + extension)

        faultyComponent = "JavaFXVideoDisplay"
        script = scriptGenerator.generate(true, "2.0.12", "2.0.4", "1.0-SNAPSHOT", "1.0.0-SNAPSHOT", faultyComponent, suffix)
        save(script, folder + File.separator + faultyComponent + suffix + ".kevs")*/
    }
}