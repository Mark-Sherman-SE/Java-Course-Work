<template>
    <div style="padding-top: 5px; padding-bottom: 5px">
        <input type="text" placeholder="Write article id" v-model="article_id" />
        <input type="text" placeholder="Write debit" v-model="debit" />
        <input type="text" placeholder="Write credit" v-model="credit" />
        <input type="text" placeholder="Write balance id" v-model="balance_id" />
        <input type="datetime-local" placeholder="Write date of creation" v-model="create_date" />

        <div style="padding-top: 15px; padding-bottom: 5px"><input type="button" value="Save" @click="save"/></div>
    </div>
</template>

<script>
    function getIndex(list, id) {
        for (var i = 0; i < list.length; i++) {
            if (list[i].id === id) {
                return i
            }
        }
        return -1
    }

    export default {
        props: ['messages', 'messageAttr'],
        data() {
            return {
                article_id: '',
                debit: '',
                credit: '',
                create_date: '',
                balance_id: '',
                id: ''
            }
        },
        watch: {
            messageAttr(newVal, oldVal) {
                this.article_id = newVal.article.id
                this.debit = newVal.debit
                this.credit = newVal.credit
                this.create_date = newVal.create_date
                this.balance_id = newVal.balance.id
                this.id = newVal.id
            }
        },
        methods: {
            save() {
                const article = {
                    id: this.article_id
                }
                const balance = {
                    id: this.balance_id
                }
                const message = {
                    article: article,
                    debit: this.debit,
                    credit: this.credit,
                    create_date: this.create_date,
                    balance: balance
                }

                if (this.id) {
                    this.$resource('/home/operation/{id}').update({id: this.id}, message).then(result =>
                        result.json().then(data => {
                            const index = getIndex(this.messages, data.id)
                            this.messages.splice(index, 1, data)
                            this.article_id = ''
                            this.debit = ''
                            this.credit = ''
                            this.create_date = ''
                            this.balance_id = ''
                            this.id = ''
                        })
                    )
                } else {
                    this.$resource('/home/operation/{id}').save({}, message).then(result =>
                        result.json().then(data => {
                            this.messages.push(data)
                            this.article_id = ''
                            this.debit = ''
                            this.credit = ''
                            this.create_date = ''
                            this.balance_id = ''
                        })
                    )
                }
            }
        }
    }
</script>

<style scoped>

</style>