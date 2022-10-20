<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="120px"
    >
      <el-form-item label="险种类别" prop="name">
        <el-input
          style="width: 200px"
          v-model="queryParams.name"
          placeholder="请输入险种类别"
          clearable
          size="small"
        />
      </el-form-item>

      <buttonGroup
        @handleFilter="handleQuery"
        @handleAdd="addClick()"
        style="float: right"
        :addHasPermi="['car:insuranceType:add']"
      />
    </el-form>
    <el-table border v-loading="loading" :data="vehicleList">
      <el-table-column
        label="序号"
        type="index"
        width="50"
        align="center"
      ></el-table-column>
      <el-table-column label="险种类别" align="center" prop="name" />
      <el-table-column label="创建时间" align="center" prop="createTime" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            v-hasPermi="['car:insuranceType:edit']"
            type="primary"
            size="small"
            plain
            @click="handleUpdate(scope.row)"
            >编辑</el-button
          >
          <el-button
            v-hasPermi="['car:insuranceType:remove']"
            type="danger"
            size="small"
            @click="handleDelete(scope.row.id)"
            >删除</el-button
          >
          <el-button
            v-hasPermi="['car:insuranceTypeItem:list']"
            type="primary"
            size="small"
            @click="handleClick(scope.row)"
            >险种</el-button
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
    <!-- -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="险种类别">
          <el-input v-model="form.name" placeholder="请输入险种类别" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" :loading="saveLoading" @click="submitForm"
          >提 交</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  insuranceTypeList,
  insuranceTypeAdd,
  insuranceTypeEditSave,
  insuranceDelete,
} from "@/api/system/vehicle";
import buttonGroup from "@/views/components/com/buttonGroup";
export default {
  components: {
    buttonGroup,
  },
  data() {
    return {
      saveLoading: false,
      title: "",
      vehicleList: [],
      open: false,
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
      },
      total: 0,
      form: {
        id: null,
        name: null,
      },
    };
  },
  mounted() {
    this.getList();
  },
  methods: {
    /** 查询 */
    getList() {
      this.loading = true;
      insuranceTypeList(this.queryParams).then((response) => {
        this.vehicleList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 提交
    submitForm() {
      console.log(this.form);
      this.saveLoading = true;
      setTimeout(() => {
        this.saveLoading = false;
      }, 1000);
      if (this.form.id) {
        insuranceTypeEditSave(this.form)
          .then((res) => {
            this.Reset();
            this.getList();
            this.open = false;
          })
          .then((res) => {});
      } else {
        insuranceTypeAdd(this.form)
          .then((res) => {
            this.Reset();
            this.getList();
            this.open = false;
          })
          .then((res) => {});
      }
    },
    Reset() {
      this.form = {
        id: null,
        name: null,
      };
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;

      this.getList();
    },
    handleUpdate(val) {
      this.form = { ...val };
      this.open = true;
      this.title = "修改";
      console.log(val);
    },
    handleDelete(id) {
      console.log(id);
      let that = this;
      that
        .$confirm("是否确认删除?", "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(function () {
          insuranceDelete(id).then((response) => {
            that.getList();
            that.$message({
              message: "删除成功",
              type: "success",
            });
          });
        })
        .catch(function () {});
      // insuranceDelete(id).then((res) => this.getList());
    },
    handleClick(row) {
      console.log(row);
      this.$router.push({
        path: "/coverageManageDetail/" + row.id,
        query: { name: row.name },
      });
    },
    addClick() {
      this.open = true;
      this.title = "新增";
    },
  },
};
</script>

<style lang="sass" scoped>
</style>
