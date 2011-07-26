package org.kevoree.experiment.trace.gui.alg

import org.kevoree.experiment.trace.TraceMessages
import java.io.{File, FileInputStream, InputStream}

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 26/07/11
 * Time: 11:33
 */

object RecursiveRGeneratorApp extends App {

  val entryPoint = "paradent61rennesgrid5000fr0"
  val resultsFolder = "/home/edaubert/workspace/kevoree-experiment/org.kevoree.experiment.trace.gui/results"

  new File(resultsFolder).listFiles().filter(f => f.isDirectory).foreach{
    f => try {
      generate(f.getAbsolutePath)
    }catch {
      case _@e => e.printStackTrace()
    }
  }


  def generate (folderName: String) {
    println(folderName)
    val inputLazy: InputStream = new FileInputStream(new File(folderName + File.separator + "trace_out"))
    val tracesLazy: TraceMessages.Traces = TraceMessages.Traces.parseFrom(inputLazy)
    val allPath = TracePath.getAllPathFrom(entryPoint, 3, tracesLazy)
    RGenerator.generateFile(RGenerator.generatePropagationTimeScript(allPath), folderName + File.separator + "outAll.r")
  }
}