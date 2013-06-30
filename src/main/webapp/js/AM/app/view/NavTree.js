Ext.define('AM.view.NavTree', {
			extend : 'Ext.panel.Panel',
			alias : 'widget.navtree',
			requires : ['Ext.layout.container.Accordion'],
			xtype : 'layout-navtree',
			layout : 'accordion',
			initComponent : function() {
			    var role;
                var items = [{
                    iconCls : 'icon-query',
                    title : '系统管理',
                    items : [{
                        xtype : 'treepanel',
                        animate : true,
                        border : 0,
                        bodyStyle : 'border:0',
                        rootVisible : false,
                        store : (config.role == 1)?'navtree.SystemTree':'navtree.SystemTree2'
                    }]
                }, {
                    iconCls : 'icon-settings',
                    title : '客户管理',
                    items : [{
                        xtype : 'treepanel',
                        animate : true,
                        border : 0,
                        bodyStyle : 'border:0',
                        rootVisible : false,
                        store : 'navtree.CustomersTree'
                    }]
                }];
                Ext.apply(this, {
                    items : items
                });
				this.callParent();
			},

			changeRenderer : function(val) {
			},

			pctChangeRenderer : function(val) {
			}
		});