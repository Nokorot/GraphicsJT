CKEDITOR.plugins.add('editstyles',
{
	init: function(editor)
    {
        var pluginName = 'editstyles';
        editor.addCommand(pluginName, {
            exec : function( editor )
            {    
            	wb_builder.site.changeStyles();
            }
        });
        editor.ui.addButton('EStyles',
            {
                label: 'Edit styles',
                command: pluginName,
                icon: CKEDITOR.plugins.getPath('editstyles') + 'button.png'
            });
    }
});