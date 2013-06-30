Ext.require(['Ext.data.*', 'Ext.grid.*','Ext.ux.*']);
Ext.define('AM.controller.CustomersManagerController', {
    extend : 'Ext.app.Controller',

    stores : ['Customers','CompanyType','SubCompany'],
    models : ['Customers','CompanyType','SubCompany'],
    views : ['Customers'],
    
    init : function(app) { 
        var tabpanel = Ext.getCmp('tabpanel');
        var mepanel = tabpanel.child('customers');
        if (!mepanel) {
            mepanel = Ext.widget('customers', {
                title : this.title
            });
            tabpanel.add(mepanel);
            tabpanel.setActiveTab(mepanel);
            this.control({
                'customers toolbar button[action="add"]':{
                    'click' : this.AddHandler
                },
                'customers toolbar button[action="del"]':{
                    'click' : this.DelHandler
                },
                'customers toolbar button[action="query"]':{
                    'click' : this.QueryHandler
                }
            })
            mepanel.getPlugin('rowediting').on('edit', this.onEditcomplete, this);
            mepanel.getPlugin('rowediting').on('cancelEdit', this.onCancelEdit, this);
            var customerstore = mepanel.getStore();
            customerstore.on("beforeload",function(){
			    Ext.apply(customerstore.proxy.extraParams, {
				    name:Ext.getCmp('name').getValue().trim(),
		        	companyTypeId:Ext.getCmp('companyTypeId').getValue(),
		        	subCompanyId:Ext.getCmp('subCompanyId').getValue(),
		        	frequency:Ext.getCmp('frequency').getValue()
	        	});
			});
        } else {
            tabpanel.setActiveTab(mepanel);
        }
    },
    QueryHandler:function(button){
        var mepanel = button.up('customers');
        mepanel.getStore().currentPage = 1;
        mepanel.getStore().reload({
        	params:{
        		page:1,start:0
        	},
        	callback: function(records, operation, success){
        		if(success){
        			if(records.length <= 0){
        				Ext.Msg.alert('警告', '没有符合查询条件的数据！');
        			}
        		}else if(obj.data == -1){
                    Ext.Msg.alert('警告', '请先登录！');
                }else{
                    Ext.Msg.alert('警告', '查询失败！');
                }
	         }
        });
    },
    
    AddHandler: function(button){
        var mepanel = button.up('customers');
        mepanel.getStore().insert(0, new AM.model.Customers());
        mepanel.getPlugin('rowediting').startEdit(0, 0);
    },
    
    DelHandler: function(button){
        var mepanel = button.up('customers');
        var selection = mepanel.getView().getSelectionModel().getSelection()[0];
        if (selection) {
            Ext.Ajax.request({
                url: '/deleteContact',
                params:{id:selection.data.id},
                success: Ext.bind(function(response){
                    var obj=Ext.JSON.decode(response.responseText);
                    if(obj.success){
                    	if(mepanel.getStore('Customers').data.length == 1){
                    		mepanel.getStore().currentPage--;
                    		mepanel.getStore('Customers').reload({page:mepanel.getStore().currentPage,start:(mepanel.getStore().currentPage-1)*mepanel.getStore().data.pageSize});
                    	}else{
                    		mepanel.getStore('Customers').reload();
                    	}
                    }else if(obj.data == -1){
                    	Ext.Msg.alert('警告', '请先登录！');
                    }else{
                    	Ext.Msg.alert('警告', '删除失败！');
                    }
                },this)
            })
        }
    },
    onEditcomplete: function (rowEditing, context) {
    	var returnobj = {};
    	for (var key in context.record.data){
    		if(key == "companyType" || key == "subCompany" || key == "createTime"){
    			continue;
    		}else {
    			returnobj[key] = context.record.data[key];
    		}
    	}
        if(context.record.data.id){
            Ext.Ajax.request({
                url: '/editContact',
                params:returnobj,
                success: Ext.bind(function(response){
                    var obj=Ext.JSON.decode(response.responseText);
                    if(obj.success){
                    	context.store.reload();
                    }else if(obj.data == -1){
                    	Ext.Msg.alert('警告', '请先登录！');
                    }else{
                    	Ext.Msg.alert('警告', '更新客户失败！');
                    }
                },this)
            })
        }else{
            Ext.Ajax.request({
                url: '/addContact',
                params:returnobj,
                success: Ext.bind(function(response){
                    var obj=Ext.JSON.decode(response.responseText);
                    if(obj.success){
                    	context.store.reload();
                    }else if(obj.data == -1){
                    	Ext.Msg.alert('警告', '请先登录！');
                    }else{
                    	Ext.Msg.alert('警告', '添加客户失败！');
                    }
                },this)
            })
        };
    },
    
    onCancelEdit: function(rowEditing, context) {
        if (context.record.phantom) {
            this.getStore('Customers').remove(context.record);
        }
    }
}); 