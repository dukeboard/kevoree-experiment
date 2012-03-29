package org.kevoree.experiment.smartForest.results

/**
 * Created with IntelliJ IDEA.
 * User: duke
 * Date: 29/03/12
 * Time: 11:34
 * To change this template use File | Settings | File Templates.
 */

object StatHandler {

  var bestValue : java.lang.Float = 0

  def putValue(v : java.lang.Float){
    bestValue = v
  }

  def getValue : java.lang.Float = {
    bestValue
  }


}
