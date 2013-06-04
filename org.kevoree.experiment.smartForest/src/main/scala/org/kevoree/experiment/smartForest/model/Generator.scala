package org.kevoree.experiment.smartForest.model

import java.net.{URLDecoder, URL}
import org.kevoree.tools.marShell.parser.KevsParser
import org.kevoree.tools.marShell.ast.Script
import org.kevoree.tools.marShell.interpreter.{KevsInterpreterAspects, KevsInterpreterContext}
import org.kevoree.tools.marShell.interpreter.KevsInterpreterAspects._
import org.kevoree.ContainerRoot
import org.kevoree.framework.KevoreeXmiHelper

/**
 * User: ffouquet
 * Date: 22/07/11
 * Time: 17:23
 */

object Generator {

  val parser: KevsParser = new KevsParser

  def generateForest(forestWidth: Int): ContainerRoot = {
    var url: URL = null
    var path: String = ""
    try {
      path = this.getClass.getClassLoader.getResource("defaultLibrary.kev").getPath
     // path = URLDecoder.decode(url.toString, "UTF-8")
    }
    catch {
      case e: Exception => {
        e.printStackTrace()
      }
    }

    val myModel: ContainerRoot = KevoreeXmiHelper.instance$.load(path)

    for (i <- 0 until (forestWidth * forestWidth)) {
      val scriptString: String = "tblock {\n addNode node" + i + ":ArduinoNode \n }"
      parser.parseScript(scriptString) match {
        case Some(script) => {
          val context = KevsInterpreterContext(myModel)
          script.interpret(context)

        }
        case None =>
      }
    }

    myModel

  }



}