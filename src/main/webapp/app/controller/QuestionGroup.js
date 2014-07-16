Ext.define('TestMaker.controller.QuestionGroup', {
    extend: 'Ext.app.Controller',

    stores: ['QuestionGroup'],

    models: ['QuestionGroup'],

    views: ['questionGroup.Form', 'questionGroup.Grid'],

    refs: [{
        ref: 'questionGroupPanel',
        selector: 'panel'
    }, {
        ref: 'questionGroupGrid',
        selector: 'grid'
    }],

    init: function() {
        this.control({
            'questiongroupgrid dataview': {
                itemdblclick: this.editGroup
            },
            'questiongroupgrid button[action=add]': {
                click: this.addGroup
            },
            'questiongroupgrid button[action=delete]': {
                click: this.deleteGroup
            },
            'questiongroupform button[action=save]': {
                click: this.updateGroup
            }
        });
    },

    addGroup: function(btn, e) {
        var edit = Ext.create('TestMaker.view.questionGroup.Form').show();

    },

    editGroup: function(grid, record) {
        var edit = Ext.create('TestMaker.view.questionGroup.Form').show();
        if (record) {
            edit.down('form').loadRecord(record);
        }
    },

    updateGroup: function(button) {
        var win = button.up('window'),
            form = win.down('form'),
            record = form.getRecord(),
            values = form.getValues(),
            store = this.getQuestionGroupStore();

        var newGroup = false;

        if (values.id > 0) {
            record.set(values);
        } else {
            record = Ext.create('TestMaker.model.QuestionGroup');
            record.set(values);
            store.add(record);
            newGroup = true;
        }

        win.close();


        if (newGroup) { //faz reload para atualziar
            store.sync({
                success: function(records, operation, success) {
                    store.load();
                }
            });
        } else {
            store.sync();
        }
    },

    deleteGroup: function(button) {

        var grid = this.getQuestionGroupGrid(),
            record = grid.getSelectionModel().getSelection(),
            store = this.getQuestionGroupStore();

        store.remove(record);
        store.sync({
            success: function(records, operation, success) {
                store.load();
            }
        });

        //faz reload para atualziar
        //this.getQuestionGroupStore().load();
    }
});