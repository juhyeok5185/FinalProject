// 주문리스트 팝업
function detailPopup(url) {
	window.open(
		url,
		"orderDetail-name",
		"scrollbar=no, width=600, height=700, top=150, left=600"
	)
};

$(document).ready(function() {
	$(document).on('click', '.orderCancel-btn', function() {
		const $orderNo = parseInt($(this).parent().prev().prev().prev().text());
		console.log($orderNo);
		
		let orderCancel = confirm('정말 취소하시겠습니까?');
		if(orderCancel) {
			alert('주문이 취소되었습니다.');
			try{
				$.ajax({
					url: '/hotel/mall/orderDelete',
					method : 'delete',
					data: {
						orderNo: $orderNo
					}
				})
			} catch(err) {
				console.log(err);
			}
		} else {
			return
		}
		location.reload();
	})
});