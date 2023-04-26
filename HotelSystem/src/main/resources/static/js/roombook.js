function printtotalprice(gradeName, gradeprice) {
	let totalprice = parseInt(gradeprice);
	console.log($('#bfcheckbox').is(":checked"))
	console.log($('#dicheckbox').is(":checked"))
	console.log(totalprice);
	if ($('#bfcheckbox').is(":checked")) {
		totalprice += parseInt($('#breakfastprice').text());

		console.log(totalprice);
	}
	if ($('#dicheckbox').is(":checked")){
		totalprice += 150000;
		console.log(totalprice);
	}

	const cntday = parseInt($('.night').text());
	const paytotalprice = totalprice * cntday;

	console.log(cntday);
	console.log(paytotalprice);
	$('#roominfo').empty();
	$('#roomname').empty();
	
	$('#roominfo').append(paytotalprice);
	$('#roomname').append(gradeName);

}
function printRoomList($roomlist, $footer) {

	const html2 = `<div>
							<input type = "number" id = "totalcnt" max="4" min="1">
							<input type="checkbox" id = "bfcheckbox">
							<span>조식여부(50,000)</span>
							<input type="checkbox" id = "dicheckbox">
							<span>석식여부(150,000)</span>
							<input type="text" id = "booker">
							<span>예약자</span>
							<input type="text" id = "booktel">
							<span>예약자 연락처</span>
				  </div>`;
	$footer.append(html2);
	console.log($roomlist);
	for (const b of $roomlist) {
		const html = `
	<div>
		<input type="text" value="${b.gradeName}" id = "gradename">
		<a>${b.gradeName}</a>
		<a id = "breakfastprice">${b.breakfastprice}</a>
		<a id = "gradeprice">${b.gradeprice}</a>
		<button type = "button" id = "test">너어딧니</button>
		<br>
	</div>`;
		if (b.reservableOrNot) {
			$footer.append(html);
		}
	}

}


function kakaojs(result) {
	console.log(result)

	$.ajax({
		type: 'get',
		url: '/pay/start',
		data: {
			item_name: `${result.gradename}`, //상품명
			quantity: "1", // 나는 고정
			total_amount: `${result.totalprice}`, //받아와야 하고
			tax_free_amount: "0" //필수라 넣어야됨
		},
		success: function(res) {
			location.href = res.next_redirect_pc_url;
		},
		error: function(error) {
			alert(error);
		}
	})

}

$(document).ready(function() {
	var clientKey = 'test_ck_D5GePWvyJnrK0W0k6q8gLzN97Eoq';
	 var tossPayments = TossPayments(clientKey)

	$('#paybookbtn').click(function() {

		$('#paymentPopup').attr('style', 'display:block;');
		$('#paymentPopup-box').attr('style', 'display:block');
		$('html').attr('style', 'overflow: hidden');
		$('#chargeInquiry').attr("style", "z-index:1; pointer-events: none;");

	});
	$('#choosePayment-box1').click(function() {
		const totalprice = $('#roominfo').text();
		const gradename = $('#roomname').text();
		parseInt(totalprice);
		kakaojs({ totalprice, gradename });
	})
	$('#choosePayment-box2').click(function() {
		const totalprice = $('#roominfo').text();
		const gradename = $('#roomname').text();
		parseInt(totalprice);
		let uuid = self.crypto.randomUUID();
		tossPayments.requestPayment('TOSSPAY', {
			amount: totalprice,
			orderId: uuid,
			orderName: gradename,
			successUrl: 'http://localhost:8081/pay/toss_success',
			failUrl: 'http://127.0.0.1:5500//fail.html'
		})
	})








	$('#paymentPopup-btn').click(function() {
		$('#paymentPopup').attr('style', 'display:none;');
		$('#paymentPopup-box').attr('style', 'display:none');
		$('html').attr('style', 'overflow:none');
		$('#chargeInquiry').attr("style", "pointer-events: none;");
	})






	const $footer = $('#bookfooter');




	$('#bookfooter').on('click', '#test', function() {
		if ($('#booker') == "" || $('#booktel') == "") {
			alert("예약자와 연락처는 필수 입력 사항입니다")
		} else {
			const gradeName = $(this).siblings('#gradename').val();
			const gradeprice = $(this).siblings('#gradeprice').text();
			printtotalprice(gradeName, gradeprice);



		}
		$('#choosePayment-box1').click(function() {
			const gradeName = $(this).siblings('#gradename').val();
			const $from = $('#from').val();
			const $to = $('#to').val();
			const $bfcheckbox = $('#bfcheckbox').val();
			const $booker = $('#booker').val();
			const $booktel = $('#booktel').val();
			const $totalcnt = $('#totalcnt').val();
			const $dicheckbox = $('#dicheckbox').val();

			const param = {

				from: $from,
				to: $to,
				totalcnt: $totalcnt,
				gradename: gradeName,
				bfcheckbox: $bfcheckbox,
				dicheckbox: $dicheckbox,
				booker: $booker,
				booktel: $booktel

			}
			$.ajax({
				url: "/hotel/client/chekin",
				data: param,
				dataType: 'json',
				method: 'post',
				success(result) {
					kakaojs(result);
				},
				error(err) {
					console.log(err);
				}
			})

			/*kakaojs(kakaoparam)*/
		})



	})

	$('#reservation-btn').click(async function() {
		const $foote2 = $('#bookfooter').html();
		if ($foote2.length == 0) {
			const $from = $('#from').val();
			const $to = $('#to').val();
			const param = {
				from: $from,
				to: $to
			}

			try {
				$roomlist = await $.ajax({
					url: "/hotel/client/roombook",
					data: param,
					dataType: 'json',
					method: 'post',

				})
				printRoomList($roomlist, $footer);

			} catch (err) {

			}
		}

		else {
			alert('이미 선택 하셧습니다')
		}
	})
	$('#dinnerbook').click(async function() {
		const $bookdate = $('#from').val();
		const $booktel = $('#booktel').val();
		const $bookpeople = $('#bookpeople').val();
		const $booker = $('#booker').val();
		const param2 = {
			bookdate: $bookdate,
			booktel: $booktel,
			bookpeople: $bookpeople,
			booker: $booker
		}
		try {
			await $.ajax({
				url: "/hotel/client/dinnerbook",
				data: param2,
				dataType: 'json',
				method: 'post',
			})
		} catch (err) { console.log(err) }
	})

})




