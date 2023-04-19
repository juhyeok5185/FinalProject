$(document).ready(function() {
	// 상품담기	
	let tPrice = 0;
	$('.item-btn').click(function() {
  		let index = $(this).data('index');
  		let price = parseInt($(`#luxurytem-${index} .itemPrice`).text().replace(/,/g,""));
		tPrice += price;
  		const totalPrice = $('#totalPrice');
  		totalPrice.text(tPrice.toLocaleString() + ' 원');
  		
  		const $tbody = $("#item-tbody");
  		for(const m of mall) {
	  		const tpl = `
	  			<tr>
	    			<td>${m.itemName}</td>
	    			<td><button id="minus">-</button><span>1</span><button id="plus">+</button></td>
	    			<td>${m.itemPrice}</td>
	    			<td><button>X</button></td>
	    		</tr>`;
	    		$tbody.append(tpl);
			  
		  }
  		
	});
	
	
	// 결제 상세정보
	$('#chargeInquiry').click(function() {
		 
	});
	
	
	// 결제 팝업창
	$('#mallPaymentBtn').click(function() {
		$('#paymentPopup').attr('style', 'display:block;');
		$('#paymentPopup-box').attr('style', 'display:block');
		$('html').attr('style', 'overflow: hidden');
			
	})
	$('#paymentPopup-btn').click(function() {
		$('#paymentPopup').attr('style', 'display:none;');
		$('#paymentPopup-box').attr('style', 'display:none');
		$('html').attr('style', 'overflow:none');
	})
	
	// 결제
	$('#choosePayment-box1').click(function() {
		
	})
	
 });
 
 