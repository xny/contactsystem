Ext.define('AM.model.Customers', {
    extend: 'Ext.data.Model',
    fields: [
    	{name: 'id'},
    	{name: 'name'},
    	{name: 'phoneNumber'},
    	{name: 'telephoneNumber'},
    	{name: 'email'},
    	{name: 'address'},
    	{name: 'companyType'},
    	{name: 'companyTypeId', convert: function (value, record) {
    					if(!!value){
    						return value;
    					}else{
	    					var obj = record.get('companyType');
	                        return  !!obj&&!!obj.id ? obj.id : '';
    					}	
                    }
        },
    	{name: 'companyName'},
    	{name: 'site'},
    	{name: 'position'},
    	{name: 'companyContact'},
    	{name: 'fax'},
    	{name: 'zipCode'},
    	{name: 'business'},
    	{name: 'subCompany'},
    	{name: 'subCompanyId', convert: function (value, record) {
    					if(!!value){
    						return value;
    					}else{
	    					var obj = record.get('subCompany');
	                        return  !!obj&&!!obj.id ? obj.id : '';
    					}	
                    }
        },
    	{name: 'frequency'},
    	{name: 'remark'},
    	{name: 'createTime'}
    ]
});