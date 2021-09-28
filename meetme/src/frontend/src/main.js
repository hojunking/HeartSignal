import { createApp } from 'vue'
import App from './App.vue'
import { router } from './router/index.js'
import { store } from './store/index.js'
//fontawsome
import { library } from '@fortawesome/fontawesome-svg-core'
import { faUserSecret } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'



import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.esm.min.js'

import './others/css/datepicker.css'
import './others/css/templatemo-style.css'
// import './others/js/datepicker.min.js'

import './others/font-awesome-4.7.0/css/font-awesome.min.css'

import './others/slick/slick.css'
import './others/slick/slick-theme.css'
// import './others/slick/slick.min.js'

// import './others/js/jquery-3.6.0.min.js'

// search tag
import '@mayank1513/vue-tag-input/dist/TagInput.css'

import VueEasyLightbox from 'vue-easy-lightbox'



const app = createApp(App)
app.use(store)
app.use(router)
app.use(VueEasyLightbox)
app.mount('#app')
library.add(faUserSecret)

app.component('font-awesome-icon', FontAwesomeIcon)

app.config.productionTip = false
