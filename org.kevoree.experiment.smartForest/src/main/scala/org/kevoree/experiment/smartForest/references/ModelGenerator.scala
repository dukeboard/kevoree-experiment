package org.kevoree.experiment.smartForest.references

import java.net.{URLDecoder, URL}
import org.kevoree.tools.marShell.parser.KevsParser
import org.kevoree.tools.marShell.interpreter.KevsInterpreterContext
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.kevoree.{KevoreePackage, ContainerRoot}
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.xmi.{XMLResource, XMIResource}
import org.kevoree.tools.marShell.interpreter.KevsInterpreterAspects._
import org.kevoree.experiment.smartForest.SmartForestExperiment

import org.kevoree.experiment.smartForest.dpa.{PeriodValues, ChangePeriodPropertyDPAO}
import java.lang.Math

/**
 * User: ffouquet
 * Date: 22/07/11
 * Time: 17:23
 */

object ModelGenerator {

  val parser: KevsParser = new KevsParser
  val superNodesIndices = List((0, 0), (0, SmartForestExperiment.forestWidth - 1), (SmartForestExperiment.forestWidth - 1, 0), (SmartForestExperiment.forestWidth - 1, SmartForestExperiment.forestWidth - 1))

  def generateForest(forestWidth: Int): ContainerRoot = {
    var url: URL = null
    var path: String = ""
    try {
      url = this.getClass.getClassLoader.getResource("defaultLibrary.kev")
      path = URLDecoder.decode(url.toString, "UTF-8")
    }
    catch {
      case e: Exception => {
        e.printStackTrace()
      }
    }

    val myModel: ContainerRoot = load(path)

    for (i <- 0 until (forestWidth * forestWidth)) {
      val scriptString: String = "tblock {\n addNode node" + i + ":ArduinoNode \n " +
        "addComponent temperature" + i + "@node" + i + " : TempSensor { " + ChangePeriodPropertyDPAO.periodPropertyName + "=\"" + getPeriod(i, forestWidth) + "\"}" +
        "addComponent humidity" + i + "@node" + i + " : HumiditySensor { " + ChangePeriodPropertyDPAO.periodPropertyName + "=\"" + getPeriod(i, forestWidth) + "\"}" +
        "addComponent smoke" + i + "@node" + i + " : SmokeSensor { " + ChangePeriodPropertyDPAO.periodPropertyName + "=\"" + getPeriod(i, forestWidth) + "\"} \n }"


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


  private def getPeriod(indice: Int, forestWidth: Int): Int = {
    val distance = getDistanceWithClosestSuperNode(indice, forestWidth)
    val targetFrequence = 1000 / PeriodValues.values.min
    val target = targetFrequence / Math.pow((forestWidth / 2), 2)
    val theoreticalFrequence = target * Math.pow(distance, 2)
    var periodeResult = PeriodValues.values.min
    PeriodValues.values.foreach{
      periode =>
        if (Math.abs(theoreticalFrequence - (1000 / periode)) < Math.abs(theoreticalFrequence - (1000 / periodeResult))){
          periodeResult = periode
        }
    }
    //println("Distance = " + distance + ", periode = " + periodeResult)
    periodeResult
  }

  private def getDistanceWithClosestSuperNode(indice: Int, forestWidth: Int): Int = {
    val i: Int = indice / forestWidth
    val j: Int = indice - (i * forestWidth)
    var minDistance = forestWidth
    superNodesIndices.foreach {
      t =>
        val distanceI = Math.abs(t._1 - i)
        val distanceJ = Math.abs(t._2 - j)
        if (Math.max(distanceI, distanceJ) < minDistance) {
          minDistance = Math.max(distanceI, distanceJ)
        }
    }
    minDistance
  }

  def load(uri: String): ContainerRoot = {
    val rs = new ResourceSetImpl();
    rs.getResourceFactoryRegistry.getExtensionToFactoryMap.put("kev", new XMIResourceFactoryImpl());
    rs.getPackageRegistry.put(KevoreePackage.eNS_URI, KevoreePackage.eINSTANCE);
    val res = rs.getResource(URI.createURI(uri), true);
    res.asInstanceOf[XMIResource].getDefaultLoadOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
    res.asInstanceOf[XMIResource].getDefaultSaveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
    val result = res.getContents.get(0);
    result.asInstanceOf[ContainerRoot]
  }


}