webpackJsonp([18],{118:function(t,n){},119:function(t,n){},120:function(t,n){},121:function(t,n){},122:function(t,n){},124:function(t,n,e){function o(t){e(122)}var i=e(18)(e(86),e(126),o,null,null);t.exports=i.exports},125:function(t,n){t.exports={render:function(){var t=this,n=t.$createElement,e=t._self._c||n;return e("div",{attrs:{id:"app"}},[e("router-view",{attrs:{API:t.API}})],1)},staticRenderFns:[]}},126:function(t,n){t.exports={render:function(){var t=this,n=t.$createElement,e=t._self._c||n;return e("div",{staticClass:"quill-editor"},[t._t("toolbar"),t._v(" "),e("div",{ref:"editor"})],2)},staticRenderFns:[]}},129:function(t,n){},36:function(t,n,e){"use strict";var o=e(2),i=e(127),r=e(34),a=function(t){return e.e(3).then(function(){var n=[e(135)];t.apply(null,n)}.bind(this)).catch(e.oe)},c=function(t){return e.e(14).then(function(){var n=[e(134)];t.apply(null,n)}.bind(this)).catch(e.oe)},u=function(t){return e.e(11).then(function(){var n=[e(138)];t.apply(null,n)}.bind(this)).catch(e.oe)},l=function(t){return e.e(2).then(function(){var n=[e(140)];t.apply(null,n)}.bind(this)).catch(e.oe)},s=function(t){return e.e(10).then(function(){var n=[e(139)];t.apply(null,n)}.bind(this)).catch(e.oe)},p=function(t){return e.e(12).then(function(){var n=[e(137)];t.apply(null,n)}.bind(this)).catch(e.oe)},d=function(t){return e.e(1).then(function(){var n=[e(141)];t.apply(null,n)}.bind(this)).catch(e.oe)},f=function(t){return e.e(5).then(function(){var n=[e(148)];t.apply(null,n)}.bind(this)).catch(e.oe)},h=function(t){return e.e(4).then(function(){var n=[e(149)];t.apply(null,n)}.bind(this)).catch(e.oe)},m=function(t){return e.e(7).then(function(){var n=[e(146)];t.apply(null,n)}.bind(this)).catch(e.oe)},v=function(t){return e.e(6).then(function(){var n=[e(147)];t.apply(null,n)}.bind(this)).catch(e.oe)},b=function(t){return e.e(8).then(function(){var n=[e(145)];t.apply(null,n)}.bind(this)).catch(e.oe)},y=function(t){return e.e(9).then(function(){var n=[e(142)];t.apply(null,n)}.bind(this)).catch(e.oe)},g=function(t){return e.e(16).then(function(){var n=[e(132)];t.apply(null,n)}.bind(this)).catch(e.oe)},w=function(t){return e.e(15).then(function(){var n=[e(133)];t.apply(null,n)}.bind(this)).catch(e.oe)},q=function(t){return e.e(0).then(function(){return t(e(143))}.bind(null,e)).catch(e.oe)},x=function(t){return e.e(0).then(function(){return t(e(144))}.bind(null,e)).catch(e.oe)},_=function(t){return e.e(13).then(function(){var n=[e(136)];t.apply(null,n)}.bind(this)).catch(e.oe)};o.default.use(i.a),o.default.use(r.a),n.a=new i.a({routes:[{path:"/",name:"login",alias:"/login",component:a},{path:"/index",component:c,children:[{path:"/",name:"bennerList",component:u},{path:"/system-push",name:"systemPush",component:l},{path:"/operating-articles",name:"operatingArticles",component:s},{path:"/add-article",name:"addArticle",component:p},{path:"/vehicle-manage",name:"vehicleManage",component:d},{path:"/user-info",name:"userInfo",component:f},{path:"/vehicle-info",name:"vehicleInfo",component:h},{path:"/ticket-info",name:"ticketInfo",component:m},{path:"/user-credit",name:"userCredit",component:v},{path:"/car-owner-info",name:"carOwnerInfo",component:b},{path:"/order",name:"order",component:y},{path:"/platform-account",name:"platformAccount",component:g},{path:"/withdraw",name:"withdraw",component:w},{path:"/account-manage",name:"accountManage",component:q},{path:"/my-account",name:"myAccount",component:x},{path:"/news",name:"news",component:_}]}]})},37:function(t,n,e){"use strict";var o=e(2),i=e(34);o.default.use(i.a),n.a=new i.a.Store({state:{activeIndex:"",articleUuid:"",userAccountUuid:"",ticketName:"",orderNumber:""},mutations:{setActiveIndex:function(t,n){t.activeIndex=n},setArticleUuid:function(t,n){t.articleUuid=n},setUserAccountUuid:function(t,n){t.userAccountUuid=n},setTicketName:function(t,n){t.ticketName=n},setOrderNumber:function(t,n){t.orderNumber=n}}})},38:function(t,n,e){"use strict";var o=e(44),i=e.n(o);n.a={getNowTime:function(t){if(!t)return"--";var n=new Date(t),t={year:n.getFullYear(),month:n.getMonth()+1,day:n.getDate()};return t.month<10&&(t.month="0"+t.month),t.day<10&&(t.day="0"+t.day),t.year+"-"+t.month+"-"+t.day},deleteItem:function(t,n,e){n.$confirm("是否确认删除?","提示",{type:"warning"}).then(function(){n.$http.post(t,e).then(function(t){var t=t.body.body;0==t.result?n.$message({type:"success",message:"删除成功!"}):n.$message(t.description),n.getList&&n.getList()},function(t){console.error("请求失败 Σ(っ°Д°;)っ"),n.getList&&n.getList()})}).catch(function(){})},clone:function(t,n){return i()(t,n)},_post:function(t,n,e){for(var o=arguments.length,i=Array(o>3?o-3:0),r=3;r<o;r++)i[r-3]=arguments[r];e.$http.post(e.API+t,n).then(function(t){var t=t.body.body;0==t.result?(console.log("%c\n ╭----------post------------- 数据 -------------------------\n","background: #C9F5C9",t,"\n ╰--------------------------- 结束 -------------------------"),i[0]&&i[0](t)):(e.$message({message:t.description,type:"error"}),i[1]&&i[1](t))},function(t){e.$message("查询异常，请重试  Σ(っ°Д°;)っ"),i[2]&&i[2](t),console.error("%c[[[server-error]]]","background: red; color: #fff; padding: 3px 0",t)})},articleFormat:function(t){return t.replace(/[ ]/g,"&nbsp;")},formatterMoney:function(t){return Math.floor(100*t)/100},copyText:function(t){if(t){var n=document.createElement("input");return n.setAttribute("value",t),document.body.appendChild(n),n.select(),document.execCommand("copy"),document.body.removeChild(n),!0}return!1},checkPower:function(t){if("/"===t||"/login"===t||"/news"===t)return!0;var n=JSON.parse(sessionStorage.getItem("power")),e=this.parsePath(t),o=[];for(var i in n)n[i].map(function(t){o.push(t)});return!!o.includes(e)},parsePath:function(t){switch(t){case"/index":return"首页轮播";case"/system-push":return"系统推送";case"/operating-articles":return"运营文章";case"/vehicle-manage":return"车型库管理";case"/user-info":return"用户信息";case"/car-owner-info":return"车东信息";case"/vehicle-info":return"车辆信息";case"/ticket-info":return"罚单信息";case"/user-credit":return"用户信用";case"/order":return"订单";case"/platform-account":return"平台账户";case"/withdraw":return"提现管理";case"/account-manage":return"账户管理";case"/my-account":return"我的账户";default:return t}}}},40:function(t,n){},41:function(t,n,e){function o(t){e(121)}var i=e(18)(e(87),e(125),o,null,null);t.exports=i.exports},86:function(t,n,e){"use strict";Object.defineProperty(n,"__esModule",{value:!0}),e(120),e(118),e(119),window.Quill||(window.Quill=e(23)),n.default={name:"quill-editor",data:function(){return{_content:"",defaultModules:{toolbar:[["bold","italic","underline","strike"],["blockquote","code-block"],[{header:1},{header:2}],[{list:"ordered"},{list:"bullet"}],[{script:"sub"},{script:"super"}],[{indent:"-1"},{indent:"+1"}],[{direction:"rtl"}],[{size:["small",!1,"large","huge"]}],[{header:[1,2,3,4,5,6,!1]}],[{color:[]},{background:[]}],[{font:[]}],[{align:[]}],["clean"],["link","image","video"]]}}},props:{content:String,value:String,disabled:Boolean,options:{type:Object,required:!1,default:function(){return{}}}},mounted:function(){this.initialize()},beforeDestroy:function(){this.quill=null},methods:{initialize:function(){if(this.$el){var t=this;t.options.theme=t.options.theme||"snow",t.options.boundary=t.options.boundary||document.body,t.options.modules=t.options.modules||t.defaultModules,t.options.modules.toolbar=void 0!==t.options.modules.toolbar?t.options.modules.toolbar:t.defaultModules.toolbar,t.options.placeholder=t.options.placeholder||"Insert text here ...",t.options.readOnly=void 0!==t.options.readOnly&&t.options.readOnly,t.quill=new Quill(t.$refs.editor,t.options),(t.value||t.content)&&t.quill.pasteHTML(t.value||t.content),t.quill.on("selection-change",function(n){n?t.$emit("focus",t.quill):t.$emit("blur",t.quill)}),t.quill.on("text-change",function(n,e,o){var i=t.$refs.editor.children[0].innerHTML,r=t.quill.getText();"<p><br></p>"===i&&(i=""),t._content=i,t.$emit("input",t._content),t.$emit("change",{editor:t.quill,html:i,text:r})}),t.$emit("ready",t.quill)}}},watch:{content:function(t,n){this.quill&&(t&&t!==this._content?(this._content=t,this.quill.pasteHTML(t)):t||this.quill.setText(""))},value:function(t,n){this.quill&&(t&&t!==this._content?(this._content=t,this.quill.pasteHTML(t)):t||this.quill.setText(""))},disabled:function(t,n){this.quill&&this.quill.enable(!t)}}}},87:function(t,n,e){"use strict";Object.defineProperty(n,"__esModule",{value:!0}),n.default={name:"app",data:function(){return{API:"http://admin.zzbcar.com/zzb-admin-api/"}}}},88:function(t,n,e){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var o=e(2),i=e(41),r=e.n(i),a=e(36),c=e(43),u=e(38),l=e(37),s=e(39),p=e.n(s),d=e(40),f=(e.n(d),e(42)),h=e.n(f);a.a.beforeEach(function(t,n,e){if(!u.a.checkPower(t.path))return void e(!1);e(),l.a.commit("setActiveIndex",t.path.slice(1))}),o.default.use(p.a),o.default.use(h.a),o.default.use(c.a),o.default.prototype.$common=u.a,o.default.config.productionTip=!1,new o.default({el:"#app",router:a.a,store:l.a,template:"<App/>",components:{App:r.a}})}},[88]);
//# sourceMappingURL=app.2b543809c10a7c49c8cd.js.map