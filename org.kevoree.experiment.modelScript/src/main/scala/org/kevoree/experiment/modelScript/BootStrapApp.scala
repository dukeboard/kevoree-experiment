package org.kevoree.experiment.modelScript


object BootStrapApp extends App {

  override def main (args: Array[String]) {
    var alwaysAskModel = false
    var sendNotification = true
    var delay = 10000

    if (args.contains("grid")) {
      Configuration.buildForGrid()
    } else if (args.contains("-grid")) {
      Configuration.build()
    } else {
      Configuration.build()
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


    /*args.find(arg => arg.startsWith("logServer=")) match {
      case Some(arg) => Configuration.logServer = arg.substring("logServer=".size, arg.size)
      case _ =>
    }*/

    /*if (args.length == 2) {
      Configuration.grid5000 = true
      Configuration.build()
      Configuration.logServer = args(1)
    } else {
      Configuration.grid5000 = false
      Configuration.build()
      var nbNodes = 0
      Configuration.packets.foreach {
        p =>
          nbNodes = nbNodes + p.nbElem
      }
    }*/

    println(Configuration.packets.mkString("\n"))
    println("logServer = " + Configuration.logServer)
    println("sendNotification = " + sendNotification)
    println("alwaysAskModel = " + alwaysAskModel)
    println("delay = " + delay)

    BootStrapAppComplex
      .bootStrap(Configuration.packets, Configuration.logServer, Configuration.ips, sendNotification, alwaysAskModel,
                  delay)
  }

}