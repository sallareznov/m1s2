set term jpeg;
set nokey;
set output 'dotplot.jpg';
set xrange[0:71];
set yrange[0:71];
plot 'dotplot.txt' with points pointtype 0;
