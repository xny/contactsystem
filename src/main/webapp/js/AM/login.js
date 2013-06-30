Ext.define('AM.view.form.LoginForm', {
    extend : 'Ext.form.Panel',
    xtype : 'login-form',
    frame : true,
    width : 320,
    bodyPadding : 10,
    style : {
        marginLeft : 'auto',
        marginRight : 'auto',
        marginTop : '200px;'
    },
    defaultType : 'textfield',
    defaults : {
        anchor : '100%'
    },
    items : [{
        allowBlank : false,
        fieldLabel : '用户名',
        id:'user',
        name : 'user',
        emptyText : 'user id',
        blankText : '用户名不能为空'
    }, {
        allowBlank : false,
        fieldLabel : '密码',
        id:'pass',
        name : 'pass',
        blankText : '密码不能为空',
        emptyText : 'password',
        inputType : 'password'
    }],
    buttons : [{
        text : '登录',
        handler : function(button, event) {
        	var username = Ext.getCmp('user').getValue().trim();
        	var password = Ext.getCmp('pass').getValue().trim();
        	if(username!='' && password!=''){
	            Ext.Ajax.request({
	                url: '/login',
	                params:{userName:username,password:password},
	                success: Ext.bind(function(response){
	                    var obj=Ext.JSON.decode(response.responseText);
	                    if(obj.success){
	                   		window.location.href = '/index';
	                    }else{
	                    	Ext.Msg.alert('警告', '登录失败！');
	                    }
	                },this)
	            })
	        }
        }
    }, {
        text : '取消',
        handler : function() {
            this.up("form").form.reset();
        }
    }]
});

Ext.application({
    name : 'HelloExt',
    launch : function() {
        Ext.create('Ext.container.Viewport', {
            items : [{
                xtype : 'login-form',
                title : '宝盛集团人脉管理系统'
            }]
        });
    }
}); 