var tree = new Ext.tree.TreePanel({
	useArrows : true,
	autoScroll : true,
	animate : true,
	enableDD : true,
	containerScroll : true,
	border : false,
	id : 'houseTreePanel',
	dataUrl : '/testhellwworld/getAllHouses',
	root : {
		nodeType : 'async',
		text : 'All Houses',
		draggable : false
	},
	contextMenu : new Ext.menu.Menu({
				items : [{
					id : 'publish-node',
					text : 'Publish',
					handler : function(node){
						//alert('done')
					}
				},{
					id : 'auth-node',
					text : 'Test Web Service',
					handler : function(node){}
				}],
				listeners : {
					afterrender : function(obj) {
						var a = 10;
					},
					itemclick : function(item) {
						switch (item.id) {
							case 'publish-node' :
							var myMask = new Ext.LoadMask(
									Ext.getBody(), {
										msg : "Please wait..."
									});
								Ext.Ajax.request({
									url : '/testhellwworld/publishHouseJson',
									params : {
										houseFk : item.parentMenu.contextNode.id
									},
									success : function(responce) {
										myMask.hide();
										Ext.Msg.alert('Status',responce.responseText);
									},
									failure : function(responce) {
										myMask.hide();
										Ext.Msg.alert('Status',responce.responseText);
									}
								});
							break;
							case 'auth-node' :
							var myMask = new Ext.LoadMask(
									Ext.getBody(), {
										msg : "Authenticating..."
									});
									
									var obj = {
									userName : 'Gaurav',
									password : 'Gaurav',
									tokenId : '1234'
									}
								Ext.Ajax.request({
									url : '/testhellwworld/authUser',
									params : {
										json : JSON.stringify(obj)
									},
									success : function(responce) {
										myMask.hide();
										Ext.Msg.alert('Status',responce.responseText);
									},
									failure : function(responce) {
										myMask.hide();
										Ext.Msg.alert('Status',responce.responseText);
									}
								});
							break;
						}
					}
				}
			}),
	listeners : {
		afterrender : function(obj) {
			Ext.getCmp('houseTreePanel').getRootNode().reload();

		},
		contextmenu : function(node, e) {
			node.select();
			var c = node.getOwnerTree().contextMenu;
			c.contextNode = node;
			c.showAt(e.getXY());
		},
		dblclick : function(node, e) {
			var centerPanel = Ext.getCmp('center-panel');
			centerPanel.removeAll();
			var houseTab = Ext.getCmp('house' + node.id);
			if (!houseTab) {
				var roomStore = new Ext.data.JsonStore({
							autoDestroy : true,
							autoLoad : true,
							url : '/testhellwworld/getRooms',
							baseParams : {
								houseFk : node.id
							},
							idProperty : 'id',
							fields : ['id', 'name']

						});
				var roomPanel = new Ext.grid.GridPanel({
					store : roomStore,
					loadMask : true,
					width : 300,
					height : 300,
					frame : true,
					title : 'Rooms',
					tbar : new Ext.Toolbar({
						items : ['->', {
							text : 'Add Room',
							id : 'addRoom',
							handler : function(btn) {
								var win;
								if (!win) {
									win = new Ext.Window({
										layout : 'fit',
										width : 500,
										modal : true,
										title : 'New Room',
										height : 300,
										animateTarget : 'addRoom',
										closeAction : 'hide',
										plain : false,
										items : new Ext.FormPanel({
											labelWidth : 140,
											id : 'addRoomForm',
											border : false,
											buttons : [{
												text : 'Submit',
												handler : function(btn) {
													var myMask = new Ext.LoadMask(
															Ext.getBody(), {
																msg : "Please wait..."
															});
													myMask.show();
													var roomFields = Ext.getCmp('roomInfoForm').items.items;
													var roomValues = {};
													for (var i = 0; i < roomFields.length; i++) {
														var field = roomFields[i];
														roomValues[field.name] = field.getValue();
													}
													win.close();
													Ext.Ajax.request({
														url : '/testhellwworld/addRooms',
														params : {
															json : JSON.stringify(roomValues)
														},
														success : function(responce) {
															myMask.hide();
															Ext.Msg.alert('Status',responce.responseText);
														},
														failure : function(responce) {
															myMask.hide();
															Ext.Msg.alert('Status',responce.responseText);
														}
													});
												}
											}, {
												text : 'Close',
												handler : function() {
													win.hide();
												}
											}],
											layout : 'fit',
											padding : '10 10 10 10',
											items : [{
												layout : 'form',
												id : 'roomInfoForm',
												border : false,
												defaults : {
													width : 230
												},
												items : [{
													xtype : 'textfield',
															fieldLabel : 'Name',
															name : 'name',
															allowBlank : false
														}, {
													xtype : 'textfield',
															fieldLabel : 'houseFK',
															name : 'houseFk',
															hidden : true,
															value : node.id
														}]
											}]
										})
									});
								}
								win.show();
							}
						}]
					}),
					columns : [
						    {
						        xtype: 'actioncolumn',
						        width : 30,
						        icon: 'resources/icons/add.png',
						        text: 'Add Board',
								id : 'addBoardAction',
								tooltip : 'Add Board',
						        handler: function(grid, rowIndex, colIndex, item, e) {
					        	  	
							        var rec = grid.getStore().getAt(rowIndex);
									var win;
									if (!win) {
										win = new Ext.Window({
											layout : 'fit',
											width : 500,
											modal : true,
											title : 'New Board',
											height : 300,
											animateTarget : 'addBoardAction',
											closeAction : 'hide',
											plain : false,
											items : new Ext.FormPanel({
												labelWidth : 140,
												id : 'addBoardForm',
												border : false,
												buttons : [{
													text : 'Submit',
													handler : function(btn) {
														var myMask = new Ext.LoadMask(
																Ext.getBody(), {
																	msg : "Please wait..."
																});
														myMask.show();
														var boardFields = Ext.getCmp('boardInfoForm').items.items;
														var boardValues = {};
														for (var i = 0; i < boardFields.length; i++) {
															var field = boardFields[i];
															boardValues[field.name] = field.getValue();
														}
														win.close();
														Ext.Ajax.request({
															url : '/testhellwworld/addBoard',
															params : {
																json : JSON.stringify(boardValues)
															},
															success : function(responce) {
																myMask.hide();
																Ext.Msg.alert('Status',responce.responseText);
															},
															failure : function(responce) {
																myMask.hide();
																Ext.Msg.alert('Status',responce.responseText);
															}
														});
													}
												}, {
													text : 'Close',
													handler : function() {
														win.hide();
													}
												}],
												layout : 'fit',
												items : [{
													padding : '10 10 10 10',
													layout : 'form',
													id : 'boardInfoForm',
													border : false,
													defaults : {
														width : 230
													},
													defaultType : 'textfield',
													items : [{
																fieldLabel : 'Name',
																name : 'name',
																allowBlank : false
															},new Ext.form.ComboBox({
																    fieldLabel: 'Device Type',
																    name : 'deviceTypeFk',
																    store: new Ext.data.JsonStore({
																			autoDestroy : true,
																			autoLoad : true,
																			triggerAction : 'all',
																			proxy : new Ext.data.HttpProxy({
																			    method: 'POST',
																			   url : '/testhellwworld/getDeviceTypeList'
																			   
																			}),
																			idProperty : 'id',
																			fields : ['id', 'name']
																		}),
																    valueField: 'id',
																    displayField: 'name'
																}), 
															{
																fieldLabel : 'roomFk',
																name : 'roomFk',
																hidden : true,
																value : rec.get('id')
															}]
												}]
											})
										});
									}
									win.show();
								
							        }      
							    },{
									header : 'Id',
									dataIndex : 'id',
									width : 30
								}, {
									header : 'Name',
									dataIndex : 'name'
								},{
							        xtype: 'actioncolumn',
							        width : 30,
							        icon: 'resources/icons/application_go.png',
							        text: 'View Board',
									tooltip : 'View Board',
							        handler: function(grid, rowIndex, colIndex, item, e) {
							         var rec = grid.getStore().getAt(rowIndex);
							         var boardTab = Ext.getCmp('boardMain-panel');
					        	 	 boardTab.removeAll();
							         var boardStore = new Ext.data.JsonStore({
										autoDestroy : true,
										autoLoad : true,
										url : '/testhellwworld/getBoardsforRoom',
										baseParams : {
											roomFk : rec.get('id')
										},
										idProperty : 'id',
										fields : ['id', 'name']
			
									});
									var boardPanel = new Ext.grid.GridPanel({
										store : boardStore,
										loadMask : true,
										width : 180,
										height : 300,
										frame : true,
										title : 'Boards',
										columns : [
												    {
												        xtype: 'actioncolumn',
												        width : 30,
												        icon: 'resources/icons/add.png',
												        text: 'Add Points',
														id : 'addPointAction',
														tooltip : 'Add Point',
												        handler:function(grid, rowIndex, colIndex, item, e) { 
												        	onAddPointAction(grid, rowIndex, colIndex, item, e)  
												        }
													    },{
															header : 'Id',
															dataIndex : 'id',
															width : 30
														}, {
															header : 'Name',
															dataIndex : 'name'
														},{
													        xtype: 'actioncolumn',
													        width : 30,
													        icon: 'resources/icons/application_go.png',
													        text: 'View Points',
															tooltip : 'View Points',
													        handler: function(grid, rowIndex, colIndex, item, e) {
													         var rec = grid.getStore().getAt(rowIndex);
													         var pointTab = Ext.getCmp('pointMain-panel');
											        	 	 pointTab.removeAll();
													         var pointStore = new Ext.data.JsonStore({
																autoDestroy : true,
																autoLoad : true,
																url : '/testhellwworld/getPoints',
																baseParams : {
																	boardFk : rec.get('id')
																},
																idProperty : 'id',
																fields : ['id', 'name']
									
															});
															var pointPanel = new Ext.grid.GridPanel({
																store : pointStore,
																loadMask : true,
																width : 300,
																height : 300,
																frame : true,
																title : 'Points',
																columns : [
																	    {
																	        xtype: 'actioncolumn',
																	        width : 30,
																	        icon: 'resources/icons/add.png',
																	        text: 'Add Points',
																			hidden : true,
																			tooltip : 'Add Point',
																	        handler: function(grid, rowIndex, colIndex, item, e) {}      
																	    },{
																			header : 'Id',
																			dataIndex : 'id',
																			width : 30
																		}, {
																			header : 'Name',
																			dataIndex : 'name'
																		},{
																	        xtype: 'actioncolumn',
																	        width : 30,
																	        icon: 'resources/icons/application_go.png',
																	        text: 'View Points',
																			hidden : true,
																			tooltip : 'View Points',
																	        handler: function(grid, rowIndex, colIndex, item, e) {}      
																	    }],
																viewConfig : {
																	forceFit : true
																}
															});
													       
													         pointTab.add(pointPanel);
													         pointTab.setActiveTab(pointPanel);
												        }      
												    }],
										viewConfig : {
											forceFit : true
										}
									});
							       
							         boardTab.add(boardPanel);
							         boardTab.setActiveTab(boardPanel);
						        }      
						    }],
					viewConfig : {
						forceFit : true
					}
				});
				
				var areaPanel =  new Ext.TabPanel({
						deferredRender : true,
						id : 'areaMain-panel',
						title : 'Areas',
						//width : '30%',
						flex : 1,
						height : 300,
						activeTab : 0,
						items : [roomPanel]
					});
					
				var boardMainPanel =  new Ext.TabPanel({
						deferredRender : false,
						id : 'boardMain-panel',
						title : 'Boards',
						flex : 1,
						//width : '30%',
						height : 300
					});
				
				
				var pointMainPanel =  new Ext.TabPanel({
						deferredRender : false,
						id : 'pointMain-panel',
						title : 'Points',
						flex : 1,
						//width : '30%',
						height : 300
					});
					
				var configPanel = new Ext.Panel({
						id : 'config-panel',
						deferredRender : false,
						layout : 'hbox',
						height : 300,
						width : '100%',
						items : [areaPanel,boardMainPanel,pointMainPanel]
					});
					
				var estiPanel = new Ext.Panel({
						id : 'esti-panel',
						deferredRender : false,
						title : 'Estimations',
						layout : 'fit',
						width : '100%',
						height : 300
					});

				var tabName = node.text;
				
				houseTab = {
					title : tabName,
					closable : true,
					layout:'vbox',
					id : 'house' + node.id,
					items : [configPanel,estiPanel]
				}
				centerPanel.add(houseTab);
			}
			centerPanel.setActiveTab('house' + node.id);
		}
	}
});

var menu = new Ext.menu.Menu({
	id : 'houseMenu',
	/*
	 * style : { overflow : 'visible' // For the Combo popup },
	 */
	items : [{
		text : 'Add House',
		id : 'addHouse',
		xtype : 'button',
		handler : function(btn) {

			var win;
			if (!win) {
				win = new Ext.Window({
					layout : 'fit',
					width : 500,
					modal : true,
					title : 'New House',
					height : 300,
					animateTarget : 'addHouse',
					closeAction : 'hide',
					plain : false,
					items : new Ext.FormPanel({
						labelWidth : 140,
						id : 'addUserForm',
						border : false,
						buttons : [{
							text : 'Submit',
							handler : function(btn) {
								var myMask = new Ext.LoadMask(Ext.getBody(), {
											msg : "Please wait..."
										});
								myMask.show();
								var userFields = Ext.getCmp('userInfoForm').items.items;
								var houseFields = Ext.getCmp('houseInfoForm').items.items;
								var userValues = {};
								var houseValues = {};
								for (var i = 0; i < userFields.length; i++) {
									var field = userFields[i];
									userValues[field.name] = field.getValue();
								}

								for (var i = 0; i < houseFields.length; i++) {
									var field = houseFields[i];
									houseValues[field.name] = field.getValue();
								}

								var userList = [];
								userList.push(userValues);
								houseValues.userList = userList
								
								win.close();
								Ext.Ajax.request({
											url : '/testhellwworld/addHouse',
											params : {
												json : JSON.stringify(houseValues)
											},
											success : function(responce) {
												myMask.hide();
												Ext.getCmp('houseTreePanel').getRootNode().reload();
												Ext.Msg.alert('Status',responce.responseText);
											},
											failure : function(responce) {
												myMask.hide();
												Ext.Msg.alert('Status',responce.responseText);
											}
										});
							}
						}, {
							text : 'Close',
							handler : function() {
								win.hide();
							}
						}],
						layout : 'fit',
						items : {
							xtype : 'tabpanel',
							activeTab : 0,
							defaults : {
								autoHeight : true,
								bodyStyle : 'padding:10px'
							},
							items : [{
										title : 'House Information',
										layout : 'form',
										id : 'houseInfoForm',
										defaults : {
											width : 230,
											bodyStyle : 'padding:10px'
										},
										defaultType : 'textfield',

										items : [{
													fieldLabel : 'House Name',
													name : 'name'
												}, {
													fieldLabel : 'Address 1',
													name : 'address1'
												}, {
													fieldLabel : 'Address 2',
													name : 'address2'
												}, {
													fieldLabel : 'City',
													name : 'city'
												}, {
													fieldLabel : 'State',
													name : 'state'
												}, {
													fieldLabel : 'Phone Number',
													name : 'phoneNumber'
												}]
									},{
										title : 'User Details',
										layout : 'form',
										id : 'userInfoForm',
										border : false,
										defaults : {
											width : 230,
											bodyStyle : 'padding:10px'
										},
										defaultType : 'textfield',
										items : [{
													fieldLabel : 'First Name',
													name : 'firstName',
													allowBlank : false
												}, {
													fieldLabel : 'Last Name',
													name : 'lastName'
												}, {
													fieldLabel : 'Company',
													name : 'company'
												}, {
													fieldLabel : 'Email',
													name : 'email',
													vtype : 'email'
												}, {
													fieldLabel : 'User Name',
													name : 'userName'
												}, {
													fieldLabel : 'Password',
													name : 'password'
												}, {
													fieldLabel : 'Mobile Number',
													name : 'mobileNumber'
												}]
									}]
						}
					})

				});
			}
			win.show();
		}
	}]
});

var tb = new Ext.Toolbar({
			items : [{
						xtype : 'textfield',
						width : 100,
						name : 'houseSearch',
						emptyText : 'Search House'
					}, '->', {
						xtype : 'button',
						text : 'Manage House',
						menu : menu
					}

			]
		});

function onButtonClick(btn) {
	Ext.example.msg('Button Click', 'You clicked the "{0}" button.', btn.text);
}

function onAddPointAction (grid, rowIndex, colIndex, item, e)
{
	
											        	  	
	var rec = grid.getStore().getAt(rowIndex);
	var win;
	if (!win) {
		win = new Ext.Window({
			layout : 'fit',
		width : 500,
		modal : true,
		title : 'New Point',
		height : 300,
		animateTarget : 'addPointAction',
		closeAction : 'hide',
		plain : false,
		items : new Ext.FormPanel({
			labelWidth : 140,
			id : 'addPointForm',
			border : false,
			buttons : [{
				text : 'Submit',
				handler : function(btn) {
					var myMask = new Ext.LoadMask(
							Ext.getBody(), {
								msg : "Please wait..."
							});
					myMask.show();
					var pointFields = Ext.getCmp('pointInfoForm').items.items;
					var pointValues = {};
					for (var i = 0; i < pointFields.length; i++) {
						var field = pointFields[i];
						pointValues[field.name] = field.getValue();
					}
					win.close();
					Ext.Ajax.request({
						url : '/testhellwworld/addPoints',
						params : {
							json : JSON.stringify(pointValues)
						},
						success : function(responce) {
							myMask.hide();
							Ext.Msg.alert('Status',responce.responseText);
						},
						failure : function(responce) {
							myMask.hide();
							Ext.Msg.alert('Status',responce.responseText);
						}
					});
				}
			}, {
				text : 'Close',
				handler : function() {
					win.hide();
				}
			}],
			layout : 'fit',
			items : [{
				padding : '10 10 10 10',
				layout : 'form',
				id : 'pointInfoForm',
				border : false,
				defaults : {
					width : 230
				},
				defaultType : 'textfield',
				items : [{
							fieldLabel : 'Name',
							name : 'name',
							allowBlank : false
						},new Ext.form.ComboBox({
							    fieldLabel: 'Point Type',
							    name : 'pointTypeFk',
							    store: new Ext.data.JsonStore({
										triggerAction : 'all',
										proxy : new Ext.data.HttpProxy({
										   method: 'POST',
										   url : '/testhellwworld/getPointTypeList'
										   
										}),
										idProperty : 'id',
										fields : ['id', 'name']
									}),
							    valueField: 'id',
							    displayField: 'name',
							    forceSelection: true,
						        triggerAction: 'all',
						        selectOnFocus:true,
							     listeners:{
								         'select': function (combo,record,index){
								         	var pointTypeFk = record.get('id');
								         	var boardFk = rec.get('id');
								         	var pointObj = {
								         	pointTypeFk : pointTypeFk,
								         	boardFk : boardFk
								         	};
								         	Ext.Ajax.request({
												url : '/testhellwworld/getActionForPointType',
												params : {
													json : JSON.stringify(pointObj)
												},
												success : function(responce) {
													
													var data = Ext.decode(responce.responseText);
													var comboArray = [];
													
													for ( var i=0;i<data.length;i++)
													{
														var action = data[i];
														var store = new Ext.data.JsonStore({
																	autoDestroy : true,
																	autoLoad : true,
																	triggerAction : 'all',
																	proxy : new Ext.data.HttpProxy({
																	    method: 'POST',
																	   url : '/testhellwworld/getAvailablePinsForAction'
																	}),
																	idProperty : 'id',
																	fields : ['id', 'pinNumber']
																});
																
														store.setBaseParam("boardFk",boardFk);
														store.setBaseParam("pinType",action.pinType);
													    var combo = new Ext.form.ComboBox({
														    fieldLabel : action.name,
														    name : action.name,
														    actionFk : action.id,
														    store: store,
														    valueField: 'id',
														    displayField: 'pinNumber',
														    forceSelection: true,
														    triggerAction: 'all',
														    selectOnFocus:true
														})
													
														comboArray.push(combo);
													}
													
													var fieldset =  {
													        xtype:'fieldset',
													        title: 'Action Details', 
													        autoHeight:true,
													        name : 'pointPinActionList',
													        columnWidth: 0.5,
													        width : 450,
													        collapsed: false,
													        getValue  : function(obj){
													        	//alert("hi");
													        	var comboList = this.items.items;
													        	var pointPinActionList = [];
													        	for(var i=0;i<comboList.length;i++)
													        	{
													        		var combo = comboList[i];
													        		var temp = {
													        			id : combo.getValue(),
													        			pinNumber : combo.getRawValue(),
													        			actionFk : combo.actionFk
													        		}
													        		pointPinActionList.push(temp);
													        	}
													        	return pointPinActionList;
													        },
													        items :[comboArray]
													    }
													
													Ext.getCmp('pointInfoForm').add(fieldset);
													Ext.getCmp('pointInfoForm').doLayout();
													
												},
												failure : function(responce) {
													myMask.hide();
													Ext.Msg.alert('Status',responce.responseText);
												}
											});
	
								         }
								    }
							}), {
							fieldLabel : 'boardFk',
							name : 'boardFk',
							hidden : true,
							value : rec.get('id')
							}]
				}]
			})
		});
	}
	win.show();
													        
}