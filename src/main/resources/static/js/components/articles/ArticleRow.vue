<template>
    <div>
        <i>({{ message.id }})</i> {{ message.name }}
        <span style="position: absolute; right: 0">
            <input type="button" value="Edit" @click="edit" />
            <input type="button" value="X" @click="del" />
        </span>
    </div>
</template>

<script>
    export default {
        props: ['message', 'editArticle', 'deleteArticle', 'messages'],
        methods: {
            edit() {
                this.editArticle(this.message);
            },
            del() {
                this.deleteArticle(this.message)
                // messageApi.remove({id: this.message.id}).then(result => {
                //     if (result.ok) {
                //         this.messages.splice(this.messages.indexOf(this.message), 1)
                //     }
                // })
            }
        }
    }
</script>

<style>

</style>
