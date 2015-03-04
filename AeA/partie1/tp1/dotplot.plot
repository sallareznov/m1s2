set term jpeg;
set nokey;
set output 'dotplot.jpg';
set xrange[0:8880];
set yrange[0:8880];
plot 'dotplot.txt' with points pointtype 0;
