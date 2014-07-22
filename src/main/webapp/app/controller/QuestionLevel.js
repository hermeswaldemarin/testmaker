Ext.define('TestMaker.controller.QuestionLevel', {
    extend: 'Ext.app.Controller',

    stores: ['QuestionLevel'],

    models: ['QuestionLevel'],

    views: ['questionLevel.Form', 'questionLevel.Grid'],

    refs: [{
        ref: 'questionLevelPanel',
        selector: 'panel'
    }, {
        ref: 'questionLevelGrid',
        selector: 'grid'
    }],

    init: function() {
        this.control({
            'questionlevelgrid dataview': {
                itemdblclick: this.editLevel
            },
            'questionlevelgrid button[action=add]': {
                click: this.addLevel
            },
            'questionlevelgrid button[action=delete]': {
                click: this.deleteLevel
            },
            'questionlevelform button[action=save]': {
                click: this.updateLevel
            }
        });
    },

    addLevel: function(btn, e) {
        var edit = Ext.create('TestMaker.view.questionLevel.Form').show();

    },

    editLevel: function(grid, record) {
        var edit = Ext.create('TestMaker.view.questionLevel.Form').show();
        if (record) {
            edit.down('form').loadRecord(record);
        }
    },

    updateLevel: function(button) {
        var win = button.up('window'),
            form = win.down('form'),
            record = form.getRecord(),
            values = form.getValues(),
            store = this.getQuestionLevelStore();

        var newGroup = false;

        if (values.id > 0) {
            record.set(values);
        } else {
            record = Ext.create('TestMaker.model.QuestionLevel');
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

    deleteLevel: function(button) {

        var grid = this.getQuestionLevelGrid(),
            record = grid.getSelectionModel().getSelection(),
            store = this.getQuestionLevelStore();

        store.remove(record);
        store.sync({
            success: function(records, operation, success) {
                store.load();
            }
        });

        //faz reload para atualziar
        //this.getquestionLevelStore().load();
    }
});