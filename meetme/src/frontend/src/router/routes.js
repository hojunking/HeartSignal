import CreateCourse from '../views/jaeho/CreateCourse.vue'
import Chat from '../views/jaeho/Chat.vue'
import CoupleCreateCourse from '../views/jaeho/CoupleCreateCourse.vue'
import HelloWorld from '../components/HelloWorld.vue'
import Test from '../views/jaeho/Test.vue'
import { defineComponent } from 'vue'

const NotFound = defineComponent({
    template: '<div>404 Not Found</div>'
})

const routes = [
    { path: '/hello', name: 'HelloWorld', component: HelloWorld, },
    {
        path: '/createCourse',
        name: 'CreateCourse',
        component: CreateCourse
    },

    {
        path: '/chat',
        name: 'Chat',
        component: Chat
    },

    {
        path: '/coupleCreateCourse',
        name: 'CoupleCreateCourse',
        component: CoupleCreateCourse
    },

    {
        path: '/test',
        name: 'Test',
        component: Test
    },

    {
        path: '/:catchAll(.*)+',
        name: 'NotFound',
        component: NotFound,
    }
]

export default routes