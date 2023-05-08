// 주문리스트 팝업
function detailPopup(url) {
	window.open(
		url,
		"orderDetail-name",
		"scrollbar=no, width=600, height=700, top=150, left=600"
	)
};

// 주문취소
$(document).ready(function() {
	$(document).on('click', '.orderDetailCancel-btn', function() {
		const $orderNo = parseInt($('#order-no').val());
		const $itemName = $('#item-name').val();
		const $orderEA = parseInt($('#order-EA').val());
		let orderCancel = confirm('정말 취소하시겠습니까?');
		if(orderCancel) {
			alert('주문이 취소되었습니다.');
			try{
				$.ajax({
					url: '/hotel/mall/orderDelete',
					method : 'delete',
					data: {
						orderNo: $orderNo,
						itemName: $itemName,
						orderEA: $orderEA
					}
				})
			} catch(err) {
				console.log(err);
			}
		} else {
			return
		}
		window.close();
		window.opener.location.reload();
	})

	$(document).on('click', '.orderCancel-btn', function() {
		const $orderNo = parseInt($(this).closest('tr').find('.order-No').val());
		const $itemName =  $(this).closest('tr').find('.item-Name').val();
		const $orderEA = parseInt($(this).closest('tr').find('.order-EA').val());

		let orderCancel = confirm('정말 취소하시겠습니까?');
		if(orderCancel) {
			alert('주문이 취소되었습니다.');
			try{
				$.ajax({
					url: '/hotel/mall/orderDelete',
					method : 'delete',
					data: {
						orderNo: $orderNo,
						itemName: $itemName,
						orderEA: $orderEA
					}
				})
			} catch(err) {
				console.log(err);
			}
			location.reload();
		} else {
			return
		}
	})
});