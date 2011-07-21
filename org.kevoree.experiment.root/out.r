nodeNames <- c("duke0","paraisseux0","paraisseux2","paraisseux1","paraisseux3","duke2","duke1","duke3")
propDelais <- c(0,69,159,193,232,56751,56826,56837)
library(Hmisc)
bpplot(propDelais,main="Downtime propagation delay")