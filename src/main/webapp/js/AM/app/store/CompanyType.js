Ext.define('AM.store.CompanyType', {
        extend:'Ext.data.Store',      
        autoLoad: true,
        model: 'AM.model.CompanyType',
        proxy : {
            type : 'ajax',
            url: '/companyTypeList',
            reader : {
                type : 'json',
                root : 'data',
                successProperty : 'success'
            }
        }
    });