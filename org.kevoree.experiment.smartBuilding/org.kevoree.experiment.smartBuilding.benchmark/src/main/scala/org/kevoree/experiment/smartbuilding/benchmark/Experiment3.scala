package org.kevoree.experiment.smartbuilding.benchmark

import org.kevoree.ContainerRoot
import org.kevoreeAdaptation.AdaptationModel
import org.kevoree.tools.marShell.ast.Script
import org.kevoree.tools.marShellTransform.{AdaptationModelWrapper, KevScriptWrapper}
import util.Random
import org.kevoree.framework.KevoreeXmiHelper

/**
 * User: ffouquet
 * Date: 23/06/11
 * Time: 10:16
 */

class Experiment3 extends AbstractExperiment {

  val random = new Random
  var knodeName = "kbenchmark"


  //STEP 0 : Init Base model
  val modelBase = this.getClass.getClassLoader.getResource("baseKBenchBig1.kev").getPath
  var model: ContainerRoot = KevoreeXmiHelper.load(modelBase)
  val model2Path = this.getClass.getClassLoader.getResource("baseKBenchBig2.kev").getPath
  var model2: ContainerRoot = KevoreeXmiHelper.load(model2Path)

  val model3Path = this.getClass.getClassLoader.getResource("baseKBenchBig3.kev").getPath
  var model3: ContainerRoot = KevoreeXmiHelper.load(model3Path)
  val model4Path = this.getClass.getClassLoader.getResource("baseKBenchBig4.kev").getPath
  var model4: ContainerRoot = KevoreeXmiHelper.load(model4Path)

  val models = List(model, model2, model3, model4)
  var previousModel: ContainerRoot = null

  override def init() {
    super.initNode(knodeName, model)
    previousModel = model
  }

  var twa : TwoWayActors = null
  def runExperiment(_twa: TwoWayActors) {
    twa = _twa;
    for (i <- 0 until 200) {
      doPhase(i)
    }
  }

  def doPhase(i: Int) {
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
    val resultScript: String = KevScriptWrapper.generateKevScriptCompressed(baseScript)
    //println("ReconfSTEP=>" + resultScript)
    val randomToken = random.nextInt(9)
    println(resultScript)
    (twa.sendAndWait(("$" + randomToken) + resultScript, ("ack" + randomToken), 2000))
    interpetResult(i, twa.recString)
    twa.recString = ""
  }

  def interpetResult(i: Int, s: String) {
    s.split('\n').foreach {
      line =>
      //println(line)
        if (line.startsWith("mem")) {
          SmartSensorsGUI.putMemValue(i, Integer.parseInt(line.substring(3).trim()))
          addToRaw(i,"SDRAM",Integer.parseInt(line.substring(3).trim()))
        }
        if (line.startsWith("emem")) {
          SmartSensorsGUI.putEMemValue(i, Integer.parseInt(line.substring(4).trim()))
          addToRaw(i,"EEPROM",Integer.parseInt(line.substring(4).trim()))
        }
        if (line.startsWith("ms")) {
          SmartSensorsGUI.putRTimeValue(i, Integer.parseInt(line.substring(2).trim()))
          addToRaw(i,"DOWNTIME",Integer.parseInt(line.substring(2).trim()))
        }
    }
  }

}