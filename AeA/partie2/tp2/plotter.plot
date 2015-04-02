set term png;

set output 'results.png';
plot 'output.txt' using 1:2 title 'Prim' with lines, 'output.txt' using 1:3 title 'Kruskal' with lines, x*log(x) title 'x^2';