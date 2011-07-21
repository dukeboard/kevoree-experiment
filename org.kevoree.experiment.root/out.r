nodeNames <- c("parapluie21rennesgrid5000fr0","parapluie21rennesgrid5000fr1","parapluie21rennesgrid5000fr2","parapluie23rennesgrid5000fr0","parapluie21rennesgrid5000fr3")
propDelais <- c(0,72,84,85,31709)
library(Hmisc)
bpplot(propDelais,main="Downtime propagation delay")