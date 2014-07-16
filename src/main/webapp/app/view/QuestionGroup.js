Ext.define('TestMaker.view.QuestionGroup', {
    extend: 'Ext.tab.Panel',
    xtype: 'questiongrouplist',
    title: 'Question Group List',
    items: [{
        xtype: 'gridpanel',
        title: 'Question Group',
        store: 'QuestionGroup',
        name: 'editableGrid',
        columns: [{
            text: 'ID',
            dataIndex: 'id',
            width: 300
        }, {
            text: 'Name',
            dataIndex: 'name',
            width: 300,
            editor: {
                xtype: 'textfield',
                allowBlank: false
            }
        }],
        columnLines: true,
        selModel: 'rowmodel',
        plugins: [
            Ext.create('Ext.grid.plugin.RowEditing', {
                clicksToEdit: 1
            })
        ],
        dockedItems: [{
            xtype: 'toolbar',
            items: [{
                action: 'add',
                text: 'Add Something'
            }, '-', {
                action: 'remove',
                text: 'Remove Something',
                disabled: true
            }]
        }],

        width: 600,
        height: 300
    }]
});