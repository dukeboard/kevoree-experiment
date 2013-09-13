package org.kevoree.microsandbox.script

import java.io.File

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 10/09/13
 * Time: 19:30
 *
 * @author Erwan Daubert
 * @version 1.0
 */

fun main(args: Array<String>) {
    val folder = File("/home/edaubert/microsandbox")
    if (!folder.exists() || (folder.isFile() && folder.delete())) {
        folder.mkdirs()
    }

    val generatorSmall = ScriptGeneratorSmallFaulty()

    generatorSmall.generateDefault(folder.getAbsolutePath())

    generatorSmall.generate(folder.getAbsolutePath(), "FaultyCPU")

//    generatorSmall.generate(folder.getAbsolutePath(), "FaultyMemory")
}