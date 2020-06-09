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

/*
function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}

var messageApi = Vue.resource('/home/article/{id}');

var messageAdd = Vue.resource('/home/article/addArticle')

Vue.component('message-form', {
    props: ['messages', 'messageAttr'],
    data: function () {
        return {
            text: '',
            id: ''
        }
    },
    watch: {
        messageAttr: function(newVal, oldVal) {
            this.text = newVal.name;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
            '<input type="text" placeholder="Write something" v-model="text"/>' +
            '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function () {
            var message = { name: this.text };

            if (this.id) {
                messageApi.update({id: this.id}, message).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.messages, data.id);
                        this.messages.splice(index, 1, data);
                        this.text = ''
                        this.id = ''
                    })
                )
            } else {
                messageAdd.save({}, message).then(result =>
                    result.json().then(data => {
                        this.messages.push(data);
                        this.text = ''
                    })
                )
            }
        }
    }
});

Vue.component('message-row', {
    props: ['message', 'editMethod', 'messages'],
    template:'<div>' +
        '<i>({{ message.id }})</i> {{ message.name }}' +
        '<span style="position: absolute; right: 0">' +
            '<input type="button" value="Edit" @click="edit" />' +
            '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.message);
        },
        del: function () {
            messageApi.remove({id: this.message.id}).then(result => {
                if (result.ok) {
                    this.messages.splice(this.messages.indexOf(this.message), 1)
                }
            })
        }
    }
});

Vue.component('messages-list', {
    props: ['messages'],
    data: function() {
      return {
          message: null
      }
    },
    template:
        '<div style="position: relative; width: 300px;">' +
            '<message-form :messages="messages" :messageAttr="message"/>' +
            '<message-row v-for="message in messages" :key="message.id" :message="message"' +
                ':editMethod="editMethod" :messages="messages" />' +
        '</div>',
    methods: {
        editMethod: function(message) {
            this.message = message;
        }
    }
});


var app = new Vue({
    el: '#app',
    template:
        '<div>' +
            '<div v-if="!profile">Необходимо авторизоваться через <a href="/login">Google</a></div>' +
            '<div v-else>' +
                '<div>{{profile.name}}&nbsp;<a href="/logout">Выйти</a></div>' +
                '<messages-list :messages="messages"/>' +
            '</div>' +
        '</div>',
    data: {
        messages: frontendData.messages,
        profile: frontendData.profile
    }
});*/
