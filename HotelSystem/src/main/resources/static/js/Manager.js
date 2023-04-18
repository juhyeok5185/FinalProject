
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

 $('.searchToggle a').click(function() {
      $('.dropdown-menu a').removeClass('active');
      $(this).addClass('active');
      let text = $(this).text();
      $('#dropdownBtn').text(text);
  });

 $('.bookCancel').on('click' , async function(){
      var booker = $(this).find('input').val();
        try {
          const searchCondition = await $.ajax({
          url: '/hotel/manager/bookCancel?bookTel=' + booker,
          method: 'post',
          });
          location.href = '/hotel/manager/bookList';
        } catch(err) {
      console.log(err);
    }
  })

  $('.checkOutBtn').on('click' , async function(){
    const booker = $(this).find('input').val();
    const roomNo = $("td:nth-child(4)", $(this).closest("tr")).text();
      try {
        const searchCondition = await $.ajax({
        url: '/hotel/manager/checkOut?bookTel=' + booker + '&roomNo=' + roomNo,
        method: 'post',
        });
        location.href = '/hotel/manager/bookList';
      } catch(err) {
    console.log(err);
  }
  })

  $('.changeBook').on('click' , async function(){
  const row = $(this).closest('tr');
  const breakfastChecked = row.find('td:nth-child(9) input[type="checkbox"]').prop('checked');
  const resNoChecked = row.find('td:nth-child(10) input[type="checkbox"]').prop('checked');
  
    var booktel = $(this).find('input').val();
    const param = {
      breakfast: breakfastChecked,
      dinner: resNoChecked,
      bookTel: booktel
    }
    try {
      const searchCondition = await $.ajax({
      url: '/hotel/manager/changeBook' ,
      method: 'post',
      data: param
      });
      alert('예약이 변경되었습니다.');
      location.href = '/hotel/manager/bookList';
    } catch(err) {
  console.log(err);
}
  })

  $('.checkInBtn').on('click' , async function(){
    //등급 불러오기
    const roomGrade = $(this).closest('tr').find('td:nth-child(6)').text();
    try {
      const roomList = await $.ajax({
      url: '/hotel/manager/checkBtn?roomGrade='+roomGrade ,
      method: 'post'
      });
      const checkInDropDown = $('.checkInDropDown > li');
      //여기부터
      checkInDropDown.empty();
      for(const r of roomList) {
        const dropdownMenu = `
        <li><a class="dropdown-item" href="#">${r.roomNo}(${r.roomStatus})</a></li>
          `
        checkInDropDown.append(dropdownMenu);
      }
    } catch(err) {}
  })
  
  $(".checkInDropDown").on("click", "a", async function(event) {
    const clickedMenuText = $(this).text();
    const [roomNo, roomStatus] = clickedMenuText.split("(");
    const parsedRoomStatus = roomStatus.replace(")", "");
    const bookerName = $("td:nth-child(1)", $(this).closest("tr")).text();
    if(parsedRoomStatus == "비어있음"){
      try {
        const searchCondition = await $.ajax({
        url: '/hotel/manager/checkIn?roomNo=' + roomNo + '&name=' + bookerName,
        method: 'post'
        });
        alert('방이 배정되었습니다.');
        location.href = '/hotel/manager/bookList';
      } catch(err) {
        console.log(err);
      }
    } else {
      alert('이미 배정이 완료된 방입니다.');
    }
  });
});