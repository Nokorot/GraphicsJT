/**
 * @license Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.plugins.add( 'wblink', {
	icons: 'link',
	onLoad: function() {
		// Add the CSS styles for anchor placeholders.
		var baseStyle = 'background:url(' + CKEDITOR.getUrl( this.path + 'images/anchor.png' ) + ') no-repeat %1 center;' +
			'border:1px dotted #00f;';

		var template = '.%2 a.cke_anchor,' +
			'.%2 a.cke_anchor_empty' +
			',.cke_editable.%2 a[name]' +
			',.cke_editable.%2 a[data-cke-saved-name]' +
			'{' +
				baseStyle +
				'padding-%1:18px;' +
				// Show the arrow cursor for the anchor image (FF at least).
				'cursor:auto;' +
			'}' +
			( CKEDITOR.env.ie ? ( 'a.cke_anchor_empty' +
			'{' +
				// Make empty anchor selectable on IE.
				'display:inline-block;' +
			'}'
			) : '' ) +
			'.%2 img.cke_anchor' +
			'{' +
				baseStyle +
				'width:16px;' +
				'min-height:15px;' +
				// The default line-height on IE.
				'height:1.15em;' +
				// Opera works better with "middle" (even if not perfect)
				'vertical-align:' + ( CKEDITOR.env.opera ? 'middle' : 'text-bottom' ) + ';' +
			'}';

		// Styles with contents direction awareness.
		function cssWithDir( dir ) {
			return template.replace( /%1/g, dir == 'rtl' ? 'right' : 'left' ).replace( /%2/g, 'cke_contents_' + dir );
		}

		CKEDITOR.addCss( cssWithDir( 'ltr' ) + cssWithDir( 'rtl' ) );
	},

	init: function( editor ) {
		// Add the link and unlink buttons.
		var dialog = null;
		var element = null;
		var range = null;
		var selection = null;
		var data = {};
		var urlControl = null;
		editor.addCommand('anchor', new CKEDITOR.addAnchorCommand());
		//editor.addCommand('anchor', new CKEDITOR.removeAnchorCommand());
		editor.addCommand('unlink', new CKEDITOR.unlinkCommand());
		editor.addCommand('wblink', {
            exec : function( editor ) {
            	//gather data;
            	selection = editor.getSelection();
            	if ( ( element = CKEDITOR.plugins.wblink.getSelectedLink( editor ) ) && element.hasAttribute( 'href' ) )
    				selection.selectElement( element );
            	if ( element ) {
            		data.type = element.data('type');
					if (!data.type) data.type = 'url';
            		data.url = element.data('url');
					if (!data.url) data.url = element.getAttribute("href");
            		data.target = element.data('target');
					if (!data.target) data.target = element.getAttribute("target");
    				data.title = element.data('title');
					if (!data.title) data.title = element.getAttribute("title");
					data.menuUID = element.data('menuUID');
					if (!data.menuUID) data.menuUID = null;
    				
    				if (data.target === 'true')
    					data.target = true;
    				else if (data.target === 'false')
    					data.target = false; 
    			}
            	else {
    				range = selection.getRanges( 1 )[ 0 ];
            		data = {};
            	}
				
				if (!urlControl) {
					urlControl = new WB_URLControl();
				}
				urlControl.setValue(data);
				urlControl.__onClick(function() {
					var attributes = {};
					var removeAttributes = ["href", "data-cke-saved-href"];
					var info = this.getValue();
					if (!info.type || !info.url) {
						return false;
					}
					
					if (info.target) attributes.target = this.getUrlTarget(); else removeAttributes.push("target");
					if (info.title) attributes.title = info.title; else removeAttributes.push("title");
					attributes.href = this.getUrl();

					for (var key in info) {
						if (!info[key]) {
							removeAttributes.push("data-" + key);
							continue;
						}
						attributes["data-" + key] = info[key];
					};
					
					if ( !element ) {
//		    				var range = selection.getRanges( 1 )[ 0 ];

						if (range) {
							// Use link URL as text with a collapsed cursor.
							if ( range.collapsed ) {
								var text = new CKEDITOR.dom.text( info.url, editor.document );
								range.insertNode( text );
								range.selectNodeContents( text );
							}

							// Apply style.
							var style = new CKEDITOR.style({ element: 'a', attributes: attributes } );
							style.type = CKEDITOR.STYLE_INLINE; // need to override... dunno why.
							style.applyToRange( range );
							range.select();
						}
					}
					else {
						element.removeAttributes(removeAttributes);
						element.setAttributes(attributes);

						selection.selectElement( element );
						delete element;
					}
				});
            },
			allowedContent: 'a[href,title,target,class]{*}(*)'
        });

		//editor.setKeystroke( CKEDITOR.CTRL + 76 /*L*/, 'wblink' );
		//editor.setKeystroke( CKEDITOR.CTRL + 75 /*K*/, 'wblink' ); // to override native IE dialog

		if ( editor.ui.addButton ) {
			editor.ui.addButton( 'WBLink', {
				label: editor.lang.link.toolbar,
				command: 'wblink',
				icon: 'link'
			});
			editor.ui.addButton( 'Unlink', {
				label: editor.lang.link.unlink,
				command: 'unlink',
				toolbar: 'links,20'
			});
			editor.ui.addButton( 'Anchor', {
				label: editor.lang.link.anchor.toolbar,
				command: 'anchor',
				toolbar: 'links,30'
			});
		}

		CKEDITOR.dialog.add( 'wblink', this.path + 'dialogs/link.js' );
		CKEDITOR.dialog.add( 'anchor', this.path + 'dialogs/anchor.js' );

		editor.on( 'doubleclick', function( evt ) {
//			evt.stop();
			
			var element = CKEDITOR.plugins.wblink.getSelectedLink( editor ) || evt.data.element;

			if ( !element.isReadOnly() ) {
				if ( element.is( 'a' ) ) {
//					evt.data.dialog = ( element.getAttribute( 'name' ) && ( !element.getAttribute( 'href' ) || !element.getChildCount() ) ) ? 'anchor' : 'link';
					editor.getSelection().selectElement( element );
					editor.execCommand('wblink');
				} /*else if ( CKEDITOR.plugins.wblink.tryRestoreFakeAnchor( editor, element ) )
					evt.data.dialog = 'anchor';*/
			}
		});

		// If the "menu" plugin is loaded, register the menu items.
		if ( editor.addMenuItems ) {
			editor.addMenuItems({
				anchor: {
					label: editor.lang.link.anchor.menu,
					command: 'anchor',
					group: 'anchor',
					order: 1
				},

				removeAnchor: {
					label: editor.lang.link.anchor.remove,
					command: 'removeAnchor',
					group: 'anchor',
					order: 5
				},

				link: {
					label: editor.lang.link.menu,
					command: 'wblink',
					group: 'link',
					order: 1
				},

				unlink: {
					label: editor.lang.link.unlink,
					command: 'unlink',
					group: 'link',
					order: 5
				}
			});
		}

		// If the "contextmenu" plugin is loaded, register the listeners.
		if ( editor.contextMenu ) {
			editor.contextMenu.addListener( function( element, selection ) {
				if ( !element || element.isReadOnly() )
					return null;

				var anchor = CKEDITOR.plugins.wblink.tryRestoreFakeAnchor( editor, element );

				if ( !anchor && !( anchor = CKEDITOR.plugins.wblink.getSelectedLink( editor ) ) )
					return null;

				var menu = {};

				if ( anchor.getAttribute( 'href' ) && anchor.getChildCount() ) {
					menu = { link: CKEDITOR.TRISTATE_OFF, unlink: CKEDITOR.TRISTATE_OFF };
				}

				
				/*if ( anchor && anchor.hasAttribute( 'name' ) )
					menu.anchor = menu.removeAnchor = CKEDITOR.TRISTATE_OFF;*/

				return menu;
			});
		}
	},

	afterInit: function( editor ) {
		// Register a filter to displaying placeholders after mode change.

		var dataProcessor = editor.dataProcessor,
			dataFilter = dataProcessor && dataProcessor.dataFilter,
			htmlFilter = dataProcessor && dataProcessor.htmlFilter,
			pathFilters = editor._.elementsPath && editor._.elementsPath.filters;

		if ( dataFilter ) {
			dataFilter.addRules({
				elements: {
					a: function( element ) {
						var attributes = element.attributes;
						if ( !attributes.name )
							return null;

						var isEmpty = !element.children.length;

						if ( CKEDITOR.plugins.wblink.synAnchorSelector ) {
							// IE needs a specific class name to be applied
							// to the anchors, for appropriate styling.
							var ieClass = isEmpty ? 'cke_anchor_empty' : 'cke_anchor';
							var cls = attributes[ 'class' ];
							if ( attributes.name && ( !cls || cls.indexOf( ieClass ) < 0 ) )
								attributes[ 'class' ] = ( cls || '' ) + ' ' + ieClass;

							if ( isEmpty && CKEDITOR.plugins.wblink.emptyAnchorFix ) {
								attributes.contenteditable = 'false';
								attributes[ 'data-cke-editable' ] = 1;
							}
						} else if ( CKEDITOR.plugins.wblink.fakeAnchor && isEmpty )
							return editor.createFakeParserElement( element, 'cke_anchor', 'anchor' );

						return null;
					}
				}
			});
		}

		if ( CKEDITOR.plugins.wblink.emptyAnchorFix && htmlFilter ) {
			htmlFilter.addRules({
				elements: {
					a: function( element ) {
						delete element.attributes.contenteditable;
					}
				}
			});
		}

		if ( pathFilters ) {
			pathFilters.push( function( element, name ) {
				if ( name == 'a' ) {
					if ( CKEDITOR.plugins.wblink.tryRestoreFakeAnchor( editor, element ) || ( element.getAttribute( 'name' ) && ( !element.getAttribute( 'href' ) || !element.getChildCount() ) ) ) {
						return 'anchor';
					}
				}
			});
		}
	}
});

/**
 * Set of link plugin's helpers.
 *
 * @class
 * @singleton
 */
CKEDITOR.plugins.wblink = {
	/**
	 * Get the surrounding link element of current selection.
	 *
	 *		CKEDITOR.plugins.link.getSelectedLink( editor );
	 *
	 *		// The following selection will all return the link element.
	 *
	 *		<a href="#">li^nk</a>
	 *		<a href="#">[link]</a>
	 *		text[<a href="#">link]</a>
	 *		<a href="#">li[nk</a>]
	 *		[<b><a href="#">li]nk</a></b>]
	 *		[<a href="#"><b>li]nk</b></a>
	 *
	 * @since 3.2.1
	 * @param {CKEDITOR.editor} editor
	 */
	getSelectedLink: function( editor ) {
		var selection = editor.getSelection();
		var selectedElement = selection.getSelectedElement();
		if ( selectedElement && selectedElement.is( 'a' ) )
			return selectedElement;

		var range = selection.getRanges( true )[ 0 ];

		if ( range ) {
			range.shrink( CKEDITOR.SHRINK_TEXT );
			return editor.elementPath( range.getCommonAncestor() ).contains( 'a', 1 );
		}
		return null;
	},

	/**
	 * Opera and WebKit don't make it possible to select empty anchors. Fake
	 * elements must be used for them.
	 *
	 * @readonly
	 * @property {Boolean}
	 */
	fakeAnchor: CKEDITOR.env.opera || CKEDITOR.env.webkit,

	/**
	 * For browsers that don't support CSS3 `a[name]:empty()`, note IE9 is included because of #7783.
	 *
	 * @readonly
	 * @property {Boolean}
	 */
	synAnchorSelector: CKEDITOR.env.ie,

	/**
	 * For browsers that have editing issue with empty anchor.
	 *
	 * @readonly
	 * @property {Boolean}
	 */
	emptyAnchorFix: CKEDITOR.env.ie && CKEDITOR.env.version < 8,

	/**
	 * @param {CKEDITOR.editor} editor
	 * @param {CKEDITOR.dom.element} element
	 * @todo
	 */
	tryRestoreFakeAnchor: function( editor, element ) {
		if ( element && element.data( 'cke-real-element-type' ) && element.data( 'cke-real-element-type' ) == 'anchor' ) {
			var link = editor.restoreRealElement( element );
			if ( link.data( 'cke-saved-name' ) )
				return link;
		}
	}
};

//TODO Much probably there's no need to expose these as public objects.

CKEDITOR.unlinkCommand = function() {};
CKEDITOR.unlinkCommand.prototype = {
	exec: function( editor ) {
		var style = new CKEDITOR.style( { element:'a',type:CKEDITOR.STYLE_INLINE,alwaysRemoveElement:1 } );
		editor.removeStyle( style );
	},

	refresh: function( editor, path ) {
		// Despite our initial hope, document.queryCommandEnabled() does not work
		// for this in Firefox. So we must detect the state by element paths.

		var element = path.lastElement && path.lastElement.getAscendant( 'a', true );

		if ( element && element.getName() == 'a' && element.getAttribute( 'href' ) && element.getChildCount() )
			this.setState( CKEDITOR.TRISTATE_OFF );
		else
			this.setState( CKEDITOR.TRISTATE_DISABLED );
	},

	contextSensitive: 1,
	startDisabled: 1
};

CKEDITOR.addAnchorCommand = function() {};
CKEDITOR.addAnchorCommand.prototype = {
	_mydlg: null,
	exec: function(editor_param) {
		var editor = editor_param;
		var sel = editor.getSelection();
		if (!sel) { return; }
		var elem = sel.getSelectedElement(), range = null;
		if (!elem) {
			range = sel.getRanges(true)[0];
			if (range) {
				range.shrink(CKEDITOR.SHRINK_TEXT);
				elem = editor.elementPath(range.getCommonAncestor()).contains("a", 1);
			}
		}
		var setAnchor = function(name, elem, range) {
			editor.focus();
			if (elem && elem.is("a")) {
				if (name) {
					elem.setAttribute("name", name);
					sel.selectElement(elem);
				} else {
					elem.removeAttribute("name");
					if (!elem.getAttribute("href")) {
						var style = new CKEDITOR.style({ element: "a", type: CKEDITOR.STYLE_INLINE, alwaysRemoveElement: true });
						editor.removeStyle(style);
					}
				}
			} else if (name) {
				var style = new CKEDITOR.style({ element: "a", attributes: { name: name } });
				style.type = CKEDITOR.STYLE_INLINE;
				style.applyToRange(range);
				range.select();
			}
		};
		var thisClass = this;
		var fields = null;
		if (!this._mydlg) {
			this._mydlg = new WB_Dialog(__("Change Anchor"));
			this._mydlg.setSize(250, null);
			this._mydlg.setNoScroll();
			fields = this._mydlg.fields;

			var td = $('<div></div>');
			this._mydlg.setContent(td);

			td.append('<label>' + __("Anchor name") + '</label>');
			td.append((fields.anchorName = new WB_TextFieldControl()).elem);
			
			fields.remBtn = this._mydlg.addButton(__("Remove"), function() {
				thisClass._mydlg.hide();
				setAnchor("", fields.myElem, fields.myRange);
			}, false, "btn-danger");
			this._mydlg.addButton(__("Cancel"));
			this._mydlg.addButton(__("Apply"), function() {
				thisClass._mydlg.hide();
				setAnchor(fields.anchorName.getValue(), fields.myElem, fields.myRange);
			}, true);
		}
		fields = this._mydlg.fields;
		
		if (elem && elem.is("a")) {
			var val = elem.getAttribute("name");
			if (!val) { val = ""; }
			fields.anchorName.setValue(val);
			fields.remBtn.show();
			fields.myElem = elem;
		} else {
			fields.anchorName.setValue("");
			fields.remBtn.hide();
			fields.myElem = null;
		}
		fields.myRange = range;

		this._mydlg.setVisible(true);
	}
};

CKEDITOR.removeAnchorCommand = function() {};
CKEDITOR.removeAnchorCommand.prototype = {
	exec: function( editor ) {
		var sel = editor.getSelection(),
			bms = sel.createBookmarks(),
			anchor;
		if ( sel && ( anchor = sel.getSelectedElement() ) && ( CKEDITOR.plugins.wblink.fakeAnchor && !anchor.getChildCount() ? CKEDITOR.plugins.wblink.tryRestoreFakeAnchor( editor, anchor ) : anchor.is( 'a' ) ) )
			anchor.remove( 1 );
		else {
			if ( ( anchor = CKEDITOR.plugins.wblink.getSelectedLink( editor ) ) ) {
				if ( anchor.hasAttribute( 'href' ) ) {
					anchor.removeAttributes( { name:1,'data-cke-saved-name':1 } );
					anchor.removeClass( 'cke_anchor' );
				} else
					anchor.remove( 1 );
			}
		}
		sel.selectBookmarks( bms );
	}
};

CKEDITOR.tools.extend( CKEDITOR.config, {
	/**
	 * @cfg {Boolean} [linkShowAdvancedTab=true]
	 * @member CKEDITOR.config
	 * @todo
	 */
	linkShowAdvancedTab: true,

	/**
	 * @cfg {Boolean} [linkShowTargetTab=true]
	 * @member CKEDITOR.config
	 * @todo
	 */
	linkShowTargetTab: true
});
