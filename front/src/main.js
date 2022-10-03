import { createApp } from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import { useCookies, globalCookiesConfig } from 'vue3-cookies'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// import * as VueGoogleMaps from "vue3-google-map"
import IconBase from '@/components/IconBase.vue'
import Vue3Geolocation from 'vue3-geolocation'

createApp(App).use(router).use(store).use(router).use(useCookies)
.use(store).use(ElementPlus).component('icon-base', IconBase).use(Vue3Geolocation)
// .use(VueGoogleMaps, {
//   load: {
//     libraries: "places",
//     region: "KR"
//   }
// })
.mount('#app')

globalCookiesConfig({
    expireTimes: "7d"
})