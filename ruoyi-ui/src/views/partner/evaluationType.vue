
<template>
  <!-- 评价指标管理 -->
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      label-width="100px"
    >
      <el-form-item label="评价指标类型" prop="name">
        <!-- <el-input
          v-model="queryParams.name"
          placeholder="请输入评价指标类型"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        /> -->
        <el-select
          v-model="queryParams.name"
          clearable
          size="small"
          placeholder="请选择评价指标类型"
        >
          <el-option
            v-for="(item, index) in options"
            :key="index"
            :label="item"
            :value="item"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="" style="float:right">
        <buttonGroup
          :addHasPermi="['pfserverperson:type:add']"
          @handleFilter="handleQuery"
          @handleAdd="addClick()"
        />
      </el-form-item>
    </el-form>

    <el-table 
    border
    v-loading="loading" :data="contractList">
      <!-- <el-table-column
        label="序号"
        type="index"
        width="50"
        align="center"
      ></el-table-column> -->
      <el-table-column label="评价指标类型" align="center" prop="name" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            class="button"
            size="small"
            @click="handleUpdate(scope.row)"
            type="primary"
            plain
            v-hasPermi="['pfserverperson:type:edit']"
            >编辑</el-button
          >
          <el-button
            class="button"
            size="small"
            @click="handleClick(scope.row)"
            type="primary"
            v-hasPermi="['pfserverperson:metrics:list']"
            >评价指标</el-button
          >
          <el-button
            class="button"
            size="small"
            @click="handleDelete(scope.row)"
            type="danger"
            v-hasPermi="['pfserverperson:type:delete']"
            >删除</el-button
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

    <el-dialog :visible.sync="open" :title="title">
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="100px"
      >
        <el-form-item label="评价指标名称" prop="name">
          <el-input
            v-model="formData.name"
            placeholder="请输入评价指标名称"
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button type="primary" :loading="btn_loading" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import buttonGroup from "@/views/components/com/buttonGroup";
import {
  ContractMetricsTypePullDown,
  ContractMetricsTypeList,
  ContractMetricsTypeAdd,
  ContractMetricsTypeInfo,
  ContractMetricsTypeDelete,
  ContractMetricsTypeEditSave,
} from "@/api/partner";
export default {
  data() {
    return {
      btn_loading: false,
      open: false,
      // 标题
      title: "",
      // 遮罩层
      loading: true,
      // 合作方管理-合同表格数据
      contractList: [],
      // 总条数
      total: 0,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
      },
      formData: {
        id: undefined,
        name: undefined,
      },
      //   下拉
      options: [],
      rules: {
        name: [],
      },
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    //   初始化
    init() {
      this.getList();
      this.ContractMetricsTypePullDownList();
    },
    // 下拉
    ContractMetricsTypePullDownList() {
      ContractMetricsTypePullDown().then((res) => {
        console.log(res);
        this.options = res.rows;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 查询合作方管理-合同列表 */
    getList() {
      this.loading = true;
      ContractMetricsTypeList(this.queryParams).then((response) => {
        console.log(response);
        this.contractList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 新增
    addClick() {
      this.open = true;
      this.title = "新增";
          this.reset();
    },
    reset() {
      this.formData = {
        id: undefined,
        name: undefined,
      };
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.open = true;
      this.title = "修改";
      ContractMetricsTypeInfo(row.id).then((res) => {
        this.formData = res.data;
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm(
        '是否确认评价指标类型为"' + row.name + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return ContractMetricsTypeDelete(ids);
        })
        .then(() => {
          this.init();
          this.msgSuccess("删除成功");
        });
    },
    // 指标评价
    handleClick(row) {
      this.$router.push({
        path: "/evaluatingIndicator/" + row.id,
        query: { name: row.name },
      });
    },
    // 弹窗保存
    handelConfirm() {
      console.log(this.formData);
      this.btn_loading = true;
      setTimeout(() => {
        this.btn_loading = false;
      }, 1000);
      if (this.formData.id) {
        ContractMetricsTypeEditSave(this.formData).then((res) => {
          this.open = false;
          this.init();
          this.reset();
        });
      } else {
        ContractMetricsTypeAdd(this.formData).then((res) => {
          this.open = false;
          this.init();
          this.reset();
        });
      }
    },
  },
  components: {
    buttonGroup,
  },
};
</script>

<style lang="scss" scoped>
</style>