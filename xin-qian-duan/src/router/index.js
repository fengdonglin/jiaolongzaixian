import Vue from 'vue'
import Router from 'vue-router'

import error from '@/view/error/error'
import shouye from '@/view/shouye/shouye'
import login  from '@/view/login/login'
import menuManger from '@/view/shouye/menuManger'
import userManger from '@/view/shouye/user/userManager'
import datamenu from '@/view/shouye/datamenu'
import datamain from '@/view/shouye/datamain'
import roleManager from '@/view/shouye/role/roleManager'
import tikuguanli from '@/view/tiku/tikuguanli'

Vue.use(Router)

export default new Router({
  mode:'history',
  routes: [
    {
      path: '/view/shouye/shouye',
      name: 'shouye',
      component: shouye,
      meta:{
        require:true//该路径的访问需要登录
      },
      children:[
        {
          path:"/datamain",
          component:datamain,
          children:[//页面主显示区域的页面路由（采用嵌套路由的方式实现）
            {path:'/error',component:error,meta:{require:false}},
            {path:'/datamain/yewu/menuManger',component:menuManger,meta:{require:true}},
            {path:'/datamain/yewu/userManger',component:userManger,meta:{require:true}},
            {path:'/datamain/yewu/roleManager',component:roleManager,meta:{require:true}},
            {path:'/datamain/yewu/tiku',component:tikuguanli,meta:{require:true}}
          ]
        }
      ]
    },{
      path: '/',
      name: 'login',
      component: login,
      meta:{
        require:false//该路径的访问不需要登录
      }
    }/*,{
      path: '/error',
      name: 'error',
      component: error,
      meta:{
        require:false//该路径的访问不需要登录
      }
    }*/,
    {
      path:"/datamenu",
      component:datamenu,
      meta:{requireAuth:true}
    }
  ]
})
