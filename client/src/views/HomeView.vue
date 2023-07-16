<script setup lang="ts">
import HomeCategoryList from "@/components/HomeCategoryList.vue";
</script>

<style>
* {
  padding: 0;
  margin: 0;
  list-style: none;
  font-size: 16px;
  font-family: Arial, "Helvetica Neue", Helvetica, sans-serif;
}

.indexbanner {
  background: url("~@/assets/images/site/bg.jpg") center center no-repeat;
  height: 689px;
}
.tips {
  width: 440px;
  text-align: center;
  padding: 100px 10px 10px 260px;
}
.tips p {
  color: #1f5af0;
  font-weight: bold;
  font-size: 20px;
  margin-bottom: 5px;
}
.tips h3 {
  text-align: center;
  padding-top: 5px;
}
.tips h3 a {
  width: 140px;
  display: block;
  text-align: center;
  color: #fff;
  background-color: #07a9ef;
  height: 50px;
  line-height: 50px;
  border: 1px solid #000;
  /*margin: 0 auto;*/
  border-radius: 10px;
  box-shadow: 1px 1px 5px 0px;
  /*margin-left: 100px;*/
}
h3 {
  color: #fff;
  padding: 3px 0;
}

.primary-button {
  display: inline-block;
  margin: 4px 2px;
  transform: scale(1);
}
.primary-button a:hover {
  background-color: #bf4612;
}
.primary-button:active {
  transform: scale(0.97);
}
</style>
<template>
  <div class="indexbanner">
    <div class="tips">
      <p>If we encounter a man of rare</p>
      <p>intellect, we should ask him</p>
      <p>what books he reads</p>
      <router-link to="/category/Best%20Seller">
        <h3 class="primary-button primary-button1">
          <a href="#">Shop Now</a>
        </h3>
      </router-link>
    </div>
  </div>
</template>
