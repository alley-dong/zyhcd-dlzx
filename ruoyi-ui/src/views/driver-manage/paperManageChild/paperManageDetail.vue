<template>
  <div class="app-container">
    <div style="width: 98%; margin: 0 20px 20px; overflow: hidden">
      <el-button
        type="primary"
        style="float: right; margin-right: 10px"
        @click="saveClick"
        :loading="saveLoading"
        v-hasPermi="['person:dDriverExam:add']"
        >提交</el-button
      >
      <!-- <router-link :to="{ path: '/addTrainingInfo', name: 'addTrainingInfo' }"> -->
      <el-button
        type="primary"
        @click="cancelClick"
        style="float: right; margin-right: 10px"
        >返回</el-button
      >
      <!-- </router-link> -->
    </div>
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
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

      <el-form-item label="联系电话" prop="contact">
        <el-input
          v-model="queryParams.contact"
          placeholder="请输入联系电话"
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
          >搜索</el-button
        >
      </el-form-item>
    </el-form>

    <el-table
      v-loading="loading"
      ref="multipleTable"
      :data="driverList"
      @selection-change="handleSelectionChange"
      :row-key="(row) => row.id"
    >
      <el-table-column type="selection" width="55" align="center" :reserve-selection="true" />
      <el-table-column
        label="序号"
        type="index"
        width="50"
        align="center"
      ></el-table-column>

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

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import {
  paperManageListZhong,
  dDriverExamAdd1,
  dExamPaperListDriver,
} from "@/api/driver/paperManage";

import { formatDate } from "@/utils/index";

export default {
  name: "Driver",
  data() {
    return {
      rules: {
        status: [],
      },
      saveLoading: false,

      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 驾驶员-司机档案表格数据
      driverList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        status: null,
        name: null,
        contact: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      checkList: [],
      dDriverExamAddList: {},
    };
  },
  filters: {
    time(val) {
      console.log(val);
      return formatDate(val, true);
    },

    state(val) {
      switch (val) {
        //   1未完成 2已完成 3超期未完成 4超期已完成
        case 1:
          return "未完成";
          break;
        case 2:
          return "已完成";
          break;
        case 3:
          return "超期未完成";
          break;
        case 4:
          return "超期已完成";
          break;
        default:
          return "全部";
          break;
      }
    },
  },
  created() {},
  mounted() {
    this.getList();
  },
  methods: {
    // 选中list
    dExamPaperListDriverData() {
      dExamPaperListDriver({ paperId: this.$route.params.id }).then((res) => {
        let arr = [];
        for (const item of res.rows) {
          let obj = this.driverList.find((_) => _.id == item.driverId);
          // if (obj.id == item.id) {
          console.log(obj);
          obj ? arr.push(obj) : null;
          // }
        }
        this.checkList = arr;
        this.toggleSelection(arr);
      });
    },
    toggleSelection(rows) {
      console.log(rows);
      if (rows) {
        rows.forEach((row) => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    //   返回
    cancelClick() {
      // this.$router.push({ path: `/paperManage`, name: "paperManage" });
      this.$router.go(-1)
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
    },
    // 提交
    saveClick() {
      this.saveLoading = true;
      // setTimeout(() => {
      //   this.saveLoading = false;
      // }, 2000);
      //TODO:
      // this.dDriverExamAddList.paperId = this.$route.params.id;
      // this.dDriverExamAddList.driverId = []; //勾选的
      // this.dDriverExamAddList.redriverId = [];
      // for (const [index, item] of this.checkList.entries()) {
      //   if (this.ids.indexOf(item) != -1) {
      //     this.ids.splice(this.ids.indexOf(item), 1);
      //   } else {
      //     this.dDriverExamAddList.redriverId.push(item);
      //   }
      // }
      // let arr = []
      // for (const item of this.ids) {
      //   arr.push(item);
      // }
      // let arr1
      // if (this.dDriverExamAddList.driverId.length !== 0) {

      //    arr1 = Array.form(new Set(this.dDriverExamAddList.driverId))
      // }else{
      //    arr1 = []
      // }
      // // let arr2 = Array.form(new Set(this.dDriverExamAddList.redriverId))
      // this.dDriverExamAddList.driverId = arr.join(
      //   ","
      // );
      // this.dDriverExamAddList.redriverId = arr1.join(
      //   ","
      // );
      //TODO:
      // 12.11从写没测
      this.dDriverExamAddList.paperId = this.$route.params.id;
      this.dDriverExamAddList.driverId = []; //勾选的
      let arr1 = [];
      let idList = [];
      for (const item of this.checkList) {
        idList.push(item.id);
      }
      for (const item of idList) {
        if (this.ids.indexOf(item) == -1) {
          console.log(item);
          arr1.push(item);
        }
      }
      console.log(arr1);
      arr1 = arr1.join();
      this.dDriverExamAddList.redriverId = arr1;
      this.dDriverExamAddList.driverId = this.ids.join();
      // 12.11从写没测
      dDriverExamAdd1(this.dDriverExamAddList)
        .then((res) => {
          this.cancelClick();
          this.saveLoading = false;
        })
        .catch((err) => {
          this.saveLoading = false;
        });
      // console.log(this.ids);
    },
    /** 查询驾驶员-司机档案列表 */
    getList() {
      this.loading = true;
      paperManageListZhong(this.queryParams).then((response) => {
        console.log(response);
        this.driverList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.dExamPaperListDriverData();
      });
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
  },
};
</script>
