package org.kevoree.library.reasoner.ecj;

import ec.EvolutionState;
import ec.Individual;
import ec.Problem;
import ec.app.tutorial4.Y;
import ec.multiobjective.MultiObjectiveFitness;
import ec.simple.SimpleProblemForm;
import org.kevoree.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.ArrayList;
import java.util.List;

public class MultiObjectiveKevoreeProblem extends Problem implements SimpleProblemForm {
    public static final String tempSensor = "TempSensor";
    public static final String smokeSensor = "SmokeSensor";
    public static final String humiditySensor = "HumiditySensor";

    private static double worseDensity = 0.0;


    public void evaluate(EvolutionState state, Individual ind,
            int subpopulation, int threadnum) {
        if (ind.evaluated) return;

        if (!(ind instanceof KevoreeMultiIndividual))
            state.output.fatal("Whoa!  It's not a KevoreeIndividual!!!",null);
        KevoreeMultiIndividual ki = (KevoreeMultiIndividual)ind;
        if (worseDensity == 0.0)
            worseDensity = evaluateWorseDensity(ki.myModel);
        
       // float[] newObjectives = {evaluateFunctionnality(ki, functionnalityValue, -functionnalityValue/4), evaluateCommunicationDelay(ki), evaluateUnusedHubs(ki), evaluateLoadBalancing(ki), evaluateUnusefullComponent(ki), evaluatearchitectureSize(ki)};
        float[] newObjectives = {evaluateDensity(ki.myModel), evaluateCPUConsumption(ki.myModel)};
        setFitness(state, ki, newObjectives);
    }


    private float evaluateCPUConsumption (ContainerRoot myModel){
        double[] cpuConsumptions = new double[myModel.getNodes().size()];
        for (int i=0; i<cpuConsumptions.length; i++){
            int size = myModel.getNodes().get(i).getComponents().size();
            if (size > 0)
                cpuConsumptions[i] = 40 + size*20;
            else
                cpuConsumptions[i] = 0;
            if (cpuConsumptions[i] > 100)
                cpuConsumptions[i] = 100;
        }
        double result = 0;
        for (int i=0; i<cpuConsumptions.length; i++){
            result+=cpuConsumptions[i];
        }
        result = result / cpuConsumptions.length;
        return (float)(result);
    }

    private void setFitness(EvolutionState state, KevoreeIndividual ki, float[] newObjectives) {
        if (!(ki.fitness instanceof MultiObjectiveFitness)) {
            state.output.fatal("Whoa!  It's not a MultiObjective!!!", null);
        }
        ((MultiObjectiveFitness) ki.fitness).setObjectives(state, newObjectives);
        ki.evaluated = true;
    }
    
    public static float evaluatePrecision(ContainerRoot myModel){
        double precision = 0.0;
        int size = myModel.getNodes().size();
        Precision[]precisionArray = new Precision[size];

        for (int i=0; i<myModel.getNodes().size();i++){
            precisionArray[i] = new Precision();
            ContainerNode myNode = myModel.getNodes().get(i);
            if (containsInstance(myNode, tempSensor)){
                precisionArray[i].setTempPrecision(100.0);
            }
            if (containsInstance(myNode, smokeSensor)){
                precisionArray[i].setSmokePrecision(100.0);
            }
            if (containsInstance(myNode, humiditySensor)){
                precisionArray[i].setHumidityPrecision(100.0);
            }
        }
        for (int k=0; k<myModel.getNodes().size(); k++){
            if (!precisionArray[k].isCompleted()){
                Integer[] neighbours = getNeighbours(k, size);
                for (int j=0;j<neighbours.length;j++){
                    if (containsInstance(myModel.getNodes().get(neighbours[j]), tempSensor)){
                        precisionArray[k].setTempPrecision(precisionArray[k].getTempPrecision()+(100.0/8.0));
                    }
                    if (containsInstance(myModel.getNodes().get(neighbours[j]), smokeSensor)){
                        precisionArray[k].setSmokePrecision(precisionArray[k].getSmokePrecision() + (100.0/8.0));
                    }
                    if (containsInstance(myModel.getNodes().get(neighbours[j]), humiditySensor)){
                        precisionArray[k].setHumidityPrecision(precisionArray[k].getHumidityPrecision()+ (100.0/8.0));
                    }
                }
            }
        }

        for (int k=0; k<precisionArray.length;k++){
            precision +=precisionArray[k].getHumidityPrecision()+ precisionArray[k].getTempPrecision() + precisionArray[k].getSmokePrecision();
        }
        precision = precision / (precisionArray.length *3);

        //return (float)(100-Math.floor(precision/10)*10);
        return (float)Math.floor(100-precision);

    }

    public static float evaluateDensity(ContainerRoot myModel){
        double density = 0.0;
        int size = myModel.getNodes().size();

        for (int i=0; i<myModel.getNodes().size();i++){
            ContainerNode myNode = myModel.getNodes().get(i);
            if (!containsInstance(myNode, tempSensor)){
                density += density(myModel, tempSensor, i);
            }
            if (!containsInstance(myNode, smokeSensor)){
                density += density(myModel, smokeSensor, i);
            }
            if (!containsInstance(myNode, humiditySensor)){
                density += density(myModel, humiditySensor, i);
            }
        }
        return (float)Math.floor(density*100/worseDensity);

    }

    public static double density(ContainerRoot myModel, String myType, int indice){
        double density = 0.0;
        int i = indice/GeneticAlgorithm.forestWidth ;
        int j = indice - (i*GeneticAlgorithm.forestWidth) ;

        int minDiffI = -1, minDiffJ = -1, maxDiffI = 1, maxDiffJ = 1;
        if (i == 0)
            minDiffI = 0;
        if (j == 0)
            minDiffJ = 0;
        if (i == GeneticAlgorithm.forestWidth-1)
            maxDiffI = 0;
        if (j == GeneticAlgorithm.forestWidth-1)
            maxDiffJ = 0;

        for (int k=minDiffI; k<=maxDiffI; k++){
            for (int l=minDiffJ; l<=maxDiffJ; l++){
                if (!(k==0 && l == 0))
                    density += calculateInDirection(myModel, k,l, myType, indice);
            }
        }
        return density;
    }

    public static double calculateInDirection(ContainerRoot myModel, int diffI, int diffJ, String myType, int indice){
        int i = indice/GeneticAlgorithm.forestWidth ;
        int j = indice - (i*GeneticAlgorithm.forestWidth) ;
        if (i == 0 || i == GeneticAlgorithm.forestWidth-1 || j == 0 || j == GeneticAlgorithm.forestWidth-1){
            return GeneticAlgorithm.forestWidth;
        }
        i += diffI;
        j += diffJ;
        int newIndice = i*GeneticAlgorithm.forestWidth+j;
        ContainerNode myNode = myModel.getNodes().get(newIndice);
        if (containsInstance(myNode, myType)){
            return 1;
        }
        else {
            return 1+calculateInDirection(myModel, diffI, diffJ, myType, newIndice);
        }
    }

    public static Integer[] getNeighbours(int indice, int size) {
        int width = (int)Math.sqrt((double)size);
        int i = indice/width;
        int j = indice - (i*width);
        int minI = Math.max(0,i-1);
        int maxI = Math.min(width-1, i+1);
        int minJ = Math.max(0,j-1);
        int maxJ = Math.min(width-1, j+1);
        List<Integer> myList = new ArrayList<Integer>();
        for (int k=minI; k<=maxI; k++){
            for (int l=minJ; l<=maxJ; l++){
                myList.add(k*width+l);
            }
        }
        return myList.toArray(new Integer[myList.size()]);

    }

    public static boolean containsInstance(ContainerNode myNode, String componentName){
        for (ComponentInstance ci:myNode.getComponents()){
            if (((NamedElement)ci.getTypeDefinition()).getName().equalsIgnoreCase(componentName)){
                return true;
            }
        }
        return false;
    }




    private static float evaluateWorseDensity(ContainerRoot myModel){
        double density = 0.0;
        int size = myModel.getNodes().size();

        for (int i=0; i<myModel.getNodes().size();i++){
            density += 3*worseDensity(i);
        }
        return (float)Math.floor(density);

    }

    private static double worseDensity( int indice){
        double density = 0.0;
        int i = indice/GeneticAlgorithm.forestWidth ;
        int j = indice - (i*GeneticAlgorithm.forestWidth) ;

        int minDiffI = -1, minDiffJ = -1, maxDiffI = 1, maxDiffJ = 1;
        if (i == 0)
            minDiffI = 0;
        if (j == 0)
            minDiffJ = 0;
        if (i == GeneticAlgorithm.forestWidth-1)
            maxDiffI = 0;
        if (j == GeneticAlgorithm.forestWidth-1)
            maxDiffJ = 0;

        for (int k=minDiffI; k<=maxDiffI; k++){
            for (int l=minDiffJ; l<=maxDiffJ; l++){
                if (!(k==0 && l == 0))
                    density += calculateWorseInDirection(k,l, indice);
            }
        }
        return density;
    }

    private static double calculateWorseInDirection(int diffI, int diffJ,  int indice){
        int i = indice/GeneticAlgorithm.forestWidth ;
        int j = indice - (i*GeneticAlgorithm.forestWidth) ;
        if (i == 0 || i == GeneticAlgorithm.forestWidth-1 || j == 0 || j == GeneticAlgorithm.forestWidth-1){
            return GeneticAlgorithm.forestWidth;
        }
        i += diffI;
        j += diffJ;
        int newIndice = i*GeneticAlgorithm.forestWidth+j;
        return 1+calculateWorseInDirection(diffI, diffJ, newIndice);
    }

}
