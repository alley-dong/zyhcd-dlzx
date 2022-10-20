<template>
  <div :class="{ 'has-logo': showLogo }">
    <logo v-if="showLogo" :collapse="isCollapse" />
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapse"
        :background-color="variables.menuBg"
        :text-color="variables.menuText"
        :unique-opened="true"
        :active-text-color="settings.theme"
        :collapse-transition="false"
        mode="vertical"
      >
        <sidebar-item
          v-for="(route, index) in permission_routes"
          :key="route.path + index"
          :item="route"
          :base-path="route.path"
        />
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";
import Logo from "./Logo";
import SidebarItem from "./SidebarItem";
import variables from "@/assets/styles/variables.scss";

export default {
  mounted() {
      let arr = [
        "*",
        "redirect",
        "http://ruoyi.vip",
        "/dict",
        "/job",
        "/gen",
        "/driver",
        "/system",
        "/monitor",
        "/tool",
        '/person'
      ];
    for (const [index, value] of this.permission_routes.entries()) {
      // console.log(value.path, value);
    
      /* 
      /dict
/redirect 
/login 
/404 
/401 
 
/user 
/dict 
/gen 
/system 
/tool 
/person 
* 
      */
      // for (const item of arr) {
      //   if (value.path === item) {
      //     this.permission_routes.splice(index, 1);
      //   }
      // }
      // // 去掉若依官网
      // if (value.path === "http://ruoyi.vip") {
      //   this.permission_routes.splice(index, 1);
      // }
      //替换首页路径 TODO:
      // if (value.children) {
      //   let index = value.children.findIndex((_v) => _v.name == "首页");
      //   if (value.children[0].path == "/recruitmentPlan") {
      //   }
      //   if (index >= 0) {
      //     value.children[index].path = "/recruitmentPlan";
      //     // /driver
      //     value.path = "/driver";
      //   }
      // }
    }
  },
  components: { SidebarItem, Logo },
  computed: {
    ...mapState(["settings"]),
    ...mapGetters(["permission_routes", "sidebar"]),
    activeMenu() {
      const route = this.$route;
      const { meta, path } = route;
      // if set path, the sidebar will highlight the path you set
      if (meta.activeMenu) {
        return meta.activeMenu;
      }
      return path;
    },
    showLogo() {
      return this.$store.state.settings.sidebarLogo;
    },
    variables() {
      return variables;
    },
    isCollapse() {
      return !this.sidebar.opened;
    },
  },
};
</script>
