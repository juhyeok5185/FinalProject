$(document).ready(function(){
	
	$('#nameSearchBtn').on('click' , async function(){
		
  	const name = $('#name').val();
  	const tel = $('#tel').val();
  	const orderNo = $('#orderNo').val();
  	
  	try {
      const mallList = await $.ajax({
      url: '/hotel/manager/managerMallList?name='+ name + '&tel=' + tel +'&orderNo=' + orderNo,
      method:'post'
      });
      const mallListArea = $('#mallListArea');
      mallListArea.empty();
      
      for (m of mallList){
      const template = `
      			<tr>
            	<th>${m.orderNo}</th>
            	<th>${m.name}</th>
            	<th>${m.tel}</th>
            	<th>
            	<button class="btn btn-secondary" style="width: 100px;" id="nameSearchBtn">주문상세</button>
            	<button class="btn btn-secondary" style="width: 100px;" id="nameSearchBtn">주문취소</button>
            	</th>
            	</tr>
     				   `;
      mallListArea.append(template);
	  }
    } catch(err) {
    }
	})
})
















/*
$(document).ready(function(){
	
	$('#nameSearchBtn').on('click' , async function(){
		
  	const name = $('#name').val();
  	const tel = $('#tel').val();
  	const orderNo = $('#orderNo').val();
  	
  	try {
      const mallList = await $.ajax({
      url: '/hotel/manager/mallList?name='+ name + '&tel=' + tel +'&orderNo=' + orderNo,
      method:'post'
      });
      const mallListArea = $('#mallListArea');
      mallListArea.empty();
      
      
      for (m of mallList){
      const template = `
      			<tr>
            	<th>${m.orderNo}</th>
            	<th>${m.name}</th>
            	<th>${m.tel}</th>
            	<th>
            	<button class="btn btn-secondary" style="width: 100px;" id="nameSearchBtn">주문상세</button>
            	<button class="btn btn-secondary" style="width: 100px;" id="nameSearchBtn">주문취소</button>
            	</th>
            	</tr>
      `;
      mallListArea.append(template);
	  }
    } catch(err) {
    }
	})
})
*/