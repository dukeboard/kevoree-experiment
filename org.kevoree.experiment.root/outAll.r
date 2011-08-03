propDelais <- c(573,349,93,172,187,84,274,275,269,277,422,447,366,585,541,558,167,234,201,84,337,398,359,395,556,608,304,575)
netSize <- c(480703)


library(Hmisc)
bpplot(propDelais,main="Downtime propagation delay")
boxplot(propDelais, propDelais, names = c("a", "b"), horizontal = TRUE, ylab = "factors", main = "title")