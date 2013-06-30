/**
 * mvc
 */
var config = {};
config.path = '/js/AM/';
config.user = window['g_obj'].name;
config.role = window['g_obj'].role;
Ext.require('Ext.container.Viewport');
Ext.Loader.setConfig({
			enabled : true
		});
		
var application = Ext.application({
	name : 'AM',

	appFolder : '/js/AM/app', 

	controllers : ['LayoutController'],

	launch : function() {
		Ext.create('Ext.container.Viewport', {
			layout : 'border',
			items : [{
						region : 'north',
						title : '欢迎'+ config.user +'!&nbsp;&nbsp;  来到宝盛人脉管理系统',
						border : false
					}, {
						region : 'west',
						collapsible : true,
						title : '菜单栏',
						width : 200,
						xtype : 'layout-navtree'
					}, {
						region : 'south',
						title : 'Copyright 2013'
					}, {
						region : 'center',
						id : 'tabpanel',
						xtype : 'tabpanel', // TabPanel itself has no title
						activeTab : 0 // First tab active by default
					}]
		});
	}
});