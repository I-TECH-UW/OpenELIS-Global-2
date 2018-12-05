var autoCompId = ""; // the id for the autocomplete widget. This should be changed in scope and passed in with the constructor
var clearNonMatching = true;
var capitialize = false;
var resultCallBack = null; // the callback for onchange
(function( $) {
	$.widget( "ui.combobox", {
		_create: function( ) {
			var self = this,
				select = this.element.hide(),
				selected = select.children( ":selected" ),
				value = selected.val() ? selected.text() : "";
			var input = this.input = $( "<input>" )
				.insertAfter( select )
				.val( value )
				.attr('id', autoCompId)
				.autocomplete({
					delay: 0,
					minLength: 1,
                    // New functionality to increase speed - based on http://stackoverflow.com/questions/5073612/jquery-ui-autocomplete-combobox-very-slow-with-large-select-lists
                    source: function( request, response ) {
                    	console.log('source');
                        var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
                        var select_el = select.get(0); // get dom element
                        var rep = new Array(); // response array
                        var maxRepSize = 50; // maximum response size
                        // simple loop for the options
                        for (var i = 0; i < select_el.length; i++) {
                            var text = select_el.options[i].text;
                            if ( select_el.options[i].value && ( !request.term || matcher.test(text) ) )
                                // add element to result array
                                rep.push({
                                    label: text, // no more bold
                                    value: text,
                                    option: select_el.options[i]
                                });
                            if ( rep.length > maxRepSize ) {
                                rep.push({
                                    label: maxRepMsg,
                                    value: "maxRepSizeReached",
                                    option: ""
                                });
                                break;
                            }
                         }
                         // send response
                         response( rep );
                    },          
                    select: function( event, ui ) {
                    	console.log('select');
                        if ( ui.item.value == "maxRepSizeReached") {
                            return false;
                        } else {
                            ui.item.option.selected = true;
                            self._trigger( "selected", event, {
                                item: ui.item.option
                            });
                        }
                    },
                    focus: function( event, ui ) {
                    	console.log('focus');
                        if ( ui.item.value == "maxRepSizeReached") {
                            return false;
                        }
                    },
                    // End new functionality to increase speed
					change: function( event, ui ) {
						console.log('change');
						if( capitialize ){
							$(this).context.value = $(this).context.value.toUpperCase();
						}
						
						
						if ( !ui.item ) {
							var matcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( $(this).val() ) + "$", "i" ),
							valid = false;
							select.children( "option" ).each(function() {
								if ( $( this ).text().match( matcher ) ) {
									this.selected = valid = true;
									return false;
								}
							});
							

							if ( !valid && clearNonMatching) {
								// remove invalid value, as it didn't match anything
								$( this ).val( "" );
								select.val( "" );
								input.data( "ui-autocomplete" ).term = "";
								
								if( window.invalidLabID != undefined){
									alert(invalidLabID);
								}
								return false;
							}
							
						}
						
						if( resultCallBack ){ 
							resultCallBack($(this).val());
						}
					}
				})
				.addClass( "ui-widget ui-widget-content ui-autocomplete-custom" ).css({width: autoCompleteWidth, padding: '2px'});

			input.data( "ui-autocomplete" )._renderItem = function( ul, item ) {
				return $( "<li></li>" )
					.data( "ui-autocomplete-item", item )
					.append( "<a>" + item.label + "</a>" )
					.appendTo( ul );
			};
		},

		destroy: function() {
			this.input.remove();
			this.element.show();
			$.Widget.prototype.destroy.call( this );
		}
	});
})( jQuery );