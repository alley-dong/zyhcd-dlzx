<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="120px"
    >
      <el-form-item label="险种名称" prop="name">
        <el-input
          style="width: 200px"
          v-model="queryParams.name"
          placeholder="请输入险种名称"
          clearable
          size="small"
        />
      </el-form-item>

      <!-- <buttonGroup
        threeButton="返回"
        @handleFilter="handleQuery"
        @handleAdd="addClick()"
        @handleImportant="backClick"
        leadingIn
      
      /> -->
      <div style="float: right">
        <el-button type="primary" size="small" @click="handleQuery"
          >查询</el-button
        >
        <el-button
          type="primary"
          v-hasPermi="['car:insuranceTypeItem:add']"
          size="small"
          @click="addClick"
          >新增</el-button
        >
        <el-button type="primary" size="small" @click="backClick"
          >返回</el-button
        >
      </div>
    </el-form>
    <el-table v-loading="loading" :data="vehicleList">
      <el-table-column
        label="序号"
        type="index"
        width="50"
        align="center"
      ></el-table-column>
      <el-table-column label="险种" align="center" prop="name" />
      <el-table-column label="险种类别" align="center" prop="typeName" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="small"
            type="primary"
            plain
            @click="handleUpdate(scope.row)"
          v-hasPermi="['car:insuranceTypeItem:edit']"
            >编辑</el-button
          >
          <el-button
            size="small"
            type="danger"
          v-hasPermi="['car:insuranceTypeItem:remove']"
            @click="handleDelete(scope.row.id)"
            >删除</el-button
          >
          <!-- <el-button @click="handleClick(scope.row.id)">险种</el-button> -->
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
          <!-- <el-input v-model="form.name" placeholder="请输入险种类别" /> -->
          {{ typeName }}
        </el-form-item>
        <el-form-item label="险种">
          <el-input v-model="form.name" placeholder="请输入险种" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" :loading="saveLoading" @click="submitForm">提 交</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  insuranceTypeItemList,
  insuranceTypeItemAdd,
  insuranceTypeItemEditSave,
  insuranceTypeItemDelete,
} from "@/api/system/vehicle";
import buttonGroup from "@/views/components/com/buttonGroup";
export default {
  components: {
    buttonGroup,
  },
  data() {
    return {
      saveLoading: false,
      typeName: null,
      title: "",
      vehicleList: [],
      open: false,
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 查询参数
      queryParams: {
        id: null,
        pageNum: 1,
        pageSize: 10,
        name: null,
      },
      total: 0,
      form: {
        id: null,
        name: null,
        typeId: null,
      },
    };
  },
  mounted() {
      this.queryParams.id = this.$route.params.id;
      this.form.typeId = this.$route.params.id;
      // debugger
    this.getList();
  },
  methods: {
    /** 查询 */
    getList() {
      console.log(this.$route.query.name);
      // this.queryParams.id = this.form.typeId = this.$route.params.id;
      this.typeName = this.$route.query.name;
      this.loading = true;
      insuranceTypeItemList(this.queryParams).then((response) => {
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
        delete this.form.typeName;
        insuranceTypeItemEditSave(this.form).then((res) => {
          this.Reset();
          this.getList();
          this.open = false;
        });
      } else {
        insuranceTypeItemAdd(this.form).then((res) => {
          this.Reset();
          this.getList();
          this.open = false;
        });
      }
    },
    Reset() {
      this.form = {
        name: null,
        id: null,
      };
      this.form.typeId = this.$route.params.id;
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
          insuranceTypeItemDelete(id).then((response) => {
            that.getList();
            that.$message({
              message: "删除成功",
              type: "success",
            });
          });
        })
        .catch(function () {});
      // insuranceTypeItemDelete(id).then((res) => this.getList());
    },
    handleClick(id) {
      console.log(id);
    },
    addClick() {
      this.open = true;
      this.title = "新增";
          this.Reset();
    },
    // 返回
    backClick() {
      this.$router.go(-1);
    },
  },
};
</script>

<style lang="sass" scoped>
</style>
