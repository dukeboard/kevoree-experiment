package org.kevoree.experiment.smartForest.results

import java.io._
import java.lang.{Long, Integer}
import com.sun.xml.internal.fastinfoset.util.ValueArray

/**
 * Created by IntelliJ IDEA.
 * User: jbourcie
 * Date: 28/07/11
 * Time: 13:56
 * To change this template use File | Settings | File Templates.
 */

case class CompleteStatisticsObject( generation : Int,  breedingDuration : Long, evaluationDuration : Long, populations : List[MultiDimensionalIndividualStatistics])
case class MultiDimensionalIndividualStatistics(rank : Int, fitness : List[Float])
case class ParetoFront (individuals : List[StatisticsIndividuals])
case class StatisticsIndividuals (rank : Int, fitness : List[Float], model : String)

object StatisticsParser {


  def parseCompleteStatistics(stream: InputStream): List[CompleteStatisticsObject] = {
    val result = new scala.collection.mutable.ArrayBuffer[CompleteStatisticsObject]
    try {
      val ipsr: InputStreamReader = new InputStreamReader(stream)
      var br: BufferedReader = new BufferedReader(ipsr)
      var line: String = null
      while (({
        line = br.readLine; line
      }) != null) {
        val firstSplit = line.split("#")
        val secondSplit = firstSplit(0).split(";")
        val generation = Integer.parseInt(secondSplit(0))
        val breedingDuration = Long.parseLong(secondSplit(1))
        val evaluationDuration = Long.parseLong(secondSplit(2))
        val thirdSplit = firstSplit(1).split('*')
        val multiDimensionalIndividualList = new scala.collection.mutable.ArrayBuffer[MultiDimensionalIndividualStatistics]
        (0 until thirdSplit.size).foreach {
          indice =>
            val fitness = thirdSplit(indice)
            val rankString = fitness.substring(fitness.indexOf("R=") + 2, fitness.indexOf("S=") - 1)
            val rank = Integer.parseInt(rankString)
            val multiFitness = fitness.substring(fitness.indexOf("[") + 1, fitness.indexOf("min]"))
            val fourthSplit = multiFitness.split(" ")
            val fitnessList = new scala.collection.mutable.ArrayBuffer[Float]
            (0 until fourthSplit.size).foreach {
              index =>
                fitnessList.append(java.lang.Float.parseFloat(fourthSplit(index)).asInstanceOf[Float])
            }
            multiDimensionalIndividualList.append(new MultiDimensionalIndividualStatistics(rank, fitnessList.toList))
        }
        result.append(new CompleteStatisticsObject(generation, breedingDuration, evaluationDuration, multiDimensionalIndividualList.toList))
      }
      br.close
    }
    catch {
      case e: IOException => {
        e.printStackTrace
      }
    }
    result.toList
  }

  def parseStatistics(stream: InputStream): ParetoFront = {
    var result = new scala.collection.mutable.ArrayBuffer[StatisticsIndividuals]
    try {
      val ipsr: InputStreamReader = new InputStreamReader(stream)
      val br: BufferedReader = new BufferedReader(ipsr)
      var line: String = null
      var pareto = false
      var evaluated = false
      var fitness = false
      var model = false

      var rank = 0
      var fitnessList = new scala.collection.mutable.ArrayBuffer[Float]

      while (({
        line = br.readLine; line
      }) != null) {
        if (!pareto && line == "Pareto Front of Subpopulation 0") {
          pareto = true
          evaluated = true
        }
        else if (pareto && evaluated) {
          fitness = true;
        }
        else if (pareto && fitness) {
          println(line)
          val rankString = line.substring(line.indexOf("R=") + 2, line.indexOf("S=") - 1)
          rank = Integer.parseInt(rankString)
          val multiFitness = line.substring(line.indexOf("[") + 1, line.indexOf("min]"))
          val fourthSplit = multiFitness.split(" ")
          fitnessList = new scala.collection.mutable.ArrayBuffer[Float]
          (0 until fourthSplit.size).foreach {
            index =>
              fitnessList.append(java.lang.Float.parseFloat(fourthSplit(index)).asInstanceOf[Float])
          }
          model = true;
        }
        else if (pareto && model) {
          result.append(new StatisticsIndividuals(rank, fitnessList.toList, line))
          evaluated = true;
        }
      }
      br.close
    }
    catch {
      case e: IOException => {
        e.printStackTrace
      }
    }
    new ParetoFront(result.toList)
  }
}