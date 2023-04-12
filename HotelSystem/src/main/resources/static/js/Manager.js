$(document).ready(function() {
    $('.dropdown-menu a').click(function() {
      let text = $(this).text();
      $('#dropdownBtn').text(text);
    });
  });