package org.kevoree.experiment.trace.gui.alg.recoverOnFailure

import org.kevoree.experiment.trace.TraceMessages
import javax.swing.{WindowConstants, JFrame}
import org.jfree.chart.{JFreeChart, ChartPanel}
import java.io._
import com.lowagie.text.Rectangle
import java.awt.Graphics2D
import com.lowagie.text.pdf.{DefaultFontMapper, PdfTemplate, PdfContentByte, PdfWriter}
import java.awt.geom.Rectangle2D

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 10/08/11
 * Time: 17:26
 */

object AppRecoverOnFailureDisplay extends App{

  // var inputLazy: InputStream = new FileInputStream(new File("/Users/ffouquet/Documents/DEV/dukeboard_github/kevoree-experiment/org.kevoree.experiment.library.gossiperNetty/results/300000_45000_-sendNotification_-alwaysAskModel__1000/trace_out"))
  //var inputModel = KevoreeXmiHelper.load("/Users/ffouquet/Documents/DEV/dukeboard_github/kevoree-experiment/org.kevoree.experiment.library.gossiperNetty/results/300000_45000_-sendNotification_-alwaysAskModel__1000/bootStrapComplex.kev")
  var inputLazy: InputStream = new FileInputStream(new File("/home/edaubert/workspace/kevoree-experiment/org.kevoree.experiment.trace.samples/60000_20000_sendNotification_-alwaysAskModel__15000/trace_out"))
  //val nbHops = NbHop(inputModel, "p00")

  var tracesLazy: TraceMessages.Traces = TraceMessages.Traces.parseFrom(inputLazy)

  var linkedTrace = TracePath.getPathFrom("p00", 3, tracesLazy)
  var linkedTrace2 = TracePath.getPathFrom("o00", 3, tracesLazy)

  linkedTrace match {
    case Some(ltrace) => {
      println(ltrace.toString)
      val frame = new JFrame();
      frame.setSize(400, 400);

      val chart = new VectorClockSingleDisseminationChartScala(ltrace)
      val jchart = chart.buildChart()
      //val chartPanel=new ChartScrollBar(0,jchart);
      val chartPanel = new ChartPanel(jchart)
      //val scroller = new JScrollBar(0, 0, 10, 0, 50)
      //scroller.getModel.addChangeListener(scroller)
      //chartPanel.add(scroller)
      chartPanel.setOpaque(false);
      frame.add(chartPanel);
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
      frame.setVisible(true);

      saveChartToPDF(jchart, "trace_out.pdf", 600, 600)

     // RGenerator.generateFile(RGenerator.generatePropagationTimeScript(ltrace),"out.r")

    }
    case None => println("Not found")
  }

  /**
   * Save chart as PDF file. Requires iText library.
   *
   * @param chart JFreeChart to save.
   * @param fileName Name of file to save chart in.
   * @param width Width of chart graphic.
   * @param height Height of chart graphic.
   * @throws Exception if failed.
   * @see <a href="http://www.lowagie.com/iText">iText</a>
   */
  def saveChartToPDF(chart: JFreeChart, fileName: String, width: java.lang.Integer, height: java.lang.Integer) {
    if (chart != null) {
      var out: BufferedOutputStream = null
      try {
        out = new BufferedOutputStream(new FileOutputStream(fileName))

        //convert chart to PDF with iText:
        val pagesize: Rectangle = new Rectangle(width.intValue(), height.intValue())
        val document: com.lowagie.text.Document = new com.lowagie.text.Document(pagesize, 50, 50, 50, 50)
        try {
          val writer: PdfWriter = PdfWriter.getInstance(document, out)
          document.addAuthor("JFreeChart")
          document.open()

          val cb: PdfContentByte = writer.getDirectContent
          val tp: PdfTemplate = cb.createTemplate(width.intValue(), height.intValue())
          val g2: Graphics2D = tp.createGraphics(width.intValue(), height.intValue(), new DefaultFontMapper())

          val r2D: Rectangle2D = new Rectangle2D.Double(0, 0, width.intValue(), height.intValue())
          chart.draw(g2, r2D, null)
          g2.dispose()
          cb.addTemplate(tp, 0, 0)
        } finally {
          document.close()
        }
      } finally {
        if (out != null) {
          out.close()
        }
      }
    } //else: input values not available
  }

}