package org.kevoree.experiment.modelScript.experiment

import org.kevoree.tools.marShell.interpreter.KevsInterpreterContext
import org.kevoree.tools.marShell.parser.{ParserUtil, KevsParser}
import org.kevoree.framework.KevoreeXmiHelper
import java.net.URL
import java.io.{InputStreamReader, BufferedReader, OutputStreamWriter, ByteArrayOutputStream}
import scala.Predef._
import org.jgrapht.alg.ConnectivityInspector
import org.kevoree.{NodeNetwork, ContainerNode}
import org.kevoree.experiment.modelScript.{KevTopo2JGraph, Configuration, TopologyGenerator, Kev2GraphML}

/**
 * User: ffouquet
 * Date: 04/08/11
 * Time: 08:43
 */

object FailureManagement extends App {

  val delay = 10000

  println("Kevoree Experiment - Failure global management")
  println("Test usability of mesh network with high level of failure across nodes")

  org.kevoree.experiment.modelScript.Configuration.build()

  println(Configuration.packets.mkString("\n"))
  println("logServer = " + Configuration.logServer)
  println("sendNotification = " + true)
  println("alwaysAskModel = " + false)
  println("delay = " + delay)


  val model = KevoreeXmiHelper.loadStream(this.getClass.getClassLoader.getResourceAsStream("baseModelEvolutionWithLight.kev"))
  val tscript = new StringBuilder

  tscript append "tblock {"

  tscript.append(TopologyGenerator.generate(
    Configuration.packets,
    Configuration.logServer,
    true,
    false,
    delay,
    5,
    2
  ))

  tscript append "addComponent "
  tscript append "myFakeConsole1"
  tscript append "@"
  tscript append findNodeName(tscript) //"duke0"
  tscript append ":"
  tscript append "FakeConsole"

  tscript append "}\n"


  val parser = new KevsParser();
  val script = parser.parseScript(tscript.toString())
  script match {
    case Some(validScript) => {
      import org.kevoree.tools.marShell.interpreter.KevsInterpreterAspects._
      if (validScript.interpret(KevsInterpreterContext(model))) {
        ParserUtil.save("bootStrapComplex.kev", model)
        val outStream = new ByteArrayOutputStream

        KevoreeXmiHelper.saveStream(outStream, model)
        outStream.flush()

        Kev2GraphML.toGraphMLFile("bootStrapComplex", model)

        //Try to push to all
        Configuration.ips.foreach {
          ip =>
            try {
              val url = new URL("rhttp://" + ip + ":8080");
              println("send to " + url)
              val conn = url.openConnection();
              conn.setConnectTimeout(2000);
              conn.setDoOutput(true);
              val wr = new OutputStreamWriter(conn.getOutputStream)
              wr.write(outStream.toString);
              wr.flush();

              // Get the response
              val rd = new BufferedReader(new InputStreamReader(conn.getInputStream));
              var line: String = rd.readLine;
              while (line != null) {
                println("ipReturn" + line)
                line = rd.readLine
              }
              wr.close();
              rd.close();

            } catch {
              case _@e => e.printStackTrace()
            }
        }



        val connectivity = new ConnectivityInspector[ContainerNode,NodeNetwork](KevTopo2JGraph(model))


        println(connectivity.connectedSetOf(model.getNodes.get(0)))






      } else {
        println("Interpreter Error")
      }

    }
    case None => {
      println("DTC Error !")
      println(parser.lastNoSuccess)
    }
  }

  def findNodeName(script: StringBuilder): String = {
    val index = script.indexOf("addNode ")
    if (index > -1) {
      //println(script.substring(index + "addNode ".length(), script.indexOf("\n", index + 1) - " : JavaSENode".length()))
      script.substring(index + "addNode ".length(), script.indexOf("\n", index + 1) - " : JavaSENode".length())
    } else {
      println("there is no node available so we cannot add a component")
      "duke0"
    }
  }





}