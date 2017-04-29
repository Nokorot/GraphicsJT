/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.advBar = false;
CKEDITOR.editorConfig = function( config ) {
	
	config.allowedContent = true;
	config.stylesSet = [];
	
	config.toolbar = [
  		[ 'Styles', 'Wbcolor', 'Wbbgcolor', 'EStyles', 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript'],	
  		[ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ],
  		[ 'NumberedList', 'BulletedList', 'WBLink', 'Unlink', 'Anchor'],
  		[ 'ShowBlocks', '|', 'More', 'Less' ],
		[ 'Languages' ],
		[ 'CloseBtn' ]
  	];
  	
	config.floatSpacePinnedOffsetY = 130;

	config.baseFloatZIndex = 10;

	// The default plugins included in the basic setup define some buttons that
	// we don't want too have in a basic editor. We remove them here.
	//config.removeButtons = 'Anchor,Underline,Strike,Subscript,Superscript';
	
	// Considering that the basic setup doesn't provide pasting cleanup features,
	// it's recommended to force everything to be plain text.
	config.forcePasteAsPlainText = true;

	// Let's have it basic on dialogs as well.
	//config.removeDialogTabs = 'link:advanced';
	
	config.removePlugins = 'link';
	
	config.extraPlugins = 'wbcolor,wbbgcolor,morebuttons,lessbuttons,editstyles,wblink,closebtn,languages';

	if (!config.keystrokes) config.keystrokes = [];
	config.keystrokes.push([CKEDITOR.CTRL + 76 /*L*/, 'wblink']);
	config.keystrokes.push([CKEDITOR.CTRL + 75 /*K*/, 'wblink']); // to override native IE dialog
	
};

CKEDITOR.on('instanceReady', function(ev) {
	if (!CKEDITOR.advBar) {
		$("#" + ev.editor.ui.instances.Wbbgcolor._.id).hide();
		$("#" + ev.editor.ui.instances.EStyles._.id).hide();
		$("#" + ev.editor.ui.instances.Strike._.id).hide();
		$("#" + ev.editor.ui.instances.Subscript._.id).hide();
		$("#" + ev.editor.ui.instances.Superscript._.id).hide();
		$("#" + ev.editor.ui.instances.ShowBlocks._.id).hide();
		$("#" + ev.editor.ui.instances.Less._.id).hide();
	}
	else {
		$("#" + ev.editor.ui.instances.More._.id).hide();
	}
	$(".cke_combo_button").click(function() {
		if (!$(".cke_combo_button").data('set')) {
			setTimeout(function() {
				wb_builder.site.reloadCkIframeStyles();
			}, 100);
			$(".cke_combo_button").data('set', true);
		}
	});
});
