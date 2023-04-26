function printtotalprice(gradeName, gradeprice) {
	console.log(typeof (gradeprice));
	console.log(gradeprice);
	let totalprice = parseInt(gradeprice);
	if ($('#bfcheckbox').is(":checked")) {
		totalprice += parseInt($('#breakfastprice').text());

		console.log(totalprice);
	}
	if ($('#dicheckbox').is(":checked")) {
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
							<span>인원수</span>
							<input type = "number" id = "totalcnt" max="4" min="1" value = "1">
							<input type="checkbox" id = "bfcheckbox">
							<span>조식여부(50,000)</span>
							<input type="checkbox" id = "dicheckbox">
							<span>석식여부(150,000)</span>
							<span>예약자</span>
							<input type="text" id = "booker">
							<span>예약자 연락처</span>
							<input type="text" id = "booktel">
				  </div><hr>`
		;
	$footer.append(html2);
	console.log($roomlist);
	let index = 1;
	for (const b of $roomlist) {
		const html = `
	<div id = "roomlist">
		<input type="hidden" value="${b.gradeName}" id = "gradename">
		<div id = "roomlistimg">
		<img src = "/img/방이미지${index}.jpg" id = "roomimg"></div>
		<div id = "roomlistinfo"><span>${b.gradeName}</span>
		<span id = "breakfastprice" style="display: none;" >${b.breakfastprice}</span>
		<span id = "gradeprice">${b.gradeprice}</span></div>
		<div id = "roomlistbtn"><img id = "booklistbtn" src = "/img/선택버튼.png"></div>
		<br>
	</div><hr>`
			;
		if (b.reservableOrNot) {
			$footer.append(html);
		}
		index++;
	}

}


function kakaojs(result) {

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
		})
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




	$('#bookfooter').on('click', '#booklistbtn', function() {
		if ($('#booker').val() == "" || $('#booktel').val() == "") {
			alert("예약자와 연락처는 필수 입력 사항입니다")
		} else {
			const gradeName = $(this).parent().prev().prev().prev().val();
			const gradeprice = $(this).parent().prev().children('#gradeprice').text();
			console.log(gradeprice);
			printtotalprice(gradeName, gradeprice);



		}




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




