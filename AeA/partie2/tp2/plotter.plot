set term png;

set output 'results_p1.png';
plot 'output_p1.txt' using 1:2 title 'Prim' with lines, 'output_p1.txt' using 1:3 title 'Kruskal' with lines, x*log(x) title 'x^2';

set output 'results_p2.png';
plot 'output_p2.txt' using 1:2 title 'Prim' with lines, 'output_p2.txt' using 1:3 title 'Kruskal' with lines, x*log(x) title 'x^2';

set output 'results_p3.png';
plot 'output_p3.txt' using 1:2 title 'Prim' with lines, 'output_p3.txt' using 1:3 title 'Kruskal' with lines, x*log(x) title 'x^2';

set output 'results_p4.png';
plot 'output_p4.txt' using 1:2 title 'Prim' with lines, 'output_p4.txt' using 1:3 title 'Kruskal' with lines, x*log(x) title 'x^2';

set output 'results_p5.png';
plot 'output_p5.txt' using 1:2 title 'Prim' with lines, 'output_p5.txt' using 1:3 title 'Kruskal' with lines, x*log(x) title 'x^2';

set output 'results_p6.png';
plot 'output_p6.txt' using 1:2 title 'Prim' with lines, 'output_p6.txt' using 1:3 title 'Kruskal' with lines, x*log(x) title 'x^2';

set output 'results_p7.png';
plot 'output_p7.txt' using 1:2 title 'Prim' with lines, 'output_p7.txt' using 1:3 title 'Kruskal' with lines, x*log(x) title 'x^2';

set output 'results_p8.png';
plot 'output_p8.txt' using 1:2 title 'Prim' with lines, 'output_p8.txt' using 1:3 title 'Kruskal' with lines, x*log(x) title 'x^2';

set output 'results_p9.png';
plot 'output_p9.txt' using 1:2 title 'Prim' with lines, 'output_p9.txt' using 1:3 title 'Kruskal' with lines, x*log(x) title 'x^2';

set output 'results_p10.png';
plot 'output_p10.txt' using 1:2 title 'Prim' with lines, 'output_p10.txt' using 1:3 title 'Kruskal' with lines, x*log(x) title 'x^2';
