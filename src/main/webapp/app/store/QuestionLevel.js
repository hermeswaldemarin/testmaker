Ext.define('TestMaker.store.QuestionLevel', {
    extend: 'Ext.data.Store',
    model: 'TestMaker.model.QuestionLevel',
    autoLoad: true,
    proxy: {
        type: 'rest',
        url: 'rest/questionlevel/',
        reader: {
            type: 'json',
            root: 'data'
        }
    }
});