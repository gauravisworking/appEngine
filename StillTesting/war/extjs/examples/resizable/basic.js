/*
This file is part of Ext JS 3.4

Copyright (c) 2011-2013 Sencha Inc

Contact:  http://www.sencha.com/contact

Commercial Usage
Licensees holding valid commercial licenses may use this file in accordance with the Commercial
Software License Agreement provided with the Software or, alternatively, in accordance with the
terms contained in a written agreement between you and Sencha.

If you are unsure which license is appropriate for your use, please contact the sales department
at http://www.sencha.com/contact.

Build date: 2013-04-03 15:07:25
*/
var ResizableExample = {
    init : function(){
        
        var basic = new Ext.Resizable('basic', {
                width: 200,
                height: 100,
                minWidth:100,
                minHeight:50
        });
        
        var animated = new Ext.Resizable('animated', {
                width: 200,
                pinned: true,
                height: 100,
                minWidth:100,
                minHeight:50,
                animate:true,
                easing: 'backIn',
                duration:.6
        });
        
        var wrapped = new Ext.Resizable('wrapped', {
            wrap:true,
            pinned:true,
            minWidth:50,
            minHeight: 50,
            preserveRatio: true
        });
        
        var transparent = new Ext.Resizable('transparent', {
            wrap:true,
            minWidth:50,
            minHeight: 50,
            preserveRatio: true,
            transparent:true
        });
        
        var custom = new Ext.Resizable('custom', {
            wrap:true,
            pinned:true,
            minWidth:50,
            minHeight: 50,
            preserveRatio: true,
            handles: 'all',
            draggable:true,
            dynamic:true
        });
        var customEl = custom.getEl();
        // move to the body to prevent overlap on my blog
        document.body.insertBefore(customEl.dom, document.body.firstChild);
        
        customEl.on('dblclick', function(){
            customEl.hide(true);
        });
        customEl.hide();
        
        Ext.get('showMe').on('click', function(){
            customEl.center();
            customEl.show(true);
        });
        
        var dwrapped = new Ext.Resizable('dwrapped', {
            wrap:true,
            pinned:true,
            width:450,
            height:150,
            minWidth:200,
            minHeight: 50,
            dynamic: true
        });
        
        var snap = new Ext.Resizable('snap', {
            pinned:true,
            width:250,
            height:100,
            handles: 'e',
            widthIncrement:50,
            minWidth: 50,
            dynamic: true
        });
    }
};

Ext.EventManager.onDocumentReady(ResizableExample.init, ResizableExample, true);