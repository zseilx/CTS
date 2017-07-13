	/* modal창 스크립트 */
	var modal = document.getElementById('Mymodal');
	var tileModal = document.getElementById('tileModal');
	var listModal = document.getElementById('listModal');
	var btn = document.getElementById('MyBtn');
	var tileBtn = document.getElementById('tileBtn');
	var cancel = document.getElementsByClassName("btn btn-default");
	
	
	btn.onclick = function() {
		modal.style.display = "block";
	}

	tileBtn.onclick = function() {
		tileModal.style.display = "block";
	}
	
	
	
	cancel[0].onclick = function() {
		modal.style.display = "none";
		//alert('hii');
	}
	
	cancel[1].onclick = function() {
		tileModal.style.display = "none";
		//alert('hii');
	}
	
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}else if(event.target == tileModal){
			tileModal.style.display = "none";
		}else if(event.target == listModal){
			listModal.style.display = "none";
		}
	}
	
	$("#drawingSave").on("click", function() {
		$("form").attr("action", "shop_RegisterForm");
		$("form").attr("method", "post");
		$("form").submit();
		//alert("등록성공");
	});
	
	$("#tileSave").on("click", function() {
		$("form").attr("action", "shop_Register");
		$("form").attr("method", "get");
		$("form").submit();
		//alert("등록성공");
	});
	
	