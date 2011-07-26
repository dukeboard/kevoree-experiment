package org.kevoree.experiment.smartForest.experiment

import org.kevoree.library.tools.dpa.DPA
import org.kevoree.tools.marShell.interpreter.{KevsInterpreterAspects, KevsInterpreterContext}
import ec.util.Parameter
import org.eclipse.emf.ecore.util.EcoreUtil
import org.kevoree.kompare.KevoreeKompareBean
import org.kevoreeAdaptation.AdaptationModel
import java.io.File
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.xmi.{XMLResource, XMIResource}
import org.kevoree.tools.marShell.parser.ParserUtil
import org.kevoree.library.reasoner.ecj.KevoreeDefaults
import org.kevoree.{ KevoreePackage,  ContainerRoot}
import ec.{Individual, EvolutionState}
import scala.collection.JavaConversions._

/**
 * Created by IntelliJ IDEA.
 * User: jbourcie
 * Date: 25/07/11
 * Time: 10:19
 * To change this template use File | Settings | File Templates.
 */

abstract class KevoreeIndividualAbstract extends Individual{

  var mutationDpas: Array[DPA]
  var minMutationDpasNumber: Int
  var maxMutationDpasNumber: Int

  var resetDpas: Array[DPA]
  var minResetDpasNumber: Int
  var maxResetDpasNumber: Int

  val baseModelPath: String

  val P_KEVOREE_INDIVIDUAL: String = "kev-ind"
  val FOLDER_TO_STORE_MODELS: String = "models-folder"

  var myModel: ContainerRoot = null

  private var model_path: String = null
  private var increment: Int = 0


  /**
   * Destructively mutates the individual in some default manner.
   */
  def defaultMutate(state: EvolutionState, thread: Int): Unit = {
    val context = new KevsInterpreterContext(myModel)
    var numberOfMutation = minMutationDpasNumber
    if (maxMutationDpasNumber != minMutationDpasNumber)
      numberOfMutation = minMutationDpasNumber + state.random(thread).nextInt(maxMutationDpasNumber-minMutationDpasNumber)
    (1 to numberOfMutation).foreach{ _ =>
      val myDPA = mutationDpas(state.random(thread).nextInt(mutationDpas.length))
      val myLists = myDPA.applyPointcut(myModel)
      if (!myLists.isEmpty) {
        val myMap = myLists.get(state.random(thread).nextInt(myLists.size))
        val script = myDPA.getASTScript(myMap)
        KevsInterpreterAspects.rich(script).interpret(context)
      }
    }
  }

  /**
   * Initializes the individual.
   */
  def reset(state: EvolutionState, thread: Int): Unit = {
    val context = new KevsInterpreterContext(myModel)
    var numberOfMutation = minResetDpasNumber
    if (maxResetDpasNumber != minResetDpasNumber)
      numberOfMutation = minResetDpasNumber + state.random(thread).nextInt(maxResetDpasNumber-minResetDpasNumber)
    (1 to numberOfMutation).foreach{ _ =>
      val myDPA = resetDpas(state.random(thread).nextInt(resetDpas.length))
      val myLists = myDPA.applyPointcut(myModel)
      if (!myLists.isEmpty) {
        val myMap = myLists.get(state.random(thread).nextInt(myLists.size))
        val script = myDPA.getASTScript(myMap)
        KevsInterpreterAspects.rich(script).interpret(context)
      }
    }
  }

  def defaultCrossover(state: EvolutionState, thread: Int, ind: KevoreeIndividualAbstract): Unit = {}

  def getGenome: AnyRef = {
    return myModel
  }

  def setGenome(gen: AnyRef): Unit = {
    if (gen.isInstanceOf[ContainerRoot]) {
      this.myModel = gen.asInstanceOf[ContainerRoot]
    }
  }

  def genomeLength: Int = {
    return size.asInstanceOf[Int]
  }

  def defaultBase: Parameter = {
    return KevoreeDefaults.base.push(P_KEVOREE_INDIVIDUAL)
  }

  override def clone: AnyRef = {
    val ki: KevoreeIndividualAbstract = super.clone.asInstanceOf[KevoreeIndividualAbstract]
    ki.myModel = EcoreUtil.copy(myModel)
    ki.mutationDpas = mutationDpas
    ki.minMutationDpasNumber = minMutationDpasNumber
    ki.maxMutationDpasNumber = maxMutationDpasNumber
    ki.resetDpas = resetDpas
    ki.minResetDpasNumber = minResetDpasNumber
    ki.maxResetDpasNumber = maxResetDpasNumber
    return ki
  }

  override def equals(ind: Any): Boolean = {
    if (!(ind.isInstanceOf[KevoreeIndividualAbstract])) {
      return false
    }
    if (myModel.getNodes.size != ind.asInstanceOf[KevoreeIndividualAbstract].myModel.getNodes.size) {
      return false
    }
    val kkb = new KevoreeKompareBean
    myModel.getNodes.foreach { cn =>
      val am = kkb.kompare(myModel, ind.asInstanceOf[KevoreeIndividualAbstract].myModel, cn.getName)
      if (!am.getAdaptations.isEmpty) {
        return false
      }
    }
    return true
  }

  override def hashCode: Int = {
    return myModel.hashCode
  }

  override def size: Long = {
    var count: Int = 0
    myModel.getNodes.foreach { cn =>
      count += cn.getComponents.size
    }
    return count
  }

  override def setup(state: EvolutionState, base: Parameter): Unit = {
    myModel = load(baseModelPath)
    model_path = state.parameters.getString(base.push(FOLDER_TO_STORE_MODELS), null)
    var stat: File = new File(model_path)
    if (stat.isDirectory) {
      for (file <- stat.listFiles) {
        file.delete
      }
      stat.delete
    }
    model_path = model_path + File.separator
  }

  def load(uri: String): ContainerRoot = {
    var rs: ResourceSetImpl = new ResourceSetImpl
    rs.getResourceFactoryRegistry.getExtensionToFactoryMap.put("kev", new XMIResourceFactoryImpl)
    rs.getPackageRegistry.put(KevoreePackage.eNS_URI, KevoreePackage.eINSTANCE)
    var res: Resource = rs.getResource(URI.createURI(uri), true)
    (res.asInstanceOf[XMIResource]).getDefaultLoadOptions.put(XMLResource.OPTION_ENCODING, "UTF-8")
    (res.asInstanceOf[XMIResource]).getDefaultSaveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8")
    var result: AnyRef = res.getContents.get(0)
    return result.asInstanceOf[ContainerRoot]
  }

  override def toString: String = {
    val path = model_path + "Models" + ({
      increment += 1; increment
    })
    ParserUtil.save(path, myModel)
    return path
  }

  override def distanceTo(otherInd: Individual): Double = {
    var result: Double = 0.0
    val kkb = new KevoreeKompareBean
    myModel.getNodes.foreach { cn =>
      val am: AdaptationModel = kkb.kompare(myModel, (otherInd.asInstanceOf[KevoreeIndividualAbstract]).myModel, cn.getName)
      result += am.getAdaptations.size
    }
    return result
  }

  /*private def compare(ki: KevoreeIndividualAbstract): List[Statment] = {
    var amAggregated: AdaptationModel = null
    var kkb: KevoreeKompareBean = new KevoreeKompareBean
    for (cn : ContainerNode <- myModel.getNodes) {
      var am: AdaptationModel = kkb.kompare(myModel, ki.myModel, cn.getName)
      if (amAggregated == null) {
        amAggregated = am
      }
      else {
        amAggregated.getAdaptations.addAll(am.getAdaptations)
      }
    }
    if (amAggregated != null) {
      var result = new ArrayList[Statment]
      var script: Script = AdaptationModelWrapper.generateScriptFromAdaptModel(amAggregated)
      var it: Iterator[Block] = script.blocks.iterator
      while (it.hasNext) {
        var itStatment: Iterator[Statment] = it.next.l.iterator
        while (itStatment.hasNext) {
          result.add(itStatment.next)
        }
      }
      return result
    }
    return null
  }*/

}