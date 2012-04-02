package org.kevoree.experiment.smartForest.results

import org.kevoree.framework.KevoreeXmiHelper
import java.io.{File, FileWriter}
import org.kevoree.ContainerRoot
import org.kevoree.experiment.smartForest.references.ModelGenerator

/**
 * User: ffouquet
 * Date: 14/09/11
 * Time: 15:41
 */

object RIndividuGenerator extends App {

  //val model = KevoreeXmiHelper.load("homega-homega-generated/models/Models113")

  val model = ModelGenerator.generateForest(20)

  generateIndividualRRepresentation(model)

  def generateIndividualRRepresentation(model: ContainerRoot) = {
    var avgFreq = List[String]()
    model.getNodes.foreach {
      node =>
        var avgLocalFreq = 0
        var nb = 0
        node.getComponents.foreach {
          component =>
            component.getTypeDefinition.getDictionaryType.get.getAttributes.find(att => att.getName == "period") match {
              case Some(att) => {
                avgLocalFreq = avgLocalFreq + Integer.parseInt(component.getDictionary.get.getValues.find(dv => dv.getAttribute == att).get.getValue)
                nb = nb + 1
              }
              case None =>
            }
        }
        if (nb != 0) {
          avgLocalFreq = avgLocalFreq / nb
        }
        avgFreq = avgFreq ++ List(avgLocalFreq.toString)
    }

    val fileWR = new FileWriter(new File("individu.r"))
    fileWR.append("z <- matrix(c(" + avgFreq.mkString(",\n") + "),nrow=" + math.sqrt(avgFreq.size) + ",ncol=" + math.sqrt(avgFreq.size) + ", byrow=TRUE,dimnames = NULL)\n")
    fileWR.append("x <- seq(0," + (math.sqrt(avgFreq.size) - 1) + ",by=1)\n")
    fileWR.append("y <- seq(0," + (math.sqrt(avgFreq.size) - 1) + ",by=1)\n")
    fileWR.append("persp(x,y,z,theta=15,phi=45)\n")
    fileWR.close()
  }
}