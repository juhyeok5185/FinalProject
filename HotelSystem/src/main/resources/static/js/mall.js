let tPrice = 0;
// 요금합계	
function totalPrice($input) {
	const totalPrice = $('#totalPrice');
	tPrice += $input
	totalPrice.text(tPrice.toLocaleString() + ' 원');
};

function order(param) {
	$.ajax({
		type: 'post',
		url: '/hotel/mall/order',
		data: param
	})
};


// 카카오JS
function kakaojs(result) {
	$.ajax({
		type: 'get',
		url: '/pay/start',
		data: {
			item_name: `${result.itemName}`, //상품명
			quantity: "1", // 나는 고정
			total_amount: `${result.itemPrice}`, //받아와야 하고
			tax_free_amount: "0" //필수라 넣어야됨
		},
		success: function(res) {
			location.href = res.next_redirect_pc_url;
		},
		error: function(error) {
				console.log(error)				
		}
	})
};

function detailPopup() {
	window.open(
		"/hotel/mall/orderDetail",
		"orderDetail-name",
		"scrollbar=no, width=600, height=700, top=150, left=600"
	);
};

$(document).ready(function() {
	let checkBtn = false;
	let count = 1;
	let click = 0;
	const stock = 3;
	let trCount = 0;
	var clientKey = 'test_ck_D5GePWvyJnrK0W0k6q8gLzN97Eoq';
	var tossPayments = TossPayments(clientKey);
	
	// 상품담기	
	$('.item-btn').click(function() {
		// 요금합계
		let index = $(this).data('index');
		let name = $(`#luxurytem-${index} .itemName`).text();
		let price = parseInt($(`#luxurytem-${index} .itemPrice`).text().replace(/,/g, ""));
		
		
		// 선택버튼 클릭횟수 제한
		let clickCnt = parseInt($(this).attr('data-click-cnt')) || 0;
  		if(clickCnt >= stock) {
    		alert('최대 '+stock+'개까지만 선택할 수 있습니다.');
    		return;
		} 
  		clickCnt++;
  		$(this).attr('data-click-cnt', clickCnt);
		
		
		// 요금상세
		const $tbody = $("#tbody");
		let existingItem = false;
		let $existingRow = null;
		$("#tbody tr").each(function() {
			if ($(this).find("#tbodyName").text() == name) {
				existingItem = true;
				$existingRow = $(this);
				return false;
			}
		});

		if (existingItem) {
			let $count = $existingRow.find('#count');
			let count = parseInt($count.text());
			count++;
			$count.text(count);

			let $price = $existingRow.find('#price');
			let existingPrice = parseInt($price.text());
			existingPrice += price;
			$price.text(existingPrice);
		} else {
			const tpl = `
	            <tr>
	                <td id="tbodyName" style="line-height:50px">${name}</td>
	                <td style="line-height:50px">
	                    <button class="btn btn-outline-dark minus" data-index=${index}>-<input type="hidden" value="${price}"></button>
	                    <span id="count">${count}</span>
	                    <button class="btn btn-outline-dark plus" data-index=${index}>+<input type="hidden" value="${price}"></button>
	                </td>
	                <td id="price" style="line-height:50px">${price}</td>
	                <td style="line-height:50px"><button class="btn btn-outline-dark delete" data-index=${index}>X</button></td>
	            </tr>`;
			$tbody.append(tpl);
			trCount++;
		}
		const totalPrice = $('#totalPrice');
		tPrice += price;
		totalPrice.text(tPrice.toLocaleString() + ' 원');
	});
	
	// 상품개수 감소
	$(document).on('click', '.minus', function() {
		const $count = $(this).parent().find('span');
 		let count = parseInt($count.text());
		if(count <= 1) {
			alert('상품은 1개 이상부터 구매가 가능합니다.');
			return;
		}
		$count.text(--count);		
		
		const $price = $(this).parent().next();
		let index = $(this).data('index');
		let itemPrice = parseInt($(`#luxurytem-${index} .itemPrice`).text().replace(/,/g, ""));
		let price = parseInt($price.text());	
		price = price-itemPrice;
		$price.text(price);
		var $input = parseInt($(this).find('input[type="hidden"]').val());
		console.log($input);
		totalPrice(-$input);
	});
	
	// 상품개수 증가
	$('#tbody').on('click', '.plus', function() {
		const $count = $(this).parent().find('span');
		let count = parseInt($count.text());
		if(count >= stock) {
			alert(stock+'개 이상 구매가 불가합니다.');
			return;
		}
		$count.text(++count);
		
		const $price = $(this).parent().next();
		let index = $(this).data('index');
		let itemPrice = parseInt($(`#luxurytem-${index} .itemPrice`).text().replace(/,/g, ""));
		let price = parseInt($price.text());
		price = price+itemPrice;
		$price.text(price);
		
		var $input = parseInt($(this).find('input[type="hidden"]').val());
		totalPrice($input);
	});
	
	// 상품삭제
	$('#tbody').on('click', '.delete', function() {
		const $tr = $(this).parent().parent();
		const $input = parseInt($(this).parent().prev().text());
		totalPrice(-$input);
		$tr.remove();
		trCount--;
	});
	
	// 결제 상세정보
	$('#chargeInquiry').on('click', function() {
		var content = document.getElementById("totalBillDetailInner");
		++click;
		if (checkBtn == true) {
			console.log("true");
		} else if (checkBtn == false) {
			if (click % 2 != 0) {
				$('html').attr('style', 'overflow: hidden');
				$('#bg-popup').attr('style', 'display:block;');
				$('#chargeInquiry').attr("style", "background-position:0 0;");
			} else if (click % 2 == 0) {
				$('#bg-popup').attr('style', 'display:none;');
				$('#chargeInquiry').attr("style", "background-position:0 -32px;");
				$('html').attr('style', 'overflow:none');
			}
			if (content.classList.contains("totalBillDetail-collapsed")) {
				content.classList.remove("totalBillDetail-collapsed");
				content.classList.add("totalBillDetail-expanded");
			} else {
				content.classList.remove("totalBillDetail-expanded");
				content.classList.add("totalBillDetail-collapsed");
			}
		}
	});

	// 결제 팝업창
	$(document).on('click','#mallPaymentBtn', function() {
		if($('.pickup').val()=="" || $(`#totalPrice`).text()=="") {
			alert('픽업날짜 및 상품 선택은 필수입니다.');
			return;
		}
		checkBtn = true;
		$('#paymentPopup').attr('style', 'display:block;');
		$('#paymentPopup-box').attr('style', 'display:block');
		$('html').attr('style', 'overflow: hidden');
	});
	$('#paymentPopup-btn').click(function() {
		checkBtn = false;
		$('#paymentPopup').attr('style', 'display:none;');
		$('#paymentPopup-box').attr('style', 'display:none');
		$('html').attr('style', 'overflow:none');
	});
	
	// 결제하기
	$('#choosePayment-box1').click(function() {
		let pickupDay = $('.pickup').val();
		let itemPrice = parseInt($(`#totalPrice`).text().replace(/,/g, ""));
		let itemName = `${trCount-1}`==0?$(`#tbodyName`).text():$(`#tbodyName`).text()+` 외${trCount-1}건`;
		
		
		kakaojs({ itemPrice, itemName });
		order({pickupDay, itemPrice, itemName});
	})
	$('#choosePayment-box2').click(function() {
		let pickupDay = $('.pickup').val();
		let itemPrice = parseInt($(`#totalPrice`).text().replace(/,/g, ""));
		let itemName = `${trCount-1}`==0?$(`#tbodyName`).text():$(`#tbodyName`).text()+` 외${trCount-1}건`;
		let uuid = self.crypto.randomUUID();
		tossPayments.requestPayment('TOSSPAY', {
			amount: itemPrice,
			orderId: uuid,
			orderName: itemName,
			successUrl: 'http://localhost:8081/pay/toss_success',
			failUrl: 'http://127.0.0.1:5500//fail.html'
		})
		order({pickupDay, itemPrice, itemName});
	})
	
	
	
});