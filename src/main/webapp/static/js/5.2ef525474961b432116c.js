webpackJsonp([5],{147:function(e,t,a){function s(e){a(243)}var o=a(18)(a(177),a(261),s,"data-v-713820b0",null);e.exports=o.exports},177:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var s=a(44),o=a.n(s);t.default={data:function(){return{word:"",searchWord:"",orderBy:2,listLoading:!1,exportLoading:!1,index:null,tableData:[],editDialog:!1,eForm:{},currentPage:1,pageSize:20,total:0,tableTop:0}},props:["API"],computed:{tableMaxHeight:function(){return this.$store.commit("setTableMaxHeight",this.tableTop),this.$store.state.tableMaxHeight}},methods:{search:function(e){this.word=this.searchWord,this.currentPage=1,this.getList(this.word)},sortTable:function(){this.orderBy=1==this.orderBy?2:1,this.getList(this.word)},getTime:function(e){return this.$common.getNowTime(e)},getUserInfo:function(e){return e.birthday&&e.idType&&e.gender&&e.userPhone&&e.avater&&e.userName&&e.idNumber&&e.driving&&e.idPicture&&e.constellation&&e.userType?0:1},getCarInfo:function(e){return e.address&&e.carName&&e.carSeries&&e.carStatus&&e.city&&e.collection&&e.color&&e.seatNumber&&e.brand&&e.modelCharacter&&e.orderQuantity&&e.registerTime&&e.vehicleAge&&e.model&&e.manualOrAutomatic&&e.licensePlateNumber?0:1},editItem:function(e,t){var a=this,s={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{user_uuid:t.userUuid}};this.$common._post("user/creditDetail",s,this,function(e){a.editDialog=!0,a.eForm=o()({},e)})},editSave:function(){var e=this;this.$confirm("是否确认修改?","提示",{}).then(function(){var t={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{user_uuid:e.eForm.user_uuid,user_status:e.eForm.userStatus}};e.$common._post("user/blackAndActivate",t,e,function(t){e.editDialog=!1,e.$message({type:"success",message:t.description}),e.getList()})}).catch(function(){})},blackAndActivate:function(e,t){var a=this;this.$confirm("是否确认修改?","提示",{}).then(function(){var e={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{user_uuid:t.userUuid,user_status:2}};a.$common._post("user/blackAndActivate",e,a,function(e){a.$message({type:"success",message:e.description}),a.getList()})}).catch(function(){})},exportExc:function(){var e=this;this.exportLoading=!0;var t={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{}};this.$http.post(this.API+"exportExcelUserCredit",t).then(function(t){e.exportLoading=!1;var t=t.body;"0"==t.body.result?location.href=t.body.fileUrl:e.$message(t.body.description)})},handleCurrentChange:function(e){this.currentPage=e,this.getList(this.word)},getList:function(){var e=this,t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"";this.listLoading=!0;var a={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{pageIndex:this.currentPage,pageSize:this.pageSize,userName:t,orderBy:this.orderBy}};this.$common._post("user/creditList",a,this,function(t){e.total=t.total,e.tableData=t.userCreditList,e.listLoading=!1},function(t){e.listLoading=!1,e.tableData=[]})}},mounted:function(){if(!sessionStorage.getItem("zhizunbao_login"))return this.$message("请登录账号"),void this.$router.push("/");this.getList(),this.tableTop=document.querySelector("#main table").getBoundingClientRect().top}}},225:function(e,t,a){t=e.exports=a(130)(!0),t.push([e.i,".personalInfo-box img[data-v-713820b0]{width:100px;vertical-align:top}","",{version:3,sources:["F:/webstorm/cichang/zhizunbao/src/views/user/user-credit.vue"],names:[],mappings:"AACA,uCACE,YAAa,AACb,kBAAoB,CACrB",file:"user-credit.vue",sourcesContent:["\n.personalInfo-box img[data-v-713820b0] {\n  width: 100px;\n  vertical-align: top;\n}\n"],sourceRoot:""}])},243:function(e,t,a){var s=a(225);"string"==typeof s&&(s=[[e.i,s,""]]),s.locals&&(e.exports=s.locals);a(131)("5cb0daab",s,!0)},261:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-row",[a("el-col",{staticClass:"search-bar"},[a("el-input",{staticClass:"w_200",attrs:{placeholder:"请输入姓名",icon:"search","on-icon-click":e.search},model:{value:e.searchWord,callback:function(t){e.searchWord=t},expression:"searchWord"}}),e._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{on:{click:e.sortTable}},[e._v(e._s(1==e.orderBy?"正":"倒")+"序排列")])],1),e._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{attrs:{loading:e.exportLoading,icon:"document"},on:{click:e.exportExc}},[e._v("导出Excel")])],1)],1)],1),e._v(" "),a("el-row",[a("el-col",[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticClass:"table",attrs:{data:e.tableData,border:"",width:"100%","max-height":e.tableMaxHeight}},[a("el-table-column",{attrs:{align:"center",label:"序号",width:"80"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                        "+e._s(t.$index+1+e.pageSize*(e.currentPage-1))+"\n                    ")]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"userName",label:"姓名"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"userId",label:"用户ID"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"creditScore",label:"信用分"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"identityScore",label:"身份信息"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"outScore",label:"外部信用"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"useCarScore",label:"用车行为"}}),e._v(" "),a("el-table-column",{staticClass:"{red: scope.row.userStatus == 2}",attrs:{align:"center",prop:"creditStatus",label:"状态",width:"120"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"操作",width:"180"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"info",size:"small"},on:{click:function(a){e.editItem(t.$index,t.row)}}},[e._v("查看详情")]),e._v(" "),a("el-button",{attrs:{size:"small",type:"danger"},on:{click:function(a){e.blackAndActivate(t.$index,t.row)}}},[e._v("拉黑")])]}}])})],1)],1)],1),e._v(" "),a("el-row",[a("el-col",[a("div",{staticClass:"block pagination"},[a("el-pagination",{attrs:{"current-page":e.currentPage,"page-size":e.pageSize,layout:"prev, pager, next, jumper","page-count":e.total},on:{"update:currentPage":function(t){e.currentPage=t},"current-change":e.handleCurrentChange}})],1)])],1),e._v(" "),a("el-dialog",{staticClass:"edit dialog-info",attrs:{size:"large",title:"用户信用",visible:e.editDialog},on:{"update:visible":function(t){e.editDialog=t}}},[a("div",{staticClass:"info-content"},[a("el-row",[a("el-col",{attrs:{span:6}},[e._v("用户ID："),a("span",[e._v(e._s(e.eForm.userId))])]),e._v(" "),a("el-col",{attrs:{span:6}},[e._v("姓名："),a("span",[e._v(e._s(e.eForm.userName))])]),e._v(" "),a("el-col",{attrs:{span:6}},[e._v("信用分："),a("span",[e._v(e._s(e.eForm.creditScore))])]),e._v(" "),a("el-col",{attrs:{span:6}},[e._v("身份信用："),a("span",[e._v(e._s(e.eForm.identityCredit))])]),e._v(" "),a("el-col",{attrs:{span:12}},[e._v("个人资料："),a("span",[e._v(e._s(0==e.getUserInfo(e.eForm)?"完整":"不完整"))])]),e._v(" "),a("el-col",{attrs:{span:12}},[e._v("车辆信息："),a("span",[e._v(e._s(0==e.getCarInfo(e.eForm)?"完整":"不完整"))])]),e._v(" "),a("el-col",[e._v("外部信用："+e._s(e.eForm.outCredit))]),e._v(" "),a("el-row",[a("el-col",[e._v("已绑定：\n                    "),e._l(e.eForm.isBinding,function(t){return a("span",{key:e.index},[e._v(e._s(t+"　"))])})],2)],1),e._v(" "),a("el-row",[a("el-col",[e._v("未绑定：\n                    "),e._l(e.eForm.notBinding,function(t){return a("span",{key:e.index,attrs:{span:5}},[e._v(e._s(t+"　"))])})],2)],1),e._v(" "),a("el-col",[e._v("用车信用："),a("span",[e._v(e._s(e.eForm.useCarCredit))])]),e._v(" "),a("el-col",[e._v("订单号：")]),e._v(" "),e._l(e.eForm.order,function(t,s){return a("el-col",{key:s,attrs:{push:1}},[e._v(e._s(s+1+"： "+t))])})],2)],1),e._v(" "),a("div",{staticClass:"info-radio"},[a("el-radio-group",{model:{value:e.eForm.userStatus,callback:function(t){e.eForm.userStatus=t},expression:"eForm.userStatus"}},[a("el-radio",{staticClass:"radio",attrs:{label:1}},[e._v("激活")]),e._v(" "),a("el-radio",{staticClass:"radio",attrs:{label:2}},[e._v("拉黑")])],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",slot:"footer"},[a("el-button",{on:{click:function(t){e.editDialog=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.editSave()}}},[e._v("确 定")])],1)])],1)},staticRenderFns:[]}}});
//# sourceMappingURL=5.2ef525474961b432116c.js.map