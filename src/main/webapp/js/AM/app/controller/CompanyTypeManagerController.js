Ext.require(['Ext.data.*', 'Ext.grid.*']);
Ext.define('AM.controller.CompanyTypeManagerController', {
    extend : 'Ext.app.Controller',
	models : ['CompanyType'],
    stores : ['CompanyType'],
    views : ['CompanyType'],
    
    init : function(app) { 
        var tabpanel = Ext.getCmp('tabpanel');
        var companytypePanel = tabpanel.child('companytype');
        if (!companytypePanel) {
            companytypePanel = Ext.widget('companytype', {
                title : this.title
            });
            tabpanel.add(companytypePanel);
            tabpanel.setActiveTab(companytypePanel);
            this.control({
                'companytype toolbar button[text="添加"]':{
                    'click' : this.CompanyYypeAddHandler
                },
                'companytype toolbar button[text="删除"]':{
                    'click' : this.CompanyYypeDelHandler
                }
            })
            companytypePanel.getPlugin('rowediting').on('edit', this.onEditcomplete, this);
            companytypePanel.getPlugin('rowediting').on('cancelEdit', this.onCancelEdit, this);
        } else {
            tabpanel.setActiveTab(companytypePanel);
        }
    },
    CompanyYypeAddHandler: function(button){
        var companytypePanel = button.up('companytype');
        companytypePanel.getStore().insert(0, new AM.model.CompanyType());
        companytypePanel.getPlugin('rowediting').startEdit(0, 0);
    },
    
    CompanyYypeDelHandler: function(button){
        var companytypePanel = button.up('companytype');
        var selection = companytypePanel.getView().getSelectionModel().getSelection()[0];
        if (selection) {
            Ext.Ajax.request({
                url: '/deleteCompanyType',
                params:{id:selection.data.id},
                success: Ext.bind(function(response){
                    var obj=Ext.JSON.decode(response.responseText);
                    if(obj.success){
                    	companytypePanel.getStore('CompanyType').reload();
                    	if(!!_companyTypestore){
                    		_companyTypestore.reload();
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
        if(context.record.data.id){
            Ext.Ajax.request({
                url: '/editCompanyType',
                params:context.record.data,
                success: Ext.bind(function(response){
                    var obj=Ext.JSON.decode(response.responseText);
                    if(obj.success){
                    	context.store.reload();
                    	if(!!_companyTypestore){
                    		_companyTypestore.reload();
                    	}
                    }else if(obj.data == -1){
                    	Ext.Msg.alert('警告', '请先登录！');
                    }else{
                    	Ext.Msg.alert('警告', '更新行业失败！');
                    }
                },this)
            })
        }else{
            Ext.Ajax.request({
                url: '/addCompanyType',
                params:context.record.data,
                success: Ext.bind(function(response){
                    var obj=Ext.JSON.decode(response.responseText);
                    if(obj.success){
                    	context.store.reload();
                    	if(!!_companyTypestore){
                    		_companyTypestore.reload();
                    	}
                    }else if(obj.data == -1){
                    	Ext.Msg.alert('警告', '请先登录！');
                    }else{
                    	Ext.Msg.alert('警告', '添加行业失败！');
                    }
                },this)
            })
        };
    },
    
    onCancelEdit: function(rowEditing, context) {
        if (context.record.phantom) {
            this.getStore('CompanyType').remove(context.record);
        }
    }
}); 