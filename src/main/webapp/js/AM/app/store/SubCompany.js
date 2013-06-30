Ext.define('AM.store.SubCompany', {
        extend:'Ext.data.Store',      
        autoLoad: true,
        model: 'AM.model.SubCompany',
        proxy : {
            type : 'ajax',
            url: '/subCompanyList',
            reader : {
                type : 'json',
                root : 'data',
                successProperty : 'success'
            }
        }
    });