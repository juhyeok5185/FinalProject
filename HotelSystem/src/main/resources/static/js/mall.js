// 요금합계
function sumTotalPrice() {
  let tPrice = 0;
  const totalPrice = $("#totalPrice");
  for (let i = 0; i < mallItem.length; i++) {
    tPrice += mallItem[i].totalPrice;
  }
  totalPrice.text(tPrice + " 원");
}

const mallItem = [
  {
    index: 1,
    count: 0,
    price: 3360000,
    name: "BEDDINGSET",
    stock: 3,
    totalPrice: 0,
  },
  {
    index: 2,
    count: 0,
    price: 2500000,
    name: "MATTRESS",
    stock: 3,
    totalPrice: 0,
  },
  {
    index: 3,
    count: 0,
    price: 1850000,
    name: "DUVET",
    stock: 3,
    totalPrice: 0,
  },
  {
    index: 4,
    count: 0,
    price: 400000,
    name: "PILLOW",
    stock: 3,
    totalPrice: 0,
  },
  {
    index: 5,
    count: 0,
    price: 180000,
    name: "ROOM SPRAY",
    stock: 3,
    totalPrice: 0,
  },
  {
    index: 6,
    count: 0,
    price: 93000,
    name: "DEFUSER",
    stock: 3,
    totalPrice: 0,
  },
];

function addTr() {
  const $tbody1 = $("#tbody1");
  const $tbody2 = $("#tbody2");

  $tbody1.empty();
  $tbody2.empty();
  for (let i = 0; i < mallItem.length; i++) {
    if (mallItem[i].count > 0) {
      const tpl = `
				<tr>
					<td class="tbodyName" id="tbodyName" style="line-height:50px">${mallItem[i].name}</td>
					<td style="line-height:50px">
						<button class="btn btn-outline-secondary minus" data-index=${mallItem[i].index}>-<input type="hidden" value="${mallItem[i].price}"></button>
						<span class="count">${mallItem[i].count}</span>
						<button class="btn btn-outline-secondary plus" data-index=${mallItem[i].index}>+<input type="hidden" value="${mallItem[i].price}"></button>
					</td>
					<td class="price" style="line-height:50px">${mallItem[i].totalPrice}</td>
					<td style="line-height:50px"><button class="btn btn-outline-secondary delete" data-index=${mallItem[i].index}>X</button></td>
				</tr>`;

      $tbody1.append(tpl);
      $tbody2.append(tpl);
    }
  }
}

class item {
  constructor(itemName, orderEA, price) {
    this.itemName = itemName;
    this.orderEA = orderEA;
    this.price = price;
  }
}

function order(pickupDay, tbodyArray) {
  $.ajax({
    type: "post",
    url: "/hotel/mall/order",
    data: {
      pickupDay: pickupDay,
      tbodyArray: JSON.stringify(tbodyArray),
    },
    success: function (result) {},
    error: function (error) {
      console.log("실패");
    },
  });
}

// 카카오JS
function kakaojs(result) {
  $.ajax({
    type: "get",
    url: "/pay/start",
    data: {
      item_name: `${result.itemName}`, //상품명
      quantity: "1", // 나는 고정
      total_amount: `${result.itemPrice}`, //받아와야 하고
      tax_free_amount: "0", //필수라 넣어야됨
    },
    success: function (res) {
      location.href = res.next_redirect_pc_url;
    },
    error: function (error) {
      console.log(error);
    },
  });
}

$(document).ready(function () {
  let checkBtn = false;
  let click = 0;
  let trCount = 0;
  var clientKey = "test_ck_D5GePWvyJnrK0W0k6q8gLzN97Eoq";
  var tossPayments = TossPayments(clientKey);
  let cartClick = 0;

  // 상품담기
  $(".item-btn").click(function () {
    let index = $(this).data("index");

    for (let i = 0; i < mallItem.length; i++) {
      if (mallItem[i].index == index) {
        if (mallItem[i].count >= mallItem[i].stock) {
          alert("최대 " + mallItem[i].stock + "개까지만 선택할 수 있습니다.");
          return;
        }
        mallItem[i].count++;
        mallItem[i].totalPrice = mallItem[i].count * mallItem[i].price;
      }
    }

    addTr();
    sumTotalPrice();
  });

  // 구매수량 감소
  $(document).on("click", ".minus", function () {
    let index = $(this).data("index");

    let minus1 = $("#tbody1 .minus[data-index='" + index + "']");
    let minus2 = $("#tbody2 .minus[data-index='" + index + "']");
    let price = 0;
    const $count1 = minus1.next();
    const $count2 = minus2.next();

    let count1 = parseInt($count1.text());
    let count2 = parseInt($count2.text());

    const $totalPrice1 = minus1.parent().next();
    const $totalPrice2 = minus2.parent().next();

    let totalPrice1 = parseInt($totalPrice1.text());
    let totalPrice2 = parseInt($totalPrice2.text());

    if (count1 == 1 || count2 == 1) {
      alert("상품은 1개 이상부터 구매가 가능합니다.");
      return;
    }
    --count1;
    --count2;

    for (let i = 0; i < mallItem.length; i++) {
      if (mallItem[i].index == index) {
        price = mallItem[i].price;
      }
    }

    let tPrice = price * count1;

    $count1.text(count1);
    $count2.text(count2);
    $totalPrice1.text(tPrice);
    $totalPrice2.text(tPrice);

    for (let i = 0; i < mallItem.length; i++) {
      if (mallItem[i].index == index) {
        mallItem[i].count = count1;
        mallItem[i].totalPrice = tPrice;
      }
    }
    sumTotalPrice();
  });

  // 구매수량 증가
  $(document).on("click", ".plus", function () {
    let index = $(this).data("index");

    let plus1 = $("#tbody1 .plus[data-index='" + index + "']");
    let plus2 = $("#tbody2 .plus[data-index='" + index + "']");

    const $count1 = plus1.prev();
    const $count2 = plus2.prev();

    let count1 = parseInt($count1.text());
    let count2 = parseInt($count2.text());

    let price = 0;

    const $totalPrice1 = plus1.parent().next();
    const $totalPrice2 = plus2.parent().next();

    let totalPrice1 = parseInt($totalPrice1.text());
    let totalPrice2 = parseInt($totalPrice2.text());

    if (count1 == 3 || count2 == 3) {
      alert("3개 이상 구매가 불가합니다.");
      return;
    }
    count1++;
    count2++;

    for (let i = 0; i < mallItem.length; i++) {
      if (mallItem[i].index == index) {
        price = mallItem[i].price;
      }
    }

    let tPrice = price * count1;

    $count1.text(count1);
    $count2.text(count2);
    $totalPrice1.text(tPrice);
    $totalPrice2.text(tPrice);

    for (let i = 0; i < mallItem.length; i++) {
      if (mallItem[i].index == index) {
        mallItem[i].count = count1;
        mallItem[i].totalPrice = tPrice;
      }
    }
    sumTotalPrice();
  });

  // 삭제
  $(document).on("click", ".delete", function () {
    let index = $(this).data("index");

    for (let i = 0; i < mallItem.length; i++) {
      if (mallItem[i].index == index) {
        mallItem[i].count = 0;
        mallItem[i].totalPrice = 0;
      }
    }
    addTr();
    sumTotalPrice();
  });

  // 결제 상세정보
  $("#chargeInquiry").on("click", function () {
    let content = document.getElementById("totalBillDetailInner");
    ++click;
    if (checkBtn == true) {
    } else if (checkBtn == false) {
      if (click % 2 != 0) {
        $("html").attr("style", "overflow: hidden");
        $("#bg-popup").attr("style", "display:block;");
        $("#chargeInquiry").attr("style", "background-position:0 0;");
      } else if (click % 2 == 0) {
        $("#bg-popup").attr("style", "display:none;");
        $("#chargeInquiry").attr("style", "background-position:0 -32px;");
        $("html").attr("style", "overflow:none");
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
  $(document).on("click", "#mallPaymentBtn", function () {
    if ($(".pickup").val() == "" || $(`#totalPrice`).text() == "") {
      alert("픽업날짜 및 상품 선택은 필수입니다.");
      return;
    }
    checkBtn = true;
    $("#paymentPopup").attr("style", "display:block;");
    $("#paymentPopup-box").attr("style", "display:block");
    $("html").attr("style", "overflow: hidden");
  });
  $("#paymentPopup-btn").click(function () {
    checkBtn = false;
    $("#paymentPopup").attr("style", "display:none;");
    $("#paymentPopup-box").attr("style", "display:none");
    $("html").attr("style", "overflow:none");
  });

  /* 결제 */
  // 카카오페이
  $("#choosePayment-box1").click(function () {
    let trCount = 0;
    let pickupDay = $(".pickup").val();
    let itemPrice = parseInt($(`#totalPrice`).text());
    let saveName = false;
    let firstName = "";
    const tbodyArray = [];
    for (let i = 0; i < mallItem.length; i++) {
      if (mallItem[i].count != 0) {
        if (saveName == false) {
          firstName = mallItem[i].name;
          saveName = true;
        }
        trCount++;
        tbodyArray.push(new item(mallItem[i].name, mallItem[i].count, mallItem[i].totalPrice));
      }
    }
    let itemName = `${trCount - 1}` == 0 ? firstName : firstName + ` 외${trCount - 1}건`;

    order(pickupDay, tbodyArray);
    kakaojs({ itemPrice, itemName });
  });
  //토스
  $("#choosePayment-box2").click(function () {
    let uuid = self.crypto.randomUUID();
    let pickupDay = $(".pickup").val();
    let itemPrice = parseInt($(`#totalPrice`).text());
    let saveName = false;
    let firstName = "";
    const tbodyArray = [];
    for (let i = 0; i < mallItem.length; i++) {
      if (mallItem[i].count != 0) {
        if (saveName == false) {
          firstName = mallItem[i].name;
          saveName = true;
        }
        trCount++;
        tbodyArray.push(new item(mallItem[i].name, mallItem[i].count, mallItem[i].totalPrice));
      }
    }
    let itemName = `${trCount - 1}` == 0 ? firstName : firstName + ` 외${trCount - 1}건`;

    order({ pickupDay, tbodyArray });
    tossPayments.requestPayment("TOSSPAY", {
      amount: itemPrice,
      orderId: uuid,
      orderName: itemName,
      successUrl: "http://localhost:8081/pay/toss_success",
      failUrl: "http://127.0.0.1:5500//fail.html",
    });
  });

  /* 반응형 */
  // 카트팝업
  $(".mallCart-icon").click(function () {
    if (cartClick % 2 != 0) {
      $(".mallCart-popup").attr("style", "display:none");
      $(".mallCart-content").attr("style", "display:none");
    } else {
      $(".mallCart-popup").attr("style", "display:block");
      $(".mallCart-content").attr("style", "display:block");
    }
    ++cartClick;
  });
});
