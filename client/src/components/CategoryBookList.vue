<script setup lang="ts">
import { useBookStore } from "@/stores/BookStore";
import CategoryBookListItem from "@/components/CategoryBookListItem.vue";
const bookStore = useBookStore();
</script>

<style scoped>
/*.container {*/
/*  overflow: hidden;*/
/*  display: flex;*/
/*}*/
/*.bookContainer {*/
/*  !*position: fixed;*!*/
/*  !*left: inherit;*!*/
/*  !*bottom: inherit;*!*/
/*  !*margin: -550px 200px 500px 460px;*!*/
/*  !*padding: 100px -80px 50px 80px;*!*/
/*  !* flex-wrap: wrap; *!*/
/*  flex-direction: column;*/
/*}*/
.right {
  padding: 20px;

  background-color: #38c0fb;
  height: auto;
  width: auto;
  box-sizing: border-box;
  justify-content: space-between;
  display: block;

  /*place-items: center;*/
  position: relative;

  /*margin-right: 58px;*/
}
.item {
  display: flex;
  padding: 3rem 6rem;
  gap: 2rem;
}
.container {
  padding-top: 10px;
  flex-wrap: wrap;
  display: flex;
  justify-content: right;
}
.right {
  display: flex;
  flex-wrap: wrap;
}
/*item {*/
/*  right: 500px;*/
/*}*/
</style>

<template>
  <div class="bookContainer">
    <div class="container">
      <div class="right">
        <h1>What we got in the ocean.</h1>
        <ul class="item">
          <template v-for="book in bookStore.bookList" :key="book.bookId">
            <div class="container">
              <category-book-list-item :book="book"></category-book-list-item>
            </div>
          </template>
        </ul>
      </div>
    </div>
  </div>
</template>
