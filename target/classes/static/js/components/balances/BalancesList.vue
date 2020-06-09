<template>
    <div style="position: relative; width: 600px;">
        <balances-form :messages="messages" :messageAttr="message"/>
        <div>
            <hr>
            <span style="text-align: center; width: 5%; display: inline-block">ID</span>
            <span style="padding-left: 5px; padding-right: 5px; text-align: center; width: 25%; display: inline-block">Create Date</span>
            <span style="padding-left: 5px; padding-right: 5px; text-align: center; width: 10%; display: inline-block">Debit</span>
            <span style="padding-left: 5px; padding-right: 5px; text-align: center; width: 10%; display: inline-block">Credit</span>
            <span style="padding-left: 5px; padding-right: 5px; text-align: center; width: 10%; display: inline-block">Amount</span>
            <hr>
        </div>
        <balance-row v-for="message in messages"
                     :key="message.id"
                     :message="message"
                     :editBalance="editBalance"
                     :deleteBalance="deleteBalance"
                     :messages="messages" />
    </div>
</template>

<script>
    import BalanceRow from 'components/balances/BalanceRow.vue'
    import BalancesForm from 'components/balances/BalancesForm.vue'


    export default {
        props: ['messages'],
        components: {
            BalanceRow,
            BalancesForm
        },
        data() {
            return {
                message: null
            }
        },
        methods: {
            editBalance(message) {
                this.message = message
            },
            deleteBalance(message) {
                this.$resource('/home/balance/{id}').remove({id: message.id}).then(result => {
                    if (result.ok) {
                        this.messages.splice(this.messages.indexOf(this.message), 1)
                    }
                })
            }
        }
    }
</script>

<style>

</style>