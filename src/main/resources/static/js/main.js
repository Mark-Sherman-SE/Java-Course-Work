import Vue from 'vue'
import VueResource from 'vue-resource'

import App from 'pages/App.vue'
import Balance from 'pages/Balance.vue'
import Operation from 'pages/Operation.vue'

Vue.use(VueResource)

new Vue({
    el: '#app',
    render: a => a(App)
})

new Vue({
    el: '#balance',
    render: a => a(Balance)
})

new Vue({
    el: '#operation',
    render: a => a(Operation)
})
