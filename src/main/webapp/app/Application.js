Ext.define('TestMaker.Application', {
    name: 'TestMaker',

    extend: 'Ext.app.Application',

    views: [
        'questionGroup.Grid', 'questionGroup.Form'
    ],

    controllers: [
        'QuestionGroup'
    ],

    stores: [
        'QuestionGroup'
    ]
});