package org.kevoree.experiment.smartForest.fitness

import org.kevoree.ContainerRoot
import edu.uci.ics.jung.graph.UndirectedGraph
import edu.uci.ics.jung.graph.Graph
import org.kevoree.experiment.smartForest.GraphElements.{MyEdge, MyVertex}
import scala.collection.JavaConversions._
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath

import scala.Boolean
import collection.mutable.Map
import java.util.{NoSuchElementException, ArrayList, List, HashMap}

/**
 * Created by IntelliJ IDEA.
 * User: jbourcie
 * Date: 09/07/12
 * Time: 09:03
 * To change this template use File | Settings | File Templates.
 */
class Indices(x:Int, y:Int) {
  def getX = x
  def getY = y
  override def equals (o : Any) : Boolean = {
    o.isInstanceOf[Indices] && o.asInstanceOf[Indices].getX == getX && o.asInstanceOf[Indices].getY == getY
  }
  override def toString() : String = "("+ x + ", " + y +")"
  override def hashCode() : Int = x+y
}

class CoverageCalculator{
  var maximumCoverage : Int = 0;
  val distanceBetweenNodes : Int = 4
  val currentNodeCoverage : Map[MyVertex, List[Indices]] = new HashMap[MyVertex, List[Indices]]()
  val currentCoveredCells : Map[Indices, List[MyVertex]] = new HashMap[Indices, List[MyVertex]]()

  def mapNodeNamesToIndices(s: String, sizeGraph : Int): Indices = {
    val indice = s.substring(4).toInt;
    val i: Int = indice / sizeGraph
    val j: Int = indice - (i * sizeGraph)
    val indices = new Indices(i*distanceBetweenNodes,j*distanceBetweenNodes)
    println(s + " -> " + indices)
    return indices
  }

  def calculateCoveredCells(indices: Indices, radius: Int): List[Indices] ={
    if (radius == 0){
      return indices :: Nil
    } else {
      val i = indices.getX
      val j = indices.getY
      val result = new ArrayList[Indices]()
      result.add(indices)
      if (i-1>=0){
        result.addAll(calculateCoveredCells(new Indices(i-1, j), radius-1))
      }
      calculateCoveredCells(new Indices(i+1, j), radius-1).foreach{indices => if (!result.contains(indices)) { result.add(indices) }}

      if (j-1>=0){
        calculateCoveredCells(new Indices(i, j-1), radius-1).foreach{indices => if (!result.contains(indices)) {result.add(indices)}}
      }
      calculateCoveredCells(new Indices(i, j+1), radius-1).foreach{indices => if (!result.contains(indices)) {result.add(indices)}}
      result
    }
  }

  def calculateMaximumCoverage (graph : Graph[MyVertex, MyEdge]) : Int = {
    val maxCoveredCells: List[Indices] = new ArrayList[Indices]()
    graph.getVertices.foreach { node =>
       val coveredCells = calculateCoveredCells(mapNodeNamesToIndices(node.getName, Math.sqrt(graph.getVertexCount).toInt), TimeVsCoverageFitnessFunctionO.readingRadius(TimeVsCoverageFitnessFunctionO.getMaximumRadius))
       coveredCells.foreach{cell => if (!maxCoveredCells.contains(cell)) { maxCoveredCells.add(cell)}}
    }
    return maxCoveredCells.size()
  }
  def initializer (graph : Graph[MyVertex, MyEdge]) = {
    maximumCoverage = calculateMaximumCoverage(graph)
    calculateInitialCoverage(graph)
  }

  def calculateInitialCoverage (graph : Graph[MyVertex, MyEdge]) = {
    graph.getVertices.foreach { node =>
       val coveredCells = calculateCoveredCells(mapNodeNamesToIndices(node.getName, Math.sqrt(graph.getVertexCount).toInt), TimeVsCoverageFitnessFunctionO.readingRadius(node.getFrequency))
       coveredCells.foreach{cell =>
         try {
           currentCoveredCells(cell) += node
         } catch {
           case e : NoSuchElementException => {
             val list = new ArrayList[MyVertex]()
             list.add(node)
             currentCoveredCells.put(cell, list)
           }
         }
       }
    }
    println(currentCoveredCells)
    currentCoveredCells.foreach{ case(cell, nodes)  =>
       nodes.foreach{ node =>
         try {
           val list = currentNodeCoverage(node)
           if (!list.contains(cell)){
             list+= cell
           }
         } catch {
           case e : NoSuchElementException => {
             val list = new ArrayList[Indices]()
             list.add(cell)
             currentNodeCoverage.put(node, list)
           }
         }
       }
    }
    println(currentNodeCoverage)
  }

  def updateNodes(nodeList : List[MyVertex]) = {
    nodeList.foreach{node =>
      currentNodeCoverage(node).foreach{cell =>
        currentCoveredCells(cell).remove(node)
        if (currentCoveredCells(cell).size()<=0){
          currentCoveredCells.remove(cell)
        }
      }
      currentNodeCoverage.remove(node)
    }
  }
  def getCurrentCoverage() : Int = (currentCoveredCells.keys.size * 100)/maximumCoverage
}

object TimeVsCoverageFitnessFunctionO{
  val readingCost = Map (0 -> 0, 1->3, 2->6, 3-> 9, 4->12)
  val readingRadius = Map (0 -> 0, 1->1, 2->2, 3-> 3, 4->4)
  val transmissionCost = 5
  def getMaximumRadius : Int = readingRadius.values.max
}

class TimeVsCoverageFitnessFunction extends FitnessFunction{

   var myRoutingPredecessor = new HashMap[MyVertex, List[MyVertex]]();

  def evaluate(model: ContainerRoot): Float = {
     var myGraph : Graph[MyVertex, MyEdge] = createGraphAccordingToModel(model);
     myRoutingPredecessor = createRoutesAccordingToGraph(myGraph)
     val myFitnessCurve : ArrayList[Float] = new ArrayList[Float]();
     var time = 0;
     val coverageCalculator = new CoverageCalculator()
     coverageCalculator.initializer(myGraph)
     while (remainingPower(myGraph)){
       myFitnessCurve.add(coverageCalculator.getCurrentCoverage())
       time+=1;
       coverageCalculator.updateNodes(eliminatePowerlessAndNonReachableNodes(myGraph))
     }
     println(myFitnessCurve)
     0
  }

  def evaluate(graph: Graph[MyVertex, MyEdge]): Float = {
     var myGraph = graph
     myRoutingPredecessor = createRoutesAccordingToGraph(myGraph)
     println("---------------------")
     println(myRoutingPredecessor)
     val myFitnessCurve : ArrayList[Float] = new ArrayList[Float]();
     var time = 0;
     val coverageCalculator = new CoverageCalculator()
     coverageCalculator.initializer(myGraph)
     while (remainingPower(myGraph)){
       myFitnessCurve.add(coverageCalculator.getCurrentCoverage())
       time+=1;
       coverageCalculator.updateNodes(eliminatePowerlessAndNonReachableNodes(myGraph))
     }
     println("--------------------")
     println(myFitnessCurve)
     0
  }

  def createGraphAccordingToModel(model: ContainerRoot) : UndirectedGraph[MyVertex, MyEdge] = {
    //TODO
    null
  }

   def createRoutesAccordingToGraph(graph: Graph[MyVertex, MyEdge]): HashMap[MyVertex, List[MyVertex]] ={
     val alg : DijkstraShortestPath[MyVertex,MyEdge] = new DijkstraShortestPath[MyVertex,MyEdge](graph);
     var routingGraph = new HashMap[MyVertex, List[MyVertex]]()
     val dataCollector = graph.getVertices.filter(node => node.isDataCollector)
     graph.getVertices.foreach{ node => routingGraph += node -> new ArrayList[MyVertex]()};
     graph.getVertices.foreach{ node =>
       var pathSizeToClosestDataCollector = graph.getVertexCount.toDouble // initialize to an arbitrary size
       var nodesPath : List[MyVertex] = new ArrayList[MyVertex]()
       dataCollector.foreach{ collector =>
         val dist = alg.getDistance(node, collector)
         if (dist.doubleValue() < pathSizeToClosestDataCollector ){
           pathSizeToClosestDataCollector = dist.doubleValue()
           nodesPath = new ArrayList[MyVertex]()
           alg.getPath(node, collector).foreach{edge =>
             val firstNode = graph.getEndpoints(edge).getFirst
             if (!firstNode.equals(node) && !firstNode.equals(collector) && !nodesPath.contains(firstNode)){
               nodesPath += firstNode
             }
             val SecondNode = graph.getEndpoints(edge).getSecond
             if (!SecondNode.equals(node) && !SecondNode.equals(collector) && !nodesPath.contains(SecondNode)){
               nodesPath += SecondNode
             }
           }
         }
       }
       nodesPath.foreach{ routingNode => routingGraph(routingNode).add(node); }
     }
     routingGraph
  }

  def remainingPower(graph : Graph[MyVertex, MyEdge]) : Boolean = graph.getVertices.exists(v => v.getPower>0);

  def eliminatePowerlessAndNonReachableNodes(graph: Graph[MyVertex, MyEdge]): List[MyVertex] = {
     val toRemove = new ArrayList[MyVertex]();
     graph.getVertices.foreach{
       node =>
         val newPower = decreasePower(node)
         if ( newPower <= 0) {
         toRemove.add(node)
         toRemove.addAll(myRoutingPredecessor(node))
       } else {
         node.setPower(newPower)
           println(node + " -> " +  newPower)
       }

     }
     println("Set of Nodes running out of battery : " + toRemove)
     toRemove.foreach{ node => graph.removeVertex(node)}
     toRemove
  }

  def decreasePower(node : MyVertex) : Int = {
    var power = node.getPower
    power -= TimeVsCoverageFitnessFunctionO.readingCost(node.getFrequency)
    val transmissionCost = TimeVsCoverageFitnessFunctionO.transmissionCost * (myRoutingPredecessor(node).size + 1)
    power -= transmissionCost
    power
  }
}