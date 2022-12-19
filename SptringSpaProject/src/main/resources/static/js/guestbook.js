/**
 * 
 */
 (gb =function(){
	$('.btnSearch').on('click',function(){
		let frm =$('.frm')[0];
		frm.nowPage.value=1;
		let param = $(frm).serialize();
		$('#section').load('/guestbook/guestbook_select',param);
	});
	
	$('.btnPrev').on('click',function(){
		let frm =$('.frm')[0];
		frm.nowPage.value= Number(frm.nowPage.value)-1;
		let param = $(frm).serialize();
		$('#section').load('/guestbook/guestbook_select',param);
	});
	
	
	$('.btnNext').on('click',function(){
		let frm =$('.frm')[0];
		frm.nowPage.value= Number(frm.nowPage.value)+1;
		console.log(frm.nowPage.value);
		let param = $(frm).serialize();
		$('#section').load('/guestbook/guestbook_select',param);
	});
	
	$('.btninsert').on('click',function(){
		let frm = $('.frm_guestbook_insert')[0];  //insert의 폼을가지고 post로던진다
		let param= $(frm).serialize();
		$.post('/guestbook/guestbook_insert',param,function(){ //성공적으로 데이터가 들어왔을 때 
			frm =$('.frm')[0];				//검색창의 정보를 다시만들어서 
		 	param = $(frm).serialize();
		$('#section').load('/guestbook/guestbook_select',param); //셀렉트 영역에 인서트 결과를 갱신해줘 
		})
	});
	gb.modifyView = function(frm){
		let div = frm.querySelector('.updateZone');
		let bz = frm.querySelector('.btnZone');
		div.style.visibility='visible';
		frm.doc.readOnly=false;
		$(bz).css('display','none');
		
	}
	gb.modifyCancel = function(frm){
		let div = frm.querySelector('.updateZone');
		let bz = frm.querySelector('.btnZone');
		div.style.visibility='hidden';
		
		frm.doc.readOnly=true;
		$(bz).css('display','block');
		
	}
	/* modal  box ------*/
	$('#btnClose').on('click', function(){
		$('#modal').css('display' , 'none');
	});
	var delForm;
	gb.modalView = function(frm){
	 	delForm = frm;
	 	$('#modal').css('display' , 'block');
	}
	/* 방명록삭제 */
	$('#btnCheck').on('click',function(){
		
		var frm = $('.frm_guestbook')[0]; 
		frm.pwd.value = $('#pwd').val();
		let param= $(frm).serialize();
		$.post('/guestbook/guestbook_delete',param,function(){  
			frm =$('.frm')[0];				 
		 	param = $(frm).serialize();
		$('#section').load('/guestbook/guestbook_select',param);
		})
	});
	/* 방명록 수정 */
	gb.update = function(frm){
		let param = $(frm).serialize();
		
		$.post('/guestbook/guestbook_update',param,function(msg){
			if(msg != '') alert(msg);
			frm =$('.frm')[0];				
		 	param = $(frm).serialize();
		$('#section').load('/guestbook/guestbook_select',param);
		});
	};
	
	
	
	
	
	
})();