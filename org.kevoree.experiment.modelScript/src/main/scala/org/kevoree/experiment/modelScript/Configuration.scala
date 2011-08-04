package org.kevoree.experiment.modelScript

import java.io.{FileReader, BufferedReader, File}

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 28/05/11
 * Time: 14:56
 */
object Configuration {

  var logServer: String = null
  var followingPlatform: String = null
  var packets: List[NodePacket] = List()
  var ips: List[String] = List()
  var nodeFile = ""
  var grid = false

  def build () {
    if (grid) {
      buildForGrid()
    } else if (!nodeFile.equals("")) {
      buildWithNodeFile()
    } else {
      buildDefault()
    }

  }

  private def buildDefault () {
    val dukeIP = "duke.irisa.fr"
    val paraisseuxIP = "paraisseux.irisa.fr"
    val abricotierIP = "abricotier.irisa.fr"
    val tombombadilIP = "tombombadil.irisa.fr"
    val cigogneIP = "cigogne.irisa.fr"
    val galadrielIP = "galadriel.irisa.fr"

    val olivierbravoIP = "131.254.102.168"
    val didieradminIP = "131.254.10.69"

    // Windows
    val lyraIP = "meaban.irisa.fr"
    logServer = paraisseuxIP
    //logServer = dukeIP
    ips = List(dukeIP, paraisseuxIP, abricotierIP, tombombadilIP, cigogneIP,
                galadrielIP, lyraIP, olivierbravoIP, didieradminIP)
    packets = List(
                    NodePacket("duke0", dukeIP, 8000, 3),
                    NodePacket("duke1", dukeIP, 8010, 3),
                    NodePacket("duke2", dukeIP, 8020, 3),
                    NodePacket("paraisseux0", paraisseuxIP, 8000, 3),
                    NodePacket("paraisseux1", paraisseuxIP, 8010, 3),
                    NodePacket("paraisseux2", paraisseuxIP, 8020, 3),
                    NodePacket("abricotier0", abricotierIP, 8000, 3),
                    NodePacket("abricotie1", abricotierIP, 8010, 3),
                    NodePacket("abricotier2", abricotierIP, 8020, 3),
                    NodePacket("tombombadil0", tombombadilIP, 8000, 3),
                    NodePacket("tombombadil1", tombombadilIP, 8010, 3),
                    NodePacket("tombombadil2", tombombadilIP, 8020, 3),
                    NodePacket("cigogne0", cigogneIP, 8000, 3),
                    NodePacket("cigogne1", cigogneIP, 8010, 3),
                    NodePacket("cigogne2", cigogneIP, 8020, 3),
                    NodePacket("galadriel0", galadrielIP, 8000, 3),
                    NodePacket("galadriel1", galadrielIP, 8010, 3),
                    NodePacket("galadriel2", galadrielIP, 8020, 3),
                    NodePacket("lyra0", lyraIP, 8000, 3),
                    NodePacket("lyra1", lyraIP, 8010, 3),
                    NodePacket("lyra2", lyraIP, 8020, 3),
                    NodePacket("olivierbravo0", olivierbravoIP, 8000, 3),
                    NodePacket("olivierbravo1", olivierbravoIP, 8010, 3),
                    NodePacket("olivierbravo2", olivierbravoIP, 8020, 3),
                    NodePacket("didieradmin0", didieradminIP, 8000, 3),
                    NodePacket("didieradmin1", didieradminIP, 8010, 3),
                    NodePacket("didieradmin2", didieradminIP, 8020, 3)
                    //                    NodePacket("pamplemousse0", pamplemousseIP, 8000, 3),
                    //                    NodePacket("pamplemousse1", pamplemousseIP, 8010, 3),
                    //                    NodePacket("pamplemousse2", pamplemousseIP, 8020, 3),
                    //                    NodePacket("celeborn0", celebornIP, 8000, 3),
                    //                    NodePacket("celeborn1", celebornIP, 8010, 3),
                    //                    NodePacket("celeborn2", celebornIP, 8020, 3),
                    //                    NodePacket("louna0", lounaIP, 8000, 3),
                    //                    NodePacket("louna1", lounaIP, 8010, 3),
                    //                    NodePacket("louna2", lounaIP, 8020, 3),
                    //                    NodePacket("peuplier0", peuplierIP, 8000, 3),
                    //                    NodePacket("peuplier1", peuplierIP, 8010, 3),
                    //                    NodePacket("peuplier2", peuplierIP, 8020, 3)
                  )
    followingPlatform = "duke00"
  }

  private def buildForGrid () {
    //    println(System.getProperty("OAR_NODE_FILE"))
    if (System.getProperty("OAR_NODE_FILE") != null) {
      val file = new File(System.getProperty("OAR_NODE_FILE"))
      if (file.exists()) {
        val reader = new BufferedReader(new FileReader(file))
        var first = true
        var line: String = reader.readLine()
        while (line != null) {
          if (!ips.contains(line)) {
            ips = ips ++ List(line)
            packets = packets ++ List(NodePacket(line.replaceAll("-", "").replaceAll("\\.", ""), line, 8000, 8))
            if (first) {
              logServer = line
              followingPlatform = line.replaceAll("-", "").replaceAll("\\.", "") + "0"
              first = false
            }
          }
          line = reader.readLine()
        }
        reader.close()
      } else {
        println("file is missing.\nBootstrap is not possible")
      }
    } else {
      println("missing $OAR_NODE_FILE variables")
    }
  }

  private def buildWithNodeFile () {
    val file = new File(nodeFile)
    if (file.exists()) {
      val reader = new BufferedReader(new FileReader(file))
      var first = true
      var line: String = reader.readLine()
      while (line != null) {
        val name = line.split(":")(0)
        val ip = line.split(":")(1)
        val basePort = Integer.parseInt(line.split(":")(2))
        if (!ips.contains(ip)) {
          ips = ips ++ List(ip)
        }
        packets = packets ++ List(NodePacket(name, ip, basePort, 3))
        if (first) {
          logServer = ip
          followingPlatform = name + "0"
          first = false
        }
        line = reader.readLine()
      }
      reader.close()
    } else {
      println("file is missing.\nBootstrap is not possible")
    }
  }
}