propDelais <- c(0)
netSize <- c()


library(Hmisc)
bpplot(propDelais,main="Downtime propagation delay")
boxplot(propDelais, propDelais, names = c("a", "b"), horizontal = TRUE, ylab = "factors", main = "title")