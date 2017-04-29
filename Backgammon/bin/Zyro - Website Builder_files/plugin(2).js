CKEDITOR.plugins.add('morebuttons',
{
	init: function(editor)
    {
        var pluginName = 'morebuttons';
        editor.addCommand(pluginName, {
            exec : function( editor )
            {    
            	CKEDITOR.advBar = true;
            	$("#" + editor.ui.instances.Wbbgcolor._.id).fadeIn("slow");
            	$("#" + editor.ui.instances.EStyles._.id).fadeIn("slow");
            	$("#" + editor.ui.instances.Strike._.id).fadeIn("slow");
            	$("#" + editor.ui.instances.Subscript._.id).fadeIn("slow");
            	$("#" + editor.ui.instances.Superscript._.id).fadeIn("slow");
            	$("#" + editor.ui.instances.ShowBlocks._.id).fadeIn("slow");
            	$("#" + editor.ui.instances.Less._.id).fadeIn("slow");
            	$("#" + editor.ui.instances.More._.id).hide();
            }
        });
        editor.ui.addButton('More',
            {
                label: 'More',
                command: pluginName,
                icon: CKEDITOR.plugins.getPath('morebuttons') + 'more2.png'
            });
    }
});