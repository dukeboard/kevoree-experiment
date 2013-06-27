package org.kevoree.experiment.smartForest;

import ec.util.MersenneTwisterFast;
import org.kevoree.ContainerRoot;
import org.kevoree.NamedElement;
import org.kevoree.framework.KevoreeXmiHelper;
import org.kevoree.library.reasoner.ecj.Precision;
import org.kevoree.experiment.smartForest.model.Generator;
import org.kevoree.experiment.smartForest.dpa.AddForestMonitoringComponentDPA;
import org.kevoree.experiment.smartForest.fitness.FitnessFunction;
import org.kevoree.experiment.smartForest.fitness.ConfidenceFitnessFunction;
import org.kevoree.experiment.smartForest.fitness.ConsumptionFitnessFunction;
import org.kevoree.experiment.smartForest.fitness.DensityFitnessFunction;
import org.kevoree.library.tools.dpa.DPA;
import org.kevoree.tools.marShell.ast.Script;
import org.kevoree.tools.marShell.interpreter.KevsInterpreterAspects;
import org.kevoree.tools.marShell.interpreter.KevsInterpreterContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RandomGeneration {

    public static int forestWidth = 10;
    public static int numberOfGeneration = 10000;

    private List<Float> results = new ArrayList<Float>();
    public float bestFitness = 100;

    public static void main(String[] args) {
        RandomGeneration rg = new RandomGeneration();
        rg.generate(forestWidth * forestWidth);
    }

    public void generate(int nodes){
 //       superNodesIndices = List((0, 0), (0, forestWidth - 1), (forestWidth - 1, 0), (forestWidth - 1, forestWidth - 1))
        FitnessFunction confF = new ConfidenceFitnessFunction();
        FitnessFunction consF = new ConsumptionFitnessFunction();
        FitnessFunction densF = new DensityFitnessFunction();
        for (int j=0; j<85; j++) {
            float best = 0;
            for (int i=0; i<numberOfGeneration; i++){
                ContainerRoot model = generateOneRandomForest(nodes);
                // TODO save the fitness
                float result = 300 - (confF.evaluate(model) + consF.evaluate(model) + densF.evaluate(model));
                if (result >= best){
                    best = result;
                    //System.out.println("Best = " + best);
                }
            }
            System.out.println(best);
        }

//        for (int i=0; i <forestWidth * forestWidth; i++) {
//            String scriptString = "tblock {\n addNode node" + i + ":ArduinoNode \n " +
//                    "addComponent temperature" + i + "@node" + i + " : TempSensor { " + ChangePeriodPropertyDPAO.periodPropertyName + "=\"" + getPeriod(i, forestWidth) + "\"}" +
//                    "addComponent humidity" + i + "@node" + i + " : HumiditySensor { " + ChangePeriodPropertyDPAO.periodPropertyName + "=\"" + getPeriod(i, forestWidth) + "\"}" +
//                    "addComponent smoke" + i + "@node" + i + " : SmokeSensor { " + ChangePeriodPropertyDPAO.periodPropertyName + "=\"" + getPeriod(i, forestWidth) + "\"} \n }";
//
//
//            parser.parseScript(scriptString) match {
//                case Some(script) => {
//                    val context = KevsInterpreterContext(myModel)
//                    script.interpret(context)
//
//                }
//                case None =>
//            }
//        }
//
//
//
//        myModel
    }

   public ContainerRoot generateOneRandomForest(int nodes){
       MersenneTwisterFast random = new MersenneTwisterFast();
       DPA myDPA = new AddForestMonitoringComponentDPA();
       ContainerRoot model = Generator.generateForest(forestWidth);
       KevsInterpreterContext context = new KevsInterpreterContext(model);
       int numberOfDPA = random.nextInt(nodes*3);
       List<Map<String, NamedElement>> myLists = myDPA.applyPointcut(model);
       for (int j =0; j<numberOfDPA; j++){
           if (!myLists.isEmpty()) {
               Map<String, NamedElement> myMap = myLists.remove(random.nextInt(myLists.size()));
               Script script = myDPA.getASTScript(myMap) ;
               KevsInterpreterAspects.rich(script).interpret(context);
           }
       }
       return model;
    }
//    public float computeFitness(boolean[][] myBool){
//        if (computePrecision(myBool)>0){
//            return 100;
//        } else {
//            return computeCPU(myBool);
//        }
//
//    }
//
//     private float computeCPU (boolean[][] myBool){
//        double[] cpuConsumptions = new double[forestWidth*forestWidth];
//        for (int i=0; i<cpuConsumptions.length; i++){
//            int componentNumbers = 0;
//            for (int j=0;j<myBool[i].length;j++){
//                if (myBool[i][j])
//                    componentNumbers++;
//            }
//            if (componentNumbers > 0)
//                cpuConsumptions[i] = 40 + componentNumbers*20;
//            else
//                cpuConsumptions[i] = 0;
//        }
//        double result = 0;
//        for (int i=0; i<cpuConsumptions.length; i++){
//            result+=cpuConsumptions[i];
//        }
//        result = result / cpuConsumptions.length;
//        return (float)(result);
//    }
//
//    public float computePrecision(boolean[][] myBool){
//        double precision = 0.0;
//        int size = forestWidth*forestWidth;
//        Precision[]precisionArray = new Precision[size];
//
//        for (int i=0; i<size;i++){
//            precisionArray[i] = new Precision();
//            if (myBool[i][0]){
//                precisionArray[i].setTempPrecision(100.0);
//            }
//            if (myBool[i][1]){
//                precisionArray[i].setSmokePrecision(100.0);
//            }
//            if (myBool[i][2]){
//                precisionArray[i].setHumidityPrecision(100.0);
//            }
//        }
//        for (int k=0; k<size; k++){
//            if (!precisionArray[k].isCompleted()){
//                Integer[] neighbours = getNeighbours(k, size);
//                for (int j=0;j<neighbours.length;j++){
//                    if (myBool[neighbours[j]][0]){
//                        precisionArray[k].setTempPrecision(precisionArray[k].getTempPrecision()+(100.0/8.0));
//                    }
//                    if (myBool[neighbours[j]][1]){
//                        precisionArray[k].setSmokePrecision(precisionArray[k].getSmokePrecision() + (100.0/8.0));
//                    }
//                    if (myBool[neighbours[j]][2]){
//                        precisionArray[k].setHumidityPrecision(precisionArray[k].getHumidityPrecision()+ (100.0/8.0));
//                    }
//                }
//            }
//        }
//
//        for (int k=0; k<precisionArray.length;k++){
//            precision +=precisionArray[k].getHumidityPrecision()+ precisionArray[k].getTempPrecision() + precisionArray[k].getSmokePrecision();
//        }
//        precision = precision / (precisionArray.length *3);
//
//        return (float)(100-Math.floor(precision/10)*10);
//    }
//
//    private Integer[] getNeighbours(int k, int size) {
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
}
