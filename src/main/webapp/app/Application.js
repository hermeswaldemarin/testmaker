Ext.define('TestMaker.Application', {
    name: 'TestMaker',

    extend: 'Ext.app.Application',

    requires: [
        'TestMaker.view.MyViewport',
        'TestMaker.view.questionGroup.Form',
        'TestMaker.view.questionGroup.Grid',
        'TestMaker.view.questionLevel.Grid',
        'TestMaker.view.questionLevel.Form'
    ],

    views: [
        'MyViewport',
        'questionGroup.Grid',
        'questionGroup.Form',
        'questionLevel.Grid',
        'questionLevel.Form'
    ],

    controllers: [
        'QuestionGroup',
        'QuestionLevel',
        'Menu'
    ]
});