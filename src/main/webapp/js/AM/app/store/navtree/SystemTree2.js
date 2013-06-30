Ext.define('AM.store.navtree.SystemTree2', {
    extend : 'Ext.data.TreeStore',
    autoLoad : true,
 	root: {
        expanded: true,
        children: [
            { text: "行业管理", id:"CompanyTypeManager", leaf: true}
        ]
    }
}); 