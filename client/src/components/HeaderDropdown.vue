<script setup lang="ts">
import { useCategoryStore } from "@/stores/CategoryStore";
const categoryStore = useCategoryStore();
</script>
<style>
* {
  padding: 0;
  margin: 0;
  list-style: none;
  font-size: 16px;
  font-family: Arial, "Helvetica Neue", Helvetica, sans-serif;
}
.categories-button:hover {
  background: #6be4ff;
}
.categories-button {
  text-indent: 2px;
  position: center;
  /*top: 38px;*/
  padding-top: 4px;
  left: 0;
  transition: 0.5s;
  display: none;
}
.categories-button {
  display: block;
  background: #07a9ef;
  border: 1px solid #000;
  border-radius: 10px;
  line-height: 34px;
  width: 113px;
  color: #fff;
  text-align: center;
}
.categories-button:hover {
  cursor: pointer;
}
categories-button a {
  text-align: center;
  display: flex;
}
a {
  text-decoration: none;
}
.categories-button ul li {
  margin-bottom: 5px;
}
.categories-button ul li a {
  display: block;
  border: 1px solid #000;
  border-radius: 10px;
  line-height: 34px;
  width: 115px;
  text-indent: 10px;
  color: #fff;
}
.categories-button ul li a.cur,
.categories-button ul li a:hover {
  color: #666;
  background: #07a9ef;
}

.header-dropdown {
  padding-top: 5px;
  color: #fff;
  float: left;
  right: 0;
  position: relative;
  transform: translateX(0%);
  transition-duration: 1000ms;
  transition-property: transform;
  transition-timing-function: ease-in-out;
  transition: 0.5s;
  left: 0;
  box-shadow: 0 0 8px -10px #008;
  cursor: pointer;
}

.header-dropdown li {
  border: 1px solid #000;
  border-radius: 10px;
  margin-bottom: 5px;
}
.header-dropdown li:hover {
  background-color: #5ce1ff;
}
.header-dropdown li a:hover {
  color: white;
}
.header-dropdown ul li a {
  display: block;
  border-radius: 10px;
  line-height: 34px;
  width: 115px;
  text-indent: 10px;
  color: #fff;
}
.header-dropdown:hover {
  display: block;
}
.header-dropdown:hover .categories-button {
  display: block;
}
.header-dropdown ul {
  color: black;
  display: none;
}
.header-dropdown:hover ul {
  display: block;
  position: absolute;
  cursor: pointer;
}
.menus {
  background-color: #07a9ef;
  width: 105px;
}
</style>
<button>
<img src="" alt="" />
</button>
<template>
  <div class="header-dropdown">
    <button class="categories-button">
      <i
        ><img
          src="@/assets/images/header/bars-solid.svg"
          style="width: 15px; height: 15px; vertical-align: middle"
      /></i>
      Categories
    </button>
    <ul>
      <li
        v-for="category in categoryStore.categoryList"
        :key="category.categoryId"
        class="menus"
      >
        <a>
          <router-link :to="'../category/' + category.name" class="droppedDown">
            {{ category.name }}
          </router-link>
        </a>
      </li>
    </ul>
  </div>
</template>
