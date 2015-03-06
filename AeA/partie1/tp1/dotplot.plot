set term jpeg;
set nokey;
set output 'dotplot.jpg';
set xrange[0:1550];
set yrange[0:1550];
plot 'dotplot.txt' with points pointtype 0;
