webpackJsonp([11],{138:function(e,t,a){function i(e){a(230)}var o=a(18)(a(168),a(248),i,null,null);e.exports=o.exports},168:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=a(44),o=a.n(i);t.default={data:function(){var e=this;return{maxLength:100,word:"",searchWord:"",orderBy:2,listLoading:!1,exportLoading:!1,tableData:[],isDisplay:"",options:[{value:"",label:"全部"},{value:1,label:"上架"},{value:2,label:"下架"}],editDialog:!1,addDialog:!1,eForm:{operationInfo:""},aForm:{operationInfo:""},currentPage:1,pageSize:20,total:0,viewPicDialog:!1,dialogVisible:!1,picUrl:null,picUrlList:null,rules:{pic:[{validator:function(t,a,i){e.picUrlList?i():i(new Error("请上传图片后重试"))},trigger:"change"}],operationInfo:[{required:!0,message:"内容不能为空",trigger:"blur"}],link:[{required:!0,message:"链接不能为空",trigger:"blur"}]}}},computed:{eLavaNum:function(){return this.eForm.operationInfo&&this.eForm.operationInfo.length||0},aLavaNum:function(){return this.aForm.operationInfo&&this.aForm.operationInfo.length||0}},props:["API"],methods:{search:function(e){this.word=this.searchWord,this.currentPage=1,this.getList(this.word)},sortTable:function(){this.orderBy=1==this.orderBy?2:1,this.getList(this.word)},formatTime:function(e,t){return this.$common.getNowTime(e.createAt&&e.createAt.time)},formatStatus:function(e,t){return 1==e.isDisplay?"上架":2==e.isDisplay?"下架":"未知"},editItem:function(e,t){var a=this,i={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{id:t.id}};this.$common._post("homePage/detail",i,this,function(e){a.eForm=o()({},e.banner),a.editDialog=!0,a.picUrlList=a.eForm.operationPicture})},editSave:function(e){var t=this;this.$refs[e].validate(function(e){e&&t.$confirm("是否确认修改?","提示",{}).then(function(){t.eForm.picUrl=t.picUrlList;var e={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{operation_info:t.eForm.operationInfo,is_display:t.eForm.isDisplay,id:t.eForm.id,operation_picture:t.eForm.picUrl,link:t.eForm.link}};t.$common._post("homePage/edit",e,t,function(e){t.editDialog=!1,t.$message({type:"success",message:"编辑成功!"}),t.getList()})}).catch(function(){})})},addItem:function(){this.aForm={isDisplay:1,operationInfo:"",picUrl:"",link:""},this.picUrlList="",this.addDialog=!0},addSave:function(e){var t=this;this.$refs[e].validate(function(e){e&&t.$confirm("是否确认添加?","提示",{}).then(function(){t.aForm.picUrl=t.picUrlList;var e={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{operation_info:t.aForm.operationInfo,is_display:t.aForm.isDisplay,operation_picture:t.aForm.picUrl,link:t.aForm.link}};t.$common._post("homePage/add",e,t,function(e){t.addDialog=!1,t.$message({type:"success",message:"添加成功!"}),t.getList()})}).catch(function(){})})},changeDisplay:function(e,t,a){var i=this;this.$confirm("是否确定修改?","提示",{}).then(function(){var e={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{is_display:a,id:t.id}};i.$common._post("homePage/editceil",e,i,function(e){i.$message({type:"success",message:"修改成功!"}),i.getList()})}).catch(function(){})},changePosition:function(e,t){},deleteItem:function(e,t){var a={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{id:t.id}};this.$common.deleteItem(this.API+"homePage/delete",this,a)},resetFields:function(e,t){this.$refs[e].resetFields(),t&&this.$refs[t].clearFiles(),this.eForm={},this.picUrlList=null},onSuccess:function(e,t,a){this.picUrlList=e.body.attachment_url,this.$message("上传成功!")},showPic:function(e,t){this.viewPicDialog=!0,this.picUrl=t.operationPicture},closeViewPic:function(){this.picUrl=""},exportExc:function(){var e=this;this.exportLoading=!0;var t={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{}};this.$http.post(this.API+"exportExcelHomePageBanner",t).then(function(t){e.exportLoading=!1;var t=t.body;"0"==t.body.result?location.href=t.body.fileUrl:e.$message(t.body.description)})},handleCurrentChange:function(e){this.currentPage=e,this.getList(this.word)},getList:function(){var e=this,t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"";this.listLoading=!0;var a={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{pageIndex:this.currentPage,pageSize:this.pageSize,operation_info:t,is_display:this.isDisplay,orderBy:this.orderBy}};this.$common._post("homePage/list",a,this,function(t){e.total=t.total,e.tableData=t.homePageBannerlist,e.listLoading=!1},function(t){e.listLoading=!1,e.tableData=[]})}},mounted:function(){if(!sessionStorage.getItem("zhizunbao_login"))return this.$message("请登录账号"),void this.$router.push("/");this.getList()}}},212:function(e,t,a){t=e.exports=a(130)(!0),t.push([e.i,".avatar-uploader .el-upload{border:1px dashed #d9d9d9;border-radius:6px;cursor:pointer;position:relative;overflow:hidden}.avatar-uploader .el-upload:hover{border-color:#20a0ff}.avatar-uploader-icon{font-size:28px;color:#8c939d;width:178px;height:178px;line-height:178px;text-align:center}.avatar{width:178px;height:178px;display:block}","",{version:3,sources:["F:/webstorm/cichang/zhizunbao/src/views/operate-info/banner-list.vue"],names:[],mappings:"AACA,4BACE,0BAA2B,AAC3B,kBAAmB,AACnB,eAAgB,AAChB,kBAAmB,AACnB,eAAiB,CAClB,AACD,kCACE,oBAAsB,CACvB,AACD,sBACE,eAAgB,AAChB,cAAe,AACf,YAAa,AACb,aAAc,AACd,kBAAmB,AACnB,iBAAmB,CACpB,AACD,QACE,YAAa,AACb,aAAc,AACd,aAAe,CAChB",file:"banner-list.vue",sourcesContent:["\n.avatar-uploader .el-upload {\n  border: 1px dashed #d9d9d9;\n  border-radius: 6px;\n  cursor: pointer;\n  position: relative;\n  overflow: hidden;\n}\n.avatar-uploader .el-upload:hover {\n  border-color: #20a0ff;\n}\n.avatar-uploader-icon {\n  font-size: 28px;\n  color: #8c939d;\n  width: 178px;\n  height: 178px;\n  line-height: 178px;\n  text-align: center;\n}\n.avatar {\n  width: 178px;\n  height: 178px;\n  display: block;\n}\n"],sourceRoot:""}])},230:function(e,t,a){var i=a(212);"string"==typeof i&&(i=[[e.i,i,""]]),i.locals&&(e.exports=i.locals);a(131)("6a772abe",i,!0)},248:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-row",[a("el-col",{staticClass:"search-bar"},[a("el-input",{staticClass:"w_200",attrs:{placeholder:"请输入运营信息",icon:"search","on-icon-click":e.search},model:{value:e.searchWord,callback:function(t){e.searchWord=t},expression:"searchWord"}}),e._v(" "),a("div",{staticClass:"m_l_20 w_100"},[a("el-select",{on:{change:e.search},model:{value:e.isDisplay,callback:function(t){e.isDisplay=t},expression:"isDisplay"}},e._l(e.options,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1),e._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{on:{click:e.sortTable}},[e._v(e._s(1==e.orderBy?"正":"倒")+"序排列")])],1),e._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{attrs:{icon:"plus"},on:{click:e.addItem}},[e._v("新建内容")])],1),e._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{attrs:{loading:e.exportLoading,icon:"document"},on:{click:e.exportExc}},[e._v("导出Excel")])],1)],1)],1),e._v(" "),a("p",{staticClass:"tips",staticStyle:{"font-size":"12px","margin-bottom":"10px",color:"#9a9a9a"}},[e._v("*轮播图上架数量上限为 4 张")]),e._v(" "),a("el-row",[a("el-col",[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],staticClass:"table",attrs:{data:e.tableData,border:"",width:"100%"}},[a("el-table-column",{attrs:{align:"center",label:"序号",width:"80"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                        "+e._s(t.$index+1+e.pageSize*(e.currentPage-1))+"\n                    ")]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"图片",width:"80"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"text"},on:{click:function(a){e.showPic(t.$index,t.row)}}},[e._v("预览")])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",prop:"operationInfo",label:"运营信息"}}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"状态",formatter:e.formatStatus}}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"上/下架时间",width:"150",formatter:e.formatTime}}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"操作",width:"250"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"info",size:"small",icon:"edit"},on:{click:function(a){e.editItem(t.$index,t.row)}}},[e._v("编辑")]),e._v(" "),a("el-button",{directives:[{name:"show",rawName:"v-show",value:2==t.row.isDisplay,expression:"scope.row.isDisplay == 2"}],attrs:{type:"warning",size:"small"},on:{click:function(a){e.changeDisplay(t.$index,t.row,1)}}},[e._v("上架")]),e._v(" "),a("el-button",{directives:[{name:"show",rawName:"v-show",value:1==t.row.isDisplay,expression:"scope.row.isDisplay == 1"}],attrs:{type:"warning",size:"small"},on:{click:function(a){e.changeDisplay(t.$index,t.row,2)}}},[e._v("下架")]),e._v(" "),a("el-button",{attrs:{size:"small",type:"danger",icon:"delete2"},on:{click:function(a){e.deleteItem(t.$index,t.row)}}},[e._v("删除")])]}}])})],1)],1)],1),e._v(" "),a("el-row",[a("el-col",[a("div",{staticClass:"block pagination"},[a("el-pagination",{attrs:{"current-page":e.currentPage,"page-size":e.pageSize,layout:"prev, pager, next, jumper","page-count":e.total},on:{"update:currentPage":function(t){e.currentPage=t},"current-change":e.handleCurrentChange}})],1)])],1),e._v(" "),a("el-dialog",{staticClass:"edit dialog-info",attrs:{"close-on-click-modal":!1,title:"编辑",visible:e.editDialog},on:{"update:visible":function(t){e.editDialog=t},close:function(t){e.resetFields("editForm","editPicFile")}}},[a("el-form",{ref:"editForm",attrs:{model:e.eForm,rules:e.rules}},[a("el-form-item",{attrs:{label:"运营信息：",prop:"operationInfo"}},[a("span",[e._v(e._s(e.eLavaNum+"/"+e.maxLength))]),e._v(" "),a("el-input",{attrs:{type:"textarea",maxlength:e.maxLength,rows:2,placeholder:"请输入信息内容"},model:{value:e.eForm.operationInfo,callback:function(t){e.eForm.operationInfo=t},expression:"eForm.operationInfo"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"超链接：",prop:"link"}},[a("el-input",{attrs:{type:"text",placeholder:"请输入轮播图跳转的超链接"},model:{value:e.eForm.link,callback:function(t){e.eForm.link=t},expression:"eForm.link"}})],1),e._v(" "),a("el-form-item",{staticClass:"upload-box",attrs:{label:"封面图：",prop:"pic"}},[a("el-upload",{ref:"editPicFile",staticClass:"avatar-uploader",attrs:{action:e.API+"media/file_upload","show-file-list":!1,"on-success":e.onSuccess}},[e.picUrlList?a("img",{staticClass:"avatar",attrs:{src:e.picUrlList}}):a("i",{staticClass:"el-icon-plus avatar-uploader-icon"})]),e._v(" "),a("p",{staticClass:"tips",staticStyle:{"font-size":"12px",color:"#9a9a9a","margin-left":"80px"}},[e._v("*轮播图上传规格：750*350")])],1),e._v(" "),a("div",{staticClass:"info-radio"},[a("el-form-item",[a("el-radio-group",{model:{value:e.eForm.isDisplay,callback:function(t){e.eForm.isDisplay=t},expression:"eForm.isDisplay"}},[a("el-radio",{staticClass:"radio",attrs:{label:1}},[e._v("上架")]),e._v(" "),a("el-radio",{staticClass:"radio",attrs:{label:2}},[e._v("下架")])],1)],1)],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",slot:"footer"},[a("el-button",{on:{click:function(t){e.editDialog=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.editSave("editForm")}}},[e._v("确 定")])],1)],1),e._v(" "),a("el-dialog",{staticClass:"add dialog-info",attrs:{title:"新建","close-on-click-modal":!1,visible:e.addDialog},on:{"update:visible":function(t){e.addDialog=t},close:function(t){e.resetFields("addForm","addPicFile")}}},[a("el-form",{ref:"addForm",attrs:{model:e.aForm,rules:e.rules}},[a("el-form-item",{attrs:{label:"运营信息：",prop:"operationInfo"}},[a("span",[e._v(e._s(e.aLavaNum+"/"+e.maxLength))]),e._v(" "),a("el-input",{attrs:{type:"textarea",maxlength:e.maxLength,rows:2,placeholder:"请输入信息内容"},model:{value:e.aForm.operationInfo,callback:function(t){e.aForm.operationInfo=t},expression:"aForm.operationInfo"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"超链接：",prop:"link"}},[a("el-input",{attrs:{type:"text",placeholder:"请输入轮播图跳转的超链接"},model:{value:e.aForm.link,callback:function(t){e.aForm.link=t},expression:"aForm.link"}})],1),e._v(" "),a("el-form-item",{staticClass:"upload-box",attrs:{label:"封面图：",prop:"pic"}},[a("el-upload",{ref:"addPicFile",staticClass:"avatar-uploader",attrs:{action:e.API+"media/file_upload","show-file-list":!1,"on-success":e.onSuccess}},[e.picUrlList?a("img",{staticClass:"avatar",attrs:{src:e.picUrlList}}):a("i",{staticClass:"el-icon-plus avatar-uploader-icon"})]),e._v(" "),a("p",{staticClass:"tips",staticStyle:{"font-size":"12px",color:"#9a9a9a","margin-left":"80px"}},[e._v("*轮播图上传规格：750*350")])],1),e._v(" "),a("div",{staticClass:"info-radio"},[a("el-form-item",[a("el-radio-group",{model:{value:e.aForm.isDisplay,callback:function(t){e.aForm.isDisplay=t},expression:"aForm.isDisplay"}},[a("el-radio",{staticClass:"radio",attrs:{label:1}},[e._v("上架")]),e._v(" "),a("el-radio",{staticClass:"radio",attrs:{label:2}},[e._v("下架")])],1)],1)],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",slot:"footer"},[a("el-button",{on:{click:function(t){e.addDialog=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.addSave("addForm")}}},[e._v("确 定")])],1)],1),e._v(" "),a("el-dialog",{staticClass:"view-pic",attrs:{visible:e.viewPicDialog},on:{"update:visible":function(t){e.viewPicDialog=t},close:e.closeViewPic}},[a("img",{attrs:{width:"100%",src:e.picUrl}})])],1)},staticRenderFns:[]}}});
//# sourceMappingURL=11.23778e05d71e0e3dcb50.js.map