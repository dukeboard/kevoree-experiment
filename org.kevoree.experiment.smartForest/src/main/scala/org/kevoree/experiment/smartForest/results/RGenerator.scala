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

      //var paraValues: List[String] = List()
      var i = 0

      result.foreach {
        generation => {

          if (i % 2 == 0) {
            println("i=" + i)
            generation.populations.foreach {
              individu =>

                if (individu.rank == 0) {
                  axe1res = axe1res ++ List(" " + i + "," + (individu.fitness(0)))
                  axe2res = axe2res ++ List(" " + i + "," + (individu.fitness(1)))
                  axe3res = axe3res ++ List(" " + i + "," + (individu.fitness(2)))
                  axeFullres = axeFullres ++ List(" " + i + "," +  ( (individu.fitness(0)+(individu.fitness(1)+(individu.fitness(2)) ))))
                }

            }
          }
          i = i + 1
        }
      }


      val fileWR = new FileWriter(new File(pathCompleteOut))
      //  fileWR.append(" axe1 <- c(" + axe1res.mkString(",") + ")\n")
      //  fileWR.append(" axe2 <- c(" + axe2res.mkString(",") + ")\n")
      // fileWR.append(" axe3 <- c(" + axe3res.mkString(",") + ")\n")
      //   fileWR.append(" axeFull <- c(" + axeFullres.mkString(",") + ")\n")

      //  fileWR.append("plot(axeFull,ylim=c(0,100),xlim=c(0," + axeFullres.size + "))\n")
      //  fileWR.append("lines(axe1, col=\"blue\")\n")
      //  fileWR.append("lines(axe2, col=\"red\")\n")
      //   fileWR.append("lines(axe3, col=\"green\")\n")

      fileWR.append("axe1 <- matrix(c(" + axe1res.mkString(",\n") + "),nrow=" + axe1res.size + ",ncol=2, byrow=TRUE,dimnames = NULL)\n")
      fileWR.append("axe2 <- matrix(c(" + axe2res.mkString(",\n") + "),nrow=" + axe2res.size + ",ncol=2, byrow=TRUE,dimnames = NULL)\n")
      fileWR.append("axe3 <- matrix(c(" + axe3res.mkString(",\n") + "),nrow=" + axe3res.size + ",ncol=2, byrow=TRUE,dimnames = NULL)\n")
      fileWR.append("axeFullres <- matrix(c(" + axeFullres.mkString(",\n") + "),nrow=" + axeFullres.size + ",ncol=2, byrow=TRUE,dimnames = NULL)\n")
      fileWR.append("par(mfrow=c(4,1))\n")
      fileWR.append("plot(axe1)\n")
      fileWR.append("plot(axe2)\n")
      fileWR.append("plot(axe3)\n")
      fileWR.append("plot(axeFullres)\n")
      fileWR.close()


    }
    catch {
      case e: FileNotFoundException => {
        e.printStackTrace
      }
    }

  }


}