function mallData(){}

function searchmallData(mallList) {
  const mallListArea = $('#mallListArea');
  mallListArea.empty();
  if (mallList.length == 0) {
	  $('.table').removeClass('table-hover');
    const tableContent = `
        <tr>
          <td id="nullresult" rowspan="12">검색결과가 없습니다.</td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        `;
    mallListArea.append(tableContent);
	}
}

$(document).ready(function(){
	$('#nameSearchBtn').on('click' , async function(){
  	const name = $('#name').val();
  	const tel = $('#tel').val();
  	const orderNo = $('#orderNo').val();
  	const contactDate = $('#contactDate').val();
  	
  	try {
      const mallList = await $.ajax({
      url: '/hotel/manager/managerMallList?name='+ name + '&tel=' + tel +'&orderNo=' + orderNo+'&contactDate=' + contactDate,
      method:'post' });
      const mallListArea = $('#mallListArea');
      mallListArea.empty();
	  searchmallData(mallList);
	  let nullresult = '';
	  
	  
	  
      for (m of mallList){
	  
	  if(m.contactDate==null){
		 nullresult = '';
		 }
		 
	  else if(m.contactDate!=null){
		nullresult = m.contactDate;
		 }
      const template = `
      			<tr>
            	<td>${m.orderNo}</td>
            	<td>${m.name}</td>
            	<td>${m.tel}</td>
            	<td>${nullresult}</td>
            	<td>
            	<button class="btn btn-secondary" style="width: 100px;" id="nameSearchBtn">주문상세</button>
            	<button class="btn btn-secondary" style="width: 100px;" id="cencelBtn">주문취소</button>
            	</td>
            	</tr>
     				   `;
      $('.table').addClass('table-hover');				   
      mallListArea.append(template);
     
	  }
    } catch(err) {
    }
	})
	
	$(document).on('keyup', function(event){
		if (event.keyCode == 13) {
			$('#nameSearchBtn').click();
    	}
	})
	
	$(document).on('click',"#cencelBtn", function(){
		const orderNo = $(this).closest('tr').children('td:first').text().trim();
		/*
		   $.ajax({
        url: '/hotel/manager/managerMallList',
        type: 'DELETE',
        data: { orderNo: orderNo },
        success: function(result){
            // 삭제 성공 시 동작할 코드 작성
            console.log(result);
        },
        error: function(err){
            // 삭제 실패 시 동작할 코드 작성
            console.log(err);
        }
    });
    */
	})
	
	
})



