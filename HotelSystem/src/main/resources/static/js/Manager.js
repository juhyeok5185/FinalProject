
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
      roomNum: $('#searchRoomNumber').val(),
      name: $('#searchName').val(),
      listType: $('.dropdown-menu a.active').data('index')
    }

    console.log(param);



    // try {
    //     const board = await $.ajax({
    //     url: '/hotel/manager/list',
    //     method: 'post',
    //     // data: param
    //   });
    //   location.href = "/board/read?no=" + board.bno;
    // } catch(err) {
    //   console.log(err);
    // }
  });







 $('.dropdown-menu a').click(function() {
      $('.dropdown-menu a').removeClass('active');
      $(this).addClass('active');
      let text = $(this).text();
      $('#dropdownBtn').text(text);
    });
});