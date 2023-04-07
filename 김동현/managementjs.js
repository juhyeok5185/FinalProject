$(document).ready(function(){
  $('.list-group-item').click(function(){
    const $listitem = $('.list-group-item');
    $($listitem).attr("class",'list-group-item');
    const target = this;
    $(target).attr("class","list-group-item active");
  })

})