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
    val failureGenerator = new FailureGenerator(Configuration.ips, poisson)

    val stream = new BufferedReader(new InputStreamReader((System.in)))
    var line = stream.readLine()
    if (args.length == 2) {
      failureGenerator.doAction(args(1))
    } else {
      while (line != null && !line.equals("q")) {
        failureGenerator.doAction(line)
        line = stream.readLine()
      }
    }
  }
}