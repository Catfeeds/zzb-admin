webpackJsonp([0],{143:function(e,t,a){function o(e){a(235)}var s=a(18)(a(173),a(253),o,"data-v-2b943ed0",null);e.exports=s.exports},144:function(e,t,a){function o(e){a(238)}var s=a(18)(a(174),a(256),o,"data-v-4324ba52",null);e.exports=s.exports},151:function(e,t,a){e.exports={default:a(152),__esModule:!0}},152:function(e,t,a){var o=a(19),s=o.JSON||(o.JSON={stringify:JSON.stringify});e.exports=function(e){return s.stringify.apply(s,arguments)}},173:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=a(44),s=a.n(o);t.default={data:function(){return{word:"",searchWord:"",orderBy:2,listLoading:!1,exportLoading:!1,tableData:[],editDialog:!1,addDialog:!1,eForm:{managerPowerChild:{checkList1:[],checkList2:[],checkList3:[],checkList4:[],checkList5:[]}},aForm:{checkList1:[],checkList2:[],checkList3:[],checkList4:[],checkList5:[]},currentPage:1,pageSize:20,total:0,tableTop:0,rules:{password:[{required:!0,message:"内容不能为空",trigger:"blur"},{min:4,message:"大于 4 个字符",trigger:"blur"}],account:[{validator:function(e,t,a){var o=/^[a-zA-Z0-9_]{4,16}$/;return t?o.test(t)?a():a(new Error("用户名由字母数字下划线组成，限6-16位")):a(new Error("用户名不能为空"))},required:!0,trigger:"blur"}],contacts:[{required:!0,message:"内容不能为空",trigger:"blur"}]}}},props:["API"],computed:{tableMaxHeight:function(){return this.$store.commit("setTableMaxHeight",this.tableTop),this.$store.state.tableMaxHeight}},methods:{search:function(e){this.word=this.searchWord,this.currentPage=1,this.getList(this.word)},sortTable:function(){this.orderBy=1==this.orderBy?2:1,this.getList(this.word)},formatTime:function(e,t){return this.$common.getNowTime(e.createAt&&e.createAt.time)},formatStatus:function(e,t){return 1==e.managerStatus?"激活":2==e.managerStatus?"冻结":"未知"},editItem:function(e,t){var a=this,o={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{manager_id:t.id}};this.$common._post("manager/detail",o,this,function(e){a.editDialog=!0,a.eForm=s()({},e.manager)})},editSave:function(e){var t=this;this.$refs[e].validate(function(e){e&&t.$confirm("是否确认修改?","提示",{}).then(function(){if(2==t.eForm.managerStatus&&JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName==t.eForm.account)return t.$message({type:"error",message:"无法冻结自己"}),void(t.eForm.managerStatus=1);var e={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{manager_id:t.eForm.id,account:t.eForm.account,password:t.eForm.password,manager_status:t.eForm.managerStatus,contacts:t.eForm.contacts,power:{checkList1:t.eForm.managerPowerChild.checkList1,checkList2:t.eForm.managerPowerChild.checkList2,checkList3:t.eForm.managerPowerChild.checkList3,checkList4:t.eForm.managerPowerChild.checkList4,checkList5:t.eForm.managerPowerChild.checkList5}}};t.$common._post("manager/update",e,t,function(e){t.editDialog=!1,t.$message({type:"success",message:e.description}),t.getList(t.word)})}).catch(function(){})})},addItem:function(){this.aForm={account:"",password:"",contacts:"",managerStatus:1,checkList1:[],checkList2:[],checkList3:[],checkList4:[],checkList5:[]},this.addDialog=!0},addSave:function(e){var t=this;this.$refs[e].validate(function(e){e&&t.$confirm("是否确认添加?","提示",{}).then(function(){var e={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{account:t.aForm.account,password:t.aForm.password,manager_status:t.aForm.managerStatus,contacts:t.aForm.contacts,power:{checkList1:t.aForm.checkList1,checkList2:t.aForm.checkList2,checkList3:t.aForm.checkList3,checkList4:t.aForm.checkList4,checkList5:t.aForm.checkList5}}};t.$common._post("manager/add",e,t,function(e){t.addDialog=!1,t.$message({type:"success",message:"添加成功!"}),t.getList()})}).catch(null)})},deleteItem:function(e,t){var a={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{manager_id:t.id}};this.$common.deleteItem(this.API+"manager/delete",this,a)},changestatus:function(e,t){var a=this;this.$confirm("是否"+(1==t.managerStatus?"冻结":"激活")+"此账号?","提示",{}).then(function(){var e=1==t.managerStatus?2:1;if(2==e&&JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName==t.account)return a.$message({type:"error",message:"无法冻结自己"}),void(t.managerStatus=1);var o={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{manager_id:t.id,manager_status:e}};a.$common._post("manager/changestatus",o,a,function(e){a.$message({type:"success",message:e.description}),a.getList(a.word)})}).catch(function(){})},resetFields:function(e){this.$refs[e].resetFields(),this.eForm={managerPowerChild:{checkList1:[],checkList2:[],checkList3:[],checkList4:[],checkList5:[]}}},exportExc:function(){var e=this;this.exportLoading=!0;var t={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{}};this.$http.post(this.API+"exportExcelManager",t).then(function(t){e.exportLoading=!1;var t=t.body;"0"==t.body.result?location.href=t.body.fileUrl:e.$message(t.body.description)})},handleCurrentChange:function(e){this.currentPage=e,this.getList(this.word)},getList:function(){var e=this,t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"";this.listLoading=!0;var a={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{pageIndex:this.currentPage,pageSize:this.pageSize,contacts:t,orderBy:this.orderBy}};this.$common._post("manager/list",a,this,function(t){e.total=t.total,e.tableData=t.list,e.listLoading=!1},function(t){e.listLoading=!1,e.tableData=[]})}},mounted:function(){if(!sessionStorage.getItem("zhizunbao_login"))return this.$message("请登录账号"),void this.$router.push("/");this.getList(),this.tableTop=document.querySelector("#main table").getBoundingClientRect().top}}},174:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=a(151),s=a.n(o);t.default={data:function(){return{account:{name:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword,id:1},showPassword:!0}},props:["API"],methods:{isShow:function(){this.showPassword=!this.showPassword},savePassword:function(){var e=this;if(!(""!==this.account.password.trim()&&this.account.password.length>=6))return void this.$message("请输入 6 位以上密码");this.$confirm("是否确认修改?","提示",{}).then(function(){var t={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{manager_id:e.account.id,password:e.account.password}};e.$common._post("manager/updatepassword",t,e,function(t){e.$message({message:t.description,type:"success"});var a={userName:e.account.name,userPassword:e.account.password};sessionStorage.setItem("zhizunbao_login",s()(a)),e.showPassword=!1})}).catch(function(){})}},mounted:function(){if(!sessionStorage.getItem("zhizunbao_login"))return this.$message("请登录账号"),void this.$router.push("/")}}},217:function(e,t,a){t=e.exports=a(130)(!0),t.push([e.i,"","",{version:3,sources:[],names:[],mappings:"",file:"account-manage.vue",sourceRoot:""}])},220:function(e,t,a){t=e.exports=a(130)(!0),t.push([e.i,".content-box[data-v-4324ba52]{width:500px;margin:100px auto;padding:10px}.content-box .content[data-v-4324ba52]{font-size:20px;line-height:40px}.content-box .show-password[data-v-4324ba52]{margin-left:10px;font-size:20px}.content-box .btn-form[data-v-4324ba52]{margin-top:20px}","",{version:3,sources:["F:/webstorm/cichang/zhizunbao/src/views/settings/my-account.vue"],names:[],mappings:"AACA,8BACE,YAAa,AACb,kBAAmB,AACnB,YAAc,CACf,AACD,uCACI,eAAgB,AAChB,gBAAkB,CACrB,AACD,6CACI,iBAAkB,AAClB,cAAgB,CACnB,AACD,wCACI,eAAiB,CACpB",file:"my-account.vue",sourcesContent:["\n.content-box[data-v-4324ba52] {\n  width: 500px;\n  margin: 100px auto;\n  padding: 10px;\n}\n.content-box .content[data-v-4324ba52] {\n    font-size: 20px;\n    line-height: 40px;\n}\n.content-box .show-password[data-v-4324ba52] {\n    margin-left: 10px;\n    font-size: 20px;\n}\n.content-box .btn-form[data-v-4324ba52] {\n    margin-top: 20px;\n}\n"],sourceRoot:""}])},235:function(e,t,a){var o=a(217);"string"==typeof o&&(o=[[e.i,o,""]]),o.locals&&(e.exports=o.locals);a(131)("55a85d0b",o,!0)},238:function(e,t,a){var o=a(220);"string"==typeof o&&(o=[[e.i,o,""]]),o.locals&&(e.exports=o.locals);a(131)("3af838a0",o,!0)},253:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-row",[a("el-col",{staticClass:"search-bar"},[a("el-input",{staticClass:"w_200",attrs:{placeholder:"请输入联系人",icon:"search","on-icon-click":e.search},model:{value:e.searchWord,callback:function(t){e.searchWord=t},expression:"searchWord"}}),e._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{on:{click:e.sortTable}},[e._v(e._s(1==e.orderBy?"正":"倒")+"序排列")])],1),e._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{attrs:{icon:"plus"},on:{click:e.addItem}},[e._v("新建账户")])],1),e._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{attrs:{loading:e.exportLoading,icon:"document"},on:{click:e.exportExc}},[e._v("导出Excel")])],1)],1)],1),e._v(" "),a("el-row",[a("el-col",[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticClass:"table",attrs:{data:e.tableData,border:"",width:"100%","max-height":e.tableMaxHeight}},[a("el-table-column",{attrs:{align:"center",label:"序号",width:"80"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                        "+e._s(t.$index+1+e.pageSize*(e.currentPage-1))+"\n                    ")]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"account",label:"账户",width:"150"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"password",label:"密码",width:"150"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"contacts",label:"联系人",width:"160"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"managerStatus",label:"状态",formatter:e.formatStatus}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"createAt.time",label:"注册时间",width:"150",formatter:e.formatTime}}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"操作",width:"240"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"info",size:"small",icon:"edit"},on:{click:function(a){e.editItem(t.$index,t.row)}}},[e._v("编辑")]),e._v(" "),a("el-button",{attrs:{size:"small",type:1==t.row.managerStatus?"info":""},domProps:{innerHTML:e._s(1==t.row.managerStatus?"冻结":"激活")},on:{click:function(a){e.changestatus(t.$index,t.row)}}}),e._v(" "),a("el-button",{attrs:{size:"small",type:"danger",icon:"delete2"},on:{click:function(a){e.deleteItem(t.$index,t.row)}}},[e._v("删除")])]}}])})],1)],1)],1),e._v(" "),a("el-row",[a("el-col",[a("div",{staticClass:"block pagination"},[a("el-pagination",{attrs:{"current-page":e.currentPage,"page-size":e.pageSize,layout:"prev, pager, next, jumper","page-count":e.total},on:{"update:currentPage":function(t){e.currentPage=t},"current-change":e.handleCurrentChange}})],1)])],1),e._v(" "),a("el-dialog",{staticClass:"edit dialog-info",attrs:{title:"编辑",visible:e.editDialog},on:{"update:visible":function(t){e.editDialog=t},close:function(t){e.resetFields("editForm")}}},[a("el-form",{ref:"editForm",attrs:{model:e.eForm,"label-position":"","label-width":"110px",rules:e.rules}},[a("el-form-item",{attrs:{label:"账户：",prop:"account"}},[a("el-input",{model:{value:e.eForm.account,callback:function(t){e.eForm.account=t},expression:"eForm.account"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"密码：",prop:"password"}},[a("el-input",{model:{value:e.eForm.password,callback:function(t){e.eForm.password=t},expression:"eForm.password"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"联系人：",prop:"contacts"}},[a("el-input",{model:{value:e.eForm.contacts,callback:function(t){e.eForm.contacts=t},expression:"eForm.contacts"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"运营信息权限："}},[a("el-checkbox-group",{model:{value:e.eForm.managerPowerChild.checkList1,callback:function(t){e.eForm.managerPowerChild.checkList1=t},expression:"eForm.managerPowerChild.checkList1"}},[a("el-checkbox",{attrs:{label:"首页轮播"}}),e._v(" "),a("el-checkbox",{attrs:{label:"系统推送"}}),e._v(" "),a("el-checkbox",{attrs:{label:"运营文章"}}),e._v(" "),a("el-checkbox",{attrs:{label:"车型库管理"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"用户管理权限："}},[a("el-checkbox-group",{model:{value:e.eForm.managerPowerChild.checkList2,callback:function(t){e.eForm.managerPowerChild.checkList2=t},expression:"eForm.managerPowerChild.checkList2"}},[a("el-checkbox",{attrs:{label:"用户信息"}}),e._v(" "),a("el-checkbox",{attrs:{label:"车东信息"}}),e._v(" "),a("el-checkbox",{attrs:{label:"车辆信息"}}),e._v(" "),a("el-checkbox",{attrs:{label:"罚单信息"}}),e._v(" "),a("el-checkbox",{attrs:{label:"用户信用"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"订单管理权限："}},[a("el-checkbox-group",{model:{value:e.eForm.managerPowerChild.checkList3,callback:function(t){e.eForm.managerPowerChild.checkList3=t},expression:"eForm.managerPowerChild.checkList3"}},[a("el-checkbox",{attrs:{label:"订单"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"财务管理权限："}},[a("el-checkbox-group",{model:{value:e.eForm.managerPowerChild.checkList4,callback:function(t){e.eForm.managerPowerChild.checkList4=t},expression:"eForm.managerPowerChild.checkList4"}},[a("el-checkbox",{attrs:{label:"平台账户"}}),e._v(" "),a("el-checkbox",{attrs:{label:"提现管理"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"系统管理权限："}},[a("el-checkbox-group",{model:{value:e.eForm.managerPowerChild.checkList5,callback:function(t){e.eForm.managerPowerChild.checkList5=t},expression:"eForm.managerPowerChild.checkList5"}},[a("el-checkbox",{attrs:{label:"账户管理"}}),e._v(" "),a("el-checkbox",{attrs:{label:"我的账户"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"状态："}},[a("el-radio-group",{model:{value:e.eForm.managerStatus,callback:function(t){e.eForm.managerStatus=t},expression:"eForm.managerStatus"}},[a("el-radio",{staticClass:"radio",attrs:{label:1}},[e._v("激活")]),e._v(" "),a("el-radio",{staticClass:"radio",attrs:{label:2}},[e._v("冻结")])],1)],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",slot:"footer"},[a("el-button",{on:{click:function(t){e.editDialog=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.editSave("editForm")}}},[e._v("确 定")])],1)],1),e._v(" "),a("el-dialog",{staticClass:"add dialog-info",attrs:{title:"新建账户",visible:e.addDialog},on:{"update:visible":function(t){e.addDialog=t},close:function(t){e.resetFields("addForm")}}},[a("el-form",{ref:"addForm",attrs:{model:e.aForm,"label-position":"","label-width":"110px",rules:e.rules}},[a("el-form-item",{attrs:{label:"账户：",prop:"account"}},[a("el-input",{model:{value:e.aForm.account,callback:function(t){e.aForm.account=t},expression:"aForm.account"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"密码：",prop:"password"}},[a("el-input",{model:{value:e.aForm.password,callback:function(t){e.aForm.password=t},expression:"aForm.password"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"联系人：",prop:"contacts"}},[a("el-input",{model:{value:e.aForm.contacts,callback:function(t){e.aForm.contacts=t},expression:"aForm.contacts"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"运营信息权限："}},[a("el-checkbox-group",{model:{value:e.aForm.checkList1,callback:function(t){e.aForm.checkList1=t},expression:"aForm.checkList1"}},[a("el-checkbox",{attrs:{label:"首页轮播"}}),e._v(" "),a("el-checkbox",{attrs:{label:"系统推送"}}),e._v(" "),a("el-checkbox",{attrs:{label:"运营文章"}}),e._v(" "),a("el-checkbox",{attrs:{label:"车型库管理"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"用户管理权限："}},[a("el-checkbox-group",{model:{value:e.aForm.checkList2,callback:function(t){e.aForm.checkList2=t},expression:"aForm.checkList2"}},[a("el-checkbox",{attrs:{label:"用户信息"}}),e._v(" "),a("el-checkbox",{attrs:{label:"车东信息"}}),e._v(" "),a("el-checkbox",{attrs:{label:"车辆信息"}}),e._v(" "),a("el-checkbox",{attrs:{label:"罚单信息"}}),e._v(" "),a("el-checkbox",{attrs:{label:"用户信用"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"订单管理权限："}},[a("el-checkbox-group",{model:{value:e.aForm.checkList3,callback:function(t){e.aForm.checkList3=t},expression:"aForm.checkList3"}},[a("el-checkbox",{attrs:{label:"订单"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"财务管理权限："}},[a("el-checkbox-group",{model:{value:e.aForm.checkList4,callback:function(t){e.aForm.checkList4=t},expression:"aForm.checkList4"}},[a("el-checkbox",{attrs:{label:"平台账户"}}),e._v(" "),a("el-checkbox",{attrs:{label:"提现管理"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"系统管理权限："}},[a("el-checkbox-group",{model:{value:e.aForm.checkList5,callback:function(t){e.aForm.checkList5=t},expression:"aForm.checkList5"}},[a("el-checkbox",{attrs:{label:"账户管理"}}),e._v(" "),a("el-checkbox",{attrs:{label:"我的账户"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"状态："}},[a("el-radio-group",{model:{value:e.aForm.managerStatus,callback:function(t){e.aForm.managerStatus=t},expression:"aForm.managerStatus"}},[a("el-radio",{staticClass:"radio",attrs:{label:1}},[e._v("激活")]),e._v(" "),a("el-radio",{staticClass:"radio",attrs:{label:2}},[e._v("冻结")])],1)],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",slot:"footer"},[a("el-button",{on:{click:function(t){e.addDialog=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.addSave("addForm")}}},[e._v("确 定")])],1)],1)],1)},staticRenderFns:[]}},256:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-card",{staticClass:"box-card content-box"},[a("el-row",[a("el-col",[a("p",{staticClass:"content"},[e._v("至尊宝后台账号："),a("span",[e._v(" "+e._s(e.account.name))])])]),e._v(" "),a("el-col",[a("p",{staticClass:"content"},[e._v("至尊宝后台密码：\n\t                "),a("el-input",{staticClass:"w_200",attrs:{type:e.showPassword?"password":"text",placeholder:"请输入密码",icon:"search","on-icon-click":e.isShow},model:{value:e.account.password,callback:function(t){e.account.password=t},expression:"account.password"}})],1)]),e._v(" "),a("el-col",{staticClass:"btn-form"},[a("el-button",{on:{click:function(e){}}},[e._v("取消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:e.savePassword}},[e._v("确定")])],1)],1)],1)],1)},staticRenderFns:[]}}});
//# sourceMappingURL=0.32bc81bb75e266a7f53a.js.map