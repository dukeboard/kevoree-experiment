z <- matrix(c(400,
10,
400,
10,
10,
10,
400,
10,
400),nrow=3.0,ncol=3.0, byrow=TRUE,dimnames = NULL)
x <- seq(0,2.0,by=1)
y <- seq(0,2.0,by=1)
persp(x,y,z,theta=15,phi=45)
