set term png;

set output 'results_p1.png';
plot 'output_p1.txt' using 1:2 title 'Welsh-Powell' with lines, 'output_p1.txt' using 1:3 title 'Greedy' with lines;

set output 'results_p3.png';
plot 'output_p3.txt' using 1:2 title 'Welsh-Powell' with lines, 'output_p3.txt' using 1:3 title 'Greedy' with lines;

set output 'results_p5.png';
plot 'output_p5.txt' using 1:2 title 'Welsh-Powell' with lines, 'output_p5.txt' using 1:3 title 'Greedy' with lines;

set output 'results_p7.png';
plot 'output_p7.txt' using 1:2 title 'Welsh-Powell' with lines, 'output_p7.txt' using 1:3 title 'Greedy' with lines;

set output 'results_p9.png';
plot 'output_p9.txt' using 1:2 title 'Welsh-Powell' with lines, 'output_p9.txt' using 1:3 title 'Greedy' with lines;
