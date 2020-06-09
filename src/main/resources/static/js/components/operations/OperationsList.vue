<template>
    <div style="position: relative; width: 800px;">
        <operation-form :messages="messages" :messageAttr="message"/>
        <div>
            <hr>
            <span style="text-align: center; width: 5%; display: inline-block">ID</span>
            <span style="padding-left: 5px; padding-right: 5px; text-align: center; width: 9%; display: inline-block">Article id</span>
            <span style="padding-left: 5px; padding-right: 5px; text-align: center; width: 10%; display: inline-block">Debit</span>
            <span style="padding-left: 5px; padding-right: 5px; text-align: center; width: 10%; display: inline-block">Credit</span>
            <span style="padding-left: 5px; padding-right: 5px; text-align: center; width: 25%; display: inline-block">Create Date</span>
            <span style="padding-left: 5px; padding-right: 5px; text-align: center; width: 9%; display: inline-block">Balance id</span>
            <hr>
        </div>
        <operation-row v-for="message in messages"
                     :key="message.id"
                     :message="message"
                     :editOperation="editOperation"
                     :deleteOperation="deleteOperation"
                     :messages="messages" />
    </div>
</template>

<script>
    import OperationRow from 'components/operations/OperationRow.vue'
    import OperationForm from 'components/operations/OperationForm.vue'


    export default {
        props: ['messages'],
        components: {
            OperationRow,
            OperationForm
        },
        data() {
            return {
                message: null
            }
        },
        methods: {
            editOperation(message) {
                this.message = message
            },
            deleteOperation(message) {
                this.$resource('/home/operation/{id}').remove({id: message.id}).then(result => {
                    if (result.ok) {
                        this.messages.splice(this.messages.indexOf(this.message), 1)
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>