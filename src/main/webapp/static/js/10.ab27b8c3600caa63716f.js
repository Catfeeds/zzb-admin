webpackJsonp([10],{139:function(t,e,a){function i(t){a(240)}var o=a(18)(a(173),a(258),i,"data-v-5e22fa60",null);t.exports=o.exports},173:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default={data:function(){return{word:"",searchWord:"",orderBy:2,listLoading:!1,exportLoading:!1,tableData:[],currentPage:1,pageSize:20,total:0,tableTop:0}},props:["API"],computed:{tableMaxHeight:function(){return this.$store.commit("setTableMaxHeight",this.tableTop),this.$store.state.tableMaxHeight}},methods:{search:function(t){this.word=this.searchWord,this.currentPage=1,this.getList(this.word)},sortTable:function(){this.orderBy=1==this.orderBy?2:1,this.getList(this.word)},formatTime:function(t,e){return this.$common.getNowTime(t.createAt&&t.createAt.time)},formatStatus:function(t,e){return 1==t.articleType?"超值体验":2==t.articleType?"发现":"未知"},formatContent:function(t,e){return""==t.articleContent?"暂无内容":t.articleContent},editItem:function(t,e){this.$store.commit("setArticleUuid",e.articleUuid),this.$router.push("/add-article")},addItem:function(){this.$store.commit("setArticleUuid",null),this.$router.push("/add-article")},copyLink:function(t,e){this.$common.copyText(e.link)?this.$message("复制成功!"):this.$message("暂无链接，未复制")},deleteItem:function(t,e){var a={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{articleUuid:e.articleUuid}};this.$common.deleteItem(this.API+"article/delete",this,a)},exportExc:function(){var t=this;this.exportLoading=!0;var e={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{}};this.$http.post(this.API+"exportExcelArticle",e).then(function(e){t.exportLoading=!1;var e=e.body;"0"==e.body.result?location.href=e.body.fileUrl:t.$message(e.body.description)})},handleCurrentChange:function(t){this.currentPage=t,this.getList(this.word)},getList:function(){var t=this,e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"";this.listLoading=!0;var a={head:{account:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName,password:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userPassword},body:{pageIndex:this.currentPage,pageSize:this.pageSize,tittle:e,orderBy:this.orderBy}};this.$common._post("article/list",a,this,function(e){t.total=e.total,t.tableData=e.articleList,t.listLoading=!1},function(e){t.listLoading=!1,t.tableData=[]})}},mounted:function(){if(!sessionStorage.getItem("zhizunbao_login"))return this.$message("请登录账号"),void this.$router.push("/");this.$store.commit("setArticleUuid",null),this.getList(),this.tableTop=document.querySelector("#main table").getBoundingClientRect().top}}},222:function(t,e,a){e=t.exports=a(130)(!0),e.push([t.i,"","",{version:3,sources:[],names:[],mappings:"",file:"operating-articles.vue",sourceRoot:""}])},240:function(t,e,a){var i=a(222);"string"==typeof i&&(i=[[t.i,i,""]]),i.locals&&(t.exports=i.locals);a(131)("be45cf2a",i,!0)},258:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("el-row",[a("el-col",{staticClass:"search-bar"},[a("el-input",{staticClass:"w_200",attrs:{placeholder:"请输入标题",icon:"search","on-icon-click":t.search},model:{value:t.searchWord,callback:function(e){t.searchWord=e},expression:"searchWord"}}),t._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{on:{click:t.sortTable}},[t._v(t._s(1==t.orderBy?"正":"倒")+"序排列")])],1),t._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{attrs:{icon:"plus"},on:{click:t.addItem}},[t._v("新建文章")])],1),t._v(" "),a("div",{staticClass:"m_l_20"},[a("el-button",{attrs:{loading:t.exportLoading,icon:"document"},on:{click:t.exportExc}},[t._v("导出Excel")])],1)],1)],1),t._v(" "),a("el-row",[a("el-col",[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.listLoading,expression:"listLoading"}],staticClass:"table",attrs:{data:t.tableData,border:"",width:"100%","max-height":t.tableMaxHeight}},[a("el-table-column",{attrs:{align:"center",label:"序号",width:"80"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n                        "+t._s(e.$index+1+t.pageSize*(t.currentPage-1))+"\n                    ")]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"tittle",label:"标题"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"articleContent",label:"内容",formatter:t.formatContent}}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"articleType",label:"状态",formatter:t.formatStatus}}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"browseTime",label:"浏览次数",width:"100"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",prop:"forwardTime",label:"转发次数",width:"100"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"发表时间",width:"120",sortable:"",formatter:t.formatTime}}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"操作",width:"230"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{size:"small",type:"info",icon:"edit"},on:{click:function(a){t.editItem(e.$index,e.row)}}},[t._v("编辑")]),t._v(" "),a("el-button",{attrs:{size:"small",type:"success"},on:{click:function(a){t.copyLink(e.$index,e.row)}}},[t._v("复制链接")]),t._v(" "),a("el-button",{attrs:{size:"small",type:"danger",icon:"delete2",title:"删除"},on:{click:function(a){t.deleteItem(e.$index,e.row)}}})]}}])})],1)],1)],1),t._v(" "),a("el-row",[a("el-col",[a("div",{staticClass:"block pagination"},[a("el-pagination",{attrs:{"current-page":t.currentPage,"page-size":t.pageSize,layout:"prev, pager, next, jumper","page-count":t.total},on:{"update:currentPage":function(e){t.currentPage=e},"current-change":t.handleCurrentChange}})],1)])],1)],1)},staticRenderFns:[]}}});
//# sourceMappingURL=10.ab27b8c3600caa63716f.js.map