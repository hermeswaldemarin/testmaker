Ext.define('TestMaker.view.Viewport', {
    extend: 'Ext.container.Viewport',
    requires: [
        'Ext.layout.container.Fit',
        'TestMaker.view.Main'
    ],

    layout: {
        type: 'fit'
    },

    items: [{
        xtype: 'questiongroupgrid'
    }]
});