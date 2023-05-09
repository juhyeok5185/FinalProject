// 요금합계	
let tPrice = 0;
function totalPrice($input) {
	const totalPrice = $('#totalPrice');
	tPrice += $input
	totalPrice.text(tPrice.toLocaleString() + ' 원');
};

class item {
	constructor(itemName, orderEA, price) {
		this.itemName = itemName;
		this.orderEA = orderEA;
		this.price = price;
	}
};

function order(pickupDay, tbodyArray) {
	$.ajax({
		type: 'post',
		url: '/hotel/mall/order',
		data: {
			pickupDay : pickupDay,
			tbodyArray : JSON.stringify(tbodyArray)
		},
		success: function(result) {
		}, 
		error: function(error) {
			console.log("실패");
		}
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

const mallItems=[
	{index: 1 , count:0 , price:3360000, name : 'BEDDING SET', stock:3},
	{index: 2 , count:0 , price:20000, name : 88, stock:3},
	{index: 3 , count:0 , price:20000, name : 88, stock:3},
	{index: 4 , count:0 , price:20000, name : 88, stock:3},
	{index: 5 , count:0 , price:20000, name : 88, stock:3},
	{index: 6 , count:0 , price:20000, name : 88, stock:3},
];


$(document).ready(function() {
	let checkBtn = false;
	let click = 0;
	let trCount = 0;
	var clientKey = 'test_ck_D5GePWvyJnrK0W0k6q8gLzN97Eoq';
	var tossPayments = TossPayments(clientKey);
	let cartClick = 0;

	
	
	// 결제 상세정보
	$('#chargeInquiry').on('click', function() {
		console.log('aaaaaaaaaa');
		let content = document.getElementById("totalBillDetailInner");
		++click;
		if (checkBtn == true) {
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
		let itemName = `${trCount-1}`==0?$(`.tbodyName`).text().replace(/ /g, ""):$(`.tbodyName`).text().replace(/ /g, "")+` 외${trCount-1}건`;
		const tbodyArray = []; 
		
		$('td.tbodyName').each(function() { 
		   tbodyArray.push(new item($(this).text().replace(/ /g, ""), $(this).parent().find('span').text(), $(this).next().next().text())); 
		});	
		order(pickupDay, tbodyArray);
		kakaojs({ itemPrice, itemName });
	})
	$('#choosePayment-box2').click(function() {
		let pickupDay = $('.pickup').val();
		let itemPrice = parseInt($(`#totalPrice`).text().replace(/,/g, ""));
		let itemName = `${trCount-1}`==0?$(`.tbodyName`).text().replace(/ /g, ""):$(`.tbodyName`).text().replace(/ /g, "")+` 외${trCount-1}건`;
		let uuid = self.crypto.randomUUID();
		const tbodyArray = []; 
		
		$('td.tbodyName').each(function() { 
		   tbodyArray.push(new item($(this).text().replace(/ /g, ""), $(this).parent().find('span').text(), $(this).next().next().text())); 
		});
		
		order({pickupDay,tbodyArray});
		tossPayments.requestPayment('TOSSPAY', {
			amount: itemPrice,
			orderId: uuid,
			orderName: itemName,
			successUrl: 'http://localhost:8081/pay/toss_success',
			failUrl: 'http://127.0.0.1:5500//fail.html'
		})
	})
	
	/* 반응형 */
	// 카트팝업 
	$('.mallCart-icon').click(function() {
		console.log();
		if (cartClick%2!=0) {
		  $('.mallCart-popup').attr('style', 'display:none');
		  $('.mallCart-content').attr('style', 'display:none');
		} else {
		  $('.mallCart-popup').attr('style', 'display:block');
		  $('.mallCart-content').attr('style', 'display:block');
		}
		++cartClick;
	  });
});