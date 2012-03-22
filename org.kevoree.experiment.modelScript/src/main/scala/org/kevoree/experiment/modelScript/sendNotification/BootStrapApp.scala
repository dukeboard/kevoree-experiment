/*
package org.kevoree.experiment.modelScript


object BootStrapApp extends App {

  override def main (args: Array[String]) {
    var alwaysAskModel = false
    var sendNotification = true
    var delay = 10000

    if (args.contains("grid")) {
      COnfiguration.grid = true
    } else if (args.contains("-grid")) {
    } else {
      Configuration.buildDefault()
    }

    if (args.contains("alwaysAskModel")) {
      alwaysAskModel = true
    } else if (args.contains("-alwaysAskModel")) {
      alwaysAskModel = false
    }

    if (args.contains("sendNotification")) {
      sendNotification = true
    } else if (args.contains("-sendNotification")) {
      sendNotification = false
    }


    args.filter(arg => arg.startsWith("delay=")).foreach{
      arg => println(arg);delay = Integer.parseInt(arg.substring("delay=".size, arg.size))
    }

    println(Configuration.packets.mkString("\n"))
    println("logServer = " + Configuration.logServer)
    println("sendNotification = " + sendNotification)
    println("alwaysAskModel = " + alwaysAskModel)
    println("delay = " + delay)

    BootStrapAppComplex
      .bootStrap(Configuration.packets, Configuration.logServer, Configuration.ips, sendNotification, alwaysAskModel,
                  delay)
  }

}*/
