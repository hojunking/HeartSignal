import {
    createRouter,
    createWebHistory
  } from 'vue-router'
  import CoupleLogList from '../views/inyeong/CoupleLogList.vue'
  
  
  const routes = [{
      path: '/CoupleLogList',
      name: 'CoupleLogList',
      component: CoupleLogList
    },
  
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
  })
  
  export default router
  
