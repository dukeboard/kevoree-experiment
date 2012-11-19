package org.kevoree.experiment.smartbuilding.benchmark

import org.kevoree.ContainerRoot
import org.kevoreeAdaptation.AdaptationModel
import org.kevoree.tools.marShell.ast.Script
import org.kevoree.tools.marShellTransform.{AdaptationModelWrapper, KevScriptWrapper}
import util.Random
import org.kevoree.framework.KevoreeXmiHelper
import org.kevoree.extra.kserial.KevoreeSharedCom
import org.kevoree.experiment.smartbuilding.com.NativeLibUtil

/**
 * User: ffouquet
 * Date: 23/06/11
 * Time: 10:16
 */

class Experiment5 extends AbstractExperiment {

  val random = new Random
  var knodeName = "kbenchmark"
  boardPortName = "*"
  boardTypeName = "atmega328"

  println("Begin read model")

  //STEP 0 : Init Base model
  val modelBase = this.getClass.getClassLoader.getResourceAsStream("baseKBenchBig21.kevs")
  var model: ContainerRoot = NativeLibUtil.loadKevs(modelBase)
  val model2Path = this.getClass.getClassLoader.getResourceAsStream("baseKBenchBig22.kevs")
  var model2: ContainerRoot = NativeLibUtil.loadKevs(model2Path)
  val model3Path = this.getClass.getClassLoader.getResourceAsStream("baseKBenchBig23.kevs")
  var model3: ContainerRoot = NativeLibUtil.loadKevs(model3Path)
  val model4Path = this.getClass.getClassLoader.getResourceAsStream("baseKBenchBig24.kevs")
  var model4: ContainerRoot = NativeLibUtil.loadKevs(model4Path)

  val models = List(model, model2, model3, model4)
  var previousModel: ContainerRoot = null

  println("Model ready")


  override def init() {
    super.initNode(knodeName, model)
    previousModel = model
  }


  def runExperiment() {
    for (i <- 0 until 100) {
      doPhase(i)
    }
  }

  def doPhase(i: Int) {
    indice = i
    var newModel = models(random.nextInt(models.size))
    while (newModel == previousModel) {
      newModel = models(random.nextInt(models.size))
    }
    doStep(previousModel, newModel, i);
    previousModel = newModel
    Thread.sleep(200)
  }

  def doStep(modelA: ContainerRoot, modelB: ContainerRoot, i: Int) {
    val modelAtoB: AdaptationModel = kompare.kompare(modelA, modelB, knodeName)
    val baseScript: Script = KevScriptWrapper.miniPlanKevScript(AdaptationModelWrapper.generateScriptFromAdaptModel(modelAtoB))
    val resultScript: String = KevScriptWrapper.generateKevScriptCompressed(baseScript,"kbenchmark")
    println("ReconfSTEP=>" + resultScript)
    val randomToken = random.nextInt(9)
    println(resultScript)

    KevoreeSharedCom.sendSynch(boardPortName, ("$" + randomToken) + resultScript, ("ack" + randomToken), 2000)

    addToRaw(i, "ssize", resultScript.size)
    SmartSensorsGUI.putSSIZEValue(i, resultScript.size)
  }

  def interpetResult(i: Int, s: String) {
    println("Result=>"+s)
    try {
      s.split('\n').foreach {
        line =>
          println(line)
          if (line.startsWith("mem")) {
            SmartSensorsGUI.putMemValue(i, Integer.parseInt(line.substring(3).trim()))
            addToRaw(i, "SDRAM", Integer.parseInt(line.substring(3).trim()))
          }
          if (line.startsWith("emem")) {
            SmartSensorsGUI.putEMemValue(i, Integer.parseInt(line.substring(4).trim()))
            addToRaw(i, "EEPROM", Integer.parseInt(line.substring(4).trim()))
          }
          if (line.startsWith("ms")) {
            SmartSensorsGUI.putRTimeValue(i, Integer.parseInt(line.substring(2).trim()))
            addToRaw(i, "DOWNTIME", Integer.parseInt(line.substring(2).trim()))
          }
      }
    } catch {
      case _@e => e.printStackTrace()
    }
  }


}