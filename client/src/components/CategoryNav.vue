<script setup lang="ts">
import { useRoute } from "vue-router";
import { useCategoryStore } from "@/stores/CategoryStore";

const categoryStore = useCategoryStore();

const route = useRoute();
</script>

<style>
h1 {
  color: white;
}
.nav-button {
  margin: 10px 20px;
  width: 100px;
  padding: 5px;
  border-radius: 10px;
  background: #07a9ef;
  color: white;
  display: inline-block;
  overflow: hidden;
}
.nav-button a {
  color: white;
}
/*.nav-button a:hover {*/
/*  color: #bf4612;*/
/*}*/
.whole_navigation {
  /*position: absolute;*/
  /*top: 250px;*/
  background: #dadbdd;
  height: 220px;
  /*margin-left: -40px;*/
  transition: height 2s;
}
.whole_navigation:hover {
  height: 300px;
  transition-timing-function: ease-in-out;
}
/*button unselected-category-button:hover {*/
/*  background-color: #bf4612;*/
/*}*/
/*.button.selected-category-button:hover,*/
/*.button.selected-category-button:active {*/
/*  background-color: #bf4612;*/
/*}*/
.button.unselected-category-button:hover,
.button.unselected-category-button:active {
  background-color: #07a9ef;
}
.button.unselected-category-button a:hover {
  color: #474e68;
}

.button.selected-category-button a {
  color: #d1d1d1;
  border-radius: 0;
}

/*.button.selected-category-button {*/
/*  background-color: #07a9ef;*/
/*}*/

/*.button.unselected-category-button,*/
/*.button.unselected-category-button:visited {*/
/*  background-color: #bf4612;*/
/*}*/

.button unselected-category-button a:hover {
  color: black;
}
.category-navcategory-nav {
  display: flex;
  align-items: center;
}
</style>

<template>
  <nav class="category-navcategory-nav">
    <div class="whole_navigation">
      <li
        v-for="category in categoryStore.categoryList"
        :key="category.categoryId"
      >
        <button class="nav-button">
          <router-link
            :to="{
              name: 'category-view',
              params: { name: category.name },
            }"
            style="color: white"
            class="button"
            :class="
              category.name === route.params.name
                ? 'selected-category-button'
                : 'unselected-category-button'
            "
            ><a> {{ category.name }}</a>
          </router-link>
        </button>
      </li>
    </div>
  </nav>
</template>
