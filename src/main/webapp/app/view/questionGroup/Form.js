Ext.define('TestMaker.view.questionGroup.Form', {
    extend: 'Ext.window.Window',
    xtype: 'questiongroupform',

    requires: ['Ext.form.Panel', 'Ext.form.field.Text'],

    title: 'Edit/Add Group',
    layout: 'fit',
    autoShow: true,
    width: 280,

    iconCls: 'icon-user',

    initComponent: function() {
        this.items = [{
            xtype: 'form',
            padding: '5 5 0 5',
            border: false,
            style: 'background-color: #fff;',

            fieldDefaults: {
                anchor: '100%',
                labelAlign: 'left',
                allowBlank: false,
                combineErrors: true,
                msgTarget: 'side'
            },

            items: [{
                xtype: 'textfield',
                name: 'id',
                fieldLabel: 'id',
                hidden: true
            }, {
                xtype: 'textfield',
                name: 'name',
                fieldLabel: 'Name'
            }]
        }];

        this.dockedItems = [{
            xtype: 'toolbar',
            dock: 'bottom',
            id: 'buttons',
            ui: 'footer',
            items: ['->', {
                iconCls: 'icon-save',
                text: 'Save',
                action: 'save'
            }, {
                iconCls: 'icon-reset',
                text: 'Cancel',
                scope: this,
                handler: this.close
            }]
        }];

        this.callParent(arguments);
    }
});