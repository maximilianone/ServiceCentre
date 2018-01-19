$(document).ready(function(){
$( ".language" ).on( "click", function( event ) {
			var currentId = $(this).attr('id');

			if(currentId == "navUkr") {
				spanNavSel.text("UKR");
			} else if (currentId == "navEng") {
				imgNavSel.attr("src",engImgLink);
				spanNavSel.text("ENG");
			}

			if(currentId == "btnUkr") {
				spanBtnSel.text("UKR");
			} else if (currentId == "btnEng") {
				spanBtnSel.text("ENG");
			}
		});
}