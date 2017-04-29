CKEDITOR.plugins.add('languages', {
	init: function(editor) {
		var langs = wb_builder.site.languages;
		if (!langs.length) return;
		
		var textArea = editor.wbTextAreaInstance;
		
		var opts = {}, initOptIdx, currOptIdx;
		var getLangCodeByName = function(langName) {
			for (var i=0, lang; lang = langs[i]; i++) {
				if (lang.name == langName) {
					return lang.code;
				}
			}
		};
		
		for (var i=0, lang; lang = langs[i]; i++) {
			opts[lang.name] = '<i class="ico-lang-' + WB_Languages.getCountryCode(lang.code) + '" style="margin-right: 4px; position: relative; top: 1px;"></i>' + lang.name;
			if (lang.code == wb_builder.site.currSelLang) {
				initOptIdx = currOptIdx = lang.name;
				textArea.editorChosenLanguage = lang.code;
			}
		}
		editor.ui.addRichCombo('Languages', {
			type: 'select',
			label: initOptIdx,
			init: function() {
				for (var i in opts) {
					this.add(i, opts[i]);
				}
				this.wbOnChange(initOptIdx);
				
				// change dropdown height
				if ((dropdown = this.wbGetDropdownElem()) && dropdown.length) {
					dropdown.addClass('dropdown_languages');
					dropdown.before('<style type="text/css">.dropdown_languages {height: ' + (24 * Object.keys(opts).length + 6) + 'px;}</style>');
				}
			},
			panel: {
				css: [
					editor.config.contentsCss,
					CKEDITOR.getUrl('skins/' + CKEDITOR.skinName + '/editor.css'),
					wb_builder.baseUrl + wb_builder.modBaseUrl + 'css/site.css'
				]
			},
			onClick: function(value) {
				this.wbOnChange(value);
			},
			onOpen: function() {
				// hack to prevent addition of &#8023; character among html
				setTimeout(function() {
					var html_arr = $(textArea.textDiv.html());
					var new_html_arr = [];
					$.each(html_arr, function() {
						if (this.nodeType != 3) {
							new_html_arr.push(this);
						}
					});
					textArea.textDiv.empty().append(new_html_arr);
				}, 10);
			},
			wbOnChange: function(langName) {
				if (Object.keys(opts).length == 1) return;
				for (var idx in opts) {
					if (idx == langName) {
						if (langCode = getLangCodeByName(langName)) {
							textArea.setValue(textArea.textDiv.html(), getLangCodeByName(currOptIdx));
							textArea.onSwitchLanguage(langCode, true);
							currOptIdx = langName;
							textArea.editorChosenLanguage = langCode;
						}
						this.setValue(langName);
						return;
					}
				}
			},
			wbGetDropdownElem: function() {
				if (!this.dropdownElem) {
					var dropdowns = $('.cke_combopanel');
					if (dropdowns.length == 1) {
						this.dropdownElem = dropdowns.eq(0);
					} else {
						var thisClass = this;
						dropdowns.each(function() {
							if ($(this).children('iframe').attr('title') == 'undefined') {
								thisClass.dropdownElem = $(this);
								return;
							}
						});
					}
				}
				return this.dropdownElem;
			},
		});
	}
});
