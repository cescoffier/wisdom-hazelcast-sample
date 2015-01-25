
(function ($) {
	$.extend({
		gracefulWebSocket: function (url, options) {
			// create a new websocket or fallback
			var ws = new WebSocket(url);
	 		$(window).unload(function () { ws.close(); ws = null });
			return ws;
		}
	});

})(jQuery);
