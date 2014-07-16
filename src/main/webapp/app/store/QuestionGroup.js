Ext.define('TestMaker.store.QuestionGroup', {
    extend: 'Ext.data.Store',
    model: 'TestMaker.model.QuestionGroup',
    autoLoad: true,
    proxy: {
        type: 'rest',
        url: 'rest/questiongroups/',
        reader: {
            type: 'json',
            root: 'data'
        }
    }
});