package org.kevoree.experiment.modelScript.failureManagement

import java.io._
import org.jgrapht.alg.ConnectivityInspector
import org.kevoree.{ContainerRoot, NodeNetwork, ContainerNode}
import scala.collection.JavaConversions._
import org.kevoree.experiment.modelScript.{ModificationGenerator, Configuration, BootStrapAppComplex}
import actors.Actor
import java.lang.Double

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 04/08/11
 * Time: 10:32
 */

object RunCompleteExperimentation extends App {

  println("Kevoree Experiment - Failure global management")
  println("Test usability of mesh network with high level of failure across nodes")

  var alwaysAskModel = false
  var sendNotification = true
  var delay = 10000
  //  var reconfigurationDelay = 20000
  //  var experimentationTime = 60000
  var failurePercentage = 50
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

  /*args.filter(arg => arg.startsWith("reconfigurationDelay=")).foreach {
    arg => reconfigurationDelay = Integer.parseInt(arg.substring("reconfigurationDelay=".size, arg.size))
  }*/

  /*args.filter(arg => arg.startsWith("experimentationTime=")).foreach {
    arg => experimentationTime = Integer.parseInt(arg.substring("experimentationTime=".size, arg.size))
  }*/

  args.filter(arg => arg.startsWith("failurePercentage=")).foreach {
    arg => failurePercentage = Integer.parseInt(arg.substring("failurePercentage=".size, arg.size))
  }

  Configuration.build()


  println(Configuration.packets.mkString("\n"))
  println("logServer = " + Configuration.logServer)
  println("followingPlatform = " + Configuration.followingPlatform)
  println("sendNotification = " + sendNotification)
  println("alwaysAskModel = " + alwaysAskModel)
  println("delay = " + delay)
  println("")
  //  println("reconfiguration delay = " + reconfigurationDelay)
  //  println("experimentation Time = " + experimentationTime)


  var currentFailurePercentage = 1
  var availableNodes: java.util.Set[ContainerNode] = new java.util.HashSet[ContainerNode]()
  var model: ContainerRoot = null
  //var nbFailure = 1
  val rScriptPFailure: StringBuilder = new StringBuilder

  val processActor = new ProcessActor()

  rScriptPFailure append "percentageFailure <- c("

  var rScriptTimeStamp: StringBuilder = new StringBuilder
  rScriptTimeStamp append "avgTimestamps <- c("
  var isFirst = true

  var rScriptNodes = new StringBuilder
  rScriptNodes append "avgnodes <- c("

  // !!!!!!!!!!!! Agent must be started !!!!!!!!!
  model = BootStrapAppComplex
    .bootStrap(Configuration.packets, Configuration.logServer, Configuration.ips, sendNotification, alwaysAskModel,
                delay, 5, 2)

  Thread.sleep(60000)

  // run GregServer
  val gregServer = Runtime.getRuntime.exec("java -jar org.kevoree.experiment.trace.server-1.2.0-SNAPSHOT.jar")

  val gregServerStream = new BufferedWriter(new OutputStreamWriter(gregServer.getOutputStream))
  val inputStreamReader = new Thread {
    override def run () {
      try {
        //val bytes: Array[Byte] = new Array[Byte](512)
        while (true) {
          processActor.process(stream.readLine())
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


  val modificationGenerator = new ModificationGenerator(Configuration.ips)
  val failureGenerator = new FailureGenerator(model)

  while (currentFailurePercentage <= failurePercentage) {

    Thread.sleep(15000)

    println("currentFailurePercentage = " + currentFailurePercentage)
    if (!isFirst) {
      rScriptPFailure append ", "
    }
    rScriptPFailure append currentFailurePercentage

    failureGenerator.reinitialize()

    // generate failures
    val currentModel = failureGenerator.generateFailure(currentFailurePercentage);
    // get topology
    val connectivity = new ConnectivityInspector[ContainerNode, NodeNetwork](KevTopo2JGraph(currentModel))
    availableNodes = connectivity.connectedSetOf(getNode(Configuration.followingPlatform, currentModel))

    val toremove = availableNodes.filter(n => n.getName.equals(Configuration.followingPlatform))
    availableNodes.removeAll(toremove)
    if (availableNodes.size() == 0) {
      rScriptTimeStamp append ", 0"
    } else if (availableNodes.size() > 0) {

      availableNodes.foreach {
        n => print(n.getName + ", ")
      }
      println()
      println(availableNodes.size)

      if (!isFirst) {
        rScriptNodes append ", "
      }
      rScriptNodes append availableNodes.size()
      isFirst = false


      processActor.init(availableNodes)
      Thread.sleep(1000)
      // run modification modification
      modificationGenerator.doAction(Configuration.followingPlatform)
      while (!processActor.isDone) {
        // sleep
        Thread.sleep(3000)
      }
      println("all nodes have received the modification")

      currentFailurePercentage = currentFailurePercentage + 2
      // store results
      var folderName = "exp2" + File.separator
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
      folderName = folderName + delay + "_" + currentFailurePercentage
      val folder = new File(folderName)
      if (folder.exists()) {
        deleteOldExperimentation(folder)
      }

      folder.mkdirs()

      copyResult("bootStrap.kev", folder)
      copyResult("bootStrapComplex.graphml", folder)
      copyResult("bootStrapComplex.kev", folder)
      copyResult("nodeFile.txt", folder)
      copyResult("trace_out", folder)
      copyResult("result", folder)
    }
  }

  //Thread.sleep(120000)
  // stop GregServer
  gregServerStream.write("q\n")
  gregServer.destroy()

  val resultBuilder = new StringBuilder()
  resultBuilder append rScriptPFailure
  resultBuilder append ")\n"
  resultBuilder append processActor.get()

  resultBuilder append rScriptNodes
  resultBuilder append ")\n"

  val rFile = new File("exp2" + File.separator + "failureaverage.r")
  val outpuStream = new FileOutputStream(rFile)
  outpuStream.write(resultBuilder.toString().getBytes("UTF-8"))
  outpuStream.flush()
  outpuStream.close()

  println("experimentation complete !")
  processActor.stop()

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

  def getNode (nodeName: String, model: ContainerRoot): ContainerNode = {
    model.getNodes.find(n => n.getName.equals(nodeName)) match {
      case Some(node) => node
      case _ => null
    }
  }

  class ProcessActor extends Actor {

    val lookedNodes = new java.util.HashSet[ContainerNode]()
    var versionNumber = 2
    var done = true
    var initialTimeStamp = 0l
    var timestamps: List[java.lang.Long] = List()
    var isFirstOnTimestamp = true


    start()

    case class INIT (nodes: java.util.Set[ContainerNode])

    case class GET ()

    case class IS_DONE ()

    case class PROCESS (line: String)

    case class STOP ()

    def stop () {
      this ! STOP()
    }

    def init (nodes: java.util.Set[ContainerNode]) {
      this !? INIT(nodes)
    }

    def get (): String = {
      (this !? GET()).asInstanceOf[String]
    }

    def isDone: Boolean = {
      //println("asking")
      val result = (this !?(500, IS_DONE()))
      result match {
        case Some(v: Boolean) => v
        case None => false
      }
    }

    def process (line: String) {
      this ! PROCESS(line)
    }

    private def processServerTrace (line: String) {
      //println(line)
      try {
        if (done) {
          val versionNumberTmp = Integer
            .parseInt(line.split("=>")(1).split(Configuration.followingPlatform + ":")(1).split(",")(0))
          if (versionNumberTmp > versionNumber) {
            versionNumber = versionNumberTmp
            println(versionNumber)
          }
        } else if (!done) {
          if (line.contains(Configuration.followingPlatform + ":" + versionNumber)) {
            val toRemove = lookedNodes.filter(n => n.getName.equals(line.split("/")(0)))
            lookedNodes.removeAll(toRemove)
            println(line.split("/")(0) + " is up to date")
            val timestamp: java.lang.Long = java.lang.Long.parseLong(line.split("/")(1).split("=>")(0)) / 1000000
            if (initialTimeStamp == 0 && line.split("/")(0).equals(Configuration.followingPlatform)) {
              initialTimeStamp = timestamp
            } else {
              timestamps = timestamps ++ List[java.lang.Long](timestamp)
            }
            if (lookedNodes.isEmpty) {
              if (!isFirstOnTimestamp) {
                rScriptTimeStamp append ", "
              }
              var averageTimestamp = 0l
              timestamps.foreach {
                t =>
                //println(t + " - " + initialTimeStamp + " = " + (t - initialTimeStamp))
                  averageTimestamp = averageTimestamp + (t - initialTimeStamp)
              }
              averageTimestamp = averageTimestamp / timestamps.size
              rScriptTimeStamp append averageTimestamp
              isFirstOnTimestamp = false
              done = true
            } else {
              lookedNodes.foreach {
                n => print(n.getName + ", ")
              }
              println()
            }
          }
        }
      } catch {
        case _@e => e.printStackTrace()
      }
    }

    def act () {
      loop {
        react {
          case INIT(nodes) => {
            timestamps = List()
            initialTimeStamp = 0
            versionNumber = versionNumber + 1
            lookedNodes.clear()
            lookedNodes.addAll(nodes)
            done = false

            reply("")
          }
          case GET() => reply(rScriptTimeStamp.toString() + ")\n")
          case IS_DONE() => reply(done)
          case PROCESS(line) => processServerTrace(line)
          case STOP() => exit()
        }
      }
    }
  }

}