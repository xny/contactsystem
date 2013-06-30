Ext.define('AM.view.CompanyType', {
        extend : 'Ext.grid.Panel',
        alias : 'widget.companytype',
        xtype: 'x-companytype',
        plugins: [{ptype:'rowediting',pluginId:'rowediting',clicksToEdit: 1,saveBtnText: '保存',
        cancelBtnText: '取消'}],
        frame: true,
        store: 'CompanyType',
        columns: [
        {
            text: 'ID',
            width: 40,
            sortable: true,
            dataIndex: 'id'
        },
        {
            text: '行业名',
            flex: 1,
            sortable: true,
            dataIndex: 'type',
            field: {
                xtype: 'textfield'
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