<template>
    <div style="padding-top: 5px; padding-bottom: 5px">
        <input type="datetime-local" placeholder="Write date of creation" v-model="create_date" />
        <input type="text" placeholder="Write debit" v-model="debit" />
        <input type="text" placeholder="Write credit" v-model="credit" />
        <input type="text" placeholder="Write amount" v-model="amount" />

        <div style="padding-top: 15px; padding-bottom: 5px"><input type="button" value="Save" @click="save"/></div>
    </div>
</template>

<script>
    function getIndex(list, id) {
        for (let i = 0; i < list.length; i++) {
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
                create_date: '',
                debit: '',
                credit: '',
                amount: '',
                id: ''
            }
        },
        watch: {
            messageAttr(newVal, oldVal) {
                this.create_date = newVal.create_date
                this.debit = newVal.debit
                this.credit = newVal.credit
                this.amount = newVal.amount
                this.id = newVal.id
            }
        },
        methods: {
            save() {
                const message = {
                    create_date: this.create_date,
                    debit: this.debit,
                    credit: this.credit,
                    amount: this.amount
                }

                if (this.id) {
                    this.$resource('/home/balance/{id}').update({id: this.id}, message).then(result =>
                        result.json().then(data => {
                            const index = getIndex(this.messages, data.id)
                            this.messages.splice(index, 1, data)
                            this.create_date = ''
                            this.debit = ''
                            this.credit = ''
                            this.amount = ''
                            this.id = ''
                        })
                    )
                } else {
                    this.$resource('/home/balance/{id}').save({}, message).then(result =>
                        result.json().then(data => {
                            this.messages.push(data)
                            this.create_date = ''
                            this.debit = ''
                            this.credit = ''
                            this.amount = ''
                        })
                    )
                }
            }
        }
    }
</script>

<style>

</style>