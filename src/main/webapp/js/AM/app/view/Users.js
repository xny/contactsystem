var _rolestore = Ext.create('Ext.data.Store', {
                        fields: ['abbr', 'name'],
                        data : [
                            {"abbr":1, "name":"管理员"},
                            {"abbr":0, "name":"普通用户"}
                        ]
                });
Ext.define('AM.view.Users', {
        extend : 'Ext.grid.Panel',
        alias : 'widget.users',
        xtype: 'x-users',
        plugins: [{ptype:'rowediting',pluginId:'rowediting',clicksToEdit: 1,saveBtnText: '保存',
        cancelBtnText: '取消'}],
        frame: true,
        store: 'Users',
        columns: [
        {
            text: 'ID',
            width: 40,
            sortable: true,
            dataIndex: 'id'
        },
        {
            text: '姓名',
            flex: 1,
            sortable: true,
            dataIndex: 'userName',
            field: {
                xtype: 'textfield'
            }
        }, {
            text: '密码',
            flex: 1,
            sortable: true,
            dataIndex: 'password',
            field: {
                xtype: 'textfield'
            }
        }, {
            header: '角色',
            flex: 1,
            sortable: true,
            dataIndex: 'role',
            field: {
                xtype: 'combobox',
                displayField: 'name',
                valueField:'abbr',
                queryMode: 'local',
                editable:false,
                store:_rolestore
            },
            renderer:function (value){
                return !!(_rolestore.findRecord('abbr', value)) ?  _rolestore.findRecord('abbr', value).data.name : "";
            }
        }],
        dockedItems: [{
            xtype: 'toolbar',
            items: [{
                xtype: 'button',
                text: '添加',
                iconCls: 'icon-add'
            }, '-', {
                xtype: 'button',
                itemId: 'delete',
                text: '删除',
                iconCls: 'icon-delete'
            }]
        }]
    });