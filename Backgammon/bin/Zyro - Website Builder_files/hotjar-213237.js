window.hjSiteSettings = window.hjSiteSettings || {"testers_widgets":[],"surveys":[],"record_targeting_rules":[],"recording_capture_keystrokes":true,"polls":[],"site_id":213237,"forms":[{"field_info":[{"field_type":"text","match_value":"user_login","id":421955,"match_attribute":"name"},{"field_type":"password","match_value":"user_password","id":421956,"match_attribute":"name"},{"field_type":"text","match_value":"email","id":421957,"match_attribute":"name"}],"targeting":[{"negate":false,"pattern":"http:\/\/site.pro\/?register=1&register_type=1","match_operation":"exact","component":"url"}],"selector_type":"css","created_epoch_time":1463722700,"selector":"1:div#menu_login > ul > li > form","id":42293},{"field_info":[{"field_type":"radio","match_value":"user_type","id":421949,"match_attribute":"name"},{"field_type":"text","match_value":"create_email","id":421950,"match_attribute":"name"},{"field_type":"text","match_value":"create_name","id":421951,"match_attribute":"name"},{"field_type":"text","match_value":"create_desc","id":421952,"match_attribute":"name"},{"field_type":"password","match_value":"create_pass","id":421953,"match_attribute":"name"},{"field_type":"password","match_value":"create_pass2","id":421954,"match_attribute":"name"}],"targeting":[{"negate":false,"pattern":"http:\/\/site.pro\/?register=1&register_type=1","match_operation":"exact","component":"url"}],"selector_type":"css","created_epoch_time":1463722691,"selector":"0:div#menu_login > ul > li > form","id":42292}],"record":false,"heatmaps":[{"created_epoch_time":1463722672,"targeting":[{"negate":false,"pattern":"http:\/\/site.pro\/for-hostings-how-it-works\/","match_operation":"simple","component":"url"}],"id":537760},{"created_epoch_time":1463722661,"targeting":[{"negate":false,"pattern":"http:\/\/site.pro\/for-franchisor\/features\/","match_operation":"simple","component":"url"}],"id":537759},{"created_epoch_time":1463722654,"targeting":[{"negate":false,"pattern":"http:\/\/site.pro\/for-partners\/features\/","match_operation":"simple","component":"url"}],"id":537758},{"created_epoch_time":1463722618,"targeting":[{"negate":false,"pattern":"http:\/\/site.pro\/?register=1&register_type=2","match_operation":"exact","component":"url"}],"id":537754}],"deferred_page_contents":[{"targeting":[{"negate":false,"pattern":"http:\/\/site.pro\/?register=1&register_type=2","match_operation":"exact","component":"url"},{"negate":false,"pattern":"desktop","match_operation":"exact","component":"device"}],"id":1312849},{"targeting":[{"negate":false,"pattern":"http:\/\/site.pro\/?register=1&register_type=2","match_operation":"exact","component":"url"},{"negate":false,"pattern":"tablet","match_operation":"exact","component":"device"}],"id":1312848},{"targeting":[{"negate":false,"pattern":"http:\/\/site.pro\/?register=1&register_type=2","match_operation":"exact","component":"url"},{"negate":false,"pattern":"phone","match_operation":"exact","component":"device"}],"id":1312847}],"feedback_widgets":[],"r":0.2205416485};

window.hjBootstrap = window.hjBootstrap || function (scriptUrl) {
    var b = function () {}, d = document, h = d.head || d.getElementsByTagName('head')[0], s, v, c, ct;

    if (!d.addEventListener) {
        return;
    }

    s = d.createElement('script');
    s.src = scriptUrl;
    h.appendChild(s);

    ct = [
        'iframe#_hjRemoteVarsFrame {',
        'display: none !important; width: 1px !important; height: 1px !important; ' +
        'opacity: 0 !important; pointer-events: none !important;',
        '}'
    ];
    c = document.createElement('style');
    c.type = 'text/css';
    if (c.styleSheet) {
        c.styleSheet.cssText = ct.join('');
    } else {
        c.appendChild(d.createTextNode(ct.join('')));
    }
    h.appendChild(c);

    v = d.createElement('iframe');
    v.style.cssText = ct[1];
    v.name = '_hjRemoteVarsFrame';
    v.title = 'Hotjar Remote Vars Frame';
    v.id = '_hjRemoteVarsFrame';
    v.src = 'https://' + (window._hjSettings.varsHost || 'vars.hotjar.com') + '/rcj-9a8c65a25e627120fda741095c91ae72.html';
    v.onload = function () {
        b.varsLoaded = true;
        if ((typeof hj != 'undefined') && hj.event) {
            hj.event.signal('varsLoaded');
        }
    };
    b.varsJar = v;

    if (d.body) {
        d.body.appendChild(v);
    } else {
        d.addEventListener('DOMContentLoaded', function () {
            d.body.appendChild(v);
        });
    }
    window.hjBootstrap = b;
};


hjBootstrap('https://script.hotjar.com/modules-5d19873aea85cbea800b9bffe46ced81.js');