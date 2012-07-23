package org.kevoree.experiment.smartForest.benchmark

import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.kevoree.{KevoreePackage, ContainerRoot}
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.xmi.{XMLResource, XMIResource}
import java.io.File

/**
 * Created by IntelliJ IDEA.
 * User: jbourcie
 * Date: 07/11/11
 * Time: 10:27
 * To change this template use File | Settings | File Templates.
 */

object  CloneBenchmark extends App {


  var myModel = load("homega-homega-generated\\models\\Models100")
  var initialTime = System.currentTimeMillis();
  (1 to 100).foreach{ i =>
    myModel = load("homega-homega-generated\\models\\Models100")
  }
  var finalTime = System.currentTimeMillis();
  println("Time spent for 10000 copy : " + (finalTime-initialTime))


  def load(uri: String): ContainerRoot = {
    val rs = new ResourceSetImpl();
    rs.getResourceFactoryRegistry.getExtensionToFactoryMap.put("*", new XMIResourceFactoryImpl());
    rs.getPackageRegistry.put(KevoreePackage.eNS_URI, KevoreePackage.eINSTANCE);
    val res = rs.getResource(URI.createURI(uri), true);
    res.asInstanceOf[XMIResource].getDefaultLoadOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
    res.asInstanceOf[XMIResource].getDefaultSaveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
    val result = res.getContents.get(0);
    result.asInstanceOf[ContainerRoot]
  }

}