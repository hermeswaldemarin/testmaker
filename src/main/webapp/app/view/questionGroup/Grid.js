Ext.define('TestMaker.view.questionGroup.Grid', {
    extend: 'Ext.grid.Panel',
    xtype: 'questiongroupgrid',

    requires: ['Ext.toolbar.Paging'],

    iconCls: 'icon-grid',

    title: 'Question Group',
    store: 'QuestionGroup',

    columns: [{
        header: "Id",
        width: 50,
        flex: 1,
        dataIndex: 'id'
    }, {
        header: "Name",
        width: 160,
        flex: 1,
        dataIndex: 'name'
    }],

    initComponent: function() {

        this.dockedItems = [{
            xtype: 'toolbar',
            items: [{
                iconCls: 'icon-save',
                text: 'Adicionar',
                action: 'add'
            }, {
                iconCls: 'icon-delete',
                text: 'Excluir',
                action: 'delete'
            }]
        }, {
            xtype: 'pagingtoolbar',
            dock: 'top',
            store: 'QuestionGroup',
            displayInfo: true,
            displayMsg: 'Question Groups {0} - {1} de {2}',
            emptyMsg: "No data found."
        }];

        this.callParent(arguments);
    }
});