propDelais <- c(11207,10823,10622,10967,10055,827,0,9951,10116,1398,199,31195,0,78,265,40482,9242,116,0,9128,9300,31428,31081,190,0,76,338)

library(Hmisc)
bpplot(propDelais,main="Downtime propagation delay")
#boxplot(propDelais, propDelais, names = c("a", "b"), horizontal = TRUE, ylab = "factors", main = "title")