Ext.define('AM.store.Users', {
        extend:'Ext.data.Store',      
        autoLoad: true,
        model:'AM.model.Users',
        proxy : {
            type : 'ajax',
            url: '/accountList',
            reader : {
                type : 'json',
                root : 'data',
                successProperty : 'success'
            }
        }
    });