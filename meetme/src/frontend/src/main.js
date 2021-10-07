import { createApp } from 'vue'
import App from './App.vue'
import { router } from './router/index.js'
import { store } from './store/index.js'

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.esm.min.js'

import './others/css/datepicker.css'
import './others/css/templatemo-style.css'

import './others/font-awesome-4.7.0/css/font-awesome.min.css'

import './others/slick/slick.css'
import './others/slick/slick-theme.css'

// search tag
import '@mayank1513/vue-tag-input/dist/TagInput.css'

const app = createApp(App)
app.use(store)
app.use(router)
app.mount('#app')