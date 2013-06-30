Ext.define('AM.store.navtree.SystemTree', {
    extend : 'Ext.data.TreeStore',
    autoLoad : true,
 	root: {
        expanded: true,
        children: [
            { text: "系统用户管理", id:"UsersManager", leaf: true },
            { text: "行业管理", id:"CompanyTypeManager", leaf: true}
        ]
    }
}); 