CKEDITOR.plugins.add('lessbuttons',
{
	init: function(editor)
    {
        var pluginName = 'lessbuttons';
        editor.addCommand(pluginName, {
            exec : function( editor )
            {    
            	CKEDITOR.advBar = false;
            	$("#" + editor.ui.instances.Wbbgcolor._.id).hide();
            	$("#" + editor.ui.instances.EStyles._.id).hide();
            	$("#" + editor.ui.instances.Strike._.id).hide();
            	$("#" + editor.ui.instances.Subscript._.id).hide();
            	$("#" + editor.ui.instances.Superscript._.id).hide();
            	$("#" + editor.ui.instances.ShowBlocks._.id).hide();
            	$("#" + editor.ui.instances.Less._.id).hide();
            	$("#" + editor.ui.instances.More._.id).show();
            }
        });
        editor.ui.addButton('Less',
            {
                label: 'Standart buttons',
                command: pluginName,
                icon: CKEDITOR.plugins.getPath('lessbuttons') + 'less2.png'
            });
    }
});