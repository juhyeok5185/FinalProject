function openPopup(url) {
  window.open(
    url,
    "memberDetail",
    "width=800, height=700, scrollbars=1, toolbar=1, menubar=yes, left=300px, top=100px"
  );
}

function searchData(list) {
  const bookListTable = $("#bookListTable");
  bookListTable.empty();
  if (list.length == 0) {
    const tableContent = `
        <tr>
          <td rowspan="12">검색결과가 없습니다.</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        `;
    bookListTable.append(tableContent);
  } else {
    for (const l of list) {
      let breakfastContent = ``;
      let resNoContent = ``;
      let buttonContent = ``;
      let roomNoContent = ``;

      if (l.roomNo == null) {
        roomNoContent = ``;
      } else if (l.roomNo != null) {
        roomNoContent = `${l.roomNo}`;
      }

      if (l.breakfast != 0) {
        breakfastContent = `<input type="checkbox" checked></input>`;
      } else if (l.breakfast == 0) {
        breakfastContent = `<input type="checkbox"></input>`;
      }

      if (l.resNo != null) {
        resNoContent = `<input type="checkbox" checked></input>`;
      } else if (l.resNo == null) {
        resNoContent = `<input type="checkbox"></input>`;
      }

      if (l.bookStatus == "체크인대기") {
        const today = new Date();
        const year = today.getFullYear().toString();
        const month = (today.getMonth() + 1).toString().padStart(2, "0");
        const day = today.getDate().toString().padStart(2, "0");
        const formattedDate = `${year}-${month}-${day}`;
        if (l.bookDate == formattedDate) {
          buttonContent = `
         <span class="dropdown">
            <button type="button" class="btn btn-primary dropdown-toggle checkInBtn" data-bs-toggle="dropdown">체크인</button>
            <ul class="dropdown-menu checkInDropDown">
              <li></li>
            </ul>
         </span>
         <button type="button" class="btn btn-primary bookCancel">예약취소 <input type="hidden" value="${l.bookTel}"></button>
          `;
        } else {
          buttonContent = `
        <button type="button" class="btn btn-primary dropdown-toggle checkInBtn" data-bs-toggle="dropdown" disabled>체크인</button>
           <ul class="dropdown-menu checkInDropDown">
              <li></li>
           </ul>
          </span>
          <button type="button" class="btn btn-primary bookCancel">예약취소 <input type="hidden" value="${l.bookTel}"></button>
        `;
        }
      } else if (l.bookStatus == "체크인완료") {
        buttonContent = `
        <button type="button" class="btn btn-danger checkOutBtn">체크아웃 <input type="hidden" value="${l.bookTel}"></button>
        <button type="button" class="btn btn-success changeBook">예약변경 <input type="hidden" value="${l.bookTel}"></button>
      `;
      } else if (l.bookStatus == "체크아웃완료") {
        buttonContent = ``;
      } else if (l.bookStatus == "예약취소") {
        buttonContent = ``;
      }

      const tableContent = `
    <tr>
    <td><a href="#" onclick="openPopup('/hotel/manager/memberDetail?name=${l.booker}')">${l.booker}</a></td>
    <td>${l.bookTel}</td>
    <td>${l.totalCount}</td>
    <td>${roomNoContent}</td>
    <td>${l.bookDate}</td>
    <td>${l.bookRoomGrade}</td>
    <td>${l.checkIn}</td>
    <td>${l.checkOut}</td>
    <td>${breakfastContent}</td>
    <td>${resNoContent}</td>
    <td>${l.bookStatus}</td>
    <td>${buttonContent}</td>
      `;
      bookListTable.append(tableContent);
    }
  }
}

function heightController(list) {
  var listAreaHeight = $("#listarea").height();
  var newPageHeight = listAreaHeight + list.length * 46;
  $("#listarea").height(newPageHeight);
}

$(document).ready(function () {
  $(document).on("click", "#searchBtn", async function () {
    let stayCheckBox = $("#stayCheckBox").is(":checked");
    let restaurantCheckBox = $("#restaurantCheckBox").is(":checked");
    let todayCheckBox = $("#todayCheckBox").is(":checked");
    const param = {
      isStay: stayCheckBox,
      isRestaurant: restaurantCheckBox,
      fromDate: $("#from").val(),
      toDate: $("#to").val(),
      todayCheckBox: todayCheckBox,
      roomNum: Number($("#searchRoomNumber").val()),
      name: $("#searchName").val(),
      listType: $(".dropdown-menu a.active").data("index"),
    };
    try {
      const list = await $.ajax({
        url: "/hotel/manager/bookSearch",
        method: "post",
        data: param,
      });

      searchData(list);
      heightController(list);
    } catch (err) {
      console.log(err);
    }
  });

  $(document).on("click", ".searchToggle a", async function () {
    $(".dropdown-menu a").removeClass("active");
    $(this).addClass("active");
    let text = $(this).text();
    $("#dropdownBtn").text(text);

    let stayCheckBox = $("#stayCheckBox").is(":checked");
    let restaurantCheckBox = $("#restaurantCheckBox").is(":checked");
    let todayCheckBox = $("#todayCheckBox").is(":checked");

    const param = {
      isStay: stayCheckBox,
      isRestaurant: restaurantCheckBox,
      fromDate: $("#from").val(),
      toDate: $("#to").val(),
      todayCheckBox: todayCheckBox,
      roomNum: Number($("#searchRoomNumber").val()),
      name: $("#searchName").val(),
      listType: $(".dropdown-menu a.active").data("index"),
    };
    try {
      const list = await $.ajax({
        url: "/hotel/manager/bookSearch",
        method: "post",
        data: param,
      });

      searchData(list);
    } catch (err) {
      console.log(err);
    }
  });

  $(document).on("click", "#blackBtn", async function () {
    var booker = $(this).find("input").val();
    try {
      const blackBtn = await $.ajax({
        url: "/hotel/manager/blackBtn?name=" + booker,
        method: "post",
      });
      alert("변경이 완료되었습니다");
      location.reload();
    } catch (err) {}
  });

  $(document).on("click", "#vipBtn", async function () {
    var booker = $(this).find("input").val();
    try {
      const blackBtn = await $.ajax({
        url: "/hotel/manager/vipBtn?name=" + booker,
        method: "post",
      });
      alert("변경이 완료되었습니다");
      location.reload();
    } catch (err) {}
  });

  $(document).on("click", "#ableBtn", async function () {
    var booker = $(this).find("input").val();
    try {
      const blackBtn = await $.ajax({
        url: "/hotel/manager/ableBtn?name=" + booker,
        method: "post",
      });
      alert("변경이 완료되었습니다");
      location.reload();
    } catch (err) {}
  });

  $(document).on("click", ".checkInDropDown a", async function (event) {
    const clickedMenuText = $(this).text();
    const [roomNo, roomStatus] = clickedMenuText.split("(");
    const parsedRoomStatus = roomStatus.replace(")", "");
    const bookerName = $("td:nth-child(2)", $(this).closest("tr")).text();
    if (parsedRoomStatus == "비어있음") {
      try {
        const searchCondition = await $.ajax({
          url:
            "/hotel/manager/checkIn?roomNo=" + roomNo + "&name=" + bookerName,
          method: "post",
        });
        alert("방이 배정되었습니다.");
        location.href = "/hotel/manager/bookList";
      } catch (err) {
        console.log(err);
      }
    } else {
      alert("이미 배정이 완료된 방입니다.");
    }
  });

  $(document).on("click", ".checkInBtn", async function () {
    //등급 불러오기
    const roomGrade = $(this).closest("tr").find("td:nth-child(6)").text();
    try {
      const roomList = await $.ajax({
        url: "/hotel/manager/checkBtn?roomGrade=" + roomGrade,
        method: "post",
      });
      const checkInDropDown = $(".checkInDropDown > li");
      checkInDropDown.empty();
      for (const r of roomList) {
        const dropdownMenu = `
        <li><a class="dropdown-item" href="#">${r.roomNo}(${r.roomStatus})</a></li>
          `;
        checkInDropDown.append(dropdownMenu);
      }
    } catch (err) {}
  });

  $(document).on("click", ".bookCancel", async function () {
    var booker = $(this).find("input").val();
    try {
      const searchCondition = await $.ajax({
        url: "/hotel/manager/bookCancel?bookTel=" + booker,
        method: "post",
      });
      location.href = "/hotel/manager/bookList";
    } catch (err) {
      console.log(err);
    }
  });

  $(document).on("click", ".checkOutBtn", async function () {
    const booker = $(this).find("input").val();
    const roomNo = $("td:nth-child(4)", $(this).closest("tr")).text();
    try {
      const searchCondition = await $.ajax({
        url: "/hotel/manager/checkOut?bookTel=" + booker + "&roomNo=" + roomNo,
        method: "post",
      });
      location.href = "/hotel/manager/bookList";
    } catch (err) {
      console.log(err);
    }
  });

  $(document).on("click", ".changeBook", async function () {
    const row = $(this).closest("tr");
    const breakfastChecked = row
      .find('td:nth-child(9) input[type="checkbox"]')
      .prop("checked");
    const resNoChecked = row
      .find('td:nth-child(10) input[type="checkbox"]')
      .prop("checked");

    var booktel = $(this).children("input").val();

    const param = {
      breakfast: breakfastChecked,
      dinner: resNoChecked,
      bookTel: booktel,
    };
    try {
      const searchCondition = await $.ajax({
        url: "/hotel/manager/changeBook",
        method: "post",
        data: param,
      });
      alert("예약이 변경되었습니다.");
      location.href = "/hotel/manager/bookList";
    } catch (err) {
      console.log(err);
    }
  });

  $(document).on("keyup", function (event) {
    // 엔터키가 눌리면
    if (event.keyCode === 13) {
      // 검색 버튼 클릭과 동일한 이벤트 발생
      $("#searchBtn").click();
    }
  });

  $(document).on("click", "#initBtn", function () {
    if ($("#stayCheckBox").is(":checked") == true) {
      $("#stayCheckBox").prop("checked", false);
    }
    if ($("#restaurantCheckBox").is(":checked") == true) {
      $("#restaurantCheckBox").prop("checked", false);
    }
    if ($("#todayCheckBox").is(":checked") == false) {
      $("#todayCheckBox").prop("checked", true);
    }
    if ($("#from").val() != "") {
      $("#from").val("");
    }
    if ($("#to").val() != "") {
      $("#to").val("");
    }
    if ($("#searchRoomNumber").val() != "") {
      $("#searchRoomNumber").val("");
    }
    if ($("#searchName").val() != "") {
      $("#searchName").val("");
    }

    var currentIndex = $(".dropdown-item.active").data("index");

    // 이전에 active 클래스가 지정된 요소에서 active 클래스를 제거함
    $(".dropdown-item.active").removeClass("active");

    // 현재 선택된 요소를 찾아서 active 클래스를 추가함
    if (currentIndex != 1) {
      var $selectedItem = $(".dropdown-item[data-index='1']");
      let text = $selectedItem.text();
      $("#dropdownBtn").text(text);
    } else {
      var $selectedItem = $(
        ".dropdown-item[data-index='" + currentIndex + "']"
      );
    }
    $("#from").attr("disabled", true);
    $("#to").attr("disabled", true);
    $selectedItem.addClass("active");
  });

  $(document).on("change", "#todayCheckBox", function () {
    if ($("#todayCheckBox").is(":checked") == false) {
      $("#from").attr("disabled", false);
    } else {
      $("#from").attr("disabled", true);
    }
  });
});
