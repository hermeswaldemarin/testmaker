Ext.define('TestMaker.Application', {
    name: 'TestMaker',

    extend: 'Ext.app.Application',

    requires: [
        'TestMaker.view.MyViewport',
        'TestMaker.view.questionGroup.Form',
        'TestMaker.view.questionGroup.Grid'
    ],

    views: [
        'MyViewport',
        'questionGroup.Grid',
        'questionGroup.Form'
    ],

    controllers: [
        'QuestionGroup',
        'Menu'
    ]
});