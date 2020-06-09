<template>
    <div>
        <div v-if="!profile">Необходимо авторизоваться через
            <a href="/login">Google</a>
        </div>
        <div v-else>
            <div style="padding-top: 5px; padding-bottom: 5px"><a href="/" >Назад</a></div>
            <div style="padding-top: 5px; padding-bottom: 5px">{{profile.name}}&nbsp;<a href="/logout">Выйти</a></div>
            <operations-list :messages="messages" />
        </div>
    </div>
</template>

<script>
    import OperationsList from 'components/operations/OperationsList.vue'
    export default {
        components: {
            OperationsList
        },
        data() {
            return {
                messages: frontendData.messages,
                profile: frontendData.profile
            }
        }
    }
</script>

<style scoped>

</style>