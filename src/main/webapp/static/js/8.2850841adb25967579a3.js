webpackJsonp([8],{145:function(e,t,a){function n(e){a(227)}var o=a(18)(a(179),a(245),n,"data-v-0685d5c4",null);e.exports=o.exports},179:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=a(44),o=a.n(n);t.default={data:function(){return{nameWord:"",idWord:"",searchName:"",searchId:"",orderBy:2,listLoading:!1,exportLoading:!1,tableData:[],tableData1:[],editDialog:!1,eForm:{userStatus:1,createAt:{}},currentPage:1,pageSize:20,total:0,tableTop:0,viewPicDialog:!1,picUrl:null}},props:["API"],computed:{tableMaxHeight:function(){return this.$store.commit("setTableMaxHeight",this.tableTop),this.$store.state.tableMaxHeight}},methods:{search:function(e){this.nameWord=this.searchName,this.idWord=this.searchId,this.currentPage=1,this.getList(this.nameWord,this.idWord)},sortTable:function(){this.orderBy=1==this.orderBy?2:1,this.getList(this.nameWord,this.idWord)},formatTime:function(e,t){return this.$common.getNowTime(e.createAt&&e.createAt.time)},getTime:function(e){return this.$common.getNowTime(e)},formatGender:function(e,t){return 1==e.gender?"男":2==e.gender?"女":"未填写"},editItem:function(e,t){var a=this;this.editDialog=!0;var n={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{user_uuid:t.userUuid}};this.$common._post("user/detail",n,this,function(e){a.editDialog=!0,a.eForm=o()({},e.user),a.eForm.bank=e.bank,a.eForm.car=e.car})},editSave:function(){var e=this;this.$confirm("是否确认修改?","提示",{type:"warning"}).then(function(){var t={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{user_uuid:e.eForm.userUuid,user_status:e.eForm.userStatus}};e.$common._post("user/operationStatus",t,e,function(t){e.editDialog=!1,e.$message({type:"success",message:t.description}),e.getList()})}).catch(function(){})},operationStatus:function(e,t){var a=this;this.$confirm("是否驳回?","提示",{type:"warning"}).then(function(){var e={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{user_uuid:t.userUuid,user_status:3}};a.$common._post("user/operationStatus",e,a,function(e){a.$message({type:"success",message:"驳回成功!"}),a.getList()})}).catch(function(){})},showPic:function(e,t){this.showDialogPic(this.tableData[e].pic)},showDialogPic:function(e){this.viewPicDialog=!0,this.picUrl=e},exportExc:function(){var e=this;this.exportLoading=!0;var t={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{}};this.$http.post(this.API+"exportCarOwnerList",t).then(function(t){e.exportLoading=!1;var t=t.body;"0"==t.body.result?location.href=t.body.fileUrl:e.$message(t.body.description)})},handleCurrentChange:function(e){this.currentPage=e,this.getList(this.nameWord,this.idWord)},getList:function(){var e=this,t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"",a=arguments.length>1&&void 0!==arguments[1]?arguments[1]:"";this.listLoading=!0;var n={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{pageIndex:this.currentPage,pageSize:this.pageSize,userName:t,id:a,orderBy:this.orderBy}};this.$common._post("user/userOwnerList",n,this,function(t){e.total=t.total,e.tableData=t.userList,e.tableData1=t.userList1,e.listLoading=!1},function(t){e.listLoading=!1,e.tableData=[],e.tableData1=[]})}},mounted:function(){if(!sessionStorage.getItem("zhizunbao_login"))return this.$message("请登录账号"),void this.$router.push("/");this.getList(),this.tableTop=document.querySelector("#main table").getBoundingClientRect().top}}},209:function(e,t,a){t=e.exports=a(130)(!0),t.push([e.i,".dialog-info .info-content[data-v-0685d5c4]{position:relative}.dialog-info .info-content .idcard-pic[data-v-0685d5c4]{max-width:400px;vertical-align:middle}.dialog-info .info-content .avatar[data-v-0685d5c4]{position:absolute;right:60px;text-align:center}.dialog-info .info-content .avatar img[data-v-0685d5c4]{width:120px;height:120px;border-radius:6px}.view-pic[data-v-0685d5c4]{text-align:center}.viewImg[data-v-0685d5c4]{width:60px;height:60px}","",{version:3,sources:["F:/webstorm/cichang/zhizunbao/src/views/user/car-owner-info.vue"],names:[],mappings:"AACA,4CACE,iBAAmB,CACpB,AACD,wDACI,gBAAiB,AACjB,qBAAuB,CAC1B,AACD,oDACI,kBAAmB,AACnB,WAAY,AACZ,iBAAmB,CACtB,AACD,wDACM,YAAa,AACb,aAAc,AACd,iBAAmB,CACxB,AACD,2BACE,iBAAmB,CACpB,AACD,0BACE,WAAY,AACZ,WAAa,CACd",file:"car-owner-info.vue",sourcesContent:["\n.dialog-info .info-content[data-v-0685d5c4] {\n  position: relative;\n}\n.dialog-info .info-content .idcard-pic[data-v-0685d5c4] {\n    max-width: 400px;\n    vertical-align: middle;\n}\n.dialog-info .info-content .avatar[data-v-0685d5c4] {\n    position: absolute;\n    right: 60px;\n    text-align: center;\n}\n.dialog-info .info-content .avatar img[data-v-0685d5c4] {\n      width: 120px;\n      height: 120px;\n      border-radius: 6px;\n}\n.view-pic[data-v-0685d5c4] {\n  text-align: center;\n}\n.viewImg[data-v-0685d5c4] {\n  width: 60px;\n  height: 60px;\n}\n"],sourceRoot:""}])},227:function(e,t,a){var n=a(209);"string"==typeof n&&(n=[[e.i,n,""]]),n.locals&&(e.exports=n.locals);a(131)("dc3ebbda",n,!0)},245:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-row",[a("el-col",{staticClass:"search-bar"},[a("el-input",{staticClass:"w_200",attrs:{placeholder:"请输入姓名"},model:{value:e.searchName,callback:function(t){e.searchName=t},expression:"searchName"}}),e._v(" "),a("div",{staticClass:"m_l_20"},[a("el-input",{staticClass:"w_200",attrs:{placeholder:"请输入ID"},model:{value:e.searchId,callback:function(t){e.searchId=t},expression:"searchId"}})],1),e._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{attrs:{icon:"search"},on:{click:e.search}},[e._v("搜索")])],1),e._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{on:{click:e.sortTable}},[e._v(e._s(1==e.orderBy?"正":"倒")+"序排列")])],1),e._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{attrs:{loading:e.exportLoading,icon:"document"},on:{click:e.exportExc}},[e._v("导出Excel")])],1)],1)],1),e._v(" "),a("el-row",[a("el-col",[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticClass:"table",attrs:{data:e.tableData,border:"",width:"100%","max-height":e.tableMaxHeight}},[a("el-table-column",{attrs:{align:"center",label:"序号",width:"80"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                        "+e._s(t.$index+1+e.pageSize*(e.currentPage-1))+"\n                    ")]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"id",label:"ID"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"userName",label:"姓名"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"userPhone",label:"手机"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"gender",label:"性别",formatter:e.formatGender}}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"车辆品牌"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",{domProps:{innerHTML:e._s(t.row.ownerPo.cars.map(function(e){return e.brand}).join("<br/>"))}})]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"图片",width:"100"},scopedSlots:e._u([{key:"default",fn:function(t){return e._l(t.row.ownerPo.cars,function(e,t){return a("img",{key:t,staticClass:"viewImg",attrs:{src:e.banner}})})}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"ownerPo.carnum1",label:"有效车辆数"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"ownerPo.carnum2",label:"历史车辆数"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"createAt.time",label:"注册时间",width:"110",formatter:e.formatTime}}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"最后一次登陆时间"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                        "+e._s(e.getTime(t.row.updateAt&&t.row.updateAt.time))+"\n                    ")]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"loginCount",label:"登录次数"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"ownerPo.sureordercount",label:"接单次数"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"ownerPo.gdp",label:"订单GDP"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"ownerPo.avg",label:"平台分佣"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"ownerPo.chajialirun",label:"差价利润"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"creditScore",label:"信用分"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"balance",label:"账户余额"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"ownerPo.ketixianjiner",label:"可提现金额"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"cashbalance",label:"提现中金额"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"ownerPo.alreadybalance",label:"已提现金额"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"操作",width:"180"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"info",size:"small"},on:{click:function(a){e.editItem(t.$index,t.row)}}},[e._v("查看详情")]),e._v(" "),a("el-button",{attrs:{size:"small",type:"danger",disabled:3==t.row.userStatus},on:{click:function(a){e.operationStatus(t.$index,t.row)}}},[e._v(e._s(3==t.row.userStatus?"已驳回":"驳　回"))])]}}])})],1)],1)],1),e._v(" "),a("el-row",[a("el-col",[a("div",{staticClass:"block pagination"},[a("el-pagination",{attrs:{"current-page":e.currentPage,"page-size":e.pageSize,layout:"prev, pager, next, jumper","page-count":e.total},on:{"update:currentPage":function(t){e.currentPage=t},"current-change":e.handleCurrentChange}})],1)])],1),e._v(" "),a("el-dialog",{staticClass:"edit dialog-info",attrs:{size:"large",title:"查看详情",visible:e.editDialog},on:{"update:visible":function(t){e.editDialog=t}}},[a("div",{staticClass:"info-content"},[a("div",{staticClass:"avatar"},[a("img",{attrs:{src:e.eForm.avater}}),e._v(" "),a("p",[e._v("头像")])]),e._v(" "),a("el-row",[a("el-col",[e._v("用户ID："),a("span",[e._v(e._s(e.eForm.id))])]),e._v(" "),a("el-col",[e._v("姓名："),a("span",[e._v(e._s(e.eForm.userName))])]),e._v(" "),a("el-col",[e._v("性别："),a("span",[e._v(e._s(e.formatGender(e.eForm)))])]),e._v(" "),a("el-col",[e._v("手机号："),a("span",[e._v(e._s(e.eForm.userPhone))])]),e._v(" "),a("el-col",[e._v("绑定信息："),a("span",[e._v(e._s(e.eForm.bank&&e.eForm.bank.length||"未绑定"))])]),e._v(" "),a("el-col",[e._v("绑定卡号："),e._l(e.eForm.bank,function(t,n){return a("span",{key:n},[e._v(e._s(t.bank+"："+t.cardNumber+"　"))])})],2),e._v(" "),a("el-col",[e._v("\n                    信用："),a("span",[e._v(e._s(e.eForm.creditScore))])]),e._v(" "),a("el-col",[e._v("\n                    账户余额：\n                    "),a("span",[e._v(e._s(e.eForm.balance))])]),e._v(" "),a("el-col",[e._v("星座："),a("span",[e._v(e._s(e.eForm.constellation))])]),e._v(" "),a("el-col",[e._v("驾龄："),a("span",[e._v(e._s(e.eForm.driving))])])],1),e._v(" "),a("el-row",[a("el-row",[a("el-col",[e._v("上传车辆："+e._s(e.eForm.car&&e.eForm.car.length||0)+"辆")]),e._v(" "),a("el-col",[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticClass:"table",attrs:{data:e.eForm.car||[],border:"",width:"100%"}},[a("el-table-column",{attrs:{align:"center",label:"序号",width:"80"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                                    "+e._s(t.$index)+"\n                                ")]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"brand",label:"车辆品牌"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"carSeries",label:"车系"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"color",label:"颜色"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"modelYear",label:"年款"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"licensePlateNumber",label:"车牌"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"time",label:"驾驶时长"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"modelNum",label:"驾驶车型数"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"totalNum",label:"历史总成交"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"driverCount",label:"驾驶次数"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"res",label:"平均响应时间"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"avg",label:"平均接单量"}})],1)],1)],1),e._v(" "),a("el-col",[e._v("注册时间："),a("span",[e._v(e._s(e.formatTime(e.eForm)))])]),e._v(" "),a("el-col",{attrs:{span:8}},[e._v("最后一次登录时间："),a("span",[e._v(e._s(e.getTime(e.eForm.updateAt&&e.eForm.updateAt.time)))])]),e._v(" "),a("el-col",{attrs:{span:8}},[e._v("登录次数："),a("span",[e._v(e._s(e.eForm.loginCount))])]),e._v(" "),a("el-col",[e._v("身份验证："),a("span",[e._v(e._s(e.eForm.authenticationStatus))])]),e._v(" "),a("el-col",[e._v("身份证/驾驶证："),a("span",[e._v(e._s(1==e.eForm.idType?"身份证":2==e.eForm.idType?"驾照":"未添加"))])]),e._v(" "),a("el-col",[e._v("照片：")]),e._v(" "),a("el-col",[a("img",{staticClass:"idcard-pic",attrs:{src:e.eForm.idPicture}})])],1)],1),e._v(" "),a("div",{staticClass:"info-radio"},[a("el-radio-group",{model:{value:e.eForm.userStatus,callback:function(t){e.eForm.userStatus=t},expression:"eForm.userStatus"}},[a("el-radio",{staticClass:"radio",attrs:{label:1}},[e._v("通过")]),e._v(" "),a("el-radio",{staticClass:"radio",attrs:{label:2}},[e._v("拉黑")]),e._v(" "),a("el-radio",{staticClass:"radio",attrs:{label:3}},[e._v("驳回")]),e._v(" "),a("el-radio",{staticClass:"radio",attrs:{label:4}},[e._v("冻结")])],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",slot:"footer"},[a("el-button",{on:{click:function(t){e.editDialog=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.editSave()}}},[e._v("确 定")])],1)]),e._v(" "),a("el-dialog",{staticClass:"view-pic",attrs:{visible:e.viewPicDialog},on:{"update:visible":function(t){e.viewPicDialog=t}}},[a("img",{attrs:{src:e.picUrl}})])],1)},staticRenderFns:[]}}});
//# sourceMappingURL=8.2850841adb25967579a3.js.map