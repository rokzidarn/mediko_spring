$( document ).ready(function(){
	
	$("#caretaker").hide();
	$( window  ).on('resize', function(){
		hideSidebar();
	});

	//WHEN PAGE LOADS

	$("#showSidebarButton").on('click', function(){
		showSidebar();
	});

	$("#hideSidebarButton").on('click', function(){
		hideSidebar();
	});
	
	$(document).on('change' , '#checkbox' , function(){

	    if(this.checked) {
	    	$("#caretaker").show();
	    }
	    else  {
	    	$("#caretaker").hide();
	    }
	});
	
	//SET CONTENT HEIGT!
	$(".content-container").css("min-height",$("#sidebarContent").height()+50);
});

function showSidebar(){
	$("#sidebar").removeClass("hidden-sm");
	$("#sidebar").removeClass("hidden-xs");
	$("#sidebar").addClass("sidebar-visible");
}

function hideSidebar(){
	$("#sidebar").addClass("hidden-sm");
	$("#sidebar").addClass("hidden-xs");
	$("#sidebar").removeClass("sidebar-visible");
}

function opencheckup(link){
	window.location.href = link;
}