propDelais <- c(205,57,168,606,0,218,247,139,196,98,182,203,0,242,272,214,150,70,100,110,0,106,166,148)
netSize <- c(114088,114088)


library(Hmisc)
bpplot(propDelais,main="Downtime propagation delay")
boxplot(propDelais, propDelais, names = c("a", "b"), horizontal = TRUE, ylab = "factors", main = "title")