Ext.define('TestMaker.view.Menu', {
    extend: 'Ext.panel.Panel',
    xtype: 'menu',

    height: 432,
    width: 251,
    layout: {
        type: 'accordion'
    },
    iconCls: 'home',
    title: 'Menu',

    initComponent: function() {
        var me = this;

        me.callParent(arguments);
    }

});