/* 유효성 체크 함수
 * chkData(유효성 체크 대상의 선택자, 메시지 내용)
 */
 function chkData(item, msg) {
	if ($(item).val().replace(/\s/g, "") == "") {
		alert(msg + " 입력해 주세요.");
		$(item).val("");
		$(item).focus();
		return false;
	} else {
		return true;
	}
}


/* dataCheck(유효성 체크 대상, 출력 영역, 메시지 내용) */
function dataCheck(item, out, msg) {
	if ($(item).val().replace(/\s/g, "") == "") {
		$(out).html(msg + " 입력해 주세요.");
		$(item).val("");
		return false;
	} else {
		return true;
	}
}

/* formCheck(유효성 체크 대상, 출력 영역, 메시지 내용) */
function formCheck(main, item, msg) {
	if (main.val().replace(/\s/g, "") == "") {
		item.html(msg + " 입력해 주세요.");
		main.val("");
		return false;
	} else {
		return true;
	}
}
