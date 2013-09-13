package org.kevoree.microsandbox.script

import java.io.InputStream
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 10/09/13
 * Time: 17:57
 *
 * @author Erwan Daubert
 * @version 1.0
 */
class ScriptGenerator : FileManipulation {
    fun generate(small: Boolean = true, kevoreeVersion: String, kevoreCoreLibraryVersion: String, microsandboxVersion: String, experimentVersion: String, faultyComponentName: String, faultyComponentSuffix: String, global: Boolean = true, allComponent: Boolean = true): String {
        // load default script
        val scriptBuilder = loadFromStream(javaClass<ScriptGenerator>().getClassLoader()!!.getResourceAsStream("nodeScript.kevs")!!)

        if (global) {
            // load Global monitoring script script
            scriptBuilder.append(loadFromStream(javaClass<ScriptGenerator>().getClassLoader()!!.getResourceAsStream("globalMonitoringScript.kevs")!!).toString())
        } else if (allComponent) {
            // load adaptiveMonitoring for all script
            scriptBuilder.append(loadFromStream(javaClass<ScriptGenerator>().getClassLoader()!!.getResourceAsStream("adaptiveMonitoringAllScript.kevs")!!).toString())
        } else {
            // load adaptiveMonitoring with ranking script
            scriptBuilder.append(loadFromStream(javaClass<ScriptGenerator>().getClassLoader()!!.getResourceAsStream("adaptiveMonitoringRankingScript.kevs")!!).toString())
        }

        if (small) {
            // load small script
            scriptBuilder.append(loadFromStream(javaClass<ScriptGenerator>().getClassLoader()!!.getResourceAsStream("smallScript.kevs")!!).toString())
        } else {
            // TODO
        }

        replaceVariable("{kevoree.version}", kevoreeVersion, scriptBuilder)

        replaceVariable("{kevoree.corelibrary.version}", kevoreCoreLibraryVersion, scriptBuilder)

        replaceVariable("{kevoree.microsandbox.version}", microsandboxVersion, scriptBuilder)

        replaceVariable("{experiment.version}", experimentVersion, scriptBuilder)

        replaceVariable("{experiment.version}", faultyComponentName + faultyComponentSuffix, scriptBuilder)

        return scriptBuilder.toString();
    }

    fun replaceVariable(variable: String, newValue: String, scriptBuilder: StringBuilder) {
        if (!newValue.equals("")) {
            var index = 0
            index = scriptBuilder.indexOf(variable, index)
            while (index < scriptBuilder.length() && index >= 0) {
                scriptBuilder.replace(index, index + variable.length(), newValue)
                index = scriptBuilder.indexOf(variable, index + newValue.length())
            }
        }
    }
}