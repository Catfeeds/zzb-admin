webpackJsonp([14],{134:function(e,t,s){function n(e){s(244)}var i=s(18)(s(164),s(262),n,null,null);e.exports=i.exports},164:function(e,t,s){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={data:function(){return{userName:JSON.parse(sessionStorage.getItem("zhizunbao_login")).userName}},computed:{activeIndex:function(){return this.$store.state.activeIndex},power:function(){return JSON.parse(sessionStorage.getItem("power"))}},props:["API"],methods:{quit:function(){var e=this;this.$confirm("是否退出?","提示",{type:"warning"}).then(function(){sessionStorage.removeItem("zhizunbao_login"),e.userName="",e.$message({message:"退出成功",type:"success"}),e.$router.push("/")}).catch(function(){})},toNews:function(){this.$router.push("/news")},checkListPower:function(e,t){return this.power&&this.power[e].includes(t)||!1}},mounted:function(){}}},226:function(e,t,s){t=e.exports=s(130)(!0),t.push([e.i,".news-btn{font-size:18px}.el-badge__content.is-dot{position:absolute;top:-3px;top:-99999px}","",{version:3,sources:["F:/webstorm/cichang/zhizunbao/src/views/index.vue"],names:[],mappings:"AACA,UACE,cAAgB,CACjB,AACD,0BACE,kBAAmB,AACnB,SAAU,AACV,YAAc,CACf",file:"index.vue",sourcesContent:["\n.news-btn {\n  font-size: 18px;\n}\n.el-badge__content.is-dot {\n  position: absolute;\n  top: -3px;\n  top: -99999px;\n}\n"],sourceRoot:""}])},244:function(e,t,s){var n=s(226);"string"==typeof n&&(n=[[e.i,n,""]]),n.locals&&(e.exports=n.locals);s(131)("3a16632c",n,!0)},262:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",[s("el-row",{staticClass:"top-bar",attrs:{type:"flex",justify:"space-between"}},[s("el-col",{attrs:{span:4,lg:3,xs:5}},[s("div",{staticClass:"logo grid-content"},[e._v("至尊宝")])]),e._v(" "),s("el-col",{staticClass:"user-info",attrs:{span:8}},[s("el-button",{attrs:{type:"text",size:"mini"},on:{click:function(t){e.toNews()}}},[e._v("\n                消息\n                "),s("el-badge",{staticClass:"news-btn icon icon-remind",attrs:{"is-dot":""}})],1),e._v(" "),s("span",{staticClass:"user-name grid-content"},[e._v(e._s(e.userName))]),e._v(" "),s("el-button",{attrs:{type:"text"},on:{click:function(t){e.quit()}}},[e._v("退出")])],1)],1),e._v(" "),s("el-row",[s("el-col",{staticClass:"side-bar",attrs:{tag:"aside",span:3,lg:3}},[s("el-menu",{staticClass:"el-menu-vertical-demo",attrs:{router:"","unique-opened":"","default-active":e.activeIndex}},[e.power&&e.power.checkList1.length?s("el-submenu",{attrs:{index:"index"}},[s("template",{slot:"title"},[e._v("运营信息")]),e._v(" "),e.checkListPower("checkList1","首页轮播")?s("el-menu-item",{attrs:{index:"index",active:!0}},[e._v("首页轮播")]):e._e(),e._v(" "),e.checkListPower("checkList1","系统推送")?s("el-menu-item",{attrs:{index:"system-push"}},[e._v("系统推送")]):e._e(),e._v(" "),e.checkListPower("checkList1","运营文章")?s("el-menu-item",{attrs:{index:"operating-articles"}},[e._v("运营文章")]):e._e(),e._v(" "),e.checkListPower("checkList1","车型库管理")?s("el-menu-item",{attrs:{index:"vehicle-manage"}},[e._v("车型库管理")]):e._e()],2):e._e(),e._v(" "),e.power&&e.power.checkList2.length?s("el-submenu",{attrs:{index:"2"}},[s("template",{slot:"title"},[e._v("用户")]),e._v(" "),s("el-submenu",{attrs:{index:"user-info"}},[s("template",{slot:"title"},[e._v("用户信息")]),e._v(" "),e.checkListPower("checkList2","用户信息")?s("el-menu-item",{attrs:{index:"user-info"}},[e._v("用户信息")]):e._e(),e._v(" "),e.checkListPower("checkList2","车东信息")?s("el-menu-item",{attrs:{index:"car-owner-info"}},[e._v("车东信息")]):e._e()],2),e._v(" "),e.checkListPower("checkList2","车辆信息")?s("el-menu-item",{attrs:{index:"vehicle-info"}},[e._v("车辆信息")]):e._e(),e._v(" "),e.checkListPower("checkList2","罚单信息")?s("el-menu-item",{attrs:{index:"ticket-info"}},[e._v("罚单信息")]):e._e(),e._v(" "),e.checkListPower("checkList2","用户信用")?s("el-menu-item",{attrs:{index:"user-credit"}},[e._v("用户信用")]):e._e()],2):e._e(),e._v(" "),e.power&&e.power.checkList3.length?s("el-menu-item",{attrs:{index:"order"}},[e._v("订单")]):e._e(),e._v(" "),e.power&&e.power.checkList4.length?s("el-submenu",{attrs:{index:"4"}},[s("template",{slot:"title"},[e._v("财务")]),e._v(" "),e.checkListPower("checkList4","平台账户")?s("el-menu-item",{attrs:{index:"platform-account"}},[e._v("平台账户")]):e._e(),e._v(" "),e.checkListPower("checkList4","提现管理")?s("el-menu-item",{attrs:{index:"withdraw"}},[e._v("提现管理")]):e._e()],2):e._e(),e._v(" "),e.power&&e.power.checkList5.length?s("el-submenu",{attrs:{index:"5"}},[s("template",{slot:"title"},[e._v("系统设置")]),e._v(" "),e.checkListPower("checkList5","账户管理")?s("el-menu-item",{attrs:{index:"account-manage"}},[e._v("账户管理")]):e._e(),e._v(" "),e.checkListPower("checkList5","我的账户")?s("el-menu-item",{attrs:{index:"my-account"}},[e._v("我的账户")]):e._e()],2):e._e()],1)],1),e._v(" "),s("el-col",{attrs:{span:19,lg:20,id:"main"}},[s("router-view",{attrs:{API:e.API}})],1)],1)],1)},staticRenderFns:[]}}});
//# sourceMappingURL=14.f2c62abfb9264f67c5a9.js.map