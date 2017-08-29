webpackJsonp([8],{194:function(e,t,r){function a(e){r(288)}var o=r(24)(r(224),r(306),a,"data-v-3846482c",null);e.exports=o.exports},224:function(e,t,r){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=r(60),o=r.n(a);t.default={data:function(){return{orderWord:"",nameWord:"",searchOrder:"",searchName:"",orderBy:2,listLoading:!1,exportLoading:!1,tableData:[],editDialog:!1,form:{orderNumber:null,userName:"",useCarTime:"",totalPrice:"",orderStatus:"",createAt:{time:null}},currentPage:1,pageSize:20,total:0,tableTop:0}},props:["API"],computed:{tableMaxHeight:function(){return this.$store.commit("setTableMaxHeight",this.tableTop),this.$store.state.tableMaxHeight}},methods:{search:function(e){this.orderWord=this.searchOrder,this.nameWord=this.searchName,this.currentPage=1,this.getList(this.nameWord,this.orderWord)},sortTable:function(){this.orderBy=1==this.orderBy?2:1,this.getList(this.nameWord,this.orderWord)},formatTime:function(e,t){return this.$common.getNowTime(e.takeCarTime&&e.takeCarTime.time)},getTime:function(e){return this.$common.getNowTime(e)},formatStatus:function(e,t){switch(e.orderStatus){case 1:return"未接单";case 2:return"拒绝接单";case 3:case 4:return"已接单";case 5:return"确认还车未收车";case 6:return"已收车";case 7:return"已取消";case 8:return"已退款";default:return"未知"}},editItem:function(e,t){var r=this,a={head:this.$common.getUserStorage(),body:{order_uuid:t.orderUuid}};this.$common._post("order/detail",a,this,function(e){r.editDialog=!0,r.form=o()({},e)})},exportExc:function(){var e=this;this.exportLoading=!0;var t={head:this.$common.getUserStorage(),body:{}};this.$http.post(this.API+"exportExcelOrder",t).then(function(t){e.exportLoading=!1;var t=t.body;"0"==t.body.result?location.href=t.body.fileUrl:e.$message(t.body.description)})},cancelOrder:function(e,t){var r=this;this.$confirm("是否确认取消订单?","提示",{type:"warning"}).then(function(){var e={head:r.$common.getUserStorage(),body:{order_uuid:t.orderUuid}};r.$common._post("order/cancelOrder",e,r,function(e){r.$message({type:"success",message:e.description}),r.getList()})}).catch(function(){})},transfer:function(e,t){var r=this;this.$prompt("请输入转账金额：","转账",{inputPattern:/^([1-9]\d{0,8}\.\d{1,2}|0\.\d{1,2}|[1-9]\d{0,8})$/,inputErrorMessage:"金额格式不正确"}).then(function(e){var a=e.value;r.$confirm("是否确认转账 "+Math.floor(100*a)/100+" 元?","提示",{type:"warning"}).then(function(){var e={head:r.$common.getUserStorage(),body:{order_uuid:t.orderUuid,money:a}};r.$common._post("order/transferOrder",e,r,function(e){r.$message({type:"success",message:e.description}),r.getList()})}).catch(function(){})}).catch(function(){})},toTicketPage:function(e){this.$router.push("/ticket-info"),this.$store.commit("setTicketName",e)},handleCurrentChange:function(e){this.currentPage=e,this.getList(this.nameWord,this.orderWord)},getList:function(){var e=this,t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"",r=arguments.length>1&&void 0!==arguments[1]?arguments[1]:"";this.listLoading=!0;var a={head:this.$common.getUserStorage(),body:{pageIndex:this.currentPage,pageSize:this.pageSize,orderNumber:r,userName:t,orderBy:this.orderBy}};this.$common._post("order/list",a,this,function(t){e.total=t.total,e.tableData=t.orderList,e.listLoading=!1},function(t){e.listLoading=!1,e.tableData=[]})}},mounted:function(){if(!sessionStorage.getItem("zhizunbao_login"))return this.$message("请登录账号"),void this.$router.push("/");this.$store.state.orderNumber&&(this.searchOrder=this.$store.state.orderNumber,this.orderWord=this.searchOrder),this.getList("",this.orderWord),this.tableTop=document.querySelector("#main table").getBoundingClientRect().top}}},270:function(e,t,r){t=e.exports=r(182)(!0),t.push([e.i,"","",{version:3,sources:[],names:[],mappings:"",file:"order.vue",sourceRoot:""}])},288:function(e,t,r){var a=r(270);"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);r(183)("c1383dee",a,!0)},306:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",[r("el-row",[r("el-col",{staticClass:"search-bar"},[r("el-input",{staticClass:"w_200",attrs:{placeholder:"请输入订单号"},model:{value:e.searchOrder,callback:function(t){e.searchOrder=t},expression:"searchOrder"}}),e._v(" "),r("div",{staticClass:"m_l_20"},[r("el-input",{staticClass:"w_200",attrs:{placeholder:"请输入用户"},model:{value:e.searchName,callback:function(t){e.searchName=t},expression:"searchName"}})],1),e._v(" "),r("div",{staticClass:"m_l_20"},[r("el-button",{attrs:{icon:"search"},on:{click:e.search}},[e._v("搜索")])],1),e._v(" "),r("div",{staticClass:"m_l_20"},[r("el-button",{on:{click:e.sortTable}},[e._v(e._s(1==e.orderBy?"正":"倒")+"序排列")])],1),e._v(" "),r("div",{staticClass:"m_l_20"},[r("el-button",{attrs:{loading:e.exportLoading,icon:"document"},on:{click:e.exportExc}},[e._v("导出Excel")])],1)],1)],1),e._v(" "),r("el-row",[r("el-col",[r("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticClass:"table",attrs:{data:e.tableData,border:"",width:"100%","max-height":e.tableMaxHeight}},[r("el-table-column",{attrs:{align:"center",label:"序号",width:"80"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                           "+e._s(t.$index+1+e.pageSize*(e.currentPage-1))+"\n                       ")]}}])}),e._v(" "),r("el-table-column",{attrs:{align:"center",prop:"orderNumber",label:"订单号"}}),e._v(" "),r("el-table-column",{attrs:{align:"center",prop:"userName",label:"租客"}}),e._v(" "),r("el-table-column",{attrs:{align:"center",prop:"user_owner_name",label:"车东"}}),e._v(" "),r("el-table-column",{attrs:{align:"center",prop:"carUuid",label:"车辆"}}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"使用时间",width:"110",formatter:e.formatTime}}),e._v(" "),r("el-table-column",{attrs:{align:"center",prop:"useCarTime",label:"时长"}}),e._v(" "),r("el-table-column",{attrs:{align:"center",prop:"leasePrice",label:"租金"}},[e._v("\n--\x3e                ")]),e._v(" "),r("el-table-column",{attrs:{align:"center",prop:"deposit",label:"押金"}}),e._v(" "),r("el-table-column",{attrs:{align:"center",prop:"takeCarAddress",label:"取车地点"}}),e._v(" "),r("el-table-column",{attrs:{align:"center",prop:"returnCarAddress",label:"还车地点"}}),e._v(" "),r("el-table-column",{attrs:{align:"center",prop:"orderStatus",label:"状态",formatter:e.formatStatus}}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"操作",width:"200"},scopedSlots:e._u([{key:"default",fn:function(t){return[r("el-button",{attrs:{type:"warning",size:"small"},on:{click:function(r){e.transfer(t.$index,t.row)}}},[e._v("转账")]),e._v(" "),r("el-button",{attrs:{type:"danger",size:"small",disabled:7==t.row.orderStatus},on:{click:function(r){e.cancelOrder(t.$index,t.row)}}},[e._v(e._s(7==t.row.orderStatus?"订单已被取消":"人工取消订单"))])]}}])})],1)],1)],1),e._v(" "),r("el-row",[r("el-col",[r("div",{staticClass:"block pagination"},[r("el-pagination",{attrs:{"current-page":e.currentPage,"page-size":e.pageSize,layout:"prev, pager, next, jumper","page-count":e.total},on:{"update:currentPage":function(t){e.currentPage=t},"current-change":e.handleCurrentChange}})],1)])],1),e._v(" "),r("el-dialog",{staticClass:"edit-dialog dialog-info",attrs:{title:"订单详情",visible:e.editDialog},on:{"update:visible":function(t){e.editDialog=t}}},[r("div",{staticClass:"info-content"},[r("el-row",[r("el-col",[e._v("订单号："),r("span",[e._v(e._s(e.form.orderNumber))])]),e._v(" "),r("el-col",[e._v("用户："),r("span",[e._v(e._s(e.form.userName))])]),e._v(" "),r("el-col",[e._v("车东："),r("span",[e._v(e._s(e.form.carOwner))])]),e._v(" "),r("el-col",[e._v("费用："),r("span",[e._v(e._s(e.form.totalPrice||0)+" 元")])]),e._v(" "),r("el-col",[e._v("取车时间："),r("span",[e._v(e._s(e.form.takeCarTime))])]),e._v(" "),r("el-col",[e._v("还车时间："),r("span",[e._v(e._s(e.form.returnCarTime))])]),e._v(" "),r("el-col",[e._v("用车时长："),r("span",[e._v(e._s(e.form.userCarTime))])]),e._v(" "),r("el-col",[e._v("取车地点："),r("span",[e._v(e._s(e.form.takeCarAddress))])]),e._v(" "),r("el-col",[e._v("还车地点："),r("span",[e._v(e._s(e.form.returnCarAddress))])]),e._v(" "),r("el-col",[e._v("罚单："+e._s(e.form.count)),e.form.count?r("el-button",{attrs:{type:"text"},on:{click:function(t){e.toTicketPage(e.form.userName)}}},[e._v("　查看")]):e._e()],1),e._v(" "),r("el-col",[e._v("状态："),r("span",[e._v(e._s(e.formatStatus(e.form)))])]),e._v(" "),r("el-col",[e._v("下单时间："),r("span",[e._v(e._s(e.form.createAt))])])],1)],1),e._v(" "),r("p",{staticClass:"info-title"},[e._v("费用明细：")]),e._v(" "),r("div",{staticClass:"info-content"},[r("el-row",[r("el-col",{attrs:{span:24}},[e._v("\n                       车辆租赁费："),r("span",[e._v(e._s(e.form.leasePrice||0)+" 元")])]),e._v(" "),r("el-col",{attrs:{span:24}},[e._v("\n                       基本保险费："),r("span",[e._v(e._s(e.form.insurancePrice||0)+" 元")])]),e._v(" "),r("el-col",{attrs:{span:24}},[e._v("\n                       其他费用："),r("span",[e._v(e._s(e.form.otherPrice||0)+" 元")])])],1)],1),e._v(" "),r("p",{staticClass:"info-title"},[e._v("车损照片：")]),e._v(" "),r("el-row",{staticClass:"info-content"},[r("el-col",[r("img",{attrs:{src:e.form.damagePicture,alt:""}})])],1),e._v(" "),r("p",{staticClass:"info-title"},[e._v("车损描述")]),e._v(" "),r("el-row",{staticClass:"info-content"},[r("el-col",[r("p",[e._v(e._s(e.form.damageDsp))])])],1),e._v(" "),r("p",{staticClass:"info-title"},[e._v("协调赔偿：")]),e._v(" "),r("div",{staticClass:"info-content"},[r("el-row",[r("el-col",[r("p",[e._v(e._s(e.form.compensateMoney||0)+"元")])])],1)],1),e._v(" "),r("div",{staticClass:"dialog-footer",slot:"footer"},[r("el-button",{attrs:{type:"primary"},on:{click:function(t){e.editDialog=!1}}},[e._v("确 定")])],1)],1)],1)},staticRenderFns:[]}}});
//# sourceMappingURL=8.176770f2e48842bf37cb.js.map