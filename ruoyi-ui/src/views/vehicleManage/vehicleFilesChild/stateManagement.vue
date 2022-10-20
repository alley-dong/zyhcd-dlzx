<template>
  <div class="app-container">
    <!-- <div style="width: 98%; margin: 0 20px 20px; overflow: hidden"> -->
    <!-- <el-button
        type="primary"
        style="float: right; margin-right: 10px"
        @click="saveClick"
        :loading="saveLoading"
        >提交</el-button
      >
      <el-button
        type="primary"
        @click="cancelClick"
        style="float: right; margin-right: 10px"
        >返回</el-button
      >  </div> -->
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
      <el-form-item label="车牌号" prop="licensePlate">
        <el-input
          v-model="queryParams.licensePlate"
          placeholder="请输入车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="状态" prop="vehicleStatus">
        <!-- <el-input
          v-model="queryParams.inspectDate"
          placeholder="请输入检车日期"
          clearable
        /> -->
        <el-select v-model="queryParams.vehicleStatus" placeholder="请选择" clearable>
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <!-- <el-button
          type="cyan"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        > -->
        <buttonGroup @handleFilter="handleQuery" twoButtonState />
      </el-form-item>
    </el-form>

    <el-table
      v-loading="loading"
      ref="multipleTable"
      :data="driverList"
      @selection-change="handleSelectionChange"
    >
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column
        label="序号"
        type="index"
        width="50"
        align="center"
      ></el-table-column>

      <el-table-column label="车牌号" align="center" prop="licensePlate" />
      <el-table-column label="状态" align="center" prop="vehicleStatus">
        <template slot-scope="scope">
          {{ scope.row.vehicleStatus | state }}
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        width="250px"
      >
        <template slot-scope="scope">
          <el-button
            type="primary"
            @click="seeClick(scope.row)"
            plain
            style="margin-right: 15px"
      v-hasPermi="['pfservercar:vehiclestatus:edit']"
            >修改状态</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <el-dialog :visible.sync="open" @close="close" title="">
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="100px"
      >
        <el-form-item label="状态" prop="mobile">
          <el-select
            v-model="formData.vehicleStatus"
            placeholder="请输入状态"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="(item, index) in options"
              :key="index"
              :label="item.label"
              :value="item.value"
              :disabled="item.disabled"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  vehicleInspectRecord7List,
  vehicleStatusEditSave,
} from "@/api/car/vehicle";

import { formatDate } from "@/utils/index";
import buttonGroup from "@/views/components/com/buttonGroup";

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
        vehicleStatus: null,
        licensePlate: null,
      },
      formData: {
        id: null,
        vehicleStatus: null,
      },
 
      options: [
        {
          value: 1,
          label: "运输中",
        },
        {
          value: 2,
          label: "维修中",
        },
        // {
        //   value: 3,
        //   label: "请假中",
        // },
        {
          value: 4,
          label: "空闲中",
        },
      ],
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
        //   车辆状态 1运输中 2维修中 3请假中 4空闲中
        case 1:
          return "运输中";
          break;
        case 2:
          return "维修中";
          break;
        case 3:
          return "请假中";
          break;
        case 4:
          return "空闲中";
          break;
        default:
          // return "全部";
          break;
      }
    },
  },
  created() {},
  components: {
    buttonGroup,
  },
  mounted() {
    this.getList();
  },
  methods: {
    //   取消
    close() {
      this.open = false;
      this.formData.vehicleStatus = null;
    },
    //   确定
    handelConfirm() {
      vehicleStatusEditSave(this.formData).then((res) => {
        this.getList();
        this.close();
      });
    },
    // 查看年检记录
    seeClick(row) {
      console.log(row.id);
      this.open = true;
      this.formData.id = row.id;
      this.formData.vehicleStatus = row.vehicleStatus;
    },
    //   返回
    // cancelClick() {
    //   this.$router.push({ path: `/paperManage`, name: "paperManage" });
    // },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
    },

    /** 查询驾驶员-司机档案列表 */
    getList() {
      this.loading = true;
      vehicleInspectRecord7List(this.queryParams).then((response) => {
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
