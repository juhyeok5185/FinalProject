let $roomlist;
$(document).ready(function() {
	const $footer = $('#bookfooter');
	$footer.append(`<p>성공</p>`)
	$('#reservation-btn').click(async function() {
		try {
			 $roomlist = await $.ajax({
				url: "/hotel/client/roombook",
				//data:param,
				dataType: 'json',
				method: 'post',
				
			})
			$footer.append(`<p>${$roomlist}</p>`);
			console.log($roomlist);
		} catch (err) {
			console.log(err)
		}
	})
})




