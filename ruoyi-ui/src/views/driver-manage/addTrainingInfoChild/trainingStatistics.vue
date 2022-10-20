<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="姓名" prop="driverName">
        <el-input
          v-model="queryParams.driverName"
          placeholder="请输入姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="联系电话" prop="tel">
        <el-input
          v-model="queryParams.tel"
          placeholder="请输入联系电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="请选择状态"
          clearable
          size="small"

        >
          <el-option
            v-for="(item, index) in statusOptions"
            :key="index"
            :label="item.label"
            :value="item.value"
            :disabled="item.disabled"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
          <el-button
          type="primary"
          size="mini"
          @click="cancelClick"
          >返回</el-button
        >
      </el-form-item>
    </el-form>


    <el-table
      v-loading="loading"
      :data="driverList"
    >
      <el-table-column label="姓名" align="center" prop="driverName" />
      <el-table-column label="联系电话" align="center" prop="tel" />
      <el-table-column label="身份证" align="center" prop="idCard" />
      <el-table-column label="学习时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.latelyTime  }}
        </template>
      </el-table-column>
      <el-table-column label="最少阅读时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.minDuration }}
        </template>
      </el-table-column>

      <el-table-column label="状态" align="center">
        <template slot-scope="scope">
          {{ scope.row.status | state}}
        </template>
      </el-table-column>


    </el-table>

    <!-- <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    /> -->
  </div>
</template>

<script>
import { trainingListRead } from "@/api/driver/noticeManage";
import { formatDate } from "@/utils/index";

export default {
  name: "Driver",
  data() {
    return {
      rules: {
        status: [],
      },
      statusOptions: [
        {
          label: "全部",
          value: null,
        },
        {
          label: "未完成",
          value: 1,
        },
        {
          label: "已完成",
          value: 2,
        },
        {
          label: "超期未完成",
          value: 3,
        },
        {
          label: "超期已完成",
          value: 4,
        },
      ],
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
        driverName: null,
        tel: null,
      },
      // 表单参数
      form: {},
      // 表单校验
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
            return '未完成'
          break;
        case 2:
            return '已完成'
          break;
        case 3:
            return '超期未完成'
          break;
        case 4:
            return '超期已完成'
          break;
        default:
            return '全部'
          break;
      }
    },
  },
  created() {
    this.queryParams.trainingId = this.$route.query.id
    this.getList();
  },
  methods: {
    //   返回
    cancelClick(){
        this.$router.go(-1)
    },
    /** 查询驾驶员-司机档案列表 */
    getList() {
      this.loading = true;
      trainingListRead(this.queryParams).then((response) => {
        console.log(response);
        this.driverList = response.rows;
        this.total = response.total;
        this.loading = false;
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