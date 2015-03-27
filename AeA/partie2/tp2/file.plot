set term png;

set output 'test.png';
plot 'test.txt' using 1:2 title 'Prim' with lines, 'test.txt' using 1:3 title 'Kruskal' with lines, x*log(x) title 'x^2';