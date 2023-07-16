<script setup lang="ts">
import { inject, ref, watch } from "vue";
import { BookItem, CategoryItem } from "@/types";
import CategoryNav from "@/components/CategoryNav.vue";
import CategoryBookList from "@/components/CategoryBookList.vue";

import { useRoute } from "vue-router";
const route = useRoute();
let bookList = ref([] as BookItem[]);

const categoryList: CategoryItem[] = inject("categoryList", []);
console.log("Name of first category:" + categoryList[0].name);

async function fetchBooks(categoryName: string) {
  // const selectedCategory =
  //   categoryList?.find((category) => (category.name = categoryName)) ||
  //   categoryList[0];
  const url =
    "http://cs5244.cs.vt.edu:8080/BookstoreFetch/api/categories/" +
    categoryName +
    "/books";

  bookList.value = await fetch(url).then((response) => response.json());
}
watch(
  () => route.params.name,
  (newName) => {
    fetchBooks(newName as string);
  },
  { immediate: true }
);
</script>
<style scoped>
/*.bookContainer {*/
/*  left: 600px;*/
/*  bottom: 600px;*/
/*  margin-left: 300px;*/
/*  margin-right: 150px;*/
/*  margin-bottom: 500px;*/
/*  margin-top: -550px;*/
/*  padding: 100px -80px 50px 80px;*/
/*}*/
</style>

<template>
  <div>
    <category-nav></category-nav>
    <category-book-list :bookList="bookList" class="bookContainer">
    </category-book-list>
  </div>
</template>
