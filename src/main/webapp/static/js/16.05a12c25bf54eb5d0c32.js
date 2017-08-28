webpackJsonp([16],{132:function(t,e,a){function o(t){a(229)}var r=a(18)(a(162),a(247),o,"data-v-13376b99",null);t.exports=r.exports},162:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=a(44),r=a.n(o);e.default={data:function(){return{orderBy:2,listLoading:!1,exportLoading:!1,tableData:[],editDialog:!1,form:{},user:{balance:0,income:0,expenditure:0},currentPage:1,pageSize:20,total:0,tableTop:0}},props:["API"],computed:{tableMaxHeight:function(){return this.$store.commit("setTableMaxHeight",this.tableTop),this.$store.state.tableMaxHeight}},methods:{sortTable:function(){this.orderBy=1==this.orderBy?2:1,this.getList(this.word)},formatTime:function(t,e){return this.$common.getNowTime(t.createAt&&t.createAt.time)},formatFinanceType:function(t,e){return 1==t.financeType?"收入":2==t.financeType?"支出":"未知"},formatterMoney:function(t){return this.$common.formatterMoney(t)},formatOrderRecordType:function(t,e){switch(t.financeType){case 1:return"押金";case 2:return"租车费用";case 3:return"赔偿费用";case 4:return"订单提成";case 5:return"保险费用";default:return"未知"}},getTime:function(t){return this.$common.getNowTime(t)},formatOrderStatus:function(t,e){if(!t.plat)return"--";if(2!=t.plat.depositStatus)return 3==t.plat.depositStatus?"以结案":"--";switch(t.plat.orderStatus){case 3:return"预定成功";case 4:case 5:return"服务中";case 6:return"已还车";default:return"--"}},editItem:function(t,e){var a=this,o={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{financeRecordUuid:e.financeRecordUuid}};this.$common._post("platform/detail",o,this,function(t){a.editDialog=!0,a.form=r()({},t.financeRecord)})},goOrderPage:function(t){this.$router.push("/order"),this.$store.commit("setOrderNumber",t)},handleCurrentChange:function(t){this.currentPage=t,this.getList(this.word)},exportExc:function(){var t=this;this.exportLoading=!0;var e={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{}};this.$http.post(this.API+"exportExcelPlatform",e).then(function(e){t.exportLoading=!1;var e=e.body;"0"==e.body.result?location.href=e.body.fileUrl:t.$message(e.body.description)})},getList:function(){var t=this;this.listLoading=!0;var e={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{pageIndex:this.currentPage,pageSize:this.pageSize,orderBy:this.orderBy}};this.$common._post("platform/list",e,this,function(e){t.total=e.total,t.tableData=e.platformList,t.user=e,t.listLoading=!1},function(e){t.listLoading=!1,t.tableData=[]})}},mounted:function(){if(!sessionStorage.getItem("zhizunbao_login"))return this.$message("请登录账号"),void this.$router.push("/");this.getList(),this.tableTop=document.querySelector("#main table").getBoundingClientRect().top}}},211:function(t,e,a){e=t.exports=a(130)(!0),e.push([t.i,"","",{version:3,sources:[],names:[],mappings:"",file:"platform-account.vue",sourceRoot:""}])},229:function(t,e,a){var o=a(211);"string"==typeof o&&(o=[[t.i,o,""]]),o.locals&&(t.exports=o.locals);a(131)("42f6f9b4",o,!0)},247:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("div",{staticClass:"blance-box"},[a("el-row",[a("el-col",{staticClass:"blance-item",attrs:{span:4}},[a("p",{staticClass:"grid-content"},[t._v(t._s(t.formatterMoney(t.user.totalmoney))+" 元")]),t._v(" "),a("p",[t._v("总营收")])]),t._v(" "),a("el-col",{staticClass:"blance-item",attrs:{span:5}},[a("p",{staticClass:"grid-content"},[t._v(t._s(t.formatterMoney(t.user.income))+" 元")]),t._v(" "),a("p",[t._v("今日收入")])]),t._v(" "),a("el-col",{staticClass:"blance-item",attrs:{span:5}},[a("p",{staticClass:"grid-content"},[t._v(t._s(t.formatterMoney(t.user.highmoney))+" 元")]),t._v(" "),a("p",[t._v("历史新高")])]),t._v(" "),a("el-col",{staticClass:"blance-item",attrs:{span:5}},[a("p",{staticClass:"grid-content"},[t._v(t._s(t.formatterMoney(t.user.expenditure))+" 元")]),t._v(" "),a("p",[t._v("今日结算支出")])]),t._v(" "),a("el-col",{staticClass:"blance-item",attrs:{span:5}},[a("p",{staticClass:"grid-content"},[t._v(t._s(t.formatterMoney(t.user.poolmoney))+" 元")]),t._v(" "),a("p",[t._v("押金池")])])],1)],1),t._v(" "),a("el-row",{staticClass:"search-bar"},[a("el-col",[a("div",{staticClass:"lf"},[a("el-button",{on:{click:t.sortTable}},[t._v(t._s(1==t.orderBy?"正":"倒")+"序排列")])],1),t._v(" "),a("div",{staticClass:"m_l_20 lf"},[a("el-button",{attrs:{loading:t.exportLoading,icon:"document"},on:{click:t.exportExc}},[t._v("导出Excel")])],1)])],1),t._v(" "),a("el-row",[a("el-col",[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.listLoading,expression:"listLoading"}],staticClass:"table",attrs:{data:t.tableData,border:"",width:"100%","max-height":t.tableMaxHeight}},[a("el-table-column",{attrs:{align:"center",label:"序号",width:"80"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n                        "+t._s(e.$index+1+t.pageSize*(t.currentPage-1))+"\n                    ")]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"plat.orderNumber",label:"订单号"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"plat.orderStatus",label:"订单状态",formatter:t.formatOrderStatus}}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"plat.userName",label:"租客"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"plat.ownerName",label:"车东"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"plat.carBrand",label:"车款"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"用车时间"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n                        "+t._s(t.getTime(e.row.plat&&e.row.plat.takeCarTime&&e.row.plat.takeCarTime.time))+"\n                    ")]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"付款时间"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n                        "+t._s(t.getTime(e.row.plat&&e.row.plat.payTime&&e.row.plat.payTime.time))+"\n                    ")]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"plat.payType",label:"付款渠道"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"plat.totalPrice",label:"付款金额"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"plat.deposit",label:"押金金额"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"plat.city",label:"城市"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"订单链接"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{type:"text"},on:{click:function(a){t.goOrderPage(e.row.plat&&e.row.plat.orderNumber||"")}}},[t._v("查看")])]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"时间",width:"110",formatter:t.formatTime}})],1)],1)],1),t._v(" "),a("el-row",[a("el-col",[a("div",{staticClass:"block pagination"},[a("el-pagination",{attrs:{"current-page":t.currentPage,"page-size":t.pageSize,layout:"prev, pager, next, jumper","page-count":t.total},on:{"update:currentPage":function(e){t.currentPage=e},"current-change":t.handleCurrentChange}})],1)])],1),t._v(" "),a("el-dialog",{staticClass:"edit dialog-info",attrs:{visible:t.editDialog},on:{"update:visible":function(e){t.editDialog=e}}},[a("div",{staticClass:"info-content"},[a("el-row",[a("el-col",[t._v("交易编码："),a("span",[t._v(t._s(t.form.financeRecordUuid))])]),t._v(" "),a("el-col",{attrs:{span:12}},[t._v("转出人："),a("span",[t._v(t._s(t.form.outMan))])]),t._v(" "),a("el-col",{attrs:{span:12}},[t._v("转出账户："),a("span",[t._v(t._s(t.form.outUser))])]),t._v(" "),a("el-col",{attrs:{span:12}},[t._v("转入人："),a("span",[t._v(t._s(t.form.intoMan))])]),t._v(" "),a("el-col",{attrs:{span:12}},[t._v("转入账户："),a("span",[t._v(t._s(t.form.intoUser))])]),t._v(" "),a("el-col",[t._v("交易金额："),a("span",[t._v(t._s(t.form.money))])]),t._v(" "),a("el-col",[t._v("收支类型："),a("span",[t._v(t._s(t.formatFinanceType(t.form)))])]),t._v(" "),a("el-col",[t._v("交易内容："),a("span",[t._v(t._s(t.formatOrderRecordType(t.form)))])]),t._v(" "),a("el-col",[t._v("交易时间："),a("span",[t._v(t._s(t.formatTime(t.form)))])])],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",slot:"footer"},[a("el-button",{attrs:{type:"primary"},on:{click:function(e){t.editDialog=!1}}},[t._v("确 定")])],1)])],1)},staticRenderFns:[]}}});
//# sourceMappingURL=16.05a12c25bf54eb5d0c32.js.map