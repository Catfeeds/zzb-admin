webpackJsonp([6],{146:function(e,t,a){function o(e){a(228)}var s=a(18)(a(176),a(246),o,"data-v-0790c93c",null);e.exports=s.exports},176:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=a(44),s=a.n(o);t.default={data:function(){var e=function(e,t,a){/^([1-9]\d*\.\d*|0\.\d+|[1-9]\d*|0)$/.test(t)?a():a(new Error("请输入正确数值"))};return{orderWord:"",orderName:"",orderAddress:"",searchOrder:"",searchName:"",searchAddress:"",orderBy:2,listLoading:!1,exportLoading:!1,tableData:[],eForm:{},aForm:{},dealwayValue:1,viewFileList:[],fileList:[],itemRow:"",editDialog:!1,addDialog:!1,uploadDialog:!1,dealwayDialog:!1,currentPage:1,pageSize:20,total:0,tableTop:0,rules:{orderNumber:[{required:!0,message:"内容不能为空",trigger:"blur"}],address:[{required:!0,message:"内容不能为空",trigger:"blur"}],illegalTime:[{type:"date",required:!0,message:"请选择日期",trigger:"change"}],money:[{validator:e,required:!0,trigger:"blur"}],points:[{validator:e,required:!0,trigger:"blur"}],illegalCode:[{required:!0,message:"内容不能为空",trigger:"blur"}]}}},computed:{getIllegalTime:function(){var e=this.aForm.illegalTime;return this.$common.getNowTime(e)+" "+e.getHours()+":"+e.getMinutes()+":"+e.getSeconds()},tableMaxHeight:function(){return this.$store.commit("setTableMaxHeight",this.tableTop),this.$store.state.tableMaxHeight}},props:["API"],methods:{search:function(e){this.orderWord=this.searchOrder,this.nameWord=this.searchName,this.addressWord=this.searchAddress,this.currentPage=1,this.getList(this.orderWord,this.nameWord,this.addressWord)},sortTable:function(){this.orderBy=1==this.orderBy?2:1,this.getList(this.orderWord,this.nameWord,this.addressWord)},formatTime:function(e,t){return this.$common.getNowTime(e.createAt&&e.createAt.time)},formatStatus:function(e,t){return 1==e.managerStatus?"激活":2==e.managerStatus?"冻结":"--"},fomatType:function(e,t){return 1==e.dealway?"自负":2==e.dealway?"委托":"未处理"},editItem:function(e,t){var a=this,o={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{ticketUuid:t.ticketUuid}};this.$common._post("ticket/detail",o,this,function(e){a.editDialog=!0,a.eForm=s()({},e),a.eForm.ticketUuid=t.ticketUuid,a.eForm.pictures=JSON.parse(a.eForm.pictures)})},changeTicketStatus:function(e,t){this.dealwayDialog=!0,this.dealwayValue=1,this.itemRow=t},ticketStatusSave:function(){var e=this;this.$confirm("是否确认修改?","提示",{}).then(function(){var t={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{ticketUuid:e.itemRow.ticketUuid,ticketStatus:2,dealWay:e.dealwayValue}};e.$common._post("ticket/updateStatus",t,e,function(t){e.dealwayDialog=!1,e.$message({type:"success",message:t.description}),e.getList()})}).catch(function(){})},addItem:function(){this.aForm={orderNumber:"",address:"",illegalTime:"",money:"",points:"",illegalCode:""},this.addDialog=!0},addSave:function(e){var t=this;this.$refs[e].validate(function(e){if(!e)return!1;t.$confirm("是否确认添加?","提示",{}).then(function(){var e={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{orderNumber:t.aForm.orderNumber,illegalTime:t.getIllegalTime,address:t.aForm.address,money:t.aForm.money,points:t.aForm.points,illegalCode:t.aForm.illegalCode}};t.$common._post("ticket/insert",e,t,function(e){t.addDialog=!1,t.$message({type:"success",message:"添加成功!"}),t.getList()})}).catch(function(){})})},onUploadSuccess:function(e,t,a){this.fileList=s()([],a)},uploadSave:function(){var e=this;if(this.fileList.length<=0)return void this.$message("图片未上传成功");this.uploadDialog=!1;var t={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{id:this.itemRow.id,pictures:this.fileList.map(function(e){return e.response.body.attachment_url})}};this.$common._post("ticket/updatePicture",t,this,function(t){e.$message({type:"success",message:t.description}),e.getList()})},deleteItem:function(e,t){var a={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{ticketUuid:t.ticketUuid}};this.$common.deleteItem(this.API+"ticket/delete",this,a)},resetFields:function(e){this.$refs[e].resetFields(),this.eForm={}},resetImgFields:function(e){this.$refs[e].clearFiles(),this.fileList=[]},exportExc:function(){var e=this;this.exportLoading=!0;var t={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{}};this.$http.post(this.API+"exportExcelTicket",t).then(function(t){e.exportLoading=!1;var t=t.body;"0"==t.body.result?location.href=t.body.fileUrl:e.$message(t.body.description)})},handleCurrentChange:function(e){this.currentPage=e,this.getList(this.orderWord,this.nameWord,this.addressWord)},getList:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"",t=this,a=arguments.length>1&&void 0!==arguments[1]?arguments[1]:"",o=arguments.length>2&&void 0!==arguments[2]?arguments[2]:"";this.listLoading=!0;var s={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{pageIndex:this.currentPage,pageSize:this.pageSize,address:o,userName:a,orderNumber:e,orderBy:this.orderBy}};this.$common._post("ticket/list",s,this,function(e){t.total=e.total,t.tableData=e.ticketList,t.listLoading=!1},function(e){t.listLoading=!1,t.tableData=[]})}},mounted:function(){if(!sessionStorage.getItem("zhizunbao_login"))return this.$message("请登录账号"),void this.$router.push("/");this.$store.state.ticketName&&(this.searchName=this.$store.state.ticketName,this.nameWord=this.searchName),this.getList("",this.nameWord),this.tableTop=document.querySelector("#main table").getBoundingClientRect().top}}},210:function(e,t,a){t=e.exports=a(130)(!0),t.push([e.i,".viewImg[data-v-0790c93c]{width:60px;height:60px;border:1px solid #efefef}","",{version:3,sources:["F:/webstorm/cichang/zhizunbao/src/views/user/ticket-info.vue"],names:[],mappings:"AACA,0BACE,WAAY,AACZ,YAAa,AACb,wBAA0B,CAC3B",file:"ticket-info.vue",sourcesContent:["\n.viewImg[data-v-0790c93c] {\n  width: 60px;\n  height: 60px;\n  border: 1px solid #efefef;\n}\n"],sourceRoot:""}])},228:function(e,t,a){var o=a(210);"string"==typeof o&&(o=[[e.i,o,""]]),o.locals&&(e.exports=o.locals);a(131)("d63f5820",o,!0)},246:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-row",[a("el-col",{staticClass:"search-bar"},[a("div",[a("el-button",{on:{click:e.sortTable}},[e._v(e._s(1==e.orderBy?"正":"倒")+"序排列")])],1),e._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{attrs:{icon:"plus"},on:{click:e.addItem}},[e._v("新建罚单")])],1),e._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{attrs:{loading:e.exportLoading,icon:"document"},on:{click:e.exportExc}},[e._v("导出Excel")])],1)]),e._v(" "),a("el-col",{staticClass:"search-bar"},[a("el-input",{staticClass:"w_200",attrs:{placeholder:"请输入订单号"},model:{value:e.searchOrder,callback:function(t){e.searchOrder=t},expression:"searchOrder"}}),e._v(" "),a("div",{staticClass:"m_l_20"},[a("el-input",{staticClass:"w_200",attrs:{placeholder:"请输入用户"},model:{value:e.searchName,callback:function(t){e.searchName=t},expression:"searchName"}})],1),e._v(" "),a("div",{staticClass:"m_l_20"},[a("el-input",{staticClass:"w_200",attrs:{placeholder:"请输入地址"},model:{value:e.searchAddress,callback:function(t){e.searchAddress=t},expression:"searchAddress"}})],1),e._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{attrs:{icon:"search"},on:{click:e.search}},[e._v("搜索")])],1)],1)],1),e._v(" "),a("el-row",[a("el-col",[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticClass:"table",attrs:{data:e.tableData,border:"",width:"100%","max-height":e.tableMaxHeight}},[a("el-table-column",{attrs:{align:"center",label:"序号",width:"80"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                        "+e._s(t.$index+1+e.pageSize*(e.currentPage-1))+"\n                    ")]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"orderNumber",label:"订单号"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"user.userName",label:"用户"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"userOwner.userName",label:"车主"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"illegalCode",label:"违章信息"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"money",label:"罚款"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"points",label:"扣分"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"dealway",label:"处理方式",formatter:e.fomatType}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"",label:"已处理凭证上传",width:"100"},scopedSlots:e._u([{key:"default",fn:function(t){return e._l(t.row.pictures,function(e,t){return a("img",{key:t,staticClass:"viewImg",attrs:{src:e}})})}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"操作",width:"320"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"info",size:"small"},on:{click:function(a){e.editItem(t.$index,t.row)}}},[e._v("查看")]),e._v(" "),a("el-button",{attrs:{type:"warning",size:"small",disabled:2==t.row.ticketStatus},on:{click:function(a){e.changeTicketStatus(t.$index,t.row)}}},[e._v(e._s(2!==t.row.ticketStatus?"处　理":"已处理"))]),e._v(" "),a("el-button",{attrs:{size:"small",type:"success",disabled:2!=t.row.ticketStatus},on:{click:function(a){e.uploadDialog=!0,e.itemRow=t.row}}},[e._v("上传图片")]),e._v(" "),a("el-button",{attrs:{size:"small",type:"danger",icon:"delete2"},on:{click:function(a){e.deleteItem(t.$index,t.row)}}},[e._v("删除")])]}}])})],1)],1)],1),e._v(" "),a("el-row",[a("el-col",[a("div",{staticClass:"block pagination"},[a("el-pagination",{attrs:{"current-page":e.currentPage,"page-size":e.pageSize,layout:"prev, pager, next, jumper","page-count":e.total},on:{"update:currentPage":function(t){e.currentPage=t},"current-change":e.handleCurrentChange}})],1)])],1),e._v(" "),a("el-dialog",{attrs:{visible:e.uploadDialog},on:{"update:visible":function(t){e.uploadDialog=t},close:function(t){e.resetImgFields("uploadForm")}}},[a("el-upload",{ref:"uploadForm",staticClass:"upload-demo",attrs:{action:e.API+"media/file_upload","on-success":e.onUploadSuccess,"file-list":e.viewFileList,"list-type":"picture"}},[a("el-button",{attrs:{size:"small",type:"primary"}},[e._v("点击上传")])],1),e._v(" "),a("div",{staticClass:"dialog-footer",slot:"footer"},[a("el-button",{on:{click:function(t){e.uploadDialog=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.uploadSave()}}},[e._v("确 定")])],1)],1),e._v(" "),a("el-dialog",{staticClass:"edit dialog-info",attrs:{visible:e.editDialog},on:{"update:visible":function(t){e.editDialog=t}}},[a("div",{staticClass:"info-content"},[a("el-row",[a("el-col",[e._v("\n                    用户ID："),a("span",[e._v(e._s(e.eForm.userId))])]),e._v(" "),a("el-col",[e._v("\n                    用户："),a("span",[e._v(e._s(e.eForm.userName))])]),e._v(" "),a("el-col",[e._v("\n                    手机号："),a("span",[e._v(e._s(e.eForm.userPhone))])]),e._v(" "),a("el-col",[e._v("\n                    订单号："),a("span",[e._v(e._s(e.eForm.orderNumber))])]),e._v(" "),a("el-col",[e._v("\n                    地点："),a("span",[e._v(e._s(e.eForm.address))])]),e._v(" "),a("el-col",[e._v("\n                    违章时间："),a("span",[e._v(e._s(e.eForm.illegalTime))])]),e._v(" "),a("el-col",[e._v("\n                    罚款金额："),a("span",[e._v(e._s(e.eForm.money))])])],1),e._v(" "),a("el-row",[a("el-col",{attrs:{span:10}},[e._v("\n                    扣分："),a("span",[e._v(e._s(e.eForm.points))])])],1),e._v(" "),a("el-row",[a("el-col",{attrs:{span:10}},[e._v("\n                    违法说明："),a("span",[e._v(e._s(e.eForm.illegalCode))])])],1)],1),e._v(" "),a("p",{staticClass:"info-title"},[e._v("处理信息：")]),e._v(" "),e._l(e.eForm.pictures,function(e,t){return a("img",{key:t,staticStyle:{width:"100%","margin-top":"10px"},attrs:{src:e,alt:""}})}),e._v(" "),a("div",{staticClass:"dialog-footer",slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.editDialog=!1}}},[e._v("确 定")])],1)],2),e._v(" "),a("el-dialog",{staticClass:"add dialog-info",attrs:{visible:e.addDialog},on:{"update:visible":function(t){e.addDialog=t},close:function(t){e.resetFields("addForm")}}},[a("div",{staticClass:"info-content"},[a("el-form",{ref:"addForm",attrs:{"label-position":"left","label-width":"120px",model:e.aForm,rules:e.rules}},[a("el-form-item",{attrs:{label:"订单号：",prop:"orderNumber"}},[a("el-input",{model:{value:e.aForm.orderNumber,callback:function(t){e.aForm.orderNumber=t},expression:"aForm.orderNumber"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"地点：",prop:"address"}},[a("el-input",{model:{value:e.aForm.address,callback:function(t){e.aForm.address=t},expression:"aForm.address"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"时间：",prop:"illegalTime"}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{editable:!1,type:"datetime",placeholder:"选择日期时间",format:"yyyy-MM-dd HH:mm:ss"},model:{value:e.aForm.illegalTime,callback:function(t){e.aForm.illegalTime=t},expression:"aForm.illegalTime"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"罚款金额：",prop:"money"}},[a("el-input",{model:{value:e.aForm.money,callback:function(t){e.aForm.money=t},expression:"aForm.money"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"扣分：",prop:"points"}},[a("el-input",{model:{value:e.aForm.points,callback:function(t){e.aForm.points=t},expression:"aForm.points"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"违法行为：",prop:"illegalCode"}},[a("el-input",{model:{value:e.aForm.illegalCode,callback:function(t){e.aForm.illegalCode=t},expression:"aForm.illegalCode"}})],1)],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",slot:"footer"},[a("el-button",{on:{click:function(t){e.addDialog=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.addSave("addForm")}}},[e._v("确 定")])],1)]),e._v(" "),a("el-dialog",{staticClass:"dialog-info",attrs:{title:"请选择处理方式",visible:e.dealwayDialog},on:{"update:visible":function(t){e.dealwayDialog=t}}},[a("div",{staticClass:"info-radio"},[a("el-form",[a("el-radio-group",{model:{value:e.dealwayValue,callback:function(t){e.dealwayValue=t},expression:"dealwayValue"}},[a("el-radio",{staticClass:"radio",attrs:{label:1}},[e._v("自负处理")]),e._v(" "),a("el-radio",{staticClass:"radio",attrs:{label:2}},[e._v("委托处理")])],1)],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.ticketStatusSave()}}},[e._v("确 定")])],1)])],1)},staticRenderFns:[]}}});
//# sourceMappingURL=6.4e61948a78ebd75df077.js.map