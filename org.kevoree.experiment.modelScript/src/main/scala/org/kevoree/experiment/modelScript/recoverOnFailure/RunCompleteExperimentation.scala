package org.kevoree.experiment.modelScript.recoverOnFailure

import java.io._
import org.kevoree.experiment.modelScript.{ModificationGenerator, Configuration, BootStrapAppComplex}

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 04/08/11
 * Time: 10:32
 */

object RunCompleteExperimentation extends App {

  println("Kevoree Experiment - Appearance, Disappearance")
  println("Test disappearance and reappeanrace of nodes to show that a node that appears always merges with the main set of nodes")

  var alwaysAskModel = false
  var sendNotification = true
  var delay = 10000
  var reconfigurationDelay = 20000
  var experimentationTime = 60000
  var nodeFile = ""

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

  args.filter(arg => arg.startsWith("delay=")).foreach {
    arg => delay = Integer.parseInt(arg.substring("delay=".size, arg.size))
  }

  args.filter(arg => arg.startsWith("nodeFile=")).foreach {
    arg => Configuration.nodeFile = arg.substring("nodeFile=".size, arg.size);
  }

  Configuration.build()


  println(Configuration.packets.mkString("\n"))
  println("logServer = " + Configuration.logServer)
  println("followingPlatform = " + Configuration.followingPlatform)
  println("sendNotification = " + sendNotification)
  println("alwaysAskModel = " + alwaysAskModel)
  println("delay = " + delay)
  println("")

  // !!!!!!!!!!!! Agent must be started !!!!!!!!!
  val model = BootStrapAppComplex
    .bootStrap(Configuration.packets, Configuration.logServer, Configuration.ips, sendNotification, alwaysAskModel,
                delay)

  var secondFollowingPlatform = Configuration.packets(3).name + "0"

  println("secondFollowingPlatform: " + secondFollowingPlatform)

  // run GregServer
  val gregServer = Runtime.getRuntime.exec("java -jar org.kevoree.experiment.trace.server-1.2.0-SNAPSHOT.jar")

  val gregServerStream = new BufferedWriter(new OutputStreamWriter(gregServer.getOutputStream))
  val inputStreamReader = new Thread {
    override def run () {
      try {
        //val bytes: Array[Byte] = new Array[Byte](512)
        while (true) {
          println(stream.readLine())
        }
      }
      catch {
        case e: IOException => {
        }
      }
    }

    private val stream: BufferedReader = new BufferedReader(new InputStreamReader(gregServer.getInputStream))
  }

  val errorStreamReader = new Thread {
    override def run () {
      try {
        val bytes: Array[Byte] = new Array[Byte](512)
        while (true) {
          stream.read(bytes)
        }
      }
      catch {
        case e: IOException => {
        }
      }
    }

    private val stream: InputStream = gregServer.getErrorStream
  }

  inputStreamReader.start()
  errorStreamReader.start()

  //Thread.sleep(60000)
  val failureGenerator = new FailureGenerator(model)
  val modificationGenerator = new ModificationGenerator(Configuration.ips)
  val endTime = System.currentTimeMillis() + experimentationTime

  val stream = new BufferedReader(new InputStreamReader((System.in)))
  var line = stream.readLine()
  while (line != null && !line.equals("q")) {
    if (line.equals("start")) {
      println("send a modification on " + Configuration.followingPlatform)
      // run modification on Configuration.followingPlatform
      modificationGenerator.doAction(Configuration.followingPlatform)
      Thread.sleep(1000)

      println("remove links between " + secondFollowingPlatform + " and all other nodes")
      failureGenerator.generateFailure(secondFollowingPlatform)
      Thread.sleep(1000)


      println("send a modification on " + Configuration.followingPlatform)
      // run modification on Configuration.followingPlatform
      modificationGenerator.doAction(Configuration.followingPlatform)
      println("send a modification on " + secondFollowingPlatform)
      // run modification on secondFollowingPlatform
      modificationGenerator.doAction(secondFollowingPlatform)
      Thread.sleep(1000)

      println("reinitiliaze links between " + secondFollowingPlatform + " and all other nodes")
      failureGenerator.reinitialize()

    }
    if (line.equals("down")) {
      println("remove links between " + secondFollowingPlatform + " and all other nodes")
      failureGenerator.generateFailure(secondFollowingPlatform)
    } else if (line.equals("up")) {
      println("reinitiliaze links between " + secondFollowingPlatform + " and all other nodes")
      failureGenerator.reinitialize()
    } else if (line.equals("modification1")) {
      println("send a modification on " + Configuration.followingPlatform)
      // run modification on Configuration.followingPlatform
      modificationGenerator.doAction(Configuration.followingPlatform)
    } else if (line.equals("modification2")) {
      println("send a modification on " + Configuration.followingPlatform)
      // run modification on Configuration.followingPlatform
      modificationGenerator.doAction(Configuration.followingPlatform)
      println("send a modification on " + secondFollowingPlatform)
      // run modification on secondFollowingPlatform
      modificationGenerator.doAction(secondFollowingPlatform)
    } else if (line.equals("select")) {
      // select the secondFollowingPlatform
      //println()
      // TODO ?
    }
    line = stream.readLine()
  }

  //Thread.sleep(120000)
  // stop GregServer
  gregServerStream.write("q\n")
  gregServer.destroy()


  // store results
  var folderName = experimentationTime + "_" + reconfigurationDelay + "_"
  if (sendNotification) {
    folderName = folderName + "sendNotification_"
  } else {
    folderName = folderName + "-sendNotification_"
  }
  if (alwaysAskModel) {
    folderName = folderName + "alwaysAskModel_"
  } else {
    folderName = folderName + "-alwaysAskModel_"
  }
  folderName = folderName + "_" + delay
  val folder = new File(folderName)
  if (folder.exists()) {
    deleteOldExperimentation(folder)
  }

  folder.mkdir()

  copyResult("bootStrap.kev", folder)
  copyResult("bootStrapComplex.graphml", folder)
  copyResult("bootStrapComplex.kev", folder)
  copyResult("nodeFile.txt", folder)
  copyResult("trace_out", folder)

  println("experimentation complete !")

  private def deleteOldExperimentation (folder: File) {
    folder.listFiles().foreach {
      f => if (f.isFile) {
        f.delete()
      } else {
        deleteOldExperimentation(f)
      }
    }
    folder.delete()
  }

  private def copyResult (filePath: String, folder: File) {
    val file = new File(filePath)
    if (file.exists()) {
      val copy = new File(folder + File.separator + file.getName)
      val inputStream = new FileInputStream(file)
      val outpuStream = new FileOutputStream(copy)
      var length: Int = 0
      val bytes = new Array[Byte](1024)
      length = inputStream.read(bytes)
      while (length > -1) {
        outpuStream.write(bytes, 0, length)
        length = inputStream.read(bytes)
      }
    }
  }
}