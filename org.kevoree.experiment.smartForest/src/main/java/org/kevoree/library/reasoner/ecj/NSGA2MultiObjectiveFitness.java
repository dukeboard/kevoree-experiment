package org.kevoree.library.reasoner.ecj;

/**
 * Created by IntelliJ IDEA.
 * User: jbourcie
 * Date: 07/07/11
 * Time: 15:36
 */
public class NSGA2MultiObjectiveFitness extends ec.multiobjective.nsga2.NSGA2MultiObjectiveFitness {

    public String fitnessToStringForHumans()
        {
        return super.fitnessToStringForHumans().replace("\n"," ");
        }


    public Float getScore() throws Exception {

        if(getNumObjectives() != 3){
            throw new Exception("DaFuck ");
        }

        return ( 300 - (getObjective(0)+getObjective(1)+getObjective(2)) );
    }

}
