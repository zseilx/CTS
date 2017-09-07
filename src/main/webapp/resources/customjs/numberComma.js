/**
 * 
 */

function thousandSeparatorCommas ( number ){ 

	 var string = number + ""; // 숫자일 경우, 문자로 바꾸기. 

	 string = string.replace( /^\s+|\s+$|,|[^+-\.\d]/g, "" ) // ±기호, 소수점, 숫자가 아닌 부분은 지우기. 

	 var regExp = /([+-]?\d+)(\d{3})(\.\d+)?/; // 필요한 정규식. 

	 while ( regExp.test( string ) ) string = string.replace( regExp, "$1" + "," + "$2" + "$3" ); // 쉼표 삽입. 

	 return string; 
} 
