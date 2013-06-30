Ext.require(['Ext.data.*', 'Ext.grid.*']);
Ext.define('AM.controller.UsersManagerController', {
    extend : 'Ext.app.Controller',

    stores : ['Users'],
    views : ['Users'],
    init : function(app) { 
        var tabpanel = Ext.getCmp('tabpanel');
        var usersPanel = tabpanel.child('users');
        if (!usersPanel) {
            usersPanel = Ext.widget('users', {
                title : this.title
            });
            tabpanel.add(usersPanel);
            tabpanel.setActiveTab(usersPanel);
            this.control({
                'users toolbar button[text="添加"]':{
                    'click' : this.UsersAddHandler
                },
                'users toolbar button[text="删除"]':{
                    'click' : this.UsersDelHandler
                }
            })
            usersPanel.getPlugin('rowediting').on('edit', this.onEditcomplete, this);
            usersPanel.getPlugin('rowediting').on('cancelEdit', this.onCancelEdit, this)
        } else {
            tabpanel.setActiveTab(usersPanel);
        }
    },
    
    UsersAddHandler: function(button){
        var usersPanel = button.up('users');
        usersPanel.getStore().insert(0, new AM.model.Users());
        usersPanel.getPlugin('rowediting').startEdit(0, 0);
    },
    
    UsersDelHandler: function(button){
        var usersPanel = button.up('users');
        var selection = usersPanel.getView().getSelectionModel().getSelection()[0];
        if (selection) {
            Ext.Ajax.request({
                url: '/deleteAccount',
                params:{id:selection.data.id},
                success: Ext.bind(function(response){
                    var obj=Ext.JSON.decode(response.responseText);
                    if(obj.success){
                    	usersPanel.getStore('Users').reload();
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
                url: '/editAccount',
                params:context.record.data,
                success: Ext.bind(function(response){
                    var obj=Ext.JSON.decode(response.responseText);
                    if(obj.success){
                    	context.store.reload();
                    }else if(obj.data == -1){
                    	Ext.Msg.alert('警告', '请先登录！');
                    }else{
                    	Ext.Msg.alert('警告', '更新用户失败！');
                    }
                },this)
            })
        }else{
            Ext.Ajax.request({
                url: '/addAccount',
                params:context.record.data,
                success: Ext.bind(function(response){
                    var obj=Ext.JSON.decode(response.responseText);
                    if(obj.success){
                    	context.store.reload();
                    }else if(obj.data == -1){
                    	Ext.Msg.alert('警告', '请先登录！');
                    }else{
                    	Ext.Msg.alert('警告', '添加用户失败！');
                    }
                },this)
            })
        };
    },
    
    onCancelEdit: function(rowEditing, context) {
        if (context.record.phantom) {
            this.getStore('Users').remove(context.record);
        }
    }
}); 