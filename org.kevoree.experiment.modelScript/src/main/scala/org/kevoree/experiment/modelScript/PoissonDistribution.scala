package org.kevoree.experiment.modelScript

import java.lang.Math

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 25/07/11
 * Time: 16:35
 */

object PoissonDistribution {
  def getPoisson (lambda: Double): Int = {
    var k = 0 //Counter
    var p = Math.random() //uniform random number
    val P = Math.exp(-lambda) //probability
    val sum = P; //cumulant
    if (sum < p) {
      do {
        k += 1
        p *= Math.random()
      } while (p > P)
    } // else done allready

    k; //return random number
  }

  def getPoissonWithUpperLimit (lambda: Double, upperLimit: Int): Int = {

    var k = 0 //Counter
    var p = Math.random() //uniform random number
    val P = Math.exp(-lambda) //probability
    val sum = P; //cumulant
    if (sum < p) {
      do {
        k += 1;
        p *= Math.random()
      } while (p > P && k < upperLimit)
    } // else done allready

    k; //return random number
  }
}