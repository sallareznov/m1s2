$(document).ready(function() {
  $(document).bind('keydown', 'left', function() {
    $('#box').removeClass().addClass('show-left');
    return false;
  });

  $(document).bind('keydown', 'right', function() {
    $('#box').removeClass().addClass('show-right');
    return false;
  });

  $(document).bind('keydown', 'up', function() {
    $('#box').removeClass().addClass('show-top');
    return false;
  });

  $(document).bind('keydown', 'down', function() {
    $('#box').removeClass().addClass('show-bottom');
    return false;
  });

  $(document).bind('keydown', 'a', function() {
    $('#box').removeClass().addClass('show-back');
    return false;
  });

  $(document).bind('keydown', 'f', function() {
    $('#box').removeClass().addClass('show-front');
    return false;
  });
});