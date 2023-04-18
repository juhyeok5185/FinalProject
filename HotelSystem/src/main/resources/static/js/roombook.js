
function printRoomList($roomlist){
	const $footer = $('#bookfooter');
	for(const b of $roomlist){
	const html = `<p>${b.gradeName}</p>
				  <p>${b.reservableOrNot}</p>
			`;
		$footer.append(html);
	}
	
}

$(document).ready(function() {
	
	$('#reservation-btn').click(async function() {
		const $from = 	$('#from').val();
		const $to = $('#to').val();
		const param = {
			from : $from,
			to: $to
		}
		console.log(param);
		try {
			 $roomlist = await $.ajax({
				url: "/hotel/client/roombook",
				data:param,
				dataType: 'json',
				method: 'post',
				
			})
			printRoomList($roomlist);
			console.log($roomlist);
		} catch (err) {
			console.log(err)
		}
	})
})




