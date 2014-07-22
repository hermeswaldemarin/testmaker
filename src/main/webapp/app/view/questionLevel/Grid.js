Ext.define('TestMaker.view.questionLevel.Grid', {
    extend: 'Ext.grid.Panel',
    xtype: 'questionlevelgrid',

    requires: ['Ext.toolbar.Paging'],

    iconCls: 'icon-grid',

    title: 'Question Level',
    store: 'QuestionLevel',

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
            store: 'QuestionLevel',
            displayInfo: true,
            displayMsg: 'Question Levels {0} - {1} de {2}',
            emptyMsg: "No data found."
        }];

        this.callParent(arguments);
    }
});