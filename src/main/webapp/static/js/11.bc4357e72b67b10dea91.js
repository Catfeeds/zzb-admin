webpackJsonp([11],{190:function(e,t,i){function o(e){i(282)}var a=i(24)(i(220),i(300),o,null,null);e.exports=a.exports},220:function(e,t,i){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=i(60),a=i.n(o);t.default={data:function(){var e=this;return{maxLength:100,word:"",searchWord:"",orderBy:2,listLoading:!1,exportLoading:!1,tableData:[],isDisplay:"",options:[{value:"",label:"全部"},{value:1,label:"上架"},{value:2,label:"下架"}],editDialog:!1,addDialog:!1,eForm:{operationInfo:""},aForm:{operationInfo:""},currentPage:1,pageSize:20,total:0,tableTop:0,viewPicDialog:!1,dialogVisible:!1,picUrl:null,picUrlList:null,rules:{pic:[{validator:function(t,i,o){e.picUrlList?o():o(new Error("请上传图片后重试"))},trigger:"change"}],operationInfo:[{required:!0,message:"内容不能为空",trigger:"blur"}],link:[{required:!0,message:"链接不能为空",trigger:"blur"}]}}},computed:{eLavaNum:function(){return this.eForm.operationInfo&&this.eForm.operationInfo.length||0},aLavaNum:function(){return this.aForm.operationInfo&&this.aForm.operationInfo.length||0},tableMaxHeight:function(){return this.$store.commit("setTableMaxHeight",this.tableTop),this.$store.state.tableMaxHeight}},props:["API"],methods:{search:function(e){this.word=this.searchWord,this.currentPage=1,this.getList(this.word)},sortTable:function(){this.orderBy=1==this.orderBy?2:1,this.getList(this.word)},formatTime:function(e,t){return this.$common.getNowTime(e.createAt&&e.createAt.time)},formatStatus:function(e,t){return 1==e.isDisplay?"上架":2==e.isDisplay?"下架":"未知"},editItem:function(e,t){var i=this,o={head:this.$common.getUserStorage(),body:{id:t.id}};this.$common._post("homePage/detail",o,this,function(e){i.eForm=a()({},e.banner),i.editDialog=!0,i.picUrlList=i.eForm.operationPicture})},editSave:function(e){var t=this;this.$refs[e].validate(function(e){e&&t.$confirm("是否确认修改?","提示",{}).then(function(){t.eForm.picUrl=t.picUrlList;var e={head:t.$common.getUserStorage(),body:{operation_info:t.eForm.operationInfo,is_display:t.eForm.isDisplay,id:t.eForm.id,operation_picture:t.eForm.picUrl,link:t.eForm.link}};t.$common._post("homePage/edit",e,t,function(e){t.editDialog=!1,t.$message({type:"success",message:"编辑成功!"}),t.getList()})}).catch(function(){})})},addItem:function(){this.aForm={isDisplay:1,operationInfo:"",picUrl:"",link:""},this.picUrlList="",this.addDialog=!0},addSave:function(e){var t=this;this.$refs[e].validate(function(e){e&&t.$confirm("是否确认添加?","提示",{}).then(function(){t.aForm.picUrl=t.picUrlList;var e={head:t.$common.getUserStorage(),body:{operation_info:t.aForm.operationInfo,is_display:t.aForm.isDisplay,operation_picture:t.aForm.picUrl,link:t.aForm.link}};t.$common._post("homePage/add",e,t,function(e){t.addDialog=!1,t.$message({type:"success",message:"添加成功!"}),t.getList()})}).catch(function(){})})},changeDisplay:function(e,t,i){var o=this;this.$confirm("是否确定修改?","提示",{}).then(function(){var e={head:o.$common.getUserStorage(),body:{is_display:i,id:t.id}};o.$common._post("homePage/editceil",e,o,function(e){o.$message({type:"success",message:"修改成功!"}),o.getList()})}).catch(function(){})},changePosition:function(e,t){},deleteItem:function(e,t){var i={head:this.$common.getUserStorage(),body:{id:t.id}};this.$common.deleteItem(this.API+"homePage/delete",this,i)},resetFields:function(e,t){this.$refs[e].resetFields(),t&&this.$refs[t].clearFiles(),this.eForm={},this.picUrlList=null},onSuccess:function(e,t,i){0==e.body.result&&(this.picUrlList=e.body.attachment_url),this.$message(e.body.description)},onError:function(e,t,i){this.$message(e.body.description)},showPic:function(e,t){this.viewPicDialog=!0,this.picUrl=t.operationPicture},closeViewPic:function(){this.picUrl=""},exportExc:function(){var e=this;this.exportLoading=!0;var t={head:this.$common.getUserStorage(),body:{}};this.$http.post(this.API+"exportExcelHomePageBanner",t).then(function(t){e.exportLoading=!1;var t=t.body;"0"==t.body.result?location.href=t.body.fileUrl:e.$message(t.body.description)})},handleCurrentChange:function(e){this.currentPage=e,this.getList(this.word)},getList:function(){var e=this,t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"";this.listLoading=!0;var i={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{pageIndex:this.currentPage,pageSize:this.pageSize,operation_info:t,is_display:this.isDisplay,orderBy:this.orderBy}};this.$common._post("homePage/list",i,this,function(t){e.total=t.total,e.tableData=t.homePageBannerlist,e.listLoading=!1},function(t){e.listLoading=!1,e.tableData=[]})}},mounted:function(){if(!sessionStorage.getItem("zhizunbao_login"))return this.$message("请登录账号"),void this.$router.push("/");this.getList(),this.tableTop=document.querySelector("#main table").getBoundingClientRect().top}}},264:function(e,t,i){t=e.exports=i(182)(!0),t.push([e.i,".avatar-uploader .el-upload{border:1px dashed #d9d9d9;border-radius:6px;cursor:pointer;position:relative;overflow:hidden}.avatar-uploader .el-upload:hover{border-color:#20a0ff}.avatar-uploader-icon{font-size:28px;color:#8c939d;width:178px;height:178px;line-height:178px;text-align:center}.avatar{width:178px;height:178px;display:block}","",{version:3,sources:["F:/webstorm/cichang/zhizunbao/src/views/operate-info/banner-list.vue"],names:[],mappings:"AACA,4BACE,0BAA2B,AAC3B,kBAAmB,AACnB,eAAgB,AAChB,kBAAmB,AACnB,eAAiB,CAClB,AACD,kCACE,oBAAsB,CACvB,AACD,sBACE,eAAgB,AAChB,cAAe,AACf,YAAa,AACb,aAAc,AACd,kBAAmB,AACnB,iBAAmB,CACpB,AACD,QACE,YAAa,AACb,aAAc,AACd,aAAe,CAChB",file:"banner-list.vue",sourcesContent:["\n.avatar-uploader .el-upload {\n  border: 1px dashed #d9d9d9;\n  border-radius: 6px;\n  cursor: pointer;\n  position: relative;\n  overflow: hidden;\n}\n.avatar-uploader .el-upload:hover {\n  border-color: #20a0ff;\n}\n.avatar-uploader-icon {\n  font-size: 28px;\n  color: #8c939d;\n  width: 178px;\n  height: 178px;\n  line-height: 178px;\n  text-align: center;\n}\n.avatar {\n  width: 178px;\n  height: 178px;\n  display: block;\n}\n"],sourceRoot:""}])},282:function(e,t,i){var o=i(264);"string"==typeof o&&(o=[[e.i,o,""]]),o.locals&&(e.exports=o.locals);i(183)("6a772abe",o,!0)},300:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",[i("el-row",[i("el-col",{staticClass:"search-bar"},[i("el-input",{staticClass:"w_200",attrs:{placeholder:"请输入运营信息",icon:"search","on-icon-click":e.search},model:{value:e.searchWord,callback:function(t){e.searchWord=t},expression:"searchWord"}}),e._v(" "),i("div",{staticClass:"m_l_20 w_100"},[i("el-select",{on:{change:e.search},model:{value:e.isDisplay,callback:function(t){e.isDisplay=t},expression:"isDisplay"}},e._l(e.options,function(e){return i("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1),e._v(" "),i("div",{staticClass:"m_l_20"},[i("el-button",{on:{click:e.sortTable}},[e._v(e._s(1==e.orderBy?"正":"倒")+"序排列")])],1),e._v(" "),i("div",{staticClass:"m_l_20"},[i("el-button",{attrs:{icon:"plus"},on:{click:e.addItem}},[e._v("新建内容")])],1),e._v(" "),i("div",{staticClass:"m_l_20"},[i("el-button",{attrs:{loading:e.exportLoading,icon:"document"},on:{click:e.exportExc}},[e._v("导出Excel")])],1)],1)],1),e._v(" "),i("p",{staticClass:"tips",staticStyle:{"font-size":"12px","margin-bottom":"10px",color:"#9a9a9a"}},[e._v("*轮播图上架数量上限为 4 张")]),e._v(" "),i("el-row",[i("el-col",[i("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticClass:"table",attrs:{data:e.tableData,border:"",width:"100%","max-height":e.tableMaxHeight}},[i("el-table-column",{attrs:{align:"center",label:"序号",width:"70"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                        "+e._s(t.$index+1+e.pageSize*(e.currentPage-1))+"\n                    ")]}}])}),e._v(" "),i("el-table-column",{attrs:{align:"center",label:"图片",width:"80"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("el-button",{attrs:{type:"text"},on:{click:function(i){e.showPic(t.$index,t.row)}}},[e._v("预览")])]}}])}),e._v(" "),i("el-table-column",{attrs:{align:"center",prop:"operationInfo",label:"运营信息"}}),e._v(" "),i("el-table-column",{attrs:{align:"center",label:"状态",formatter:e.formatStatus}}),e._v(" "),i("el-table-column",{attrs:{align:"center",label:"上/下架时间",width:"150",formatter:e.formatTime}}),e._v(" "),i("el-table-column",{attrs:{align:"center",label:"操作",width:"250"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("el-button",{attrs:{type:"info",size:"small",icon:"edit"},on:{click:function(i){e.editItem(t.$index,t.row)}}},[e._v("编辑")]),e._v(" "),i("el-button",{directives:[{name:"show",rawName:"v-show",value:2==t.row.isDisplay,expression:"scope.row.isDisplay == 2"}],attrs:{type:"warning",size:"small"},on:{click:function(i){e.changeDisplay(t.$index,t.row,1)}}},[e._v("上架")]),e._v(" "),i("el-button",{directives:[{name:"show",rawName:"v-show",value:1==t.row.isDisplay,expression:"scope.row.isDisplay == 1"}],attrs:{type:"warning",size:"small"},on:{click:function(i){e.changeDisplay(t.$index,t.row,2)}}},[e._v("下架")]),e._v(" "),i("el-button",{attrs:{size:"small",type:"danger",icon:"delete2"},on:{click:function(i){e.deleteItem(t.$index,t.row)}}},[e._v("删除")])]}}])})],1)],1)],1),e._v(" "),i("el-row",[i("el-col",[i("div",{staticClass:"block pagination"},[i("el-pagination",{attrs:{"current-page":e.currentPage,"page-size":e.pageSize,layout:"prev, pager, next, jumper","page-count":e.total},on:{"update:currentPage":function(t){e.currentPage=t},"current-change":e.handleCurrentChange}})],1)])],1),e._v(" "),i("el-dialog",{staticClass:"edit dialog-info",attrs:{"close-on-click-modal":!1,title:"编辑",visible:e.editDialog},on:{"update:visible":function(t){e.editDialog=t},close:function(t){e.resetFields("editForm","editPicFile")}}},[i("el-form",{ref:"editForm",attrs:{model:e.eForm,rules:e.rules}},[i("el-form-item",{attrs:{label:"运营信息：",prop:"operationInfo"}},[i("span",[e._v(e._s(e.eLavaNum+"/"+e.maxLength))]),e._v(" "),i("el-input",{attrs:{type:"textarea",maxlength:e.maxLength,rows:2,placeholder:"请输入信息内容"},model:{value:e.eForm.operationInfo,callback:function(t){e.eForm.operationInfo=t},expression:"eForm.operationInfo"}})],1),e._v(" "),i("el-form-item",{attrs:{label:"超链接：",prop:"link"}},[i("el-input",{attrs:{type:"text",placeholder:"请输入轮播图跳转的超链接"},model:{value:e.eForm.link,callback:function(t){e.eForm.link=t},expression:"eForm.link"}})],1),e._v(" "),i("el-form-item",{staticClass:"upload-box",attrs:{label:"封面图：",prop:"pic"}},[i("el-upload",{ref:"editPicFile",staticClass:"avatar-uploader",attrs:{action:e.API+"media/file_upload","show-file-list":!1,"on-error":e.onError,"on-success":e.onSuccess}},[e.picUrlList?i("img",{staticClass:"avatar",attrs:{src:e.picUrlList}}):i("i",{staticClass:"el-icon-plus avatar-uploader-icon"})]),e._v(" "),i("p",{staticClass:"tips",staticStyle:{"font-size":"12px",color:"#9a9a9a","margin-left":"80px"}},[e._v("*轮播图上传规格：750*350")])],1),e._v(" "),i("div",{staticClass:"info-radio"},[i("el-form-item",[i("el-radio-group",{model:{value:e.eForm.isDisplay,callback:function(t){e.eForm.isDisplay=t},expression:"eForm.isDisplay"}},[i("el-radio",{staticClass:"radio",attrs:{label:1}},[e._v("上架")]),e._v(" "),i("el-radio",{staticClass:"radio",attrs:{label:2}},[e._v("下架")])],1)],1)],1)],1),e._v(" "),i("div",{staticClass:"dialog-footer",slot:"footer"},[i("el-button",{on:{click:function(t){e.editDialog=!1}}},[e._v("取 消")]),e._v(" "),i("el-button",{attrs:{type:"primary"},on:{click:function(t){e.editSave("editForm")}}},[e._v("确 定")])],1)],1),e._v(" "),i("el-dialog",{staticClass:"add dialog-info",attrs:{title:"新建","close-on-click-modal":!1,visible:e.addDialog},on:{"update:visible":function(t){e.addDialog=t},close:function(t){e.resetFields("addForm","addPicFile")}}},[i("el-form",{ref:"addForm",attrs:{model:e.aForm,rules:e.rules}},[i("el-form-item",{attrs:{label:"运营信息：",prop:"operationInfo"}},[i("span",[e._v(e._s(e.aLavaNum+"/"+e.maxLength))]),e._v(" "),i("el-input",{attrs:{type:"textarea",maxlength:e.maxLength,rows:2,placeholder:"请输入信息内容"},model:{value:e.aForm.operationInfo,callback:function(t){e.aForm.operationInfo=t},expression:"aForm.operationInfo"}})],1),e._v(" "),i("el-form-item",{attrs:{label:"超链接：",prop:"link"}},[i("el-input",{attrs:{type:"text",placeholder:"请输入轮播图跳转的超链接"},model:{value:e.aForm.link,callback:function(t){e.aForm.link=t},expression:"aForm.link"}})],1),e._v(" "),i("el-form-item",{staticClass:"upload-box",attrs:{label:"封面图：",prop:"pic"}},[i("el-upload",{ref:"addPicFile",staticClass:"avatar-uploader",attrs:{action:e.API+"media/file_upload","show-file-list":!1,"on-error":e.onError,"on-success":e.onSuccess}},[e.picUrlList?i("img",{staticClass:"avatar",attrs:{src:e.picUrlList}}):i("i",{staticClass:"el-icon-plus avatar-uploader-icon"})]),e._v(" "),i("p",{staticClass:"tips",staticStyle:{"font-size":"12px",color:"#9a9a9a","margin-left":"80px"}},[e._v("*轮播图上传规格：750*350")])],1),e._v(" "),i("div",{staticClass:"info-radio"},[i("el-form-item",[i("el-radio-group",{model:{value:e.aForm.isDisplay,callback:function(t){e.aForm.isDisplay=t},expression:"aForm.isDisplay"}},[i("el-radio",{staticClass:"radio",attrs:{label:1}},[e._v("上架")]),e._v(" "),i("el-radio",{staticClass:"radio",attrs:{label:2}},[e._v("下架")])],1)],1)],1)],1),e._v(" "),i("div",{staticClass:"dialog-footer",slot:"footer"},[i("el-button",{on:{click:function(t){e.addDialog=!1}}},[e._v("取 消")]),e._v(" "),i("el-button",{attrs:{type:"primary"},on:{click:function(t){e.addSave("addForm")}}},[e._v("确 定")])],1)],1),e._v(" "),i("el-dialog",{staticClass:"view-pic",attrs:{visible:e.viewPicDialog},on:{"update:visible":function(t){e.viewPicDialog=t},close:e.closeViewPic}},[i("img",{attrs:{width:"100%",src:e.picUrl}})])],1)},staticRenderFns:[]}}});
//# sourceMappingURL=11.bc4357e72b67b10dea91.js.map