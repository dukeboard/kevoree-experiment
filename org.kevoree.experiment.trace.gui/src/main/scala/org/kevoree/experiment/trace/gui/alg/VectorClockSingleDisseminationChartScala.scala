package org.kevoree.experiment.trace.gui.alg

import org.jfree.chart.{ChartFactory, JFreeChart}
import java.awt.Color
import util.Random
import scala.collection.JavaConversions._
import java.lang.Long
import collection.immutable.List
import collection.immutable.List._
import java.util._
import org.jfree.chart.axis.SymbolAxis
import org.jfree.data.xy.{XYSeriesCollection, XYSeries}
import org.jfree.chart.plot.{XYPlot, PlotOrientation}
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer

class VectorClockSingleDisseminationChartScala {

  var beginningOfTime: java.lang.Long = 0l

  def setBeginOfTime(begin: java.lang.Long) {
    beginningOfTime = begin
  }

  def print(ltraces: List[LinkedTrace]) {
    var i = 0
    ltraces.foreach {
      t =>
        buildCategory(t, i)
        i = i + 1
    }
    populateCollection(xyseriescollection)
  }

  var nodes = new TreeSet[String]()
  var gidCateg = new java.lang.Integer(0)
  var categoryRepresentations = new HashMap[String, List[(String, Long)]]()

  private def buildCategory(trace: LinkedTrace, gidCateg: Int) {
    if (!nodes.contains(trace.trace.getClientId)) {
      nodes.add(trace.trace.getClientId)
    }
    trace.sucessors.foreach {
      successor =>
        nodes.add(successor.trace.getClientId)
        var timeStampMilli: Long = ((trace.trace.getTimestamp.longValue() - beginningOfTime.longValue()) / 1000000)
        var values: List[(String, Long)] = categoryRepresentations.get(gidCateg + "")
        if (values == null) {
          values = List[(String, Long)]()
        }
        values = values ++ List((trace.trace.getClientId, timeStampMilli))
        categoryRepresentations.put(gidCateg + "", values)
        timeStampMilli = ((successor.trace.getTimestamp.longValue() - beginningOfTime.longValue()) / 1000000)
        values = categoryRepresentations.get(gidCateg + "")
        if (values == null) {
          values = List[(String, Long)]()
        }
        values.find(tuple => tuple._1 == successor.trace.getClientId) match {
          case Some(v) => // NO OP
          case None => values = values ++ List((successor.trace.getClientId, timeStampMilli))
        }
        categoryRepresentations.put(gidCateg + "", values)

        if (successor.sucessors.size > 0) {
          buildCategory(successor, gidCateg)
        }
    }
  }


  val xyseriescollection: XYSeriesCollection = new XYSeriesCollection

  private def populateCollection(dataset: XYSeriesCollection) {
    categoryRepresentations.foreach {
      tuple: (String, List[(String, Long)]) =>
        val xyseries: XYSeries = new XYSeries(tuple._1)
        println("categ => " + tuple._1 + " : ")
        tuple._2.foreach {
          tupleValue: (String, Long) =>
            xyseries.add(tupleValue._2, 0/* HEHEHE TO REMOVE */)
            println("\t" + tupleValue._1 + "(" + tupleValue._2 + ")")
        }
        dataset.addSeries(xyseries)
    }
  }

  def buildChart(): JFreeChart = {
    val jfreechart: JFreeChart = ChartFactory
      .createXYLineChart("VectorClock updates", "Times", "nodes", xyseriescollection, PlotOrientation.VERTICAL, false, true,
      false)
    val xyplot: XYPlot = jfreechart.getPlot.asInstanceOf[XYPlot]
    val xylineandshaperenderer: XYLineAndShapeRenderer = xyplot.getRenderer.asInstanceOf[XYLineAndShapeRenderer]
    xylineandshaperenderer.setBaseShapesVisible(true)

    for (i <- 0 until gidCateg.intValue() + 1) {
      val rand = new Random
      val color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
      xyplot.getRenderer.setSeriesPaint(i, color)
    }

    val symbolaxis: SymbolAxis = new SymbolAxis("Nodes", nodes.toArray(List("").toArray))
    xyplot.setRangeAxis(symbolaxis)
    jfreechart
  }
}