import { createApp } from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// import * as VueGoogleMaps from "vue3-google-map"


createApp(App).use(router).use(store).use(router)
.use(store).use(ElementPlus)
// .use(VueGoogleMaps, {
//   load: {
//     libraries: "places",
//     region: "KR"
//   }
// })
.mount('#app')
