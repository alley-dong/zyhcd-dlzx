<template>
  <div>
    <Header :showRight="showRight"></Header>
    <ul class="content">
      <li v-for="item in list" :key="item.id" @click="handleClick(item)">
        <!-- <router-link to="/index"> -->
        <div class="li">
          <span>时间：{{ item.createTime }}</span>
          <!-- <i class="right-1"  style="float:right"></i> -->
          <div style="display:inline-block;flex:1">
            <span style="margin-right:0.3rem">{{
              item.status == 1 ? "未读" : "已读"
            }}</span>
            <van-icon
              name="delete"
              @click.stop="removeClick(item.id)"
              class="right-2"
            />
          </div>
        </div>
        <div>
          <p>{{ item.typeEnum }}</p>
        </div>

        <!-- </router-link> -->
      </li>
    </ul>
  </div>
</template>

<script>
import Footer from "@/components/Footer";
import Header from "@/components/Header";
import {
  N03AdministratorMessageList,
  msgN03AdministratorDelMessage,
  N03AdministratorReadStatus
} from "@/api/index";
import { MessageBox } from "mint-ui";
export default {
  data() {
    return {
      showRight: false,
      list: [],
      listQuery: {
        pageNum: 1,
        pageSize: 10,
      },
    };
  },
  created() {
    this.getList();
  },
  components: {
    Footer,
    Header,
  },
  methods: {
    // 获取table的表格数据
    getList() {
      N03AdministratorMessageList({})
        .then((response) => {
          console.log(response);
          this.list = response.rows;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    removeClick(id) {
      MessageBox.confirm("确定执行此操作?").then((action) => {
        msgN03AdministratorDelMessage({ id }).then((res) => {
          this.getList();
        });
      });
    },
    handleClick(row) {
      N03AdministratorReadStatus({id:row.id}).then(res=>{

        switch (row.type) {
        case 1:
          this.$router.push("/myAgent/subsidyList/" + row.relationId);
          break;
        case 2:
          this.$router.push("/myAgent/leave/" + row.relationId);
          break;
        case 7:
        case 8:
          // this.$router.push("/myAgent/adopt/" + row.relationId);
          this.$router.push({path:"/myAgent/adopt/" + row.relationId,query:{type:row.type}});
          break;
        case 4:
          this.$router.push("/myAgent/accident/" + row.relationId);
          break;
        case 6:
          this.$router.push(
            "/myAgent/recruitmentPlanExamine/" + row.relationId
          );
          break;
        case 9:
          this.$router.push(
            "/myAgent/abnormalSituation/" + row.relationId
          );
          break;
        case 10:
          this.$router.push(
            "/myAgent/approved/" + row.relationId
          );
          break;
          // 车辆
            case 11:
          this.$router.push(
            "/myAgent/insurance/" + row.relationId
          );
          break;
        case 12:
          this.$router.push(
            "/myAgent/collectionManagement/" + row.relationId
          );
          break;
           case 13:
          this.$router.push(
            "/myAgent/carRegularInspection/" + row.relationId
          );
          break;
        default:
          break;
      }
      })
    },
  },
};
</script>

<style lang="scss" scoped>
.content {
  width: 100%;
  position: fixed;
  top: 48px;
  height: calc(100% - 48px);
  padding-bottom: 10px;
  overflow-y: auto;
  li {
    padding-bottom: 0.5rem;
    border-bottom: 0.05rem solid #333;
  }
}
.li {
  display: flex;
  padding-left: 1rem;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
  padding-right: 0.5rem;
  text-align: right;
}
</style>
