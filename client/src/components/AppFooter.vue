<script setup lang="ts"></script>

<style>
* {
  padding: 0;
  margin: 0;
  list-style: none;
  font-size: 16px;
  font-family: Arial, "Helvetica Neue", Helvetica, sans-serif;
}

.footer {
  background-color: #363434;
  padding: 6px 10px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
  /*margin-top: -800px;*/
  text-decoration: none;
  bottom: 0;
  overflow: hidden;
  position: relative;
}
a {
  text-decoration: none;
  color: #333;
}
.about {
  color: #fff;
}
.about a {
  color: #fff;
  font-size: 14px;
  font-family: Arial, "Helvetica Neue", Helvetica, sans-serif;
  text-decoration: underline;
}
.about a:hover {
  font-weight: bold;
}

.copyt {
  padding-top: 5px;
}
</style>

<template>
  <div class="footer">
    <div class="link">
      <router-link to="/">
        <a href="#">
          <img src="@/assets/images/footer/face.jpg" alt="" />
        </a>
        <a href="#">
          <img src="@/assets/images/footer/tiww.jpg" alt="" />
        </a>
        <a href="#">
          <img src="@/assets/images/footer/ing.jpg" alt="" />
        </a>
      </router-link>
    </div>
    <div class="about">
      <router-link to="/">About | </router-link>
      <router-link to="/"> Contact | </router-link>
      <router-link to="/"> Directions </router-link>
    </div>
    <div class="copyt">
      <img src="@/assets/images/footer/copy.jpg" alt="" />
    </div>
  </div>
</template>
