webpackJsonp([12],{137:function(e,t,a){function r(e){a(239)}var i=a(18)(a(167),a(257),r,null,null);e.exports=i.exports},167:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=a(23);a.n(r);t.default={data:function(){var e=this,t=function(t,a,r){""==e.form.articleContent?r(new Error("内容不能为空")):r()},a=function(t,a,r){e.form.articlePicture?r():r(new Error("请添加成功后重试"))},r=function(e,t,a){""===t?a(new Error("请选择车辆ID")):a()};return{form:{tittle:"",articleType:"",articleContent:"",articlePicture:"",carID1:"",carID2:"",activityCarID:""},searchForm1:{carName1:"",options:""},searchForm2:{carName1:"",options:""},searchForm3:{carName1:"",options:""},options:[{value:"1",label:"超值体验"},{value:"2",label:"发现"}],editorOption:{placeholder:"请输入文本..."},publishStatus:!0,rules:{tittle:[{required:!0,message:"请填写内容后发布",trigger:"blur"},{min:2,message:"大于 2 个字符",trigger:"blur"}],content:[{validator:t,trigger:"change"}],articleType:[{required:!0,message:"请选择文章类型",trigger:"change"}],pic:[{validator:a,trigger:"change"}],carID1:[{required:!0,validator:r,trigger:"change"}],carID2:[{required:!0,validator:r,trigger:"change"}],activityCarID:[{required:!0,validator:r,trigger:"change"}]},loading:!1}},props:["API"],computed:{articleUuid:function(){return this.$store.state.articleUuid}},methods:{publish:function(e){var t=this;this.$refs[e].validate(function(e){e&&t.$confirm("是否确认发布?","提示",{}).then(function(){var e={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{tittle:t.form.tittle,articleType:+t.form.articleType,articleContent:t.$common.articleFormat(t.form.articleContent),articlePicture:t.form.articlePicture,carID1:t.form.carID1,carID2:t.form.carID2,activityCarID:t.form.activityCarID}};t.$common._post("article/newArticle",e,t,function(e){t.$message({type:"success",message:e.description}),t.$router.push("/operating-articles")})}).catch(function(){})})},editSave:function(e){var t=this;this.$refs[e].validate(function(e){e&&t.$confirm("是否确认保存?","提示",{}).then(function(){t.form.articleContent=t.$common.articleFormat(t.form.articleContent);var e={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{tittle:t.form.tittle,articleType:+t.form.articleType,articleContent:t.$common.articleFormat(t.form.articleContent),articlePicture:t.form.articlePicture,articleUuid:t.articleUuid,carID1:t.form.carID1,carID2:t.form.carID2,activityCarID:t.form.activityCarID}};t.$common._post("article/editAndSave",e,t,function(e){t.$message({type:"success",message:e.description}),t.$router.push("/operating-articles")})}).catch(function(){})})},searchCarName:function(e,t){var a=this;this[e].options=[],this.form[t]="";var r={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{carName:this[e].carName1||this[e].carName2||this[e].carName3}};this.$common._post("article/toSerchCar",r,this,function(t){a[e].options=t.list.map(function(e){return{value:e.id||"",label:e.id||"",carName:e.carName||"未知车名",brand:e.brand||"未知品牌",modelYear:e.modelYear||"未知年款",carOwnerName:e.carOwnerName||"未知车主",carSeries:e.carSeries||"未知车系"}})})},resetForm:function(e){this.$refs[e].resetFields(),this.form={tittle:"",articleType:"",articleContent:"",articlePicture:"",carID1:"",carID2:"",activityCarID:""}},beforeAvatarUpload:function(e){console.log(e)},onSuccess:function(e,t,a){this.form.articlePicture=e.body.attachment_url,this.$message("上传成功!")},getDetail:function(){var e=this,t={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{articleUuid:this.articleUuid}};this.$common._post("article/detail",t,this,function(t){console.log("getgetgetgetgetgetdata",t),e.form=t,e.form.articleType+=""})}},mounted:function(){if(!sessionStorage.getItem("zhizunbao_login"))return this.$message("请登录账号"),void this.$router.push("/");this.$store.commit("setActiveIndex","operating-articles"),this.articleUuid&&(this.publishStatus=!1,this.getDetail())}}},221:function(e,t,a){t=e.exports=a(130)(!0),t.push([e.i,".avatar-uploader .el-upload{border:1px dashed #d9d9d9;border-radius:6px;cursor:pointer;position:relative;overflow:hidden}.avatar-uploader .el-upload:hover{border-color:#20a0ff}.avatar-uploader-icon{font-size:28px;color:#8c939d;width:178px;height:178px;line-height:178px;border:1px dashed #d9d9d9;text-align:center}.avatar{width:178px;height:178px;display:block}.ql-toolbar .ql-formats .ql-picker-label:before,.ql-toolbar .ql-formats .ql-picker-label svg{vertical-align:top}.ql-container .ql-editor{min-height:400px;padding-bottom:1em;max-height:25em}.add-article .searchCarNameIpt{display:inline-block}.add-article .tittle-bar.label{font-size:14px;margin-bottom:10px;display:inline-block;width:130px;text-align:right}","",{version:3,sources:["F:/webstorm/cichang/zhizunbao/src/views/operate-info/add-article.vue"],names:[],mappings:"AAGA,4BACE,0BAA2B,AAC3B,kBAAmB,AACnB,eAAgB,AAChB,kBAAmB,AACnB,eAAiB,CAClB,AACD,kCACE,oBAAsB,CACvB,AACD,sBACE,eAAgB,AAChB,cAAe,AACf,YAAa,AACb,aAAc,AACd,kBAAmB,AACnB,0BAA2B,AAC3B,iBAAmB,CACpB,AACD,QACE,YAAa,AACb,aAAc,AACd,aAAe,CAChB,AAGD,6FACE,kBAAoB,CACrB,AACD,yBACE,iBAAkB,AAClB,mBAAoB,AACpB,eAAiB,CAClB,AACD,+BACE,oBAAsB,CACvB,AACD,+BACE,eAAgB,AAChB,mBAAoB,AACpB,qBAAsB,AACtB,YAAa,AACb,gBAAkB,CACnB",file:"add-article.vue",sourcesContent:['\n@charset "UTF-8";\n/*上传框*/\n.avatar-uploader .el-upload {\n  border: 1px dashed #d9d9d9;\n  border-radius: 6px;\n  cursor: pointer;\n  position: relative;\n  overflow: hidden;\n}\n.avatar-uploader .el-upload:hover {\n  border-color: #20a0ff;\n}\n.avatar-uploader-icon {\n  font-size: 28px;\n  color: #8c939d;\n  width: 178px;\n  height: 178px;\n  line-height: 178px;\n  border: 1px dashed #d9d9d9;\n  text-align: center;\n}\n.avatar {\n  width: 178px;\n  height: 178px;\n  display: block;\n}\n\n/*编辑器*/\n.ql-toolbar .ql-formats .ql-picker-label:before, .ql-toolbar .ql-formats .ql-picker-label svg {\n  vertical-align: top;\n}\n.ql-container .ql-editor {\n  min-height: 400px;\n  padding-bottom: 1em;\n  max-height: 25em;\n}\n.add-article .searchCarNameIpt {\n  display: inline-block;\n}\n.add-article .tittle-bar.label {\n  font-size: 14px;\n  margin-bottom: 10px;\n  display: inline-block;\n  width: 130px;\n  text-align: right;\n}\n'],sourceRoot:""}])},239:function(e,t,a){var r=a(221);"string"==typeof r&&(r=[[e.i,r,""]]),r.locals&&(e.exports=r.locals);a(131)("7adab756",r,!0)},257:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-row",{staticClass:"add-article"},[a("el-col",{attrs:{span:20}},[a("el-form",{ref:"editForm",attrs:{model:e.form,"label-width":"130px",rules:e.rules}},[a("el-form-item",{attrs:{label:"标题",prop:"tittle"}},[a("el-input",{attrs:{maxlength:50,placeholder:"新建标题"},model:{value:e.form.tittle,callback:function(t){e.form.tittle=t},expression:"form.tittle"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"类型",prop:"articleType"}},[a("el-select",{attrs:{placeholder:"文章类型"},model:{value:e.form.articleType,callback:function(t){e.form.articleType=t},expression:"form.articleType"}},e._l(e.options,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"正文",prop:"content"}},[a("el-alert",{staticStyle:{"margin-bottom":"10px","line-height":"20px"},attrs:{title:"文章图片规格：封面图（750 * 300）",type:"warning","show-icon":""}}),e._v(" "),a("quill-editor",{ref:"editArea",staticClass:"edit-box",attrs:{options:e.editorOption},model:{value:e.form.articleContent,callback:function(t){e.form.articleContent=t},expression:"form.articleContent"}})],1),e._v(" "),a("el-form-item",{staticClass:"upload-box",attrs:{label:"封面图：",prop:"pic"}},[a("el-upload",{staticClass:"avatar-uploader",attrs:{action:e.API+"media/file_upload","show-file-list":!1,"before-upload":e.beforeAvatarUpload,"on-success":e.onSuccess}},[this.form.articlePicture?a("img",{staticClass:"avatar",attrs:{src:this.form.articlePicture}}):a("i",{staticClass:"el-icon-plus avatar-uploader-icon"})]),e._v(" "),a("p",{staticClass:"tips",staticStyle:{"font-size":"12px",color:"#9a9a9a"}},[e._v("*封面图上传规格：750*300")])],1),e._v(" "),a("p",{staticClass:"tittle-bar"},[e._v("推荐车辆：")]),e._v(" "),a("p",{staticClass:"tittle-bar label"},[e._v("车辆ID1：")]),e._v(" "),a("el-form-item",{staticClass:"searchCarNameIpt"},[a("el-input",{attrs:{maxlength:30,icon:"search",placeholder:"查询品牌"},on:{click:function(t){e.searchCarName("searchForm1","carID1")}},model:{value:e.searchForm1.carName1,callback:function(t){e.searchForm1.carName1=t},expression:"searchForm1.carName1"}})],1),e._v(" "),a("el-form-item",{staticClass:"m_l_20",staticStyle:{display:"inline-block"},attrs:{prop:"carID1"}},[a("el-select",{attrs:{placeholder:"选择推荐车辆ID"},model:{value:e.form.carID1,callback:function(t){e.form.carID1=t},expression:"form.carID1"}},e._l(e.searchForm1.options,function(t){return a("el-option",{key:t.value,staticStyle:{height:"50px"},attrs:{label:t.label,value:t.value}},[a("p",{staticStyle:{"font-size":"15px"}},[e._v(e._s(t.carName+" (ID:"+t.value+")"))]),e._v(" "),a("p",{staticStyle:{"font-size":"13px",color:"#ababab"}},[e._v(e._s(t.carSeries+"  "+t.modelYear+" "+t.carOwnerName))])])}))],1),e._v(" "),a("br"),e._v(" "),a("p",{staticClass:"tittle-bar label"},[e._v("车辆ID2：")]),e._v(" "),a("el-form-item",{staticClass:"searchCarNameIpt"},[a("el-input",{attrs:{maxlength:30,icon:"search",placeholder:"查询品牌"},on:{click:function(t){e.searchCarName("searchForm2","carID2")}},model:{value:e.searchForm2.carName1,callback:function(t){e.searchForm2.carName1=t},expression:"searchForm2.carName1"}})],1),e._v(" "),a("el-form-item",{staticClass:"m_l_20",staticStyle:{display:"inline-block"},attrs:{prop:"carID2"}},[a("el-select",{attrs:{placeholder:"选择推荐车辆ID"},model:{value:e.form.carID2,callback:function(t){e.form.carID2=t},expression:"form.carID2"}},e._l(e.searchForm2.options,function(t){return a("el-option",{key:t.value,staticStyle:{height:"50px"},attrs:{label:t.label,value:t.value}},[a("p",{staticStyle:{"font-size":"15px"}},[e._v(e._s(t.carName+" (ID:"+t.value+")"))]),e._v(" "),a("p",{staticStyle:{"font-size":"13px",color:"#ababab"}},[e._v(e._s(t.carSeries+"  "+t.modelYear+" "+t.carOwnerName))])])}))],1),e._v(" "),a("br"),e._v(" "),a("p",{staticClass:"tittle-bar label"},[e._v("本期活动车辆ID：")]),e._v(" "),a("el-form-item",{staticClass:"searchCarNameIpt"},[a("el-input",{attrs:{maxlength:30,icon:"search",placeholder:"查询品牌"},on:{click:function(t){e.searchCarName("searchForm3","activityCarID")}},model:{value:e.searchForm3.carName1,callback:function(t){e.searchForm3.carName1=t},expression:"searchForm3.carName1"}})],1),e._v(" "),a("el-form-item",{staticClass:"m_l_20",staticStyle:{display:"inline-block"},attrs:{prop:"activityCarID"}},[a("el-select",{attrs:{placeholder:"本期活动车辆ID"},model:{value:e.form.activityCarID,callback:function(t){e.form.activityCarID=t},expression:"form.activityCarID"}},e._l(e.searchForm3.options,function(t){return a("el-option",{key:t.value,staticStyle:{height:"50px"},attrs:{label:t.label,value:t.value}},[a("p",{staticStyle:{"font-size":"15px"}},[e._v(e._s(t.carName+" (ID:"+t.value+")"))]),e._v(" "),a("p",{staticStyle:{"font-size":"13px",color:"#ababab"}},[e._v(e._s(t.carSeries+"  "+t.modelYear+" "+t.carOwnerName))])])}))],1),e._v(" "),a("br"),e._v(" "),a("el-form-item",[a("el-button",{on:{click:function(t){e.resetForm("editForm")}}},[e._v("重置")]),e._v(" "),e.publishStatus?a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.publish("editForm")}}},[e._v("发布")]):e._e(),e._v(" "),e.publishStatus?e._e():a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.editSave("editForm")}}},[e._v("保存")])],1)],1)],1)],1)},staticRenderFns:[]}}});
//# sourceMappingURL=12.ea60a54c5d1f4b343bae.js.map