set term jpeg;
set nokey;
set output 'dotplot.jpg';
set xrange[0:1231];
set yrange[0:1231];
plot 'dotplot.txt' with points pointtype 0;
