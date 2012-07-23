package org.kevoree.experiment.smartForest.benchmark

import org.kevoree.framework.KevoreeXmiHelper
import org.kevoree.ContainerRoot
import org.kevoree.experiment.smartForest.references.ModelGenerator
import org.kevoree.experiment.smartForest.dpa.AddForestMonitoringComponentDPA
import org.kevoree.tools.marShell.interpreter.{KevsInterpreterContext, KevsInterpreterAspects}
import org.eclipse.emf.ecore.util.EcoreUtil
import java.io.{FileInputStream, File}

/**
 * Created by IntelliJ IDEA.
 * User: jbourcie
 * Date: 07/11/11
 * Time: 11:01
 * To change this template use File | Settings | File Templates.
 */

object ModelBenchmarking extends App {

  val myModel = ModelGenerator.generateForest(20)
  val copyIterations = 1000
  val saveIterations = 100
  val loadIterations = 1
  val readIterations = 1000
  val writeIterations = 1000
  val pathToTempModel = "Models"+File.separator+"tempModel.kev"

  println("Time spent for " + copyIterations + " copy : " + benchmarkCopy(myModel, copyIterations))
  println("Time spent for " + saveIterations + " save : " + benchmarkSave(myModel, saveIterations))

  KevoreeXmiHelper.save(pathToTempModel, myModel)
  println("Time spent for " + loadIterations + " load : " + benchmarkLoad(pathToTempModel, loadIterations))
  println("Time spent for " + readIterations + " read : " + benchmarkModelReadingThroughDPA(myModel, readIterations))
  println("Time spent for " + writeIterations + " write : " + benchmarkModelModifcationsThroughDPA(myModel, writeIterations))


  def benchmarkCopy(model: ContainerRoot, iterations: Int)= {
    val initialTime = System.currentTimeMillis()
    (1 to iterations).foreach {
      i =>
        EcoreUtil.copy(model)
    }
    val finalTime = System.currentTimeMillis()
    finalTime - initialTime
  }

  def benchmarkSave(model: ContainerRoot, iterations: Int) = {
    val initialTime = System.currentTimeMillis();
    (1 to iterations).foreach {
      i =>
        KevoreeXmiHelper.save(pathToTempModel, model)
    }
    val finalTime = System.currentTimeMillis();
    finalTime - initialTime
  }

  def benchmarkLoad(path: String, iterations: Int) = {
    val initialTime = System.currentTimeMillis();
    (1 to iterations).foreach {
      i =>
        KevoreeXmiHelper.loadStream(new FileInputStream(new File(path)))
    }
    val finalTime = System.currentTimeMillis();
    finalTime - initialTime
  }

  def benchmarkModelReadingThroughDPA(model: ContainerRoot, iterations: Int) = {
    val smartForestDPA = new AddForestMonitoringComponentDPA()
    val initialTime = System.currentTimeMillis();
    (1 to iterations).foreach {
      i =>
        smartForestDPA.applyPointcut(model)
    }
    val finalTime = System.currentTimeMillis();
    finalTime - initialTime
  }

  def benchmarkModelModifcationsThroughDPA(model: ContainerRoot, iterations: Int) = {
    val smartForestDPA = new AddForestMonitoringComponentDPA()
    var totalTime = 0l
    (1 to iterations).foreach {
      i =>
        val tempModel = EcoreUtil.copy(model)
        val context = new KevsInterpreterContext(tempModel)
        val myLists = smartForestDPA.applyPointcut(tempModel)
        if (!myLists.isEmpty) {
          val myMap = myLists.get(0)
          val script = smartForestDPA.getASTScript(myMap)
          val initialTime = System.currentTimeMillis()
          KevsInterpreterAspects.rich(script).interpret(context)
          val finalTime = System.currentTimeMillis()
          totalTime += finalTime - initialTime
        }
    }

    totalTime
  }
}