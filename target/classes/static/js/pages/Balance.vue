<template>
    <div>
        <div v-if="!profile">Необходимо авторизоваться через
            <a href="/login">Google</a>
        </div>
        <div v-else>
            <div style="padding-top: 5px; padding-bottom: 5px"><a href="/" >Назад</a></div>
            <div style="padding-top: 5px; padding-bottom: 5px">{{profile.name}}&nbsp;<a href="/logout">Выйти</a></div>
            <balances-list :messages="messages" />
        </div>
    </div>
</template>

<script>
    import BalancesList from 'components/balances/BalancesList.vue'
    export default {
        components: {
            BalancesList
        },
        data() {
            return {
                messages: frontendData.messages,
                profile: frontendData.profile
            }
        }
    }
</script>

<style>

</style>