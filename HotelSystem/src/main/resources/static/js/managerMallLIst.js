function openPopup(url) {
  console.log(url);
  window.open(url, "managerorderDetail", "width=500, height=350, scrollbars=1, toolbar=1, menubar=yes, left=300px, top=100px,text-align: center;");
}

function searchmallData() {
  try {
    const mallListArea = $("#mallListArea");
    mallListArea.empty();
    $(".table").removeClass("table-hover");
  } catch (err) {
    const mallListArea = $("#mallListArea");
    const tableContent = `
        <tr>
          <td id="nullresult" rowspan="12">${err.message}</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        `;
    mallListArea.append(tableContent);
  }
}

function searchmallData(mallList) {
  const mallListArea = $("#mallListArea");
  mallListArea.empty();
  if (mallList.length == 0) {
    $(".table").removeClass("table-hover");
    const tableContent = `
        <tr>
          <td id="nullresult" rowspan="12">검색결과가 없습니다.</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        `;
    mallListArea.append(tableContent);
  }
}

$(document).ready(function () {
  const token = $("#token").val();
  console.log(token);
  $("#nameSearchBtn").on("click", async function () {
    const name = $("#name").val();
    const tel = $("#tel").val();
    const orderNo = $("#orderNo").val();
    const contactDate = $("#contactDate").val();

    try {
      const mallList = await $.ajax({
        url: "/hotel/manager/managerMallList?name=" + name + "&tel=" + tel + "&orderNo=" + orderNo + "&contactDate=" + contactDate + "&_csrf=" + token,
        method: "post",
      });
      const mallListArea = $("#mallListArea");
      mallListArea.empty();
      searchmallData(mallList);
      let nullresult = "";

      for (m of mallList) {
        if (m.contactDate == null) {
          nullresult = "";
        } else if (m.contactDate != null) {
          nullresult = m.contactDate;
        }
        const template = `
      			<tr>
            	<td>${m.orderNo}</td>
            	<td>${m.name}</td>
            	<td>${m.tel}</td>
            	<td>${nullresult}</td>
            	<td>
            	<button class="btn btn-secondary" style="width: 100px;" id="nameSearchBtn" onclick="openPopup('/hotel/manager/managerorderDetail?orderNo=${m.orderNo}')">
            	  주문상세
            	</button>
            	<button class="btn btn-secondary" style="width: 100px;" id="cencelBtn">주문취소</button>
            	</td>
            	</tr>
     				  `;
        $(".table").addClass("table-hover");
        mallListArea.append(template);
      }
    } catch (err) {}
  });

  $(document).on("keyup", function (event) {
    if (event.keyCode == 13) {
      $("#nameSearchBtn").click();
    }
  });

  $(document).on("click", "#cencelBtn", async function () {
    const orderno = $(this).closest("tr").children("td:first").text().trim();
    try {
      const response = await $.ajax({
        url: "/hotel/manager/delete?orderNo=" + orderno + "&_csrf=" + token,
        method: "post",
      });
      alert("주문이 취소되었습니다.");
      console.log(response);
    } catch (err) {
      console.log(err);
    }
    $.ajax({
      url: "/pay/cancel_do",
      data: { orderno, _csrf: token },
      method: "post",
      statusCode: {
        200: function () {
          location.href = "/hotel/manager/managerMallList";
          alert("예약 취소가 완료되었습니다.");
        },
      },
    });
  });

  $(document).on("click", "#orderdetailcencelBtn", async function () {
    try {
      const orderNo = $("#hiddenvalue").val();
      console.log(typeof orderNo);
      const response = await $.ajax({
        url: "/hotel/manager/delete",
        method: "post",
        data: {
          orderNo: orderNo,
          _csrf: token,
        },
      });

      $.ajax({
        url: "/pay/cancel_do",
        data: { orderNo, _csrf: token },
        method: "post",
        statusCode: {
          200: function () {
            alert("예약 취소가 완료되었습니다.");
          },
        },
      });

      alert("주문이 취소되었습니다.");
      window.opener.location.reload();
      window.close();
      console.log(response);
    } catch (err) {
      console.log(err);
    }
  });

  // 상품재고수량복구
  $(".update-btn").click(function () {
    let $itemno = parseInt($(this).parent().prev().prev().prev().text());
    console.log(typeof $itemno);
    $.ajax({
      url: "/hotel/manager/mall/updateItemEA",
      method: "post",
      data: {
        itemno: $itemno,
      },
      success: function () {},
      error: function (error) {
        console.log(error);
      },
    });
  });
});
