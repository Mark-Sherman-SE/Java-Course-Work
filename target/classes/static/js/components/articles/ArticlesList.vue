<template>
    <div style="position: relative; width: 300px;">
        <article-form :messages="messages" :messageAttr="message"/>
        <article-row v-for="message in messages"
                     :key="message.id"
                     :message="message"
                     :editArticle="editArticle"
                     :deleteArticle="deleteArticle"
                     :messages="messages" />
    </div>
</template>

<script>
    import ArticleRow from 'components/articles/ArticleRow.vue'
    import ArticleForm from 'components/articles/ArticleForm.vue'


    export default {
        props: ['messages'],
        components: {
            ArticleRow,
            ArticleForm
        },
        data() {
            return {
                message: null
            }
        },
        methods: {
            editArticle(message) {
                this.message = message
            },
            deleteArticle(message) {
                this.$resource('/home/article/{id}').remove({id: message.id}).then(result => {
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
