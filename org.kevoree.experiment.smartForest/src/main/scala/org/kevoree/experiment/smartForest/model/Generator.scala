package org.kevoree.experiment.smartForest.model

import java.net.{URLDecoder, URL}
import org.kevoree.tools.marShell.parser.KevsParser
import org.kevoree.tools.marShell.ast.Script
import org.kevoree.tools.marShell.interpreter.{KevsInterpreterAspects, KevsInterpreterContext}
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.kevoree.{KevoreePackage, NamedElement, ContainerRoot}
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.xmi.{XMLResource, XMIResource}
import org.kevoree.tools.marShell.interpreter.KevsInterpreterAspects._

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
      url = this.getClass.getClassLoader.getResource("baseMyNodes.kev")
      path = URLDecoder.decode(url.toString, "UTF-8")
    }
    catch {
      case e: Exception => {
        e.printStackTrace()
      }
    }

    val myModel: ContainerRoot = load(path)

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