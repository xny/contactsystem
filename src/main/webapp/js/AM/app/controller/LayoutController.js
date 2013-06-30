Ext.define('AM.controller.LayoutController', {
    extend: 'Ext.app.Controller',
    
	stores: ['navtree.SystemTree','navtree.SystemTree2','navtree.CustomersTree'
    ],
    models : ['CompanyType','SubCompany'],
    views: [
        'NavTree'
    ],

    init: function() {
		this.control({
            'treepanel': {
                 'itemclick': this.onItemClick 
            }
        });
    },
    
    onItemClick: function (tree, record, item, index, e, eOpts) {
         var self = this;
         if (record.get('id') == 'UsersManager') {
             Ext.require("AM.controller.UsersManagerController", function () {
                 var usersManagerController =  Ext.create('AM.controller.UsersManagerController', {
                     title: record.data.text
                 });
                 usersManagerController.init();
             }, self);
         } else if (record.get('id') == 'CompanyTypeManager') {
             Ext.require("AM.controller.CompanyTypeManagerController", function () {
                 var companyTypeManager =  Ext.create('AM.controller.CompanyTypeManagerController', {
                     title: record.data.text
                 });
                 companyTypeManager.init();
             }, self);
         } else if (record.get('id') == 'CustomersManager') {
             Ext.require("AM.controller.CustomersManagerController", function () {
                 var customersManagerController =  Ext.create('AM.controller.CustomersManagerController', {
                     title: record.data.text
                 });
                 customersManagerController.init();
             }, self);
         }
     }
});