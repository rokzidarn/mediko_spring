$( document ).ready(function(){
	
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