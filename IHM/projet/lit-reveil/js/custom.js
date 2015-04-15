var forecast = $('#week-forecast');
var params = $('#weather-params');
var temp = $('#temperature');
$(document).ready(function() {
    var i = 1000;
    forecast.children('li').each(function() {
        $(this).delay(i).queue(function() {
            $(this).addClass('active');
            $(this).dequeue();
        });
        i += 250;
    });

    var ii = 600;
    params.children('li').each(function() {
        $(this).delay(ii).queue(function() {
            $(this).addClass('active');
            $(this).dequeue();
        });
        ii += 200;
    });

    $(temp).delay(400).queue(function() {
        $(this).addClass('active');
        $(this).dequeue();
    });
});