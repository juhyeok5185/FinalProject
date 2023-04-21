function darkmode() {
	let element = document.body;
	let checkbox = document.getElementById("dark-checkbox");

	if (checkbox.checked) {
		element.classList.add("dark-mode");
		localStorage.setItem("dark-mode-enabled", "true");
	} else {
		element.classList.remove("dark-mode");
		localStorage.setItem("dark-mode-enabled", "false");
	}
}

function chackDarkMode() {
	let $body = $('body');
	let $checkbox = $('#dark-checkbox');
	let darkModeEnabled = localStorage.getItem('dark-mode-enabled');
	let $logoImg = $('#logoImg');

	if (darkModeEnabled === 'true') {
		$body.addClass('dark-mode');
		$logoImg.attr('src', "/img/logo_darkmode.png");
		$checkbox.prop('checked', true);

		$('#img1').attr('src', '/img/res3.jpg');
		$('#img2').attr('src', '/img/hotel15.jpg');
		$('#img3').attr('src', '/img/pool2.jpg');

	} else {
		$body.removeClass('dark-mode');
		$logoImg.attr('src', "/img/logo.png");
		$checkbox.prop('checked', false);


		$('#img1').attr('src', '/img/pool6.jpg');
		$('#img2').attr('src', '/img/pool4.jpg');
		$('#img3').attr('src', '/img/pool.jpg');
	}

	$checkbox.on('change', function() {
		if ($checkbox.prop('checked')) {
			$logoImg.attr('src', "/img/logo_darkmode.png");
			$body.addClass('dark-mode');
			localStorage.setItem('dark-mode-enabled', 'true');
			$('#img1').attr('src', '/img/res3.jpg');
			$('#img2').attr('src', '/img/hotel15.jpg');
			$('#img3').attr('src', '/img/pool2.jpg');
		} else {
			$body.removeClass('dark-mode');
			$logoImg.attr('src', "/img/logo.png");
			localStorage.setItem('dark-mode-enabled', 'false');
			$('#img1').attr('src', '/img/pool6.jpg');
			$('#img2').attr('src', '/img/pool4.jpg');
			$('#img3').attr('src', '/img/pool.jpg');
		}
	});
}

function googleTranslateElementInit() {
	new google.translate.TranslateElement({
		pageLanguage: 'ko',
		includedLanguages: 'ko,zh-CN,zh-TW,ja,vi,th,tl,km,my,mn,ru,en,fr,ar',
		layout: google.translate.TranslateElement.InlineLayout.SIMPLE,
		autoDisplay: false
	}, 'google_translate_element');
}


$(document).ready(function() {
	chackDarkMode();

	//동현
	$('.bookbtn').click(function() {
		$('#booknewdim').attr('style', 'display:block;');
		$('#bookBox').attr('style', 'display:block');
		$('html').attr('style', 'overflow: hidden');
	});
	$('#bookbtn2').click(function() {
		$('#booknewdim').attr('style', 'display:none;');
		$('#bookBox').attr('style', 'display:none');
		$('html').attr('style', 'overflow:none');
	});
	$('#bookbedbox').click(function(){
		location.href='/hotel/client/roombook';
		
	})
	$('#bookrestbox').click(function(){
		location.href='/hotel/client/dinnerbook';
	})


	$('#dark-checkbox').click(function() {
		if ($(this).is(':checked')) {
			$('#img3').attr('src', '/img/pool2.jpg');
			$('#img2').attr('src', '/img/night2.jpg');
			$('#img1').attr('src', '/img/night.jpg');
		} else {
			$('#img1').attr('src', '/img/pool.jpg');
			$('#img3').attr('src', '/img/lobby.jpg');
			$('#img2').attr('src', '/img/room.jpg');
		}
	});


	//주혁
	let dateFormat = "mm/dd/yy",
      from = $( "#from" )
        .datepicker({
          defaultDate: "+1w",
          changeMonth: true,
          numberOfMonths: 2,
        })
        .on( "change", function() {
          to.datepicker( "option", "minDate", getDate( this ) );
          const $to = $('#to');
          $to.prop("disabled" , false);
        }),
      to = $( "#to" ).datepicker({
        defaultDate: "+1w",
        changeMonth: true,
        numberOfMonths: 2
      })
      .on( "change", function() {
        from.datepicker( "option", "maxDate", getDate( this ) );
        const splitFrom = from.val().split('/'); 
        const fromYear = splitFrom[2];
        const fromMonth = splitFrom[0] - 1; 
        const fromDay = splitFrom[1];
        const intFrom = new Date(fromYear, fromMonth, fromDay);
        const splitTo = to.val().split('/');
        const toYear = splitTo[2];
        const toMonth = splitTo[0] - 1;
        const toDay = splitTo[1];
        const intTo = new Date(toYear,toMonth,toDay);
        const diffInMilliseconds = intFrom - intTo; 
        const diffInSeconds = diffInMilliseconds / 1000; 
        const diffInMinutes = diffInSeconds / 60; 
        const diffInHours = diffInMinutes / 60; 
        const diffInDays = diffInHours / 24; 
        let night = -diffInDays;

        $('.night').text(night + "박");
      });
 

	function getDate(element) {
		let date;
		try {
			date = $.datepicker.parseDate(dateFormat, element.value);
		} catch (error) {
			date = null;
		}
		return date;
	}
})


