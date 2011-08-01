package org.kevoree.experiment.smartForest.results

import org.kevoree.experiment.smartForest.SmartForestExperiment
import java.io._

/**
 * User: ffouquet
 * Date: 28/07/11
 * Time: 17:07
 */

object RGenerator {

  def generateEvolutionCurveFig() {

    val pathComplete: String = SmartForestExperiment.getComputerFullName + "-" + SmartForestExperiment.folderToStoreTempFile + File.separator + "completeStat.stat"
    val pathCompleteOut: String = SmartForestExperiment.getComputerFullName + "-" + SmartForestExperiment.folderToStoreTempFile + File.separator + "completeStat.r"
    try {
      val stream: InputStream = new FileInputStream(new File(pathComplete))
      if (stream == null) System.out.println("Perdu")
      val result = StatisticsParser.parseCompleteStatistics(stream)

      var axe1res: List[String] = List()
      var axe2res: List[String] = List()
      var axe3res: List[String] = List()
      var axeFullres: List[String] = List()

      result.foreach {
        generation => {
          var res = (300f,300f,300f)
          var res2 = 300f
          generation.populations.foreach {
            popuplation =>
              res2 = math.min(res2 , (popuplation.fitness(0)+popuplation.fitness(1)+popuplation.fitness(2)) )
              res = (
                math.min(res._1,popuplation.fitness(0)),
                math.min(res._2,popuplation.fitness(1)),
                math.min(res._3,popuplation.fitness(2)))
          }
          axe1res = axe1res ++ List(res._1.toString)
          axe2res = axe2res ++ List(res._2.toString)
          axe3res = axe3res ++ List(res._3.toString)
          axeFullres = axeFullres ++ List((res2/3).toString)

        }
      }

      val fileWR = new FileWriter(new File(pathCompleteOut))
      fileWR.append(" axe1 <- c(" + axe1res.mkString(",") + ")\n" )
      fileWR.append(" axe2 <- c(" + axe2res.mkString(",") + ")\n" )
      fileWR.append(" axe3 <- c(" + axe3res.mkString(",") + ")\n" )
      fileWR.append(" axeFull <- c(" + axeFullres.mkString(",") + ")\n" )

      fileWR.append("plot(axeFull,ylim=c(0,100),xlim=c(0,"+axeFullres.size+"))\n")
      fileWR.append("lines(axe1, col=\"blue\")\n")
      fileWR.append("lines(axe2, col=\"red\")\n")
      fileWR.append("lines(axe3, col=\"green\")\n")

      fileWR.close()


    }
    catch {
      case e: FileNotFoundException => {
        e.printStackTrace
      }
    }

  }


}