import CoupleLogList from '../views/inyeong/CoupleLogList.vue'
import CoupleLogInsert from '../views/inyeong/CoupleLogInsert.vue'
import CreateCourse from '../views/jaeho/CreateCourse.vue'
import HelloWorld from '../components/HelloWorld.vue'
import { defineComponent } from 'vue'

const NotFound = defineComponent({
    template: '<div>404 Not Found</div>'
})

const routes = [
    { path: '/', redirect: '/hello' },
    { path: '/hello', name: 'HelloWorld', component: HelloWorld, },
    {
        path: '/coupleLogList',
        name: 'CoupleLogList',
        component: CoupleLogList,
        alias:'/CoupleLogList'
    },
    {
        path: '/coupleLogInsert',
        name: 'CoupleLogInsert',
        component: CoupleLogInsert,
        alias:'/CoupleLogInsert'
    },

    {
        path: '/createCourse',
        name: 'CreateCourse',
        component: CreateCourse
    },

    {
        path: '/:catchAll(.*)+',
        name: 'NotFound',
        component: NotFound,
    }
]

export default routes