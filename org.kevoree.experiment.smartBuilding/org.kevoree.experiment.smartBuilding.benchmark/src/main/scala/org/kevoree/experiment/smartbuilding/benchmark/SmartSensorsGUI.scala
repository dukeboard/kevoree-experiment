package org.kevoree.experiment.smartbuilding.benchmark

import info.monitorenter.gui.chart.Chart2D
import java.awt.Color
import info.monitorenter.gui.chart.traces.{Trace2DSimple, Trace2DLtd}
import info.monitorenter.gui.chart.rangepolicies.RangePolicyMinimumViewport
import javax.swing.{JLabel, BoxLayout, JPanel}
import info.monitorenter.gui.chart.IAxis.AxisTitle

/**
 * User: ffouquet
 * Date: 22/06/11
 * Time: 18:46
 */

object SmartSensorsGUI {

  var chartMem = new Chart2D();
  chartMem.setBackground(Color.white);
  //chartMem.setGridColor(Color.ORANGE)

  var axisXMem = chartMem.getAxisX();
  // axisXMem.setPaintGrid(true)

  var axisYMem = chartMem.getAxisY();
  axisXMem.setStartMajorTick(false);
  axisXMem.setMajorTickSpacing(10);
  axisXMem.setMinorTickSpacing(10)
  axisXMem.setAxisTitle(new AxisTitle("reconfiguration step"))
  var traceMem = new Trace2DSimple();
  traceMem.setName("Dynamic memory")
  traceMem.setColor(Color.RED);
  chartMem.addTrace(traceMem);

  var chartEMem = new Chart2D();
  chartEMem.setBackground(Color.white);
  var axisXEMem = chartEMem.getAxisX();
  axisXEMem.setStartMajorTick(false);
  axisXEMem.setMajorTickSpacing(10);
  axisXEMem.setMinorTickSpacing(10)
  axisXEMem.setAxisTitle(new AxisTitle("reconfiguration step"))
  var traceEMem = new Trace2DSimple();
  traceEMem.setColor(Color.GREEN);
  traceEMem.setName("Persistent memory")
  chartEMem.addTrace(traceEMem);

  var chartRTime = new Chart2D();
  chartRTime.setBackground(Color.white);
  var axisXRTime = chartRTime.getAxisX();
  axisXRTime.setStartMajorTick(false);
  axisXRTime.setMajorTickSpacing(10);
  axisXRTime.setMinorTickSpacing(10)
  axisXRTime.setAxisTitle(new AxisTitle("reconfiguration step"))
  var traceTTime = new Trace2DSimple();
  traceTTime.setColor(Color.black)
  traceTTime.setName("Downtime")
  chartRTime.addTrace(traceTTime);

  var chartSSIZE = new Chart2D();
  chartSSIZE.setBackground(Color.white);
  var axisXSSIZE = chartSSIZE.getAxisX();
  axisXSSIZE.setStartMajorTick(false);
  axisXSSIZE.setMajorTickSpacing(10);
  axisXSSIZE.setMinorTickSpacing(10)
  axisXSSIZE.setAxisTitle(new AxisTitle("reconfiguration step"))
  var traceSSIZE = new Trace2DSimple();
  traceSSIZE.setColor(Color.blue);
  traceSSIZE.setName("KevScript size")
  chartSSIZE.addTrace(traceSSIZE);


  var p: JPanel = null

  def getPanel = {
    if (p == null) {
      p = new JPanel()
      p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS))
      p.add(chartMem)
      p.add(chartEMem)
      p.add(chartRTime)
      p.add(chartSSIZE)
    }
    p
  }

  def clear() {
    traceMem.removeAllPoints()
    traceEMem.removeAllPoints()
    traceTTime.removeAllPoints()
    traceSSIZE.removeAllPoints()
  }

  def putMemValue(index: Int, i: Int) {
    traceMem.addPoint(index, i)
  }

  def putEMemValue(index: Int, i: Int) {
    traceEMem.addPoint(index, i)
  }

  def putRTimeValue(index: Int, i: Int) {
    traceTTime.addPoint(index, i)
  }
  def putSSIZEValue(index: Int, i: Int) {
    traceSSIZE.addPoint(index, i)
  }

}