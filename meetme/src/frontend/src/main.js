import { createApp } from 'vue'
import App from './App.vue'
import { router } from './router/index.js'

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'

import './others/css/datepicker.css'
import './others/css/templatemo-style.css'
import './others/js/datepicker.min.js'

import './others/font-awesome-4.7.0/css/font-awesome.min.css'

import './others/slick/slick.css'
import './others/slick/slick-theme.css'
import './others/slick/slick.min.js'

const app = createApp(App)
app.use(router)
app.mount('#app')