webpackJsonp([15],{185:function(t,e,a){function o(t){a(288)}var n=a(24)(a(215),a(306),o,"data-v-67549f64",null);t.exports=n.exports},215:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=a(60),n=a.n(o);e.default={data:function(){return{word:"",searchWord:"",orderBy:2,listLoading:!1,exportLoading:!1,tableData:[],editDialog:!1,eForm:{},currentPage:1,pageSize:20,total:0,tableTop:0}},props:["API"],computed:{tableMaxHeight:function(){return this.$store.commit("setTableMaxHeight",this.tableTop),this.$store.state.tableMaxHeight}},methods:{search:function(t){this.word=this.searchWord,this.currentPage=1,this.getList(this.word)},sortTable:function(){this.orderBy=1==this.orderBy?2:1,this.getList(this.word)},formatTime:function(t,e){return this.$common.getNowTime(t.handleTime&&t.handleTime.time)},getTime:function(t,e){return this.$common.getNowTime(t.handleTime)},formatStatus:function(t,e){return 1==t.status?"申请中":2==t.status?"已通过":3==t.status?"已驳回":"未知"},editItem:function(t,e){var a=this,o={head:this.$common.getUserStorage(),body:{id:e.id}};this.$common._post("withdrawalsRecord/detail",o,this,function(t){a.editDialog=!0,a.eForm=n()({},t.withdrawalsRecord)})},deleteItem:function(t,e){var a={head:this.$common.getUserStorage(),body:{id:e.id}};this.$common.deleteItem(this.API+"withdrawalsRecord/delete",this,a)},changeAgree:function(t,e){var a=this;this.$confirm("是否确认修改?","提示",{}).then(function(){var t={head:a.$common.getUserStorage(),body:{id:e.id}};a.$common._post("withdrawalsRecord/agree",t,a,function(t){a.$message({type:"success",message:t.description}),a.getList(a.word)})}).catch(function(){})},changeReject:function(t,e){var a=this;this.$confirm("是否确认修改?","提示",{}).then(function(){var t={head:a.$common.getUserStorage(),body:{id:e.id}};a.$common._post("withdrawalsRecord/reject",t,a,function(t){a.$message({type:"success",message:t.description}),a.getList(a.word)})}).catch(function(){})},exportExc:function(){var t=this;this.exportLoading=!0;var e={head:this.$common.getUserStorage(),body:{}};this.$http.post(this.API+"exportExcelWithdrawalsRecord",e).then(function(e){t.exportLoading=!1;var e=e.body;"0"==e.body.result?location.href=e.body.fileUrl:t.$message(e.body.description)})},handleCurrentChange:function(t){this.currentPage=t,this.getList(this.word)},getList:function(){var t=this,e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"";this.listLoading=!0;var a={head:this.$common.getUserStorage(),body:{pageIndex:this.currentPage,pageSize:this.pageSize,userId:e,orderBy:this.orderBy}};this.$common._post("withdrawalsRecord/list",a,this,function(e){t.total=e.total,t.tableData=e.list,t.listLoading=!1},function(e){t.listLoading=!1,t.tableData=[]})}},mounted:function(){if(!sessionStorage.getItem("zhizunbao_login"))return this.$message("请登录账号"),void this.$router.push("/");this.getList(),this.tableTop=document.querySelector("#main table").getBoundingClientRect().top}}},270:function(t,e,a){e=t.exports=a(182)(!0),e.push([t.i,"","",{version:3,sources:[],names:[],mappings:"",file:"withdraw.vue",sourceRoot:""}])},288:function(t,e,a){var o=a(270);"string"==typeof o&&(o=[[t.i,o,""]]),o.locals&&(t.exports=o.locals);a(183)("52e276e4",o,!0)},306:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("el-row",[a("el-col",{staticClass:"search-bar"},[a("div",{staticClass:"w_200"},[a("el-input",{staticClass:"w_200",attrs:{placeholder:"请输入关键字",icon:"search","on-icon-click":t.search},model:{value:t.searchWord,callback:function(e){t.searchWord=e},expression:"searchWord"}})],1),t._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{on:{click:t.sortTable}},[t._v(t._s(1==t.orderBy?"正":"倒")+"序排列")])],1),t._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{attrs:{loading:t.exportLoading,icon:"document"},on:{click:t.exportExc}},[t._v("导出Excel")])],1)])],1),t._v(" "),a("el-row",[a("el-col",[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.listLoading,expression:"listLoading"}],staticClass:"table",attrs:{data:t.tableData,border:"",width:"100%","max-height":t.tableMaxHeight}},[a("el-table-column",{attrs:{align:"center",label:"序号",width:"80"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n                        "+t._s(e.$index+1+t.pageSize*(t.currentPage-1))+"\n                    ")]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"withdrawalsRecordUuid",label:"打款流水号"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"userId",label:"用户ID"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"userName",label:"姓名"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"bank",label:"开户银行"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"accountNumber",label:"提现账户"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"money",label:"金额"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"handleUuid",label:"负责人ID"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"createName",label:"操作人"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"状态",formatter:t.formatStatus}}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"提现时间",width:"150",formatter:t.getTime}}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"操作",width:"280"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{size:"small",type:"info"},on:{click:function(a){t.editItem(e.$index,e.row)}}},[t._v("查看")]),t._v(" "),a("el-button",{attrs:{disabled:1!=e.row.status,size:"small",type:"success"},on:{click:function(a){t.changeAgree(e.$index,e.row)}}},[t._v("通过")]),t._v(" "),a("el-button",{attrs:{disabled:1!=e.row.status,size:"small",type:"warning"},on:{click:function(a){t.changeReject(e.$index,e.row)}}},[t._v("驳回")]),t._v(" "),a("el-button",{attrs:{size:"small",type:"danger",icon:"delete2"},on:{click:function(a){t.deleteItem(e.$index,e.row)}}},[t._v("删除")])]}}])})],1)],1)],1),t._v(" "),a("el-row",[a("el-col",[a("div",{staticClass:"block pagination"},[a("el-pagination",{attrs:{"current-page":t.currentPage,"page-size":t.pageSize,layout:"prev, pager, next, jumper","page-count":t.total},on:{"update:currentPage":function(e){t.currentPage=e},"current-change":t.handleCurrentChange}})],1)])],1),t._v(" "),a("el-dialog",{staticClass:"edit dialog-info",attrs:{title:"查看详情",visible:t.editDialog},on:{"update:visible":function(e){t.editDialog=e}}},[a("div",{staticClass:"info-content"},[a("el-row",[a("el-col",{attrs:{span:12}},[t._v("\n                    提现账号："),a("span",[t._v(t._s(t.eForm.accountNumber))])]),t._v(" "),a("el-col",{attrs:{span:12}},[t._v("\n                    提现类型："),a("span",[t._v(t._s(1==t.eForm.accountType?"银行卡":2==t.eForm.accountType?"支付宝":3==t.eForm.accountType?"微信":"未知"))])]),t._v(" "),a("el-col",{attrs:{span:12}},[t._v("\n                    提现状态："),a("span",[t._v(t._s(1==t.eForm.applyStatus?"申请中":2==t.eForm.applyStatus?"已通过":3==t.eForm.applyStatus?"已驳回":"未知"))])]),t._v(" "),a("el-col",{attrs:{span:12}},[t._v("\n                    提现银行："),a("span",[t._v(t._s(t.eForm.bank))])]),t._v(" "),a("el-col",{attrs:{span:12}},[t._v("\n                    处理意见："),a("span",[t._v(t._s(t.eForm.handleDsp))])]),t._v(" "),a("el-col",{attrs:{span:12}},[t._v("\n                    处理时间："),a("span",[t._v(t._s(t.formatTime(t.eForm)))])]),t._v(" "),a("el-col",{attrs:{span:12}},[t._v("\n                    提现金额："),a("span",[t._v(t._s(t.eForm.money))])]),t._v(" "),a("el-col",{attrs:{span:12}},[t._v("\n                    银行卡开户人姓名："),a("span",[t._v(t._s(t.eForm.openAccountName))])])],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:function(e){t.editDialog=!1}}},[t._v("确 定")])],1)])],1)},staticRenderFns:[]}}});
//# sourceMappingURL=15.a258339d0801d8b20b2d.js.map