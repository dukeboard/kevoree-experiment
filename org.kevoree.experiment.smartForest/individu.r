z <- matrix(c(503,
510,
63,
283,
336,
1000,
353,
70,
46,
260,
16,
190,
506,
20,
100,
356,
416,
35,
26,
10,
50,
13,
126,
190,
33,
265,
346,
363,
260,
183,
700,
63,
20,
253,
33,
13,
130,
10,
346,
515,
203,
10,
76,
300,
30,
183,
60,
346,
346,
50,
366,
20,
500,
500,
65,
370,
383,
110,
113,
30,
186,
35,
20,
625,
20,
26,
275,
25,
193,
23,
15,
356,
30,
50,
30,
353,
45,
255,
300,
123,
186,
20,
343,
10,
30,
673,
40,
20,
186,
353,
26,
583,
433,
130,
106,
343,
255,
70,
346,
25,
550,
213,
30,
60,
275,
513,
506,
533,
343,
520,
26,
76,
433,
110,
33,
113,
20,
96,
353,
500,
265,
376,
265,
35,
73,
275,
183,
70,
116,
500,
100,
103,
100,
106,
343,
120,
510,
1000,
300,
265,
100,
275,
23,
233,
510,
60,
176,
30,
346,
176,
200,
213,
515,
176,
40,
135,
10,
265,
510,
1000,
150,
40,
100,
100,
510,
433,
90,
83,
20,
1000,
36,
50,
106,
60,
26,
15,
100,
683,
36,
45,
366,
503,
333,
56,
0,
513,
93,
353,
346,
20,
40,
216,
76,
150,
500,
75,
175,
250,
46,
130,
380,
55,
200,
625,
30,
360,
300,
500,
106,
510,
210,
433,
133,
100,
175,
186,
110,
380,
23,
126,
33,
40,
35,
183,
10,
183,
45,
175,
26,
20,
133,
103,
233,
70,
40,
266,
186,
30,
216,
505,
30,
150,
370,
30,
216,
533,
1000,
100,
100,
183,
256,
100,
40,
200,
20,
145,
36,
46,
65,
63,
50,
65,
750,
265,
33,
380,
363,
55,
193,
275,
506,
190,
426,
106,
33,
520,
1000,
25,
33,
30,
333,
525,
255,
0,
270,
346,
35,
550,
256,
43,
140,
30,
30,
30,
180,
40,
15,
300,
135,
16,
250,
133,
363,
360,
350,
233,
270,
25,
375,
265,
40,
63,
35,
56,
100,
60,
750,
75,
35,
206,
110,
50,
190,
40,
35,
370,
75,
15,
65,
625,
173,
1000,
40,
43,
516,
363,
30,
360,
1000,
90,
350,
505,
270,
140,
360,
150,
70,
80,
10,
516,
250,
40,
170,
180,
750,
126,
110,
133,
186,
40,
43,
430,
176,
20,
376,
70,
170,
33,
20,
356,
30,
525,
510,
75,
176,
525,
40,
516,
353,
190,
63,
53,
30,
15,
30,
266,
46,
533,
583,
176,
206,
30,
550,
500,
380,
10,
36,
1000,
15,
10),nrow=20.0,ncol=20.0, byrow=TRUE,dimnames = NULL)
x <- seq(0,19.0,by=1)
y <- seq(0,19.0,by=1)
persp(x,y,z,theta=15,phi=45)
