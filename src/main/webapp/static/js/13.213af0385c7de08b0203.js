webpackJsonp([13],{136:function(e,t,a){function s(e){a(233)}var o=a(18)(a(166),a(251),s,"data-v-29ba582e",null);e.exports=o.exports},166:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={data:function(){return{word:"",searchWord:"",orderBy:2,listLoading:!1,exportLoading:!1,tableData:[],form:{adminMessage:""},editDialog:!1,currentPage:1,pageSize:20,total:0,tableTop:0}},props:["API"],computed:{tableMaxHeight:function(){return this.$store.commit("setTableMaxHeight",this.tableTop),this.$store.state.tableMaxHeight}},methods:{search:function(e){this.word=this.searchWord,this.currentPage=1,this.getList(this.word)},sortTable:function(){this.orderBy=1==this.orderBy?2:1,this.getList(this.word)},formatTime:function(e,t){return this.$common.getNowTime(e.createAt&&e.createAt.time)},formatStatus:function(e,t){return 1==e.isRead?"未读":2==e.isRead?"已读":"未知"},formatType:function(e,t){return 1==e.messageType?"提现申请":2==e.messageType?"上传车辆":2==e.messageType?"个人资料":"--"},editItem:function(e,t){var a=this,s={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{message_id:t.id}};this.$common._post("adminmessage/detail",s,this,function(e){a.form.adminMessage=e.adminMessage,a.editDialog=!0})},deleteItem:function(e,t){var a={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{message_id:t.id}};this.$common.deleteItem(this.API+"adminmessage/delete",this,a)},exportExc:function(){var e=this;this.exportLoading=!0;var t={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{}};this.$http.post(this.API+"exportExcelAdminMessage",t).then(function(t){e.exportLoading=!1;var t=t.body;"0"==t.body.result?location.href=t.body.fileUrl:e.$message(t.body.description)})},handleCurrentChange:function(e){this.currentPage=e,this.getList(this.word)},getList:function(){var e=this,t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"";this.listLoading=!0;var a={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{pageIndex:this.currentPage,pageSize:this.pageSize,messageContent:t,orderBy:this.orderBy}};this.$common._post("adminmessage/list",a,this,function(t){e.total=t.total,e.tableData=t.list,e.listLoading=!1},function(t){e.listLoading=!1,e.tableData=[]})}},mounted:function(){if(!sessionStorage.getItem("zhizunbao_login"))return this.$message("请登录账号"),void this.$router.push("/");this.getList(),this.tableTop=document.querySelector("#main table").getBoundingClientRect().top}}},215:function(e,t,a){t=e.exports=a(130)(!0),t.push([e.i,".news-content[data-v-29ba582e]{line-height:28px;font-size:16px;text-indent:2em}","",{version:3,sources:["F:/webstorm/cichang/zhizunbao/src/views/news/news.vue"],names:[],mappings:"AACA,+BACE,iBAAkB,AAClB,eAAgB,AAChB,eAAiB,CAClB",file:"news.vue",sourcesContent:["\n.news-content[data-v-29ba582e] {\n  line-height: 28px;\n  font-size: 16px;\n  text-indent: 2em;\n}\n"],sourceRoot:""}])},233:function(e,t,a){var s=a(215);"string"==typeof s&&(s=[[e.i,s,""]]),s.locals&&(e.exports=s.locals);a(131)("b8077a2c",s,!0)},251:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-row",[a("el-col",{staticClass:"search-bar"},[a("el-input",{staticClass:"w_200",attrs:{placeholder:"请输入消息内容",icon:"search","on-icon-click":e.search},model:{value:e.searchWord,callback:function(t){e.searchWord=t},expression:"searchWord"}}),e._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{on:{click:e.sortTable}},[e._v(e._s(1==e.orderBy?"正":"倒")+"序排列")])],1),e._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{attrs:{loading:e.exportLoading,icon:"document"},on:{click:e.exportExc}},[e._v("导出Excel")])],1)],1)],1),e._v(" "),a("el-row",[a("el-col",[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticClass:"table",attrs:{data:e.tableData,border:"",width:"100%","max-height":e.tableMaxHeight}},[a("el-table-column",{attrs:{align:"center",label:"序号",width:"80"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                        "+e._s(t.$index+1+e.pageSize*(e.currentPage-1))+"\n                    ")]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"messageType",label:"消息类型",formatter:e.formatType}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"userUuid",label:"用户ID"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"messageContent",label:"消息内容"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"isRead",label:"状态",width:"80",formatter:e.formatStatus}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"createAt.time",label:"时间",width:"150",formatter:e.formatTime}}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"操作",width:"160"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"info",size:"small"},on:{click:function(a){e.editItem(t.$index,t.row)}}},[e._v("查看")]),e._v(" "),a("el-button",{attrs:{size:"small",type:"danger",icon:"delete2"},on:{click:function(a){e.deleteItem(t.$index,t.row)}}},[e._v("删除")])]}}])})],1)],1)],1),e._v(" "),a("el-row",[a("el-col",[a("div",{staticClass:"block pagination"},[a("el-pagination",{attrs:{"current-page":e.currentPage,"page-size":e.pageSize,layout:"prev, pager, next, jumper","page-count":e.total},on:{"update:currentPage":function(t){e.currentPage=t},"current-change":e.handleCurrentChange}})],1)])],1),e._v(" "),a("el-dialog",{staticClass:"dialog-info",attrs:{title:"查看",visible:e.editDialog},on:{"update:visible":function(t){e.editDialog=t},close:function(t){e.getList(e.word)}}},[a("div",{staticClass:"info-content"},[a("p",{staticClass:"news-content"},[e._v(e._s(e.form.adminMessage.messageContent))])]),e._v(" "),a("div",{staticClass:"dialog-footer",slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.editDialog=!1}}},[e._v("确 定")])],1)])],1)},staticRenderFns:[]}}});
//# sourceMappingURL=13.213af0385c7de08b0203.js.map