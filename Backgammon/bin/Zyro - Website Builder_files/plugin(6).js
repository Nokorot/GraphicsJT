
CKEDITOR.plugins.add('closebtn', {
	icons: 'closebtn',
	init: function(editor) {
		editor.addCommand('closebtn', {
			exec: function(editor) {
				WB_TextArea.hideEditors();
			}
		});
		editor.ui.addButton('CloseBtn', {
			label: __('Done'),
			command: 'closebtn',
			toolbar: 'closebtn'
		});
	}
});
