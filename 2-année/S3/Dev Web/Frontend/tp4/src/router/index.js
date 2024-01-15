import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/shop',
    name: 'shop',
    component: () => import('../views/ShopView.vue'),
    children: [
      {
        path: '/shop/home',
        name: 'shophome',
        components: {
          shopmain: () => import('../views/ShopHome.vue'),
        },
        alias: '/shop'
      },
      {
        path: '/shop/buy',
        name: 'shopbuy',
        components: {
          shopmain: () => import('../views/ShopBuy.vue')
        },
      },
      // {
      //   path: '/shop/pay/:idcmd?',
      //   name: 'shoppay',
      //   components: {
      //     shopmain: () => import('../views/ShopPay.vue')
      //   },
      //   props: {
      //     shopmain: true
      //   },
      // },
      {
        path: '/shop/login',
        name: 'shoplogin',
        components: {
          shopmain: () => import('../views/ShopLoginView.vue')
        },
      },
      // {
      //   path: '/shop/logout',
      //   name: 'shoplogout',
      //   components: {
      //     shopmain: () => import('../views/ShopLogout.vue')
      //   },
      // },
      // {
      //   path: '/shop/orders',
      //   name: 'shoporders',
      //   components: {
      //     shopmain: () => import('../views/ShopOrders.vue')
      //   },
      // }
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
