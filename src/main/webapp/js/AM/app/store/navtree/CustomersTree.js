Ext.define('AM.store.navtree.CustomersTree', {
    extend : 'Ext.data.TreeStore',
    autoLoad : true,
 	root: {
        expanded: true,
        children: [
            { text: "客户管理", id:"CustomersManager", leaf: true }
        ]
    }
}); 