package org.kevoree.library.reasoner.ecj;

import ec.EvolutionState;
import ec.Individual;
import ec.Species;
import ec.util.Parameter;
import org.kevoree.experiment.smartForest.experiment.KevoreeIndividualAbstract;

public class KevoreeModelSpeciesForMutation extends Species {

    public static final String P_KEVOREE_SPECIES = "species";
    
    @Override
    public Parameter defaultBase() {
        return KevoreeDefaults.base().push(P_KEVOREE_SPECIES);
    }
    
    @Override
    public Individual newIndividual(final EvolutionState state, int thread)
    {
        KevoreeIndividualAbstract ind = (KevoreeIndividualAbstract)super.newIndividual(state, thread);
        return ind;
    }

}
