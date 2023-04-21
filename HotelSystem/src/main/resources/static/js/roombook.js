
function printRoomList($roomlist, $footer) {

	const html2 = `<div>
							<input type = "number" id = "totalcnt" max="4" min="1">
							<input type="checkbox" id = "bfcheckbox">
							<span>조식여부</span>
							<input type="checkbox" id = "dicheckbox">
							<span>석식여부</span>
							<input type="text" id = "booker">
							<span>예약자</span>
							<input type="text" id = "booktel">
							<span>예약자 연락처</span>
	</div>`;
	$footer.append(html2);
	for (const b of $roomlist) {
		const html = `
	<div>
	<input type="text" value="${b.gradeName}" id = "gradename">
	<a>${b.gradeName}</a>
	<button type = "button" id = "test">너어딧니</button>
	<br>
			</div>`;
		if (b.reservableOrNot) {
			$footer.append(html);
		}
	}

}

$(document).ready(function() {
	const $footer = $('#bookfooter');
	$('#bookfooter').on('click', '#test', function() {
		const gradeName = $(this).siblings('#gradename').val();
		const $from = $('#from').val();
		const $to = $('#to').val();
		const $bfcheckbox = $('#bfcheckbox').val();
		const $booker = $('#booker').val();
		const $booktel = $('#booktel').val();
		const $totalcnt = $('#totalcnt').val();
		const $dicheckbox = $('#dicheckbox').val()
		const param = {

			from: $from,
			to: $to,
			totalcnt: $totalcnt,
			gradename: gradeName,
			bfcheckbox: $bfcheckbox,
			dicheckbox : $dicheckbox,
			booker: $booker,
			booktel: $booktel
			
		}
		if ($booker == "" || $booktel == "") {
			alert("예약자와 연락처는 필수 입력 사항입니다")
		} else {

			$.ajax({
				url: "/hotel/client/chekin",
				data: param,
				dataType: 'json',
				method: 'post',
			})
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




