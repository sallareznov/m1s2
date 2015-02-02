set term jpeg;
set nokey;
set output 'dotplot.jpg';
set xrange[0:8890];
set yrange[0:8890];
plot 'dotplot.txt' with points;
