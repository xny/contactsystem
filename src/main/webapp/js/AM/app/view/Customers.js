var _frequencystore = Ext.create('Ext.data.Store', {
                        fields: ['abbr', 'name'],
                        data : [
                            {"abbr":"1", "name":"非常高"},
                            {"abbr":"2", "name":"高"},
                            {"abbr":"3", "name":"一般"},
                            {"abbr":"4", "name":"低"},
                            {"abbr":"5", "name":"非常低"}
                        ]
                });
_companyTypestore = Ext.create('AM.store.CompanyType');
_subCompanyestore = Ext.create('AM.store.SubCompany');
Ext.define('AM.view.Customers', {
			extend : 'Ext.grid.Panel',
			alias : 'widget.customers',
			xtype : 'x-customers',
			plugins : [{
						ptype : 'rowediting',
						pluginId : 'rowediting',
						clicksToEdit : 1,
						saveBtnText : '保存',
						cancelBtnText : '取消'
					}],
			autoScroll :true,
			forceFit : false,
			store : 'Customers',
			bbar: {
                xtype: 'pagingtoolbar',
                pageSize: 10,
                store: 'Customers',
                displayMsg:'显示第{0}条数据到{1}条数据,一共有{2}条', 
                emptyMsg:'没有记录',
                firstText:'第一页',
                nextText:'下一页',
                prevText:'上一页',
                lastText:'最后一页',
                refreshText:'刷新'
            },
			columns : [{
						text : 'ID',
						width : 40,
						sortable : true,
						dataIndex : 'id'
					}, {
						text : '姓名',
						width : 110,
						sortable : true,
						dataIndex : 'name',
						allowBlank : false,
						blankText : '姓名不能为空',
						field : {
							xtype : 'textfield'
						}
					}, {
						text : '手机',
						width : 110,
						sortable : true,
						dataIndex : 'phoneNumber',
						field : {
							xtype : 'textfield'
						}
					}, {
						text : '电话',
						width : 110,
						sortable : true,
						dataIndex : 'telephoneNumber',
						field : {
							xtype : 'textfield'
						}
					}, {
						text : 'Email',
						width : 140,
						sortable : true,
						dataIndex : 'email',
						field : {
							xtype : 'textfield'
						}
					}, {
						text : '家庭住址',
						width : 280,
						sortable : true,
						dataIndex : 'address',
						field : {
							xtype : 'textfield'
						}
					}, {
						text : '公司行业',
						width : 120,
						sortable : true,
						dataIndex : 'companyTypeId',
						field: {
			                xtype: 'combobox',
			                displayField: 'type',
			                valueField:'id',
			                queryMode: 'local',
			                editable:false,
			                store:_companyTypestore
			            },
			            renderer:function (value){
			                return !!(_companyTypestore.findRecord('id', value)) ?  _companyTypestore.findRecord('id', value).data.type : "";
			            }
					}, {
						text : '公司名',
						width : 160,
						sortable : true,
						dataIndex : 'companyName',
						field : {
							xtype : 'textfield'
						}
					}, {
						text : '公司网址',
						width : 150,
						sortable : true,
						dataIndex : 'site',
						field : {
							xtype : 'textfield'
						}
					}, {
						text : '职务',
						width : 110,
						sortable : true,
						dataIndex : 'position',
						field : {
							xtype : 'textfield'
						}
					}, {
						text : '公司联系人',
						width : 110,
						sortable : true,
						dataIndex : 'companyContact',
						field : {
							xtype : 'textfield'
						}
					}, {
						text : '传真',
						width : 110,
						sortable : true,
						dataIndex : 'fax',
						field : {
							xtype : 'textfield'
						}
					}, {
						text : '邮编',
						width : 80,
						sortable : true,
						dataIndex : 'zipCode',
						field : {
							xtype : 'textfield'
						}
					}, {
						text : '业务往来事由',
						width : 280,
						sortable : true,
						dataIndex : 'business',
						field : {
							xtype : 'textfield'
						}
					}, {
						text : '由哪个子公司发展',
						width : 250,
						sortable : true,
						dataIndex : 'subCompanyId',
						field: {
			                xtype: 'combobox',
			                displayField: 'subCompanyName',
			                valueField:'id',
			                queryMode: 'local',
			                editable:false,
			                store:_subCompanyestore
			            },
			            renderer:function (value){
			                return !!(_subCompanyestore.findRecord('id', value)) ?  _subCompanyestore.findRecord('id', value).data.subCompanyName : "";
			            }
					}, {
						text : '联系频率',
						width : 80,
						sortable : true,
						dataIndex : 'frequency',
						field: {
			                xtype: 'combobox',
			                displayField: 'name',
			                valueField:'abbr',
			                queryMode: 'local',
			                editable:false,
			                store:_frequencystore
			            },
			            renderer:function (value){
			                return !!(_frequencystore.findRecord('abbr', value)) ?  _frequencystore.findRecord('abbr', value).data.name : "";
			            }
					}, {
						text : '备注',
						width : 350,
						sortable : true,
						dataIndex : 'remark',
						field : {
							xtype : 'textfield'
						}
					}, {
						text : '操作时间',
						width : 150,
						sortable : true,
						dataIndex : 'createTime',
						renderer:function (value){
							if(isNaN(value)){
								return '';
							}else{
			               		return Ext.util.Format.date(new Date(value),'Y-m-d H:i:s');
			               	}
			            }
					}],
			dockedItems : [{
						xtype : 'toolbar',
						items : [{
									xtype : 'button',
									text : '添加',
									iconCls : 'icon-add',
									action:'add'
								}, '-', {
									xtype : 'button',
									itemId : 'delete',
									text : '删除',
									iconCls : 'icon-delete',
									action:'del'
								}, '-' ,{
							        xtype: 'textfield',
							        name: 'name',
							        id:'name',
							        fieldLabel: '姓名',
							        labelAlign :'right'
							    },{
							        xtype: 'combobox',
					                displayField: 'type',
					                valueField:'id',
					                queryMode: 'local',
					                editable:true,
					                store:_companyTypestore,
							        name: 'companyTypeId',
							        id: 'companyTypeId',
							        fieldLabel: '公司行业',
							        labelAlign :'right'
							    },{
							        xtype: 'combobox',
					                displayField: 'subCompanyName',
					                valueField:'id',
					                queryMode: 'local',
					                editable:true,
					                store:_subCompanyestore,
							        name: 'subCompanyId',
							        id: 'subCompanyId',
							        fieldLabel: '宝盛子公司',
							        labelAlign :'right'
							    },{
							        xtype: 'combobox',
					                displayField: 'name',
					                valueField:'abbr',
					                queryMode: 'local',
					                editable:true,
					                store:_frequencystore,
							        name: 'frequency',
							        id: 'frequency',
							        fieldLabel: '联系频率',
							        labelAlign :'right'
							    },{
									xtype : 'button',
									text : '查询',
									iconCls : 'icon-query',
									action:'query'
								}]
					}]
		});
		