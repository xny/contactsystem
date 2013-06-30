Ext.define('AM.store.Customers', {
        extend:'Ext.data.Store',      
        autoLoad: true,
        model:'AM.model.Customers',
        pageSize: 5,
        proxy : {
            type : 'ajax',
            url: '/getContactList',
            reader : {
                type : 'json',
                root : 'data',
                successProperty : 'success',
                totalProperty: 'total'
            }
        }
    });
    
