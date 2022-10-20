<template>
  <div class="app-container">
    <div style="width: 98%; margin: 0 20px 20px; overflow: hidden">
      <el-button
        type="primary"
        style="float: right; margin-right: 10px"
        @click="saveClick"
        :loading="saveLoading"
        >保存</el-button
      >
      <!-- <router-link :to="{ path: '/addTrainingInfo', name: 'addTrainingInfo' }"> -->
      <el-button
        type="primary"
        @click="cancel"
        style="float: right; margin-right: 10px"
        >返回</el-button
      >
      <!-- </router-link> -->
    </div>
    <div class="top">
      <div class="check-peoper">选择人员</div>
      <div class="xian"></div>
    </div>
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="90px"
    >
      <el-form-item label="驾驶员姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入驾驶员姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系方式" prop="urgentTel">
        <el-input
          v-model="queryParams.urgentTel"
          placeholder="请输入联系方式"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >查询</el-button
        >
      </el-form-item>
    </el-form>

    <el-table :data="noticeList" ref="singleTable" @selection-change="handleSelectionChange" :row-key="(row) => row.id">
      <el-table-column type="selection" width="55" align="center" :reserve-selection="true" />

      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="性别" align="center" prop="sex" >
        <template slot-scope="scope">
          <div>
            {{scope.row.sex==0?'男':'女'}}
          </div>
        </template>
      </el-table-column>
      <el-table-column label="联系电话" align="center" prop="contact" />
      <el-table-column label="身份证" align="center" prop="idCard" />
    </el-table>

    <!--<pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />-->
    <!-- 选择培训人员 -->
  </div>
</template>

<script>
import { selectParticipantsList } from "@/api/driver/noticeManage";

export default {
  data() {
    return {
      // 选中数组
      ids: [],
      names: [],
      saveLoading: false,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 公告表格数据
      noticeList: [],
      checkList:[],
      // 总条数
      total: 0,
      // 查询参数
      queryParams: {
        // pageNum: 1,
        // pageSize: 10,
        name: undefined,
        urgentTel: undefined,
        status: undefined,
      },
    };
  },
  mounted() {
    if (localStorage.getItem("selectTrainersIds")) {
      this.checkList =localStorage.getItem("selectTrainersIds").split(',');
    }
    this.init();
  },
  methods: {
    init() {
      this.getList();
    },
    // 选中数据
    setCurrent(row) {
      // this.$refs.singleTable.setCurrentRow(row);
      //  rows.forEach(row => {
        setTimeout(() => {
          
          this.$refs.singleTable.toggleRowSelection(row);
        }, 300);
          // });
    },
    // 选择群组
    selectGroupClick() {
      // let arr = []
      for (const item of this.noticeList) {
        for (const data of this.checkList) {
          if (item.id == data) {
            // console.log(item);
            this.setCurrent(item);
          }
        }
      }
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.names = selection.map((item) => item.name);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    // 选择参与人员列表
    getList() {
      selectParticipantsList(this.queryParams).then((res) => {
        console.log(res);
        this.noticeList = res.rows;
        this.total = res.total;
        this.selectGroupClick();
        // console.log(localStorage.getItem("selectTrainersIds"));
        // if (localStorage.getItem("selectTrainersIds")) {
        //   for (const item of localStorage
        //     .getItem("selectTrainersIds")
        //     .split(",")) {
        //       debugger
        //       for (const data of res.rows) {
        //         if (item==data.id) {
        //           this.setCurrent(data)
        //         }
        //       }
        //   }
        // }
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    // 取消
    cancel() {
      console.log(this);
      this.$router.go(-1);
    },
    //   保存
    saveClick() {
      console.log(this.ids);
      this.saveLoading = true;
      setTimeout(() => {
        this.saveLoading = false;
      }, 2000);
      localStorage.setItem("selectTrainersIds", this.ids);
      localStorage.setItem("selectTrainersNames", this.names);
      this.cancel();
      //  this.$router.push({
      //   path: `/addTrainingInfo`,
      // });
    },
  },
};
</script>

<style lang="scss" scoped>
.top {
  display: flex;
  margin-bottom: 50px;
  .check-peoper {
    margin-right: 30px;
  }
  .xian {
    flex: 1;
    height: 3px;
    background-color: #333399;
    margin-top: 7px;
  }
}
</style>
