package org.kevoree.experiment.modelScript

import java.io.{InputStreamReader, BufferedReader}

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 28/05/11
 * Time: 15:00
 */
object FailureApp extends App {

  override def main (args: Array[String]) {


    if (args.contains("grid")) {
      Configuration.buildForGrid()
    } else if (args.contains("-grid")) {
      Configuration.build()
    } else {
      Configuration.build()
    }

    var poisson = false
    if (args.contains("poisson")) {
      poisson = true
    } else if (args.contains("-poisson")) {
      poisson = false
    }

    var alwaysDown = false
    var alwaysUp = false
    if (args.contains("alwaysDown")) {
      alwaysDown = true
    } else if (args.contains("alwaysUp")) {
      alwaysUp = true
    }

    val failureGenerator = new FailureGenerator(Configuration.ips, poisson)

    val stream = new BufferedReader(new InputStreamReader((System.in)))

    if (alwaysDown || alwaysUp) {
      failureGenerator.doAction(args(1))
    } else {
      var line = stream.readLine()
      while (line != null && !line.equals("q")) {
        failureGenerator.doAction(line)
        line = stream.readLine()
      }
    }
  }
}