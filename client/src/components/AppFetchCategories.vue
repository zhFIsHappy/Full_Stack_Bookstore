<script setup lang="ts">
import { provide } from "vue";
import { CategoryItem } from "@/types";
import AppHeader from "@/components/AppHeader.vue";
import AppFooter from "@/components/AppFooter.vue";
import { apiUrl } from "@/services/ApiService";
import { useCategoryStore } from "@/stores/CategoryStore";
import { useRoute } from "vue-router";

const route = useRoute();
const categoryStore = useCategoryStore();

categoryStore.fetchCategories();

console.log(categoryStore.categoryList[0]);
// provide("categoryList", categoryList);
</script>

<style>
.main-content {
  display: flex;
}
/*.wrapper {*/
/*  min-height: calc(100vh - 70px);*/
/*}*/
</style>
<!--top: 500px-->
<template>
  <div id="app-fetch-categories">
    <app-header></app-header>
    <router-view class="main-content"></router-view>
    <app-footer></app-footer>
  </div>
</template>
