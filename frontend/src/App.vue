<template>
  <v-app>
<!--    <v-app-bar-->
<!--      app-->
<!--      color="primary"-->
<!--      dark-->
<!--    >-->
<!--      <router-link class="white&#45;&#45;text" to="/">Основной экран</router-link> |-->
<!--      <router-link class="white&#45;&#45;text" to="/editor">Редактор</router-link>-->
<!--    </v-app-bar>-->
      <router-view/>
  </v-app>
</template>

<script>

export default {
  name: 'App',

  data: () => ({
    //
  }),
};
</script>
