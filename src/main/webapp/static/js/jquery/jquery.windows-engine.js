/**
 *  jQuery Windows Engine Plugin
 *@requires jQuery v1.2.6
 *  http://www.socialembedded.com/labs
 *
 *  Copyright (c)  Hernan Amiune (hernan.amiune.com)
 *  Dual licensed under the MIT and GPL licenses:
 *  http://www.opensource.org/licenses/mit-license.php
 *  http://www.gnu.org/licenses/gpl.html
 * 
 *  Version: 0.6
 */
 
function closeAjaxWindow(){
	jQuery('.window-container').fadeOut();
	jQuery('#bgtransparent').remove();
	jQuery('.window-content').html('');
}

var jqWindowsEngineZIndex = 100; 
(function(jQuery){ 

/**
 * @option string windowTitle, the tile to display on the window
 * @option HTML content, the content to display on the window
 * @option string ajaxURL, URL address to load the content, this has priority over content
 * @option int width, the initial width of the window
 * @option int height, the initial height of the window
 * @option int posx, the initial x position of the window
 * @option int posy, the initial y position of the window
 * @option function onDragBegin: onDragBegin callback function,
 * @option function onDragEnd: onDragEnd callback function,
 * @option function onResizeBegin: onResizeBegin callback function,
 * @option function onResizeEnd: onResizeEnd callback function,
 * @option function onAjaxContentLoaded: onAjaxContentLoaded callback function,
 * @option boolean statusBar, enable or disable the window status bar
 * @option boolean minimizeButton, enable or disable the window minimize button
 * @option HTML minimizeIcon, an html text to display as the minize icon
 * @option boolean maximizeButton, enable or disable the window maximize button
 * @option HTML maximizeIcon, an html text to display as the maximize icon
 * @option boolean closeButton, enable or disable the window close button
 * @option HTML closeIcon, an html text to display as the close icon
 * @option boolean draggable, enable or disable the window dragging
 * @option boolean resizeable, enable or disable the window resize button
 * @option HTML resizeIcon, an html text to display as the resize icon
 * @option string windowType, a string "normal", "video", or "iframe"
 *
 * @type jQuery
 *
 * @name jqWindowsEngine
 * @cat Plugins/Windows
 * @author Hernan Amiune (amiune@gmail.com)
 */ 
jQuery.fn.newWindow = function(options){

    var lastMouseX = 0;
    var lastMouseY = 0;

    var defaults = {
        windowTitle : "",
		content: "",
		ajaxURL: "",
        width : 200,
        height : 200,
        posx : 50,
        posy : 50,
		onDragBegin: null,
		onDragEnd: null,
		onResizeBegin: null,
		onResizeEnd: null,
		onAjaxContentLoaded: null,
        statusBar: true,
		minimizeButton: true,
		minimizeIcon: "-",
		maximizeButton: true,
		maximizeIcon: "O",
		closeButton: true,
		closeIcon: "X",
		draggable: true,
		resizeable: true,
		resizeIcon: "#",
		windowType: "standard"
    };
  
    var options = jQuery.extend(defaults, options);
    
    $windowContainer = jQuery('<div class="window-container"></div>');
    $windowTitleBar = jQuery('<div class="window-titleBar"></div>');        
    $windowMinimizeButton = jQuery('<div class="window-minimizeButton"></div>');
	$windowMaximizeButton = jQuery('<div class="window-maximizeButton"></div>');
	$windowCloseButton = jQuery('<div class="window-closeButton"></div>');
	$windowContent = jQuery('<div class="window-content"></div>');
	$windowStatusBar = jQuery('<div class="window-statusBar"></div>');
	$windowResizeIcon = jQuery('<div class="window-resizeIcon"></div>');
	
	
	if(options.windowType=="video" || options.windowType=="iframe")
	  $windowContent.css("overflow","hidden");
	
	var setFocus = function($obj){
//	    $obj.css("z-index",jqWindowsEngineZIndex++);
	}
	
	var resize = function($obj, width, height){
		width = parseInt(width);
		height = parseInt(height);

		$obj.attr("lastWidth",width).attr("lastHeight",height);

		width = width+"px";
		height = height+"px";
		
		$obj.css("width", width).css("height", height);
		
		if(options.windowType=="video"){
		  $obj.children(".window-content").children("embed").css("width", width).css("height", height);
		  $obj.children(".window-content").children("object").css("width", width).css("height", height);
		  $obj.children(".window-content").children().children("embed").css("width", width).css("height", height);
		  $obj.children(".window-content").children().children("object").css("width", width).css("height", height);
		}else if(options.windowType=="iframe"){		
	      $obj.children(".window-content").children("iframe").css("width", width).css("height", height);
		}
	}
	
	var move = function($obj, x, y){
	    
		x = parseInt(x);
		y = parseInt(y);
		
		$obj.attr("lastX",x)
		    .attr("lastY",y);
		
        x = x+"px";
		y = y+"px";		
			
		$obj.css("left", x)
	        .css("top", y);
	}

	var dragging = function(e, $obj){
	    if(options.draggable){
		e = e ? e : window.event;
	    var newx = parseInt($obj.css("left")) + (e.clientX - lastMouseX);
        var newy = parseInt($obj.css("top")) + (e.clientY - lastMouseY);
	    lastMouseX = e.clientX;
	    lastMouseY = e.clientY;
	  
	    move($obj,newx,newy);
		}
	};
	
	var resizing = function(e, $obj){
	  
	  e = e ? e : window.event;
	  var w = parseInt($obj.css("width"));
	  var h = parseInt($obj.css("height"));
	  w = w<100 ? 100 : w;
	  h = h<50 ? 50 : h;
	  var neww = w + (e.clientX - lastMouseX);
      var newh = h + (e.clientY - lastMouseY);
	  lastMouseX = e.clientX;
	  lastMouseY = e.clientY;
	  
	  resize($obj, neww, newh);
	};
	
	$windowTitleBar.bind('mousedown', function(e){
	    $obj = jQuery(e.target).parent();
		setFocus($obj);
		
	    if($obj.attr("state") == "normal"){
	        e = e ? e : window.event;
		    lastMouseX = e.clientX;
		    lastMouseY = e.clientY;
		    
		    jQuery(document).bind('mousemove', function(e){
			    dragging(e, $obj);
		    });
		    
			
		    jQuery(document).bind('mouseup', function(e){
				if(options.onDragEnd != null)options.onDragEnd();
				jQuery(document).unbind('mousemove');
				jQuery(document).unbind('mouseup');
		    });
			
			if(options.onDragBegin != null)options.onDragBegin();
	    }
    });
	
	$windowResizeIcon.bind('mousedown', function(e){
		$obj = jQuery(e.target).parent().parent();
		setFocus($obj);
		
		if($obj.attr("state") == "normal"){
			e = e ? e : window.event;
			lastMouseX = e.clientX;
			lastMouseY = e.clientY;

			jQuery(document).bind('mousemove', function(e){
				resizing(e, $obj);
			});

			jQuery(document).bind('mouseup', function(e){
				if(options.onResizeEnd != null)options.onResizeEnd();
				jQuery(document).unbind('mousemove');
				jQuery(document).unbind('mouseup');
			});
			
			if(options.onResizeBegin != null)options.onResizeBegin();
		}
	  
    });
	
	$windowMinimizeButton.bind('click', function(e){
	    $obj = jQuery(e.target).parent().parent();
		setFocus($obj);
		if($obj.attr("state") == "normal"){
	        jQuery(e.target).parent().next().slideToggle("slow");
		}
    });
	
	$windowMaximizeButton.bind('click', function(e){
	  $obj = jQuery(e.target).parent().parent();
	  setFocus($obj);
	  if($obj.attr("state") == "normal"){
		  if(options.windowType=="standard"){
		    $obj.animate({
		      top: "5px",
			  left: "5px",
			  width: jQuery(window).width()-15,
			  height: jQuery(window).height()-45
		    },"slow");
		  }
		  else{
			tmpx = $obj.attr("lastX");
		    tmpy = $obj.attr("lastY");
			tmpwidth = $obj.attr("lastWidth");
		    tmpheight = $obj.attr("lastHeight");
			move($obj, 5, 5);
		    resize($obj,jQuery(window).width()-15,jQuery(window).height()-45);
			$obj.attr("lastX",tmpx);
		    $obj.attr("lastY",tmpy);
			$obj.attr("lastWidth",tmpwidth);
		    $obj.attr("lastHeight",tmpheight);
		  }
		  $obj.attr("state","maximized")
	  }
	  else if($obj.attr("state") == "maximized"){
	    if(options.windowType=="standard"){ 
		  $obj.animate({
		    top: $obj.attr("lastY"),
			left: $obj.attr("lastX"),
			width: $obj.attr("lastWidth"),
			height: $obj.attr("lastHeight")
		  },"slow");
		}
		else{
		  resize($obj,$obj.attr("lastWidth"),$obj.attr("lastHeight"));
		  move($obj,$obj.attr("lastX"),$obj.attr("lastY"));
		}
		$obj.attr("state","normal")
	  }
	  
    });
	
	$windowCloseButton.bind('click', function(e){
	  jQuery('.window-container').fadeOut();
	  jQuery('#bgtransparent').remove();
	  jQuery('.window-content').html('');
    });
	
	$windowContent.click(function(e){
      setFocus(jQuery(e.target).parent());
    });
	$windowStatusBar.click(function(e){
      setFocus(jQuery(e.target).parent());
    });
	
	move($windowContainer,options.posx,options.posy);
	resize($windowContainer,options.width,options.height);
	$windowContainer.attr("state","normal");
	$windowTitleBar.append(options.windowTitle);
	
	if(options.minimizeButton)
	    $windowTitleBar.append($windowMinimizeButton)
	if(options.maximizeButton)
	    $windowTitleBar.append($windowMaximizeButton)
	if(options.closeButton)  
	    $windowTitleBar.append($windowCloseButton);
	
	if(options.resizeable)
	    $windowStatusBar.append($windowResizeIcon);
	
	$windowContainer.append($windowTitleBar)
	$windowContainer.append($windowContent)
	
	if(options.statusBar)
	    $windowContainer.append($windowStatusBar);
	
	$windowContainer.css("display","none");
	
	return this.each(function(index) {
		var $this = jQuery(this);      	
		
		$windowMinimizeButton.html(options.minimizeIcon);
		$windowMaximizeButton.html(options.maximizeIcon);
		$windowCloseButton.html(options.closeIcon);
		$windowResizeIcon.html(options.resizeIcon);
		
		$this['window-engine'] = $windowContainer;
		jQuery('body').append($windowContainer);

		$this.click(function(event){
			var obj = event.target ? event.target : event.srcElement;
			var idLink = jQuery(obj).attr('id');
			if(idLink.split('_').length >= 3){
				jQuery(obj).attr('selectLink',idLink.split('_')[2]);
			}

			event.preventDefault();
			$window = $this['window-engine'];

			var wscr = jQuery(window).width();
			var hscr = jQuery(window).height();
			var bgdiv = jQuery('<div>').attr({className: 'bgtransparent',id: 'bgtransparent'});
			bgdiv.css("width", wscr);
			bgdiv.css("height", hscr);
			jQuery('body').append(bgdiv);
			
			var wcnt = jQuery($window).width();
			var hcnt = jQuery($window).height();
			var mleft = ((wscr-wcnt)/2) + jQuery(window).scrollLeft();
			var mtop = (hscr-hcnt)/2 + jQuery(window).scrollTop();
			jQuery($window).css("left", mleft);
			jQuery($window).css("top", mtop);
			
			if(options.ajaxURL != ""){
				 jQuery.ajax({
				   type: "GET",
				   url: eval(options.ajaxURL).toString(),
				   dataType: "html",
				   success: function(data){
					 $window.children(".window-content").html(data);
					 jQuery(".pagebanner").after("<br><br>");
					 if(options.onAjaxContentLoaded != null) options.onAjaxContentLoaded(); 
				   }
				 });
		    
			}
			else $window.children(".window-content").html(options.content);
			if(!options.draggable)
			    $window.children(".window-titleBar").css("cursor","default");
			setFocus($window);
            $window.fadeIn();			
		});
	});

  
}})(jQuery);
