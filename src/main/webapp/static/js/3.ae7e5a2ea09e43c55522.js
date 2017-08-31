webpackJsonp([3],{201:function(e,t,a){function o(e){a(293)}var r=a(24)(a(231),a(311),o,"data-v-5feecd12",null);e.exports=r.exports},231:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=a(60),r=a.n(o);t.default={data:function(){return{word:"",searchWord:"",orderBy:2,listLoading:!1,exportLoading:!1,tableData:[],eForm:{},editDialog:!1,radio:"1",currentPage:1,pageSize:20,total:0,tableTop:0}},props:["API"],computed:{tableMaxHeight:function(){return this.$store.commit("setTableMaxHeight",this.tableTop),this.$store.state.tableMaxHeight}},methods:{search:function(e){this.word=this.searchWord,this.currentPage=1,this.getList(this.word)},sortTable:function(){this.orderBy=1==this.orderBy?2:1,this.getList(this.word)},formatTime:function(e,t){return this.$common.getNowTime(e.createAt&&e.createAt.time)},getTime:function(e){return this.$common.getNowTime(e)},formatStatus:function(e,t){return 1==e.managerStatus?"激活":2==e.managerStatus?"冻结":"--"},getCarStatus:function(e){switch(e){case 1:return"审核中";case 2:return"审核成功";case 3:return"审核未通过";case 4:return"冻结";case 5:return"驳回";default:return"--"}},formatVehicleDeliveryMode:function(e){switch(e){case 2:return"自助交车";case 0:case void 0:case null:case 1:return"当面交车";default:return"--"}},editItem:function(e,t){var a=this,o={head:this.$common.getUserStorage(),body:{carUuid:t.car_uuid}};this.$common._post("car/detail",o,this,function(e){a.editDialog=!0,a.eForm=r()({},e.car),a.eForm.userName=e.userName})},editSave:function(e){var t=this;this.$confirm("是否确认修改?","提示",{}).then(function(){var e={head:t.$common.getUserStorage(),body:{carUuid:t.eForm.carUuid,carStatus:t.eForm.carStatus}};t.$common._post("car/updateCarStatus",e,t,function(e){t.editDialog=!1,t.$message({type:"success",message:e.description}),t.getList()})}).catch(function(){})},deleteItem:function(e,t){var a={head:this.$common.getUserStorage(),body:{carUuid:t.car_uuid}};this.$common.deleteItem(this.API+"car/delete",this,a)},changestatus:function(e,t){var a=this;this.$confirm("是否确认修改?","提示",{}).then(function(){var e={head:a.$common.getUserStorage(),body:{carUuid:t.car_uuid,carStatus:4}};a.$common._post("car/updateCarStatus",e,a,function(e){a.editDialog=!1,a.$message({type:"success",message:e.description}),a.getList()})}).catch(function(){})},exportExc:function(){var e=this;this.exportLoading=!0;var t={head:this.$common.getUserStorage(),body:{}};this.$http.post(this.API+"exportExcelCar",t).then(function(t){e.exportLoading=!1;var t=t.body;"0"==t.body.result?location.href=t.body.fileUrl:e.$message(t.body.description)})},handleCurrentChange:function(e){this.currentPage=e,this.getList(this.word)},getList:function(){var e=this,t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"";this.listLoading=!0;var a={head:this.$common.getUserStorage(),body:{pageIndex:this.currentPage,pageSize:this.pageSize,modelCharacter:t,orderBy:this.orderBy}};this.$common._post("car/list",a,this,function(t){e.total=t.total,e.tableData=t.carList,e.listLoading=!1},function(t){e.listLoading=!1,e.tableData=[]})}},mounted:function(){if(!sessionStorage.getItem("zhizunbao_login"))return this.$message("请登录账号"),void this.$router.push("/");this.getList(),this.tableTop=document.querySelector("#main table").getBoundingClientRect().top}}},275:function(e,t,a){t=e.exports=a(182)(!0),t.push([e.i,"","",{version:3,sources:[],names:[],mappings:"",file:"vehicle-info.vue",sourceRoot:""}])},293:function(e,t,a){var o=a(275);"string"==typeof o&&(o=[[e.i,o,""]]),o.locals&&(e.exports=o.locals);a(183)("6bd7b352",o,!0)},311:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-row",[a("el-col",{staticClass:"search-bar"},[a("el-input",{staticClass:"w_200",attrs:{placeholder:"请输入车型",icon:"search","on-icon-click":e.search},model:{value:e.searchWord,callback:function(t){e.searchWord=t},expression:"searchWord"}}),e._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{on:{click:e.sortTable}},[e._v(e._s(1==e.orderBy?"正":"倒")+"序排列")])],1),e._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{attrs:{loading:e.exportLoading,icon:"document"},on:{click:e.exportExc}},[e._v("导出Excel")])],1)],1)],1),e._v(" "),a("el-row",[a("el-col",[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticClass:"table",attrs:{data:e.tableData,border:"",width:"100%","max-height":e.tableMaxHeight}},[a("el-table-column",{attrs:{align:"center",label:"序号",width:"80"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                        "+e._s(t.$index+1+e.pageSize*(e.currentPage-1))+"\n                    ")]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"car_uuid",label:"车辆ID"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"user_uuid",label:"用户ID"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"最后登录时间"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                        "+e._s(e.getTime(t.row.updatetime&&t.row.updatetime.time))+"\n                    ")]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"car.modelCharacter",label:"车型"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"car.color",label:"颜色"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"car.address",label:"取车位置"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"hot",label:"需求热度"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"状态",width:"120"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                        "+e._s(e.getCarStatus(t.row.car.carStatus))+"\n                    ")]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"ordercount",label:"订单数"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"rate",label:"差价利润"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"price",label:"上架价格"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"操作",width:"260"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"info",size:"small"},on:{click:function(a){e.editItem(t.$index,t.row)}}},[e._v("查看详情")]),e._v(" "),a("el-button",{attrs:{type:"warning",size:"small",disabled:4==t.row.carStatus},on:{click:function(a){e.changestatus(t.$index,t.row)}}},[e._v(e._s(4==t.row.carStatus?"已冻结":"冻　结"))]),e._v(" "),a("el-button",{attrs:{size:"small",type:"danger",icon:"delete2"},on:{click:function(a){e.deleteItem(t.$index,t.row)}}},[e._v("删除")])]}}])})],1)],1)],1),e._v(" "),a("el-row",[a("el-col",[a("div",{staticClass:"block pagination"},[a("el-pagination",{attrs:{"current-page":e.currentPage,"page-size":e.pageSize,layout:"prev, pager, next, jumper","page-count":e.total},on:{"update:currentPage":function(t){e.currentPage=t},"current-change":e.handleCurrentChange}})],1)])],1),e._v(" "),a("el-dialog",{staticClass:"edit dialog-info",attrs:{size:"large",visible:e.editDialog},on:{"update:visible":function(t){e.editDialog=t}}},[a("div",{staticClass:"info-content"},[a("el-row",[a("el-col",[e._v("用户："),a("span",[e._v(e._s(e.eForm.userName))])]),e._v(" "),a("el-col",[e._v("车主："),a("span",[e._v(e._s(e.eForm.carOwnerName))])]),e._v(" "),a("el-col",[e._v("车辆ID："),a("span",[e._v(e._s(e.eForm.carUuid))])]),e._v(" "),a("el-col",[e._v("车型："),a("span",[e._v(e._s(e.eForm.model))])]),e._v(" "),a("el-col",[e._v("车牌："),a("span",[e._v(e._s(e.eForm.licensePlateNumber))])]),e._v(" "),a("el-col",[e._v("颜色："),a("span",[e._v(e._s(e.eForm.color))])]),e._v(" "),a("el-col",[e._v("注册时间："),a("span",[e._v(e._s(e.getTime(e.eForm.registerTime&&e.eForm.registerTime.time)))])]),e._v(" "),a("el-col",[e._v("排量："),a("span",[e._v(e._s(e.eForm.displacement))])]),e._v(" "),a("el-col",[e._v("发动机号："),a("span",[e._v(e._s(e.eForm.engineNumber))])]),e._v(" "),a("el-col",[e._v("车架号："),a("span",[e._v(e._s(e.eForm.vehicleIdentificationNumber))])]),e._v(" "),a("el-col",[e._v("变速箱："),a("span",[e._v(e._s(1==e.eForm.manualOrAutomatic?"自动":2==e.eForm.manualOrAutomatic?"手动":3==e.eForm.manualOrAutomatic?"手自一体":"--"))])]),e._v(" "),a("el-col",[e._v("年款："),a("span",[e._v(e._s(e.eForm.modelYear))])]),e._v(" "),a("el-col",[e._v("座位数："),a("span",[e._v(e._s(e.eForm.seatNumber))])]),e._v(" "),a("el-col",[e._v("类型："),a("span",[e._v(e._s(1==e.eForm.carType?"出租":2==e.eForm.carType?"日常":"--"))])]),e._v(" "),a("el-col",[e._v("城市："),a("span",[e._v(e._s(e.eForm.city))])]),e._v(" "),a("el-col",[e._v("接单量："),a("span",[e._v(e._s(e.eForm.orderQuantity))])]),e._v(" "),a("el-col",[e._v("\n                    罚款数："),a("span",[e._v(e._s(e.eForm.userID))])]),e._v(" "),a("el-col",[e._v("\n                    出租价格：\n                    "),a("span",[e._v("周末"+e._s(e.eForm.weekendPrice))]),e._v("\n                      \n                    "),a("span",[e._v("工作日"+e._s(e.eForm.workingDayPrice))])]),e._v(" "),a("el-col",{attrs:{span:12}},[e._v("\n                    人车合影："),a("img",{staticClass:"photo",attrs:{src:e.eForm.manCarPhoto}})]),e._v(" "),a("el-col",{attrs:{span:12}},[e._v("\n                    行驶证照片："),a("img",{staticClass:"photo",attrs:{src:e.eForm.drivingLicensePhoto}})]),e._v(" "),a("el-col",[e._v("上传时间："),a("span",[e._v(e._s(e.formatTime(e.eForm)))])])],1)],1),e._v(" "),a("p",{staticClass:"info-title"},[e._v("车辆配置：")]),e._v(" "),a("div",{staticClass:"info-content"},[a("el-row",[a("el-col",{attrs:{span:5}},[e._v("\n                    交车方式："),a("span",[e._v(e._s(e.formatVehicleDeliveryMode(e.eForm.vehicleDeliveryMode)))])]),e._v(" "),a("el-col",{attrs:{span:5}},[e._v("\n                    行车里程："),a("span",[e._v(e._s(e.eForm.mileage))])]),e._v(" "),a("el-col",{attrs:{span:5}},[e._v("\n                    车龄："),a("span",[e._v(e._s(e.eForm.vehicleAge))])]),e._v(" "),a("el-col",{attrs:{span:5}},[e._v("\n                    是否有导航："),a("span",[e._v(e._s(1==e.eForm.isNavigation?"有":2==e.eForm.isNavigation?"无":"--"))])]),e._v(" "),a("el-col",{attrs:{span:5}},[e._v("\n                    是否能外接mp3："),a("span",[e._v(e._s(1==e.eForm.isMp3?"能":2==e.eForm.isMp3?"不能":"--"))])])],1),e._v(" "),a("el-row",[a("el-col",[e._v("\n                    车辆描述："),a("span",[e._v(e._s(e.eForm.carDsp))])])],1)],1),e._v(" "),a("div",{staticClass:"info-radio"},[a("el-radio-group",{model:{value:e.eForm.carStatus,callback:function(t){e.eForm.carStatus=t},expression:"eForm.carStatus"}},[a("el-radio",{staticClass:"radio",attrs:{label:1}},[e._v("审核中")]),e._v(" "),a("el-radio",{staticClass:"radio",attrs:{label:2}},[e._v("审核成功")]),e._v(" "),a("el-radio",{staticClass:"radio",attrs:{label:3}},[e._v("审核未通过")]),e._v(" "),a("el-radio",{staticClass:"radio",attrs:{label:4}},[e._v("冻结")]),e._v(" "),a("el-radio",{staticClass:"radio",attrs:{label:5}},[e._v("驳回")])],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",slot:"footer"},[a("el-button",{on:{click:function(t){e.editDialog=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.editSave("editForm")}}},[e._v("确 定")])],1)])],1)},staticRenderFns:[]}}});
//# sourceMappingURL=3.ae7e5a2ea09e43c55522.js.map