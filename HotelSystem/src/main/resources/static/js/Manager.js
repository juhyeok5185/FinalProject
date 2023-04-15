
$(document).ready(function() {
  
   
  $('#searchBtn').on('click' , async function(){
    let stayCheckBox = $('#stayCheckBox').is(':checked');
    let restaurantCheckBox = $('#restaurantCheckBox').is(':checked');
    if(stayCheckBox==false && restaurantCheckBox == false){
      stayCheckBox = true;
      restaurantCheckBox = true;
    } 
    
    const param = {
      isStay: stayCheckBox,
      isRestaurant: restaurantCheckBox,
      fromDate: $('#from').val(),
      toDate: $('#to').val(),
      roomNum: Number($('#searchRoomNumber').val()),
      name: $('#searchName').val(),
      listType: $('.dropdown-menu a.active').data('index')
    }




    try {
          const searchCondition = await $.ajax({
          url: '/hotel/manager/bookSearch',
          method: 'post',
          data: param
          });


        } catch(err) {
      console.log(err);
    }
  });







 $('.dropdown-menu a').click(function() {
      $('.dropdown-menu a').removeClass('active');
      $(this).addClass('active');
      let text = $(this).text();
      $('#dropdownBtn').text(text);
    });
});