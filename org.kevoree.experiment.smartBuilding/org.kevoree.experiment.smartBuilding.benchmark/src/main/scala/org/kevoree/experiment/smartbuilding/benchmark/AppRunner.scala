/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.kevoree.experiment.smartbuilding.benchmark

import org.kevoree.experiment.smartbuilding.com.NativeLibUtil
import org.kevoree.library.arduinoNodeType.utils.ArduinoHomeFinder
import javax.swing.{JFrame}
import org.kevoree.extra.osgi.rxtx.KevoreeSharedCom

object AppRunner extends App {

  ArduinoHomeFinder.checkArduinoHome()

 // val expes = List(new Experiment1, new Experiment2, new Experiment3, new Experiment4, new Experiment5)

  var expe = new Experiment5//expes(4)
 // expe.boardPortName = "/dev/tty.usbmodem411"
 // expe.boardTypeName = "mega2560"
 // expe.boardPortName = "/dev/tty.usbserial-A400g2se"
 // expe.boardTypeName = "atmega328"

  // expes.foreach{ expe =>
  runExperiment(expe);
  // }



  System.exit(0)


  def runExperiment(absExp: AbstractExperiment) {
    val frame = new JFrame("Kevoree Arduino Benchmark => " + absExp.getClass.getName);
    frame.getContentPane.add(SmartSensorsGUI.getPanel);
    frame.setSize(1024, 768);
    frame.setVisible(true);
    absExp.init()
    NativeLibUtil.standaloneRxTx()





    absExp.runExperiment()
    KevoreeSharedCom.killAll()
    absExp.saveRawDump()
    absExp.saveRowImage()
    absExp.saveRScript()
    frame.dispose()
    SmartSensorsGUI.clear()
  }


}
