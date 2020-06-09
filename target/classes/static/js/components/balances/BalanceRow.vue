<template>
    <div>
        <i style="text-align: center; width: 5%; display: inline-block">({{ message.id }})</i>
        <span style="padding-left: 5px; padding-right: 5px; text-align: center; width: 25%; display: inline-block">{{ message.create_date }}</span>
        <span style="padding-left: 5px; padding-right: 5px; text-align: center; width: 10%; display: inline-block">{{ message.debit }}</span>
        <span style="padding-left: 5px; padding-right: 5px; text-align: center; width: 10%; display: inline-block">{{ message.credit }}</span>
        <span style="padding-left: 5px; padding-right: 5px; text-align: center; width: 10%; display: inline-block">{{ message.amount }}</span>
        <span style="position: absolute; right: 0">
            <input type="button" value="Edit" @click="edit" />
            <input type="button" value="X" @click="del" />
        </span>
    </div>
</template>

<script>
    export default {
        props: ['message', 'editBalance', 'deleteBalance', 'messages'],
        methods: {
            edit() {
                this.editBalance(this.message);
            },
            del() {
                this.deleteBalance(this.message)
            }
        }
    }
</script>

<style>

</style>